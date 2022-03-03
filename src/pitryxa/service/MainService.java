package pitryxa.service;

import pitryxa.listener.MouseListener;
import pitryxa.listener.TimerListener;
import pitryxa.graphics.Window;

public class MainService {

    private final Window window = new Window();
    private final CellService cellService = new CellService();

    public MainService() {
        addMouseListener();
        addTimerListener();
        window.draw(cellService.getAbsoluteCells());
    }

    private void addTimerListener() {
        new TimerListener(this);
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

    }
}