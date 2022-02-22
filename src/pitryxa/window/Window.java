package pitryxa.window;

import pitryxa.listener.MouseListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import static pitryxa.Parameters.*;

public class Window extends JFrame implements ActionListener {

    public Window() {
        initFrame();
        redraw();
        setVisible(true);
        addMouseListeners();
    }

    private void redraw() {

    }

    private void addMouseListeners() {
        MouseAdapter mouseListener = new MouseListener();
        getContentPane().addMouseListener(mouseListener);
        getContentPane().addMouseWheelListener(mouseListener);
        getContentPane().addMouseMotionListener(mouseListener);
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

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
