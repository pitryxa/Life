package pitryxa.service;

import pitryxa.model.cell.AbsoluteCell;
import pitryxa.model.cell.CellVersion2;
import pitryxa.model.cell.mapper.CellMapper;
import pitryxa.repository.CellRepository;

import java.awt.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import static pitryxa.Parameters.*;
import static pitryxa.model.cell.mapper.CellMapper.toAbsoluteCell;
import static pitryxa.model.cell.mapper.CellMapper.toAbsoluteCellList;

public class CellService {

    private final CellRepository cellRepository = new CellRepository();
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
        Set<CellVersion2> cells = new HashSet<>(Set.of(
                new CellVersion2(0, 0)
                , new CellVersion2(-1, 0)
                , new CellVersion2(0, 1)
                , new CellVersion2(-1, 2)
                , new CellVersion2(-2, 0)
        ));
        saveCells(cells);
    }

    private void saveCells(Set<CellVersion2> cells) {
        cellRepository.setCells(cells);
        cellRepository.setAbsoluteCells(toAbsoluteCellList(cells));
    }

    public void calculateNextGeneration() {
        Set<CellVersion2> newGeneration = new HashSet<>();
        Set<CellVersion2> cells = cellRepository.getCells();

        cells.parallelStream().forEach(c -> {
            AtomicInteger nearCellsCount = new AtomicInteger();
            nearCellsDelta.parallelStream().forEach(d -> {
                        CellVersion2 potentialCell = new CellVersion2(c.getX() + d.x, c.getY() + d.y);
                        if (isCellExist(potentialCell)) {
                            nearCellsCount.getAndIncrement();
                            return;
                        }

                        if (isTimeToBirth(potentialCell)) {
                            newGeneration.add(potentialCell);
                        }
                    });
            if (isCellStillAlive(nearCellsCount)) {
                newGeneration.add(c);
            }
        });
        saveCells(newGeneration);
    }

    private boolean isCellStillAlive(AtomicInteger nearCellsCount) {
        return nearCellsCount.get() >= MIN_AMOUNT_FOR_LIFE && nearCellsCount.get() <= MAX_AMOUNT_FOR_LIFE;
    }

    private boolean isTimeToBirth(CellVersion2 potentialCell) {
        long nearCellsCount = nearCellsDelta.parallelStream()
                .filter(d -> {
                    CellVersion2 nearCell = generateNearCell(potentialCell, d);
                    return isCellExist(nearCell);
                })
                .count();
        return nearCellsCount == AMOUNT_NEAR_CELLS_FOR_BIRTH;
    }

    private CellVersion2 generateNearCell(CellVersion2 cell, Point delta) {
        return new CellVersion2(cell.getX() + delta.x, cell.getY() + delta.y);
    }

    public boolean isCellExist(CellVersion2 cell) {
        return cellRepository.getCells().contains(cell);
    }

    public Set<CellVersion2> getCells() {
        return cellRepository.getCells();
    }

    public Set<AbsoluteCell> getAbsoluteCells() {
        return cellRepository.getAbsoluteCells();
    }

    public void addCell(CellVersion2 cell) {
        cellRepository.addCell(cell);
        cellRepository.addAbsoluteCell(toAbsoluteCell(cell));
    }

    public void removeCell(CellVersion2 cell) {
        cellRepository.removeCell(cell);
        cellRepository.removeAbsoluteCell(toAbsoluteCell(cell));
    }

    private void changeCellSize(int scale) {
        FULL_SIZE_CELL = Math.max(MIN_SIZE_CELL + 2, FULL_SIZE_CELL - 2 * scale);
    }

    public void scaleCells(int scale) {
        changeCellSize(scale);
        recalculateAbsoluteCells();
    }

    private void recalculateAbsoluteCells() {
        cellRepository.setAbsoluteCells(toAbsoluteCellList(cellRepository.getCells()));
    }
}
