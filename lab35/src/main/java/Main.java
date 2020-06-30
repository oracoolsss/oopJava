import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        GameModel gm = new GameModel();
        SnakeController sc = new SnakeController();
        GameView gv = new GameView(gm.getWidth(), gm.getHeight(), gm.getPartSize(), gm.getSpaceForScoresWidth(), sc);
        sc.setModel(gm);
        sc.setView(gv);
        gv.setGameModel(gm);
        gm.registerObserver(gv);
        gm.start(stage);
    }
}