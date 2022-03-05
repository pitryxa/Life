package pitryxa.service;

import pitryxa.model.cell.AbsoluteCell;
import pitryxa.model.cell.Cell;
import pitryxa.repository.CellRepository;

import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import static pitryxa.parameters.Parameters.*;
import static pitryxa.model.CoordinateSystem.moveCenter;
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
        Set<Cell> cells = new HashSet<>(Set.of(
                new Cell(0, 0)
                , new Cell(-1, 0)
                , new Cell(0, 1)
                , new Cell(-1, 2)
                , new Cell(-2, 0)
        ));
        saveCells(cells);
    }

    private void saveCells(Set<Cell> cells) {
        cellRepository.setCells(cells);
        cellRepository.setAbsoluteCells(toAbsoluteCellList(cells));
    }

    public void calculateNextGeneration() {
        Set<Cell> newGeneration = new HashSet<>();
        Set<Cell> cells = cellRepository.getCells();

        cells.forEach(c -> {
            AtomicInteger nearCellsCount = new AtomicInteger();
            nearCellsDelta.forEach(d -> {
                        Cell potentialCell = new Cell(c.getX() + d.x, c.getY() + d.y);
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

    private boolean isTimeToBirth(Cell potentialCell) {
        long nearCellsCount = nearCellsDelta.stream()
                .filter(d -> {
                    Cell nearCell = generateNearCell(potentialCell, d);
                    return isCellExist(nearCell);
                })
                .count();
        return nearCellsCount == AMOUNT_NEAR_CELLS_FOR_BIRTH;
    }

    private Cell generateNearCell(Cell cell, Point delta) {
        return new Cell(cell.getX() + delta.x, cell.getY() + delta.y);
    }

    public boolean isCellExist(Cell cell) {
        return cellRepository.getCells().contains(cell);
    }

    public Set<Cell> getCells() {
        return cellRepository.getCells();
    }

    public Set<AbsoluteCell> getAbsoluteCells() {
        return cellRepository.getAbsoluteCells();
    }

    public void addCell(Cell cell) {
        cellRepository.addCell(cell);
        cellRepository.addAbsoluteCell(toAbsoluteCell(cell));
    }

    public void removeCell(Cell cell) {
        cellRepository.removeCell(cell);
        cellRepository.removeAbsoluteCell(toAbsoluteCell(cell));
    }

    private void changeCellSize(int scale) {
        setCellFullSize(
                Math.max(MIN_SIZE_CELL + 2, getCellFullSize() - 2 * scale));
    }

    public void scaleCells(int scale) {
        changeCellSize(scale);
        recalculateAbsoluteCells();
    }

    private void recalculateAbsoluteCells() {
        cellRepository.setAbsoluteCells(toAbsoluteCellList(cellRepository.getCells()));
    }

    public void move(int deltaX, int deltaY) {
        moveCenter(deltaX, deltaY);
        recalculateAbsoluteCells();
    }
}
