package bean;

import java.io.Serializable;

public class BaseBean implements Serializable {
    private int startDelay;

    public int getStartDelay() {
        return startDelay;
    }

    public void setStartDelay(int startDelay) {
        this.startDelay = startDelay;
    }
}
