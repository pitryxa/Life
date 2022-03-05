package pitryxa.model;

import static pitryxa.parameters.Parameters.*;

public class CoordinateSystem {

    private static int centerX = WIDTH_FIELD * getCellFullSize() / 2;
    private static int centerY = HEIGHT_FIELD * getCellFullSize() / 2;

    public static int getCenterX() {
        return centerX;
    }

    public static int getCenterY() {
        return centerY;
    }

    public static void moveCenter(int deltaX, int deltaY) {
        centerX -= 0.05 * deltaX;
        centerY -= 0.05 * deltaY;
    }
}
