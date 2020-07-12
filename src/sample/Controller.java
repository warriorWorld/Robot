package sample;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import base.BaseController;
import bean.MainBean;
import dialog.AlertDialog;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import listener.RobotListener;
import robot.BaseRobot;
import robot.ClickIdiotRobot;
import utils.KeyboardUtil;
import utils.MouseUtil;

public class Controller extends BaseController implements Initializable {
    public MenuItem getCoordinateMi;
    public MenuItem getColorMi;
    public Button startBtn;
    public TextField delayTf;
    private Robot mRobot;
    private MouseUtil mMouseUtil;
    private KeyboardUtil mKeyboardUtil;
    private List<BaseRobot> mRobots = new ArrayList<>();
    public ListView menuLv;
    public StackPane mStackPane;
    private int currentScenePos = 0;
    private MainBean mMainBean;

    private enum ButtonState {
        START,
        STOP
    }

    private ButtonState mButtonState = ButtonState.STOP;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            mRobot = new Robot();
            mMouseUtil = new MouseUtil();
            mKeyboardUtil = new KeyboardUtil();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        mMainBean = MainCaretaker.get();
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
        RobotListener robotListener = new RobotListener() {
            @Override
            public void onStop(String reason) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(reason);
                        mButtonState = ButtonState.STOP;
                        toggleBtnState();
                    }
                });
            }
        };
        startBtn.setOnAction(event -> {
            switch (mButtonState) {
                case STOP:
                    try {
                        MainBean mainBean = new MainBean();
                        mainBean.setStartDelay(Integer.valueOf(delayTf.getText()));
                        MainCaretaker.save(mainBean);
                        mRobots.get(currentScenePos).start(mainBean.getStartDelay(),robotListener);
                        mButtonState = ButtonState.START;
                    } catch (Exception e) {
                        e.printStackTrace();
                        AlertDialog.display("请输入开始延迟时间");
                    }
                    break;
                case START:
                    mRobots.get(currentScenePos).stop();
                    mButtonState = ButtonState.STOP;
                    break;
            }
            toggleBtnState();
        });
        if (null != mMainBean) {
            delayTf.setText(mMainBean.getStartDelay() + "");
        }
    }

    private void toggleBtnState() {
        switch (mButtonState) {
            case START:
                startBtn.setText("停止");
                break;
            case STOP:
                startBtn.setText("开始");
                break;
        }
    }

    @Override
    public void setScene(Scene scene) {
        super.setScene(scene);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                System.out.println(event.getCode());
                if (event.getCode().toString().equals("ESCAPE")) {
                    mRobots.get(currentScenePos).stop();
                    mButtonState = ButtonState.STOP;
                    toggleBtnState();
                }
            }
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
