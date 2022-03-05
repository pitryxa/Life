package pitryxa.model.cell;

import static pitryxa.parameters.Parameters.getCellFullSize;

public class AbsoluteCell extends AbstractCell {

    private final int width = getCellFullSize() - 1;

    public AbsoluteCell(int x, int y) {
        super(x, y);
    }

    public int getWidth() {
        return width;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
