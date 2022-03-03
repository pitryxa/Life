package pitryxa.model.cell.mapper;

import pitryxa.model.cell.AbsoluteCell;
import pitryxa.model.cell.CellVersion2;

import static pitryxa.Parameters.FULL_SIZE_CELL;
import static pitryxa.model.CoordinateSystem.*;

public class CellMapper {

    public AbsoluteCell toAbsoluteCell(CellVersion2 cell) {
        return new AbsoluteCell(
                toAbsoluteX(cell.getX()),
                toAbsoluteY(cell.getY())
        );
    }

    public int toCellX(int absoluteX) {
        int intX = (absoluteX + FULL_SIZE_CELL / 2 - getCenterX()) / FULL_SIZE_CELL;
        return intX < 0 ? intX - 1 : intX;
    }

    public int toCellY(int absoluteY) {
        int intY = (getCenterY() - absoluteY - FULL_SIZE_CELL / 2) / FULL_SIZE_CELL;
        return intY < 0 ? intY : intY + 1;
    }

    public int toAbsoluteX(int cellX) {
        return getCenterX() + (cellX * FULL_SIZE_CELL) - (FULL_SIZE_CELL / 2) + 1;
    }

    public int toAbsoluteY(int cellY) {
        return getCenterY() - (cellY * FULL_SIZE_CELL) - (FULL_SIZE_CELL / 2) + 1;
    }
}
