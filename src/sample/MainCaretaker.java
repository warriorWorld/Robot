package sample;

import bean.MainBean;
import config.ShareKeys;
import utils.ShareObjUtil;

public class MainCaretaker {

    public static void save(MainBean bean) {
        ShareObjUtil.saveObject(bean, ShareKeys.MAIN_KEY);
    }

    public static MainBean get() {
        return (MainBean) ShareObjUtil.getObject(ShareKeys.MAIN_KEY);
    }

    public static void clean() {
        ShareObjUtil.deleteFile(ShareKeys.MAIN_KEY);
    }
}
