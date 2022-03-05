package pitryxa.listener;

import pitryxa.service.MainService;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class MouseListener extends MouseAdapter {

    private static final int LEFT_BUTTON = 1;
    private static final int RIGHT_BUTTON = 3;

    private final MainService mainService;
    private Point mousePressPoint;

    public MouseListener(MainService mainService) {
        this.mainService = mainService;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch (e.getButton()) {
            case LEFT_BUTTON:
                mainService.pressLeftButton(e.getPoint());
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
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int deltaX = mousePressPoint.x - e.getX();
        int deltaY = mousePressPoint.y - e.getY();
        mainService.move(deltaX, deltaY);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mousePressPoint = e.getPoint();
    }
}
