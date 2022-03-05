package pitryxa.graphics;

import pitryxa.model.cell.AbsoluteCell;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

import static pitryxa.parameters.Parameters.COLOR_CELL;
import static pitryxa.model.CoordinateSystem.getCenterX;
import static pitryxa.model.CoordinateSystem.getCenterY;

public class MainPanel extends JPanel {

    private transient Set<AbsoluteCell> cells = new HashSet<>();

    public void setCells(Set<AbsoluteCell> cells) {
        this.cells = cells;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.setColor(COLOR_CELL);
        cells.forEach(c -> g2D.fillRect(c.getX(), c.getY(), c.getWidth(), c.getWidth()));
        drawCenterPoint(g2D);
    }

    private void drawCenterPoint(Graphics2D g2D) {
        g2D.setColor(Color.RED);
        g2D.drawOval(getCenterX() - 1, getCenterY() - 1, 2, 2);
    }
}
