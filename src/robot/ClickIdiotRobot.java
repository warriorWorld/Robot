package robot;

import java.io.IOException;

import bean.ClickIdiotBean;
import javafx.fxml.FXMLLoader;
import listener.RobotListener;
import sun.rmi.runtime.Log;

public class ClickIdiotRobot extends BaseRobot {
    private final String TAG = "ClickIdiotRobot";
    private ClickIdiotController mController;

    @Override
    public void init() {
        super.init();
        try {
            FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("/fxml/click_idiot.fxml"));
            mParent = fxmlLoader1.load();
            mController = fxmlLoader1.getController();

            FXMLLoader fxmlLoader8 = new FXMLLoader(getClass().getResource("/fxml/item_main_options.fxml"));
            mMenu = fxmlLoader8.load();
            MainItemController itemController8 = fxmlLoader8.getController();
            itemController8.setIconIv("/drawable/ic_stupid.png");
            itemController8.setOptionText("连点智障");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(int startDelay, RobotListener listener) {
        super.start(startDelay, listener);
        save();
        new Thread(new Runnable() {
            @Override
            public void run() {
                mRobot.delay(startDelay);
                mMouseUtil.delayMove(mController.getX(), mController.getY());
                int interval = mController.getInterval();
                for (int i = 0; i < mController.getCount(); i++) {
                    if (interrupt) {
                        System.out.println("interrupt");
                        if (null != listener) {
                            listener.onStop("end by interrupt");
                        }
                        return;
                    }
                    mRobot.delay(interval);
                    if ("0".equals(mController.getKey())) {
                        System.out.println("左键");
                        mMouseUtil.delayClick();
                    } else if ("2".equals(mController.getKey())) {
                        System.out.println("右键");
                        mMouseUtil.delayClickRight();
                    } else {
                        System.out.println("键盘：" + mController.getKey());
                        mKeyboardUtil.delayInput(mController.getKey());
                    }
                }
                if (null != listener) {
                    listener.onStop("end success");
                }
            }
        }).start();
    }

    private void save() {
        ClickIdiotBean bean = new ClickIdiotBean();
        bean.setX(mController.getX());
        bean.setY(mController.getY());
        bean.setInterval(mController.getInterval());
        bean.setKey(mController.getKey());
        bean.setRepeatCount(mController.getCount());
        ClickIdiotCaretaker.saveClickIdiot(bean);
    }
}
