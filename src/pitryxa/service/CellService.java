package pitryxa.service;

import pitryxa.model.cell.AbsoluteCell;
import pitryxa.model.cell.CellVersion2;
import pitryxa.model.cell.mapper.CellMapper;
import pitryxa.repository.CellRepository;

import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static pitryxa.Parameters.*;

public class CellService {

    private final CellRepository cellRepository = new CellRepository();
    private final CellMapper mapper = new CellMapper();
    private final List<Point> nearCellsDelta;

    public CellService() {
        startInitCells();

        nearCellsDelta = List.of(
                new Point(-1, 1)
                , new Point(0, 1)
                , new Point(1, 1)
                , new Point(-1, 0)
                , new Point(1, 0)
                , new Point(-1, -1)
                , new Point(0, -1)
                , new Point(1, -1)
        );
    }

    private void startInitCells() {
        List.of(
                new CellVersion2(0, 0)
                , new CellVersion2(-1, 0)
                , new CellVersion2(0, 1)
                , new CellVersion2(-1, 2)
                , new CellVersion2(-2, 0)
        ).parallelStream().forEach(cellRepository::addCell);
    }

    public void calculateNextGeneration() {
        Set<CellVersion2> newGeneration = new HashSet<>();
        Set<CellVersion2> cells = cellRepository.getCells();

        cells.parallelStream().forEach(c -> {
            AtomicInteger nearCellsCount = new AtomicInteger();
            nearCellsDelta.parallelStream()
                    .forEach(d -> {
                        CellVersion2 potentialCell = new CellVersion2(c.getX() + d.x, c.getY() + d.y);
                        if (isCellExist(potentialCell, cells)) {
                            nearCellsCount.getAndIncrement();
                            return;
                        }

                        if (isTimeToBirth(potentialCell, cells)) {
                            newGeneration.add(potentialCell);
                        }
                    });
            if (isCellStillAlive(nearCellsCount)) {
                newGeneration.add(c);
            }
        });
        cellRepository.setCells(newGeneration);
    }

    private boolean isCellStillAlive(AtomicInteger nearCellsCount) {
        return nearCellsCount.get() >= MIN_AMOUNT_FOR_LIFE && nearCellsCount.get() <= MAX_AMOUNT_FOR_LIFE;
    }

    private boolean isTimeToBirth(CellVersion2 potentialCell, Set<CellVersion2> cells) {
        long nearCellsCount = nearCellsDelta.parallelStream()
                .filter(d -> {
                    CellVersion2 nearCell = generateNearCell(potentialCell, d);
                    return isCellExist(nearCell, cells);
                })
                .count();
        return nearCellsCount == AMOUNT_NEAR_CELLS_FOR_BIRTH;
    }

    private CellVersion2 generateNearCell(CellVersion2 cell, Point delta) {
        return new CellVersion2(cell.getX() + delta.x, cell.getY() + delta.y);
    }

    private boolean isCellExist(CellVersion2 cell, Set<CellVersion2> cells) {
        return cells.contains(cell);
    }

    public Set<CellVersion2> getCells() {
        return cellRepository.getCells();
    }

    public Set<AbsoluteCell> getAbsoluteCells() {
        return cellRepository.getCells()
                .parallelStream()
                .map(mapper::toAbsoluteCell)
                .collect(Collectors.toSet());
    }

}
