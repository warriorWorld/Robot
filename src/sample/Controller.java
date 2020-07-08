package sample;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import base.BaseController;
import dialog.AlertDialog;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.DirectoryChooser;
import robot.BaseRobot;
import robot.ClickIdiotRobot;
import utils.KeyboardUtil;
import utils.MouseUtil;

public class Controller extends BaseController implements Initializable {
    public MenuItem getCoordinateMi;
    public MenuItem getColorMi;
    public Button startBtn;
    private Robot mRobot;
    private MouseUtil mMouseUtil;
    private KeyboardUtil mKeyboardUtil;
    private List<BaseRobot> mRobots = new ArrayList<>();
    public ListView menuLv;
    public StackPane mStackPane;
    private int currentScenePos = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            mRobot = new Robot();
            mMouseUtil = new MouseUtil();
            mKeyboardUtil = new KeyboardUtil();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        initUI();
        initRobots();
    }

    private void initUI() {
        getCoordinateMi.setOnAction(event -> {
            mRobot.delay(5000);
            AlertDialog.display("坐标", mMouseUtil.getMousePositionString(), "确定");
        });
        getColorMi.setOnAction(event -> {
            mRobot.delay(5000);
            AlertDialog.display("色值", mMouseUtil.getMousePositionColorString(), "确定");
        });
        startBtn.setOnAction(event -> {
            mRobots.get(currentScenePos).start();
        });
    }

    private void initRobots() {
        BaseRobot mClickIdiot = new ClickIdiotRobot();
        mClickIdiot.init();
        mRobots.add(mClickIdiot);

        for (int i = 0; i < mRobots.size(); i++) {
            menuLv.getItems().add(mRobots.get(i).getMenu());
        }
        menuLv.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        menuLv.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton().toString().equals("PRIMARY")) {
                    toggleContent(menuLv.getSelectionModel().getSelectedIndex());
                }
            }
        });
    }

    private void toggleContent(int position) {
        currentScenePos = position;
        switch (position) {
            case 0:
                mStackPane.getChildren().clear();
                mStackPane.getChildren().add(mRobots.get(position).getParent());
                break;
        }
    }
}
