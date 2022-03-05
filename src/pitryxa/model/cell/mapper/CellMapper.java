package pitryxa.model.cell.mapper;

import pitryxa.model.cell.AbsoluteCell;
import pitryxa.model.cell.Cell;

import java.awt.*;
import java.util.Set;
import java.util.stream.Collectors;

import static pitryxa.parameters.Parameters.getCellFullSize;
import static pitryxa.model.CoordinateSystem.*;

public class CellMapper {

    public static AbsoluteCell toAbsoluteCell(Cell cell) {
        return new AbsoluteCell(
                toAbsoluteX(cell.getX()),
                toAbsoluteY(cell.getY())
        );
    }

    public static Cell toCell(Point absolutePoint) {
        return new Cell(
                toCellX(absolutePoint.x),
                toCellY(absolutePoint.y)
        );
    }

    public static Set<AbsoluteCell> toAbsoluteCellList(Set<Cell> cells) {
        return cells.parallelStream().map(CellMapper::toAbsoluteCell).collect(Collectors.toSet());
    }

    private static int toCellX(int absoluteX) {
        int distanceFromCenter = absoluteX - getCenterX();
        return toRelativeCoordinate(distanceFromCenter);
    }

    private static int toCellY(int absoluteY) {
        int distanceFromCenter = getCenterY() - absoluteY;
        return toRelativeCoordinate(distanceFromCenter);
    }

    private static int toRelativeCoordinate(int distanceFromCenter) {
        return distanceFromCenter < 0
                ? (distanceFromCenter - getCellFullSize() / 2) / getCellFullSize()
                : (distanceFromCenter + getCellFullSize() / 2) / getCellFullSize()
        ;
    }

    private static int toAbsoluteX(int cellX) {
        return getCenterX() + (cellX * getCellFullSize()) - (getCellFullSize() / 2) + 1;
    }

    private static int toAbsoluteY(int cellY) {
        return getCenterY() - (cellY * getCellFullSize()) - (getCellFullSize() / 2) + 1;
    }
}
