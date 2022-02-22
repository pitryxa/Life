package pitryxa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import static pitryxa.Parameters.*;

public class Window extends JFrame implements ActionListener {

    private static Window window;

    MouseAdapter mouseListener = new MouseListener(this);
    public final Timer timer = new Timer(200,this);
    public final CellService cellService = new CellService();

    private Window() {
        initFrame();
        redraw();
        setVisible(true);
        getContentPane().addMouseListener(mouseListener);
        getContentPane().addMouseWheelListener(mouseListener);
        getContentPane().addMouseMotionListener(mouseListener);
    }

    public static Window getInstance() {
        if (window == null) {
            window = new Window();
        }
        return window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        cellService.processCells();
        redraw();
    }

    public void redraw() {
        getContentPane().removeAll();
        cellService.getCells().parallelStream().forEach(this::add);
        this.setTitle(String.valueOf(cellService.getCells().size()));
        getContentPane().repaint();
    }

    private void initFrame() {
        Dimension dimension = new Dimension(WIDTH_FIELD * FULL_SIZE_CELL, HEIGHT_FIELD * FULL_SIZE_CELL);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(dimension);
        getContentPane().setPreferredSize(dimension);
        setLocationRelativeTo(null);
        setTitle("Life");
        setResizable(false);
        setLayout(null);
        getContentPane().setBackground(BACKGROUND_COLOR_FIELD);
        pack();
    }
}
