package pitryxa.service;

import pitryxa.listener.MouseListener;
import pitryxa.listener.TimerListener;
import pitryxa.window.Window;

import javax.swing.*;

import static pitryxa.Parameters.TIMER_DELAY_MILLISECONDS;

public class AppService {

    private Window window;
    private MouseListener mouseListener;
    private TimerListener timerListener;
    private Timer timer;
    private CellService cellService;

    public AppService() {
        this.window = new Window(this);
        this.mouseListener = new MouseListener(this);
        this.timerListener = new TimerListener(this);
        this.cellService = new CellService(this);
        this.timer = new Timer(TIMER_DELAY_MILLISECONDS, timerListener);
    }

    public Window getWindow() {
        return window;
    }

    public MouseListener getMouseListener() {
        return mouseListener;
    }

    public TimerListener getTimerListener() {
        return timerListener;
    }

    public Timer getTimer() {
        return timer;
    }

    public CellService getCellService() {
        return cellService;
    }
}
