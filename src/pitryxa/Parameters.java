package pitryxa;

import java.awt.*;

public class Parameters {

    public static int WIDTH_FIELD = 41;
    public static int HEIGHT_FIELD = 41;
    public static int FULL_SIZE_CELL = 16;
    public static final int MIN_SIZE_CELL = 2;

    public static Color COLOR_CELL = Color.DARK_GRAY;
    public static Color BACKGROUND_COLOR_FIELD = Color.LIGHT_GRAY;

    public static final int AMOUNT_NEAR_CELLS_FOR_BIRTH = 3;
    public static final int MIN_AMOUNT_FOR_LIFE = 2;
    public static final int MAX_AMOUNT_FOR_LIFE = 3;

    public static final int TIMER_DELAY_MILLISECONDS = 300;


    private Parameters() { throw new IllegalStateException("Utility class"); }


}
