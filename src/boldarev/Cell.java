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
    private List<Cell> nearCells = new ArrayList<>();

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

    public boolean addNearCell(Cell nearCell) {
        return nearCells.add(nearCell);
    }

    public boolean removeNearCell(Cell nearCell) {
        return nearCells.remove(nearCell);
    }

    public List<Cell> getNearCells() {
        return nearCells;
    }

    public void setNearCells(List<Cell> nearCells) {
        this.nearCells = nearCells;
    }

    public boolean isNearCell(Cell otherCell) {
        if (this.equals(otherCell)) {
            return false;
        }
        return (Math.abs(this.cellX - otherCell.cellX) <= 1 && Math.abs(this.cellY - otherCell.cellY) <= 1);
    }

    public int getNearCellsCount() {
        return nearCells.size();
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
        if (cellY != other.cellY)
            return false;
        return true;
    }
}
