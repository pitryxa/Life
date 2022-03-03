package pitryxa.model.cell;

public class CellVersion2 extends AbstractCell {

    public CellVersion2(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CellVersion2 other = (CellVersion2) obj;
        if (this.getHashCode() != other.getHashCode()) {
            return false;
        }
        if (this.x != other.x)
            return false;
        return this.y == other.y;
    }
}
