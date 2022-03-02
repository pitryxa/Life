package pitryxa.listener;

import pitryxa.service.MainService;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
}
