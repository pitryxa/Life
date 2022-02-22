package pitryxa;

import javax.swing.*;
import java.awt.*;

import static pitryxa.Parameters.*;

public class Cell extends JPanel {

    private final CoordinateSystem coordinateSystem = CoordinateSystem.getInstance();

    private final int cellX;
    private final int cellY;
    public Integer hashCode;

    public Cell(int cellX, int cellY) {
        this.cellX = cellX;
        this.cellY = cellY;
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

    public int getHashCode() {
        if (hashCode == null) {
            hashCode = hashCode();
        }
        return hashCode;
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
        if (this.getHashCode() != other.getHashCode()) {
            return false;
        }
        if (cellX != other.cellX)
            return false;
        return cellY == other.cellY;
    }
}
