package pitryxa.service;

import pitryxa.listener.MouseListener;
import pitryxa.listener.TimerListener;
import pitryxa.graphics.Window;

import javax.swing.*;

import static pitryxa.Parameters.TIMER_DELAY_MILLISECONDS;

public class MainService {

    private final Window window = new Window();
    private final CellService cellService = new CellService();
    private Timer timer;

    public MainService() {
        addMouseListener();
        addTimerListener();
        window.draw(cellService.getAbsoluteCells());
    }

    private void addTimerListener() {
        TimerListener timerListener = new TimerListener(this);
        timer = new Timer(TIMER_DELAY_MILLISECONDS, timerListener);
        timer.start();
    }

    private void addMouseListener() {
        MouseListener mouseListener = new MouseListener(this);
        window.addMouseListener(mouseListener);
    }

    public void pressLeftButton() {

    }

    public void pressRightButton() {

    }

    public void timerAction() {
        cellService.calculateNextGeneration();
        window.draw(cellService.getAbsoluteCells());
    }
}