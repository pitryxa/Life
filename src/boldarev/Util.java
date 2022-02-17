package boldarev;

import java.awt.*;

public class Util {

    private Util() { throw new IllegalStateException("Utility class"); }

    public static Color getColor(int x, int y) throws AWTException {
        return new Robot().getPixelColor(x, y);
    }
}
