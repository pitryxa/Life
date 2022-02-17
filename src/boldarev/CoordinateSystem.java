package boldarev;

import java.awt.*;

import static boldarev.Parameters.*;

public class CoordinateSystem {
    private static CoordinateSystem system;

    private int centerX;
    private int centerY;

    private CoordinateSystem() {
        setCenter(WIDTH_FIELD * FULL_SIZE_CELL / 2, HEIGHT_FIELD * FULL_SIZE_CELL / 2);
    }

    public static CoordinateSystem getInstance() {
        if (system == null) {
            system = new CoordinateSystem();
        }
        return system;
    }

    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public void setCenter(int x, int y) {
        setCenterX(x);
        setCenterY(y);
    }

    public Point getCenter() {
        return new Point(centerX, centerY);
    }

    public void moveCenter(int deltaX, int deltaY) {
        centerX -= 0.1 * deltaX;
        centerY -= 0.1 * deltaY;
    }

    public int toCellX(int absoluteX) {
        double doubleX = (absoluteX + (double) FULL_SIZE_CELL / 2 - getCenterX()) / (double) FULL_SIZE_CELL;
        return doubleX < 0 ? (int) doubleX - 1 : (int) doubleX;
    }

    public int toCellY(int absoluteY) {
        double doubleY = (getCenterY() - absoluteY - (double) FULL_SIZE_CELL / 2) / (double) FULL_SIZE_CELL;
        return doubleY < 0 ? (int) doubleY : (int) doubleY + 1;
    }

    public Point toCellPoint(int absoluteX, int absoluteY) {
        return new Point(toCellX(absoluteX), toCellY(absoluteY));
    }

    public int toAbsoluteX(int cellX) {
        return centerX + (cellX * FULL_SIZE_CELL) - (FULL_SIZE_CELL / 2);
    }

    public int toAbsoluteY(int cellY) {
        return centerY - (cellY * FULL_SIZE_CELL) - (FULL_SIZE_CELL / 2);
    }

}
