package d1.project.nanjing.liuhe.common;

/**
 * 正则检验类
 * @author wkk
 **/
public class RegexpConstant {

    public final static String ACCOUNT = "^[a-zA-Z0-9_]{6,20}$";
    public final static String PHONE = "^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$";
    public final static String PASSWORD = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[^\\w\\s]).{6,20}$";
    public static final String EMAIL = "^[a-z0-9A-Z]+[- | a-z0-9A-Z . _]+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-z]{2,}$";
    public static final String SETTLED_STATUS = "已入驻|已驳回";
}
