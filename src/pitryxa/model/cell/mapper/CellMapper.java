package pitryxa.model.cell.mapper;

import pitryxa.model.cell.AbsoluteCell;
import pitryxa.model.cell.CellVersion2;

import java.awt.*;
import java.util.Set;
import java.util.stream.Collectors;

import static pitryxa.Parameters.FULL_SIZE_CELL;
import static pitryxa.model.CoordinateSystem.*;

public class CellMapper {

    public static AbsoluteCell toAbsoluteCell(CellVersion2 cell) {
        return new AbsoluteCell(
                toAbsoluteX(cell.getX()),
                toAbsoluteY(cell.getY())
        );
    }

    public static CellVersion2 toCell(Point absolutePoint) {
        return new CellVersion2(
                toCellX(absolutePoint.x),
                toCellY(absolutePoint.y)
        );
    }

    public static Set<AbsoluteCell> toAbsoluteCellList(Set<CellVersion2> cells) {
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
                ? (distanceFromCenter - FULL_SIZE_CELL / 2) / FULL_SIZE_CELL
                : (distanceFromCenter + FULL_SIZE_CELL / 2) / FULL_SIZE_CELL
        ;
    }

    private static int toAbsoluteX(int cellX) {
        return getCenterX() + (cellX * FULL_SIZE_CELL) - (FULL_SIZE_CELL / 2) + 1;
    }

    private static int toAbsoluteY(int cellY) {
        return getCenterY() - (cellY * FULL_SIZE_CELL) - (FULL_SIZE_CELL / 2) + 1;
    }
}
