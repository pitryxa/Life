package pitryxa.model.cell;

public class Cell extends AbstractCell {

    public Cell(int x, int y) {
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
        Cell other = (Cell) obj;
        if (this.getHashCode() != other.getHashCode()) {
            return false;
        }
        if (this.x != other.x)
            return false;
        return this.y == other.y;
    }
}
