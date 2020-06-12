import java.util.ArrayList;
import java.util.List;

public class Snake {
    private int number;
    private Direction direction;

    private List<SnakePart> parts;

    public Snake(int number) {
        this.number = number;
        this.direction = Direction.up;
        parts = new ArrayList<>();
    }

    public void addPart(SnakePart newPart) {
        parts.add(newPart);
    }

    public int getSnakeLength() {
        return parts.size();
    }

    public List<SnakePart> getParts() {
        return parts;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int newNumber) {
        this.number = newNumber;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction newDirection) {
        this.direction = newDirection;
    }

    public boolean checkCoordinatesMatches(int x, int y) {
        for(SnakePart part: parts) {
            if(part.getX() == x && part.getY() == y) {
                return true;
            }
        }
        return false;
    }

    public boolean checkHeadCoordinatesMatches(int x, int y) {
        return parts.get(0).getX() == x && parts.get(0).getY() == y;
    }

    public void checkSelfEating() {
        for(int i = 1; i < parts.size(); i++) {
            if(parts.get(0).getX() == parts.get(i).getX() && parts.get(0).getY() == parts.get(i).getY()) {
                for(int j = parts.size() - 1; j > i; j--) {
                    parts.remove(j);
                }
                break;
            }
        }
    }

    public void move(int height, int width) {
        for (int i = parts.size() - 1; i >= 1; i--) {
            parts.get(i).setX(parts.get(i - 1).getX());
            parts.get(i).setY(parts.get(i - 1).getY());
        }


        switch (direction) {
            case up:
                parts.get(0).increaseYByValue(-1);
                //snakeParts.get(0).y--;
                break;
            case down:
                parts.get(0).increaseYByValue(1);
                //snakeParts.get(0).y++;
                break;
            case left:
                parts.get(0).increaseXByValue(-1);
                //snakeParts.get(0).x--;
                break;
            case right:
                parts.get(0).increaseXByValue(1);
                //snakeParts.get(0).x++;
                break;
        }
        for(int i = 0; i < parts.size(); i++) {
            if (parts.get(i).getY() < 0) {
                parts.get(i).increaseYByValue(height);
                //snakeParts.get(i).y += height;
            }
            if (parts.get(i).getY() >= height) {
                parts.get(i).increaseYByValue(-height);
                //snakeParts.get(i).y -= height + 1;
            }
            if (parts.get(i).getX() < 0) {
                parts.get(i).increaseXByValue(width);
            }
            if (parts.get(i).getX() >= width) {
                parts.get(i).increaseXByValue(-width);
            }
        }
    }
}
