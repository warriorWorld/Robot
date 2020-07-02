package sample;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import base.BaseController;
import dialog.AlertDialog;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.stage.DirectoryChooser;
import utils.KeyboardUtil;
import utils.MouseUtil;

public class Controller extends BaseController implements Initializable {
    public MenuItem getCoordinateMi;
    public MenuItem getColorMi;
    private Robot mRobot;
    private MouseUtil mMouseUtil;
    private KeyboardUtil mKeyboardUtil;

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
    }
}
