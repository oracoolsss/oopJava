import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Pair;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class SnakeController {
    private List<Snake> snakes;
    private GameModel gm;
    private GameView gv;
    public SnakeController() {
    }

    public void setModel(GameModel gameModel) {
        this.snakes = gameModel.getSnakes();
        this.gm = gameModel;
    }
    public void setView(GameView gv) {
        this.gv = gv;
    }

    public void updateScores(String playerName) {
        Pair<String, Integer> newRecord = new Pair<>(playerName, gm.getCurrentScore());
        gm.getScores().add(newRecord);
        Collections.sort(gm.getScores(), new Comparator<Pair<String, Integer>>() {
            @Override
            public int compare(Pair<String, Integer> p1, Pair<String, Integer> p2) {
                return p2.getValue() - p1.getValue();
            }
        });

        while (gm.getScores().size() > gm.getMAXSCORECOUNT()) {
            gm.getScores().remove(gm.getScores().size() - 1);
        }
        updateScoreFile(gm.getScores());
    }

    void updateScoreFile(List<Pair<String, Integer>> scores) {
        File scoreFile = new File("scores.txt");
        if(!scoreFile.exists()) {
            try {
                scoreFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileWriter fileWriter = null;
        BufferedWriter writer = null;
        try {
            fileWriter = new FileWriter(scoreFile);
            writer = new BufferedWriter(fileWriter);

            for (Pair<String, Integer> score : scores) {
                writer.write(score.getKey());
                writer.append('\n');
                writer.write(score.getValue().toString());
                writer.append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void keyPressed(KeyEvent key) {
        if(key.getCode() == KeyCode.ENTER) {
            gm.restartGame();
        }
        if(key.getCode() == KeyCode.R && gm.isNewRecord() && gm.isGameOver() && !gm.isRecordRecorded()) {
            gm.setRecordrecorded();
            gv.showInputTextDialog();
        }

        for(Snake snake: snakes) {
            if(snake.getNumber() % 2 == 1) {
                if (key.getCode() == KeyCode.W && snake.getDirection() != Direction.down) {
                    snake.setDirection(Direction.up);
                }
                if (key.getCode() == KeyCode.A && snake.getDirection() != Direction.right) {
                    snake.setDirection(Direction.left);
                }
                if (key.getCode() == KeyCode.S && snake.getDirection() != Direction.up) {
                    snake.setDirection(Direction.down);
                }
                if (key.getCode() == KeyCode.D && snake.getDirection() != Direction.left) {
                    snake.setDirection(Direction.right);
                }
            }
            else {
                if (key.getCode() == KeyCode.UP && snake.getDirection() != Direction.down) {
                    snake.setDirection(Direction.up);
                }
                if (key.getCode() == KeyCode.LEFT && snake.getDirection() != Direction.right) {
                    snake.setDirection(Direction.left);
                }
                if (key.getCode() == KeyCode.DOWN && snake.getDirection() != Direction.up) {
                    snake.setDirection(Direction.down);
                }
                if (key.getCode() == KeyCode.RIGHT && snake.getDirection() != Direction.left) {
                    snake.setDirection(Direction.right);
                }
            }
        }


    }
}
