package pitryxa.model;

import static pitryxa.Parameters.*;

public class CoordinateSystem {

    private static int centerX = WIDTH_FIELD * FULL_SIZE_CELL / 2;
    private static int centerY = HEIGHT_FIELD * FULL_SIZE_CELL / 2;

    public static int getCenterX() {
        return centerX;
    }

    public static int getCenterY() {
        return centerY;
    }
}
