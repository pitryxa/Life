package pitryxa;

import java.awt.*;

import static pitryxa.Parameters.*;

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

    public void setCenter(int x, int y) {
        this.centerX = x;
        this.centerY = y;
    }

    public void moveCenter(int deltaX, int deltaY) {
        centerX -= 0.1 * deltaX;
        centerY -= 0.1 * deltaY;
    }

    private int toCellX(int absoluteX) {
        int intX = (absoluteX + FULL_SIZE_CELL / 2 - centerX) / FULL_SIZE_CELL;
        return intX < 0 ? intX - 1 : intX;
    }

    private int toCellY(int absoluteY) {
        int intY = (centerY - absoluteY - FULL_SIZE_CELL / 2) / FULL_SIZE_CELL;
        return intY < 0 ? intY : intY + 1;
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
