package pitryxa.repository;

import pitryxa.model.cell.CellVersion2;

import java.util.HashSet;
import java.util.Set;

public class CellRepository {

    private Set<CellVersion2> cells = new HashSet<>();

    public Set<CellVersion2> getCells() {
        return cells;
    }

    public void setCells(Set<CellVersion2> cells) {
        this.cells = cells;
    }

    public void addCell(CellVersion2 cell) {
        cells.add(cell);
    }

}
