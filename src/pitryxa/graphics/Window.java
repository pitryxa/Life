package pitryxa.graphics;

import pitryxa.model.cell.AbsoluteCell;
import pitryxa.model.cell.CellVersion2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.util.Set;

import static pitryxa.Parameters.*;

public class Window extends JFrame {

    private static final String TITLE = "Life";
    private final Container container = this.getContentPane();

    private final MainPanel mainPanel = new MainPanel();

    public Window() {
        startInit();
        setVisible(true);
    }

    public void redraw(JPanel panel) {
        container.removeAll();
        add(panel);
        revalidate();
    }

    public void addMouseListener(MouseAdapter mouseListener) {
        container.addMouseListener(mouseListener);
        container.addMouseWheelListener(mouseListener);
        container.addMouseMotionListener(mouseListener);
    }

    private void startInit() {
        initMainPanel();
        initFrame();
        add(mainPanel);
        pack();
    }

    private void initFrame() {
        setTitle(TITLE);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(mainPanel.getPreferredSize());
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void initMainPanel() {
        Dimension dimension = new Dimension(WIDTH_FIELD * FULL_SIZE_CELL, HEIGHT_FIELD * FULL_SIZE_CELL);
        mainPanel.setPreferredSize(dimension);
        mainPanel.setBackground(BACKGROUND_COLOR_FIELD);
    }

    public void draw(Set<AbsoluteCell> cells) {
        mainPanel.setCells(cells);
        mainPanel.repaint();
    }
}
