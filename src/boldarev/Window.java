package boldarev;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import static boldarev.Parameters.*;

public class Window extends JFrame implements ActionListener {

    private static Window window;

    MouseAdapter mouseListener = new MouseListener(this);
    public final Timer timer = new Timer(200,this);
    public final Cells cells = new Cells();

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
        cells.processCells();
        redraw();
    }

    public void redraw() {
        getContentPane().removeAll();
        cells.getCells().parallelStream().forEach(this::add);
        this.setTitle(String.valueOf(cells.getCells().size()));
        getContentPane().repaint();
    }

    private void initFrame() {
        Dimension dim = new Dimension(WIDTH_FIELD * FULL_SIZE_CELL, HEIGHT_FIELD * FULL_SIZE_CELL);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(dim);
        getContentPane().setPreferredSize(dim);
        setLocationRelativeTo(null);
        setTitle("Life");
        setResizable(false);
        setLayout(null);
        getContentPane().setBackground(BACKGROUND_COLOR_FIELD);
        pack();
    }
}
