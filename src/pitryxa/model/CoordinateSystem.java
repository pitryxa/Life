package pitryxa.model;

import static pitryxa.Parameters.*;

public class CoordinateSystem {

    private int centerX;
    private int centerY;

    public CoordinateSystem() {
        this.centerX = WIDTH_FIELD * FULL_SIZE_CELL / 2;
        this.centerY = HEIGHT_FIELD * FULL_SIZE_CELL / 2;
    }

    public void setCenter(int x, int y) {
        this.centerX = x;
        this.centerY = y;
    }


}
