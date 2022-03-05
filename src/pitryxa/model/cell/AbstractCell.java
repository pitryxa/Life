package pitryxa.model.cell;

public abstract class AbstractCell {

    protected final int x;
    protected final int y;
    protected Integer hashCode;

    protected AbstractCell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHashCode() {
        if (hashCode == null) {
            hashCode = hashCode();
        }
        return hashCode;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AbstractCell other = (AbstractCell) obj;
        if (this.getHashCode() != other.getHashCode()) {
            return false;
        }
        if (this.x != other.x)
            return false;
        return this.y == other.y;
    }
}
