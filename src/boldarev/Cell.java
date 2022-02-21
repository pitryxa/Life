package boldarev;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static boldarev.Parameters.*;

public class Cell extends JPanel {

    private final CoordinateSystem coordinateSystem = CoordinateSystem.getInstance();

    private final int cellX;
    private final int cellY;
    public final int hashCode;

    public Cell(int cellX, int cellY) {
        this.cellX = cellX;
        this.cellY = cellY;
        hashCode = hashCode();
        setBackground(COLOR_CELL);
        scale();
    }

    public Cell(Point cellPoint) {
        this(cellPoint.x, cellPoint.y);
    }

    public void scale() {
        setSize(FULL_SIZE_CELL - 2, FULL_SIZE_CELL - 2);
        setLocation(coordinateSystem.toAbsoluteX(cellX) + 1, coordinateSystem.toAbsoluteY(cellY) + 1);
    }

    public int getCellX() {
        return cellX;
    }

    public int getCellY() {
        return cellY;
    }

    public boolean isNearCell(Cell otherCell) {
        if (this.equals(otherCell)) {
            return false;
        }
        return (Math.abs(this.cellX - otherCell.cellX) <= 1 && Math.abs(this.cellY - otherCell.cellY) <= 1);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + cellX;
        result = prime * result + cellY;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cell other = (Cell) obj;
        if (this.hashCode != other.hashCode) {
            return false;
        }
        if (cellX != other.cellX)
            return false;
        return cellY == other.cellY;
    }
}
