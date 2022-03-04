package pitryxa.repository;

import pitryxa.model.cell.AbsoluteCell;
import pitryxa.model.cell.CellVersion2;

import java.util.HashSet;
import java.util.Set;

public class CellRepository {

    private Set<CellVersion2> cells = new HashSet<>();
    private Set<AbsoluteCell> absoluteCells = new HashSet<>();

    public Set<CellVersion2> getCells() {
        return cells;
    }

    public void setCells(Set<CellVersion2> cells) {
        this.cells = cells;
    }

    public void setAbsoluteCells(Set<AbsoluteCell> absoluteCells) {
        this.absoluteCells = absoluteCells;
    }

    public Set<AbsoluteCell> getAbsoluteCells() {
        return absoluteCells;
    }

    public synchronized void addCell(CellVersion2 cell) {
        cells.add(cell);
    }

    public synchronized void addAbsoluteCell(AbsoluteCell cell) {
        absoluteCells.add(cell);
    }

    public void removeCell(CellVersion2 cell) {
        cells.remove(cell);
    }

    public void removeAbsoluteCell(AbsoluteCell cell) {
        absoluteCells.remove(cell);
    }
}
