package pitryxa.parameters;

import java.awt.*;

public class Parameters {

    public static final int WIDTH_FIELD = 41;
    public static final int HEIGHT_FIELD = 41;
    public static final int MIN_SIZE_CELL = 2;
    public static final Color COLOR_CELL = Color.ORANGE.darker();
    public static final Color BACKGROUND_COLOR_FIELD = Color.DARK_GRAY;
    public static final int AMOUNT_NEAR_CELLS_FOR_BIRTH = 3;
    public static final int MIN_AMOUNT_FOR_LIFE = 2;
    public static final int MAX_AMOUNT_FOR_LIFE = 3;
    public static final int TIMER_DELAY_MILLISECONDS = 500;

    private static int cellFullSize = 16;

    private Parameters() { throw new IllegalStateException("Utility class"); }

    public static int getCellFullSize() {
        return cellFullSize;
    }

    public static void setCellFullSize(int cellFullSize) {
        Parameters.cellFullSize = cellFullSize;
    }


}
