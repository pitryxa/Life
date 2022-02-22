package pitryxa.listener;

import pitryxa.service.AppService;
import pitryxa.window.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseListener extends MouseAdapter {

    private static final int LEFT_BUTTON = 1;
    private static final int RIGHT_BUTTON = 3;

    private Point mousePressPoint;
    private Timer timer;
    private Window window;
    private AppService appService;

    public MouseListener(AppService appService) {
        this.appService = appService;
        this.timer = appService.getTimer();
        this.window = appService.getWindow();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        window.redraw2();
        window.paint(window.getGraphics());
    }
}
