package boldarev;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static boldarev.Parameters.*;

public class Cells implements Serializable {

    private static final CoordinateSystem coordinateSystem = CoordinateSystem.getInstance();

    private List<Cell> cells = new ArrayList<>(1000);

    public Cells() {
        cells.addAll(initCells(List.of(
                new Cell(0, 0)
                , new Cell(-1, 0)
                , new Cell(0, 1)
                , new Cell(-1, 2)
                , new Cell(-2, 0)
        )));
    }

    public List<Cell> getCells() {
        return cells;
    }

    public void setCells(List<Cell> cells) {
        this.cells = cells;
    }

    public List<Cell> processCells() {
        Container contentPane = Window.getInstance().getContentPane();
        List<Cell> newGenerationCells = new ArrayList<>(1000);
        cells.parallelStream().forEach(cell -> {
            int nearCount = getNearCount(cell);
            if (nearCount >= MIN_AMOUNT_FOR_LIFE && nearCount <= MAX_AMOUNT_FOR_LIFE) {
                newGenerationCells.add(cell);
            }
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {

                    if (i == 0 && j == 0
                            || contentPane.findComponentAt(cell.getX() + i * FULL_SIZE_CELL, cell.getY() + j * FULL_SIZE_CELL) != contentPane)
                        continue;

                    Cell potentialCell = new Cell(cell.getCellX() + i, cell.getCellY() + j);

                    if (!newGenerationCells.contains(potentialCell) && getNearCount(potentialCell) == AMOUNT_NEAR_CELLS_FOR_BIRTH) {
                        newGenerationCells.add(potentialCell);
                    }
                }
            }
        });
        cells = newGenerationCells;
        return cells;
    }

    private int getNearCount(Cell cell) {
        Container contentPane = Window.getInstance().getContentPane();
        int nearCount = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;
                if (contentPane.findComponentAt(cell.getX() + i * FULL_SIZE_CELL, cell.getY() + j * FULL_SIZE_CELL) != contentPane) {
                    nearCount++;
                }
            }
        }
        return nearCount;
    }

    private List<Cell> initCells(List<Cell> clearCells) {
        clearCells.parallelStream().forEach(cell -> cell.setNearCells(
                clearCells.parallelStream()
                        .filter(cell::isNearCell)
                        .collect(Collectors.toList()))
        );
        return clearCells;
    }

    public void cellAction(int x, int y, Color color) {
        if (COLOR_CELL.equals(color)) {
            removeCell(x, y);
        } else if (BACKGROUND_COLOR_FIELD.equals(color)) {
            addCell(x, y);
        }
    }

    private void addCell(int x, int y) {
        Point cellPoint = coordinateSystem.toCellPoint(x, y);
        cells.add(new Cell(cellPoint));
        initCells(cells);
    }

    private void removeCell(int x, int y) {
        Point cellPoint = coordinateSystem.toCellPoint(x, y);
        cells.remove(new Cell(cellPoint));
        initCells(cells);
    }

    public void scale() {
        cells.parallelStream().forEach(Cell::scale);
    }
}
