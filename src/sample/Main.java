package sample;

import base.BaseController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = fxmlLoader.load();
        BaseController controller = fxmlLoader.getController(); //获取Controller的实例对象
        Scene scene = new Scene(root, 800, 800);

        primaryStage.setTitle("Robot");
        primaryStage.setScene(scene);
        primaryStage.show();
        controller.setStage(primaryStage);
        controller.setScene(scene);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
