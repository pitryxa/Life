package pitryxa.repository;

import pitryxa.model.CellVersion2;

import java.util.HashSet;
import java.util.Set;

public class CellRepository {

    private final Set<CellVersion2> cells = new HashSet<>();

    public Set<CellVersion2> getCells() {
        return cells;
    }

    public void addCell(CellVersion2 cell) {
        cells.add(cell);
    }

    public void removeCell(CellVersion2 cell) {
        cells.remove(cell);
    }
}
