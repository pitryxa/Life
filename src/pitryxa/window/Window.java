package pitryxa.window;

import pitryxa.service.AppService;
import pitryxa.service.CellService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;

import static pitryxa.Parameters.*;

public class Window extends JFrame {

    private AppService appService;
    private final Container contentPane = this.getContentPane();
//    private final Graphics2D graphics2D;

    public Window(AppService appService) {
        this.appService = appService;
//        graphics2D = (Graphics2D) contentPane.getGraphics();
        startInitFrame();
        redraw();
        setVisible(true);
    }

    public void redraw() {
        Graphics2D g2D = (Graphics2D) contentPane.getGraphics();
        g2D.setColor(COLOR_CELL);
        g2D.fillRect(10, 10, 50, 50);
        paint(g2D);
//        this.getContentPane().repaint();
    }

    public void redraw2() {
        Graphics2D g2D = (Graphics2D) contentPane.getGraphics();
        g2D.setColor(COLOR_CELL);
        g2D.fillRect(60, 60, 50, 50);
//        this.getContentPane().repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
//        redraw();
    }

    public void addMouseListener(MouseAdapter mouseListener) {
        contentPane.addMouseListener(mouseListener);
        contentPane.addMouseWheelListener(mouseListener);
        contentPane.addMouseMotionListener(mouseListener);
    }

    private void startInitFrame() {
        Dimension dimension = new Dimension(WIDTH_FIELD * FULL_SIZE_CELL, HEIGHT_FIELD * FULL_SIZE_CELL);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(dimension);
        contentPane.setPreferredSize(dimension);
        setLocationRelativeTo(null);
        setTitle("Life");
        setResizable(false);
        setLayout(null);
        contentPane.setBackground(BACKGROUND_COLOR_FIELD);
        pack();
    }

}
