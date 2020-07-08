package robot;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class ClickIdiotRobot extends BaseRobot {
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
    public void start() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mRobot.delay(5000);
                mMouseUtil.delayMove(mController.getX(), mController.getY());
                for (int i = 0; i < mController.getCount(); i++) {
                    if (interrupt) {
                        return;
                    }
                    mMouseUtil.delayClick();
                }
            }
        }).start();
    }

    @Override
    public void stop() {

    }
}
