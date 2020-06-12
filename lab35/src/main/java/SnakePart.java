public class SnakePart {
    private int x;
    private int y;

    public SnakePart(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void increaseXByValue(int additionalValue) {
        this.x += additionalValue;
    }

    public void increaseYByValue(int additionalValue) {
        this.y += additionalValue;
    }
}
