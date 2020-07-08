package robot;

import java.net.URL;
import java.util.ResourceBundle;

import base.BaseController;
import dialog.AlertDialog;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

public class ClickIdiotController extends BaseController implements Initializable {
    public TextArea xInput;
    public TextArea yInput;
    public TextArea keyInput;
    public TextArea inputIntervalInput;
    public TextArea countInput;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public int getX() {
        try {
            return Integer.parseInt(xInput.getText());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            AlertDialog.display("请输入数字");
            return 0;
        }
    }

    public int getY() {
        try {
            return Integer.parseInt(yInput.getText());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            AlertDialog.display("请输入数字");
            return 0;
        }
    }

    public String getKey() {
        return keyInput.getText();
    }

    public int getCount() {
        try {
            return Integer.parseInt(countInput.getText());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            AlertDialog.display("请输入数字");
            return 0;
        }
    }
}
