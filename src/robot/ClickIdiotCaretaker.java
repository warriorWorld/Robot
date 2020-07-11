package robot;

import bean.ClickIdiotBean;
import config.ShareKeys;
import utils.ShareObjUtil;

public class ClickIdiotCaretaker {

    public static void saveClickIdiot(ClickIdiotBean bean) {
        ShareObjUtil.saveObject(bean, ShareKeys.CLICK_IDIOT_KEY);
    }

    public static ClickIdiotBean getClickIdiot() {
        return (ClickIdiotBean) ShareObjUtil.getObject(ShareKeys.CLICK_IDIOT_KEY);
    }

    public static void clean() {
        ShareObjUtil.deleteFile(ShareKeys.CLICK_IDIOT_KEY);
    }
}
