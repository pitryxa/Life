package pitryxa.service;

import pitryxa.listener.MouseListener;
import pitryxa.listener.TimerListener;
import pitryxa.graphics.Window;
import pitryxa.model.cell.Cell;

import javax.swing.*;

import java.awt.*;

import static pitryxa.parameters.Parameters.*;
import static pitryxa.model.cell.mapper.CellMapper.toCell;

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
    }

    private void addMouseListener() {
        MouseListener mouseListener = new MouseListener(this);
        window.addMouseListener(mouseListener);
    }

    public void pressLeftButton(Point absolutePoint) {
        Cell cell = toCell(absolutePoint);
        if (cellService.isCellExist(cell)) {
            cellService.removeCell(cell);
        } else {
            cellService.addCell(cell);
        }
        redraw();
    }

    public void pressRightButton() {
        if (timer.isRunning()) {
            timer.stop();
            return;
        }
        timer.start();
    }

    public void timerAction() {
        cellService.calculateNextGeneration();
        redraw();
    }

    public void scale(int scale) {
        cellService.scaleCells(scale);
        redraw();
    }

    private void redraw() {
        window.draw(cellService.getAbsoluteCells());
    }

    public void move(int deltaX, int deltaY) {
        cellService.move(deltaX, deltaY);
        redraw();
    }
}