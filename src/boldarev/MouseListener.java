package boldarev;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import static boldarev.Parameters.FULL_SIZE_CELL;
import static boldarev.Parameters.MIN_SIZE_CELL;
import static boldarev.Util.getColor;

public class MouseListener extends MouseAdapter {

    private static final CoordinateSystem coordinateSystem = CoordinateSystem.getInstance();

    private static final int LEFT_BUTTON = 1;
    private static final int RIGHT_BUTTON = 3;

    private final Window window;
    private Point mousePressPoint;

    public MouseListener(Window window) {
        this.window = window;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch (e.getButton()) {
            case LEFT_BUTTON:
                try {
                    Color color = getColor(e.getXOnScreen(), e.getYOnScreen());
                    window.cellService.cellAction(e.getX(), e.getY(), color);
                    window.redraw();
                } catch (AWTException ex) {
                    ex.printStackTrace();
                }
                break;
            case RIGHT_BUTTON:
                if (window.timer.isRunning()) {
                    window.timer.stop();
                } else {
                    window.timer.start();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int scale = e.getWheelRotation();
        FULL_SIZE_CELL = Math.max(MIN_SIZE_CELL + 2, FULL_SIZE_CELL - 2 * scale);
        window.cellService.scale();
        window.redraw();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int deltaX = mousePressPoint.x - e.getX();
        int deltaY = mousePressPoint.y - e.getY();
        coordinateSystem.moveCenter(deltaX, deltaY);
        window.cellService.scale();
        window.redraw();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mousePressPoint = e.getPoint();
    }
}
