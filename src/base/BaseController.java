package base;

import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class BaseController {
    protected Stage stage;
    protected Scene scene;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }
}
