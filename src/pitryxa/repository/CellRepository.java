package pitryxa.repository;

import pitryxa.model.cell.AbsoluteCell;
import pitryxa.model.cell.Cell;

import java.util.HashSet;
import java.util.Set;

public class CellRepository {

    private Set<Cell> cells = new HashSet<>();
    private Set<AbsoluteCell> absoluteCells = new HashSet<>();

    public Set<Cell> getCells() {
        return cells;
    }

    public void setCells(Set<Cell> cells) {
        this.cells = cells;
    }

    public void setAbsoluteCells(Set<AbsoluteCell> absoluteCells) {
        this.absoluteCells = absoluteCells;
    }

    public Set<AbsoluteCell> getAbsoluteCells() {
        return absoluteCells;
    }

    public synchronized void addCell(Cell cell) {
        cells.add(cell);
    }

    public synchronized void addAbsoluteCell(AbsoluteCell cell) {
        absoluteCells.add(cell);
    }

    public void removeCell(Cell cell) {
        cells.remove(cell);
    }

    public void removeAbsoluteCell(AbsoluteCell cell) {
        absoluteCells.remove(cell);
    }
}
