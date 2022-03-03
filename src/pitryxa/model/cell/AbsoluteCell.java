package pitryxa.model.cell;

import static pitryxa.Parameters.FULL_SIZE_CELL;

public class AbsoluteCell extends AbstractCell {

    private final int width = FULL_SIZE_CELL - 1;

    public AbsoluteCell(int x, int y) {
        super(x, y);
    }

    public int getWidth() {
        return width;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AbsoluteCell other = (AbsoluteCell) obj;
        if (this.getHashCode() != other.getHashCode()) {
            return false;
        }
        if (this.x != other.x)
            return false;
        return this.y == other.y;
    }
}
