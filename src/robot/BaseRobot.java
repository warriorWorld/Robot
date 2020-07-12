package robot;

import java.awt.AWTException;
import java.awt.Robot;

import javafx.scene.Parent;
import listener.RobotListener;
import utils.KeyboardUtil;
import utils.MouseUtil;

public abstract class BaseRobot {
    protected boolean interrupt;
    protected MouseUtil mMouseUtil;
    protected KeyboardUtil mKeyboardUtil;
    protected Parent mParent;
    protected Parent mMenu;
    protected Robot mRobot;

    public void init() {
        try {
            mRobot = new Robot();
            mMouseUtil = new MouseUtil();
            mKeyboardUtil = new KeyboardUtil();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public  void start(int startDelay,RobotListener listener){
        interrupt=false;
    }

    public void stop() {
        interrupt = true;
    }

    public Parent getParent() {
        return mParent;
    }

    public Parent getMenu() {
        return mMenu;
    }
}
