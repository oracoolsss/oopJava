import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.abs;

public class GameModel extends Application implements Observable  {
    private List<Observer> observers;
    private static final int MAXLENGTH = 13;
    private static int speed = 10;
    private static int width = 20;
    private static int height = 20;
    private static int foodX = 0;
    private static int foodY = 0;
    private static int partSize = 25;
    private int spaceForScoresWidth = 200;
    private static boolean gameOver = false;
    private static Random rand = new Random();
    private final int MAXSCORECOUNT = 10;
    private Stage stage;

    private boolean isRecordRecorded;

    private GameView gv;

    private List<Snake> snakes;
    private List<Pair<String, Integer>> scores;

    public GameModel() {
        observers = new LinkedList<>();
        snakes = new ArrayList<>();
        scores = new ArrayList<>();
    }

    List<Snake> getSnakes() {
        return this.snakes;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            stage = primaryStage;
            notifyObservers("start");
            loadScores();
            newFood();
            //gv.setStage(primaryStage);

            new AnimationTimer() {
                long lastTick = 0;

                public void handle(long now) {
                    if (lastTick == 0) {
                        lastTick = now;
                        tick();
                        return;
                    }

                    if (now - lastTick > 1000000000 / speed) {
                        lastTick = now;
                        tick();
                    }
                }

            }.start();

            //snakes generating
            generateSnakes();
            isRecordRecorded = false;
            notifyObservers("draw");
            //gv.draw();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void tick() {
        if(gameOver) {
            notifyObservers("data updated");
            //gv.updateView(gameOver, snakes, foodX, foodY);
            return;
        }
        for (Snake snake: snakes) {
            snake.move(height, width);
        }

        // eat food исправить блин точно надо
        int snakesWhoEatCount = 0;
        for(Snake snake: snakes) {
            if(snake.checkHeadCoordinatesMatches(foodX, foodY)) {
                snakesWhoEatCount ++;
            }
        }

        for(Snake snake: snakes) {
            if(snake.checkHeadCoordinatesMatches(foodX, foodY) && snakesWhoEatCount < 2) {
                snake.getParts().add(new SnakePart(-1, -1));
            }
        }
        if(snakesWhoEatCount != 0) {
            newFood();
        }

        for (Snake snake: snakes) {
            snake.checkSelfEating();
        }

        for (Snake snake: snakes) {
            if(snake.getSnakeLength() > MAXLENGTH) {
                gameOver = true;
            }
        }
        notifyObservers("data updated");
        //gv.updateView(gameOver, snakes, foodX, foodY);
    }

    public void newFood() {
        if(snakes.size() == 0) {
            foodX = rand.nextInt(width);
            foodY = rand.nextInt(height);
            return;
        }
        start: while (true) {
            foodX = rand.nextInt(width);
            foodY = rand.nextInt(height);
            for(Snake s: snakes) {
                if(s.checkCoordinatesMatches(foodX, foodY)) {
                    continue start;
                }
            }
            break;

        }
    }

    //public void setSnakeController(SnakeController sc) {
    //    this.sc = sc;
    //}
    public void setView(GameView gv) {
        this.gv = gv;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getSpaceForScoresWidth() {
        return spaceForScoresWidth;
    }

    public int getPartSize() {
        return partSize;
    }

    private void generateSnakes() {
        snakes.clear();
        snakes.add(new Snake(1));
        snakes.add(new Snake(2));
        for(Snake snake: snakes) {
            for(int i = 0; i < 3; i++) {
                snake.addPart(new SnakePart(width / 2 + 2 - 4 * (snake.getNumber() % 2), height / 2));
            }
        }
    }

    public void restartGame() {
        if(gameOver) {
            generateSnakes();
            gameOver = false;
        }
    }

    private void loadScores() {
        FileReader readFile;
        BufferedReader reader = null;

        try {
            readFile = new FileReader("scores.txt");
            reader = new BufferedReader(readFile);
            parseScores(reader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(reader != null)
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void parseScores(BufferedReader reader) {
        int i = 0;
        String name;
        String pointsString;
        Integer points;
        while (i < MAXSCORECOUNT) {
            try {
                name = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
            try {
                pointsString = reader.readLine();
                points = Integer.parseInt(pointsString);
            } catch (Exception e) {
                break;
            }
            scores.add(new Pair<>(name, points));
            i++;
        }
    }

    public List<Pair<String, Integer>> getScores() {
        return  scores;
    }

    public boolean isNewRecord() {
        if(scores.size() == MAXSCORECOUNT) {
            return scores.get(scores.size()-1).getValue() < abs(snakes.get(0).getSnakeLength() - snakes.get(1).getSnakeLength());
        }
        return true;
    }

    int getCurrentScore() {
        return abs(snakes.get(0).getSnakeLength() - snakes.get(1).getSnakeLength());
    }

    public boolean isGameOver() {
        return gameOver;
    }
    public int getMAXSCORECOUNT() {
        return MAXSCORECOUNT;
    }

    public boolean isRecordRecorded() {
        return isRecordRecorded;
    }
    public void setRecordrecorded() {
        isRecordRecorded = true;
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.notification(message);
        }
    }

    public int getFoodX() {
        return foodX;
    }

    public int getFoodY() {
        return foodY;
    }

    public Stage getStage() {
        return stage;
    }
}
