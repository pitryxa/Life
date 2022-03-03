package pitryxa.listener;

import pitryxa.service.MainService;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import static pitryxa.Parameters.FULL_SIZE_CELL;
import static pitryxa.Parameters.MIN_SIZE_CELL;

public class MouseListener extends MouseAdapter {

    private static final int LEFT_BUTTON = 1;
    private static final int RIGHT_BUTTON = 3;

    private final MainService mainService;

    public MouseListener(MainService mainService) {
        this.mainService = mainService;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch (e.getButton()) {
            case LEFT_BUTTON:
                mainService.pressLeftButton();
                break;
            case RIGHT_BUTTON:
                mainService.pressRightButton();
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int scale = e.getWheelRotation();
        mainService.scale(scale);



//        FULL_SIZE_CELL = Math.max(MIN_SIZE_CELL + 2, FULL_SIZE_CELL - 2 * scale);
//        window.cellService.scale();
//        window.redraw();
    }
}
