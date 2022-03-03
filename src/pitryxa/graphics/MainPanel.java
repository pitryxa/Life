package pitryxa.graphics;

import pitryxa.model.cell.AbsoluteCell;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

import static pitryxa.Parameters.COLOR_CELL;

public class MainPanel extends JPanel {

    private Set<AbsoluteCell> cells = new HashSet<>();

    public void setCells(Set<AbsoluteCell> cells) {
        this.cells = cells;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.setColor(COLOR_CELL);
        cells.forEach(c -> g2D.fillRect(c.getX(), c.getY(), c.getWidth(), c.getWidth()));
    }
}
