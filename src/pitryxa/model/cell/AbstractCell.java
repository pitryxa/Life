package pitryxa.model.cell;

public abstract class AbstractCell {

    protected final int x;
    protected final int y;
    protected Integer hashCode;

    public AbstractCell(int x, int y) {
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
}
