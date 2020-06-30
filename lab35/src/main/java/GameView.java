import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.List;
import java.util.Optional;

import static java.lang.Double.min;

public class GameView implements Observer{
    private int width;
    private int height;
    private int partSize;
    private int spaceForScoresWidth;
    private Color foodColor;

    private VBox root;
    private Canvas c;
    private GraphicsContext gc;
    private Stage primaryStage;
    private Scene scene;

    private GameModel gm;
    private SnakeController sc;

    public GameView(int width, int height, int partSize, int spaceForScoresWidth, SnakeController sc) {
        this.width = width;
        this.height = height;
        this.partSize = partSize;
        this.spaceForScoresWidth = spaceForScoresWidth;
        this.sc = sc;
        root = new VBox();
        c = new Canvas(width * partSize + spaceForScoresWidth, height * partSize);
        gc = c.getGraphicsContext2D();
        root.getChildren().add(c);

        foodColor = Color.AQUA;
        this.scene = new Scene(root, width * partSize + spaceForScoresWidth, height * partSize);

        scene.addEventFilter(KeyEvent.KEY_PRESSED, this.sc::keyPressed);
    }

    public VBox getRoot() {
        return root;
    }

    public void setGameModel(GameModel gm) {
        this.gm = gm;
    }

    public void setStage(Stage stage) {
        primaryStage = stage;
    }

    public void draw() {
        primaryStage.setScene(scene);
        primaryStage.setTitle("SUPER MEGA GIGA SNAKE PVP GAME GOTY EDITION");
        primaryStage.show();
        updateScoresScreen();
    }

    void updateScoresScreen() {
        gc.setFill(Color.BLACK);
        gc.fillRect(width * partSize, 0, spaceForScoresWidth, height * partSize);

        gc.setFill(Color.WHITE);

        gc.fillRect(width * partSize, 0, 2, height * partSize);

        gc.setFont(new Font("", height * partSize / 20.0));
        gc.fillText("Scores:", width * partSize + spaceForScoresWidth / 10.0, height * partSize / 20.0);

        gc.setFill(Color.WHITE);
        int temp = 2;
        if(gm.getScores().size() > 0) {
            for(Pair<String, Integer> score : gm.getScores()) {
                gc.setFont(new Font("", height * partSize / 20.0 * min(1.0, 7.5 / score.getKey().length())));
                gc.fillText(score.getKey(), width * partSize + spaceForScoresWidth / 10.0, height * partSize / 20.0 * temp);
                gc.setFont(new Font("", height * partSize / 20.0));
                gc.fillText(score.getValue().toString(), width * partSize + spaceForScoresWidth / 10.0, height * partSize / 20.0 * (temp + 1));
                temp += 2;
            }
        }
    }

    public void updateView(boolean isGameOver, List<Snake> snakes, int foodX, int foodY) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, width * partSize, height * partSize);

        gc.setFill(foodColor);
        gc.fillOval(foodX * partSize, foodY * partSize, partSize, partSize);

        for(Snake s: snakes) {
            if(s.getNumber() % 2 == 1) {
                gc.setFill(Color.GREEN);
            }
            else {
                gc.setFill(Color.BLUEVIOLET);
            }
            for(SnakePart spart: s.getParts()) {
                gc.fillRect(spart.getX() * partSize, spart.getY() * partSize, partSize - 1, partSize - 1);
            }
        }

        for(Snake s: snakes) {
            gc.setFill(Color.WHITE);
            gc.setFont(new Font("", 30));
            gc.fillText("Score " + s.getNumber() + ": " + (s.getSnakeLength() - 3), width * partSize / 50.0, 30 * s.getNumber());
        }
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("", height * partSize / 40.0));
        gc.fillText("First player: WASD\nSecond player: arrows", width * partSize / 50.0, 80.0);



        if (isGameOver) {
            gc.setFill(Color.RED);
            gc.setFont(new Font("", height * partSize / 10.0));
            gc.fillText("GAME OVER", width * partSize / 5.0, height * partSize / 2.0);

            gc.setFill(Color.WHITE);
            gc.setFont(new Font("", height * partSize / 20.0));
            gc.fillText("Press ENTER to restart", width * partSize / 4.0, height * partSize / 2.0 + height * partSize / 10.0);

            if(gm.isNewRecord()) {
                gc.fillText("OGO WOW, new record!\nPress R to write\nyour name, champion", width * partSize / 4.0, height * partSize / 2.0 + 2 * height * partSize / 10.0);
            }
            return;
        }


    }

    public void showInputTextDialog() {
        String winnerColor = gm.getSnakes().get(0).getSnakeLength() > gm.getSnakes().get(1).getSnakeLength() ? "green" : "blueviolet";
        TextInputDialog dialog = new TextInputDialog();

        dialog.setTitle("OGO WOW, new record!");
        dialog.setHeaderText("Write your name, " + winnerColor + " snake player");
        dialog.setContentText("Name:");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(name -> {
            sc.updateScores(name);
        });
        gc.setFill(Color.BLACK);
        gc.fillRect(width * partSize, 0, spaceForScoresWidth, height * partSize);

        gc.setFill(Color.WHITE);

        gc.fillRect(width * partSize, 0, 2, height * partSize);

        gc.setFont(new Font("", height * partSize / 20.0));
        gc.fillText("Scores:", width * partSize + spaceForScoresWidth / 10.0, height * partSize / 20.0);

        gc.setFill(Color.WHITE);
        int temp = 2;
        for(Pair<String, Integer> score : gm.getScores()) {
            gc.setFont(new Font("", height * partSize / 20.0 * min(1.0, 7.5 / score.getKey().length())));
            gc.fillText(score.getKey(), width * partSize + spaceForScoresWidth / 10.0, height * partSize / 20.0 * temp);
            gc.setFont(new Font("", height * partSize / 20.0));
            gc.fillText(score.getValue().toString(), width * partSize + spaceForScoresWidth / 10.0, height * partSize / 20.0 * (temp + 1));
            temp += 2;
        }
    }

    @Override
    public void notification(String message) {
        if (message.equals("start")) {
            System.out.println("start");
            this.primaryStage = gm.getStage();
        }
        else if (message.equals("draw")) {
            System.out.println("draw");
            this.draw();
        }
        else if (message.equals("data updated")) {
            this.updateView(gm.isGameOver(), gm.getSnakes(), gm.getFoodX(), gm.getFoodY());
        }
    }
}
