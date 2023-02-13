package d1.project.nanjing.liuhe.common;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * 一些常量，包括枚举等
 *
 * @author Buter
 * @date 2020/3/18 16:09
 */
public class Constants {
    /**
     * 用户登录后token失效时间，暂定1天
     */
    public final static int TOKEN_EXPIRE_TIME = 24 * 60 * 60;
    /**
     * 用户登录后token失效时间，暂定1天   小程序端
     */
    public final static int APP_TOKEN_EXPIRE_TIME = 30 * 24 * 60 * 60;
    /**
     * 不可删除角色
     */
    public final static List<String> CANNOT_DELETED_ROLE = Arrays.asList("admin", "default");
    /**
     * 不可删除菜单
     */
    public final static List<String> CANNOT_DELETED_MENU_TREE = Arrays.asList("02d0aa6c0e644e3f90aea6cd7cdd3d59","045bc95a27624d44b94575b610749269",
            "1046cf27f46549609da95cc719277eb1","142f0f4993dc4e9d97bbc249e3091926","180889784e7041e88fc1983571ff2dfb","1b31f8af13044cdbbad8816d355d147b",
            "21984b06e86c4667940869dea5ac8c20","2c3aa4735b484699af812281bb3f78a8","4c16960f95974299a5fd610df6a1ed54","4ddee9efffc84344a3d6c08726686792",
            "4e0a5bb8061340aaba2032fb5ce7c6f8","5306c986ed644d7f93f5370c38b10a50","58c067850c0c4e7e88722a0ac43668a3","68f58248669b406984663e6b7539dc4f",
            "6ef3337c0eba4df2ab099811cff61674","81271ed483d54ddb814b8c72de9e7b04","8fe6ba6f57954cd4a69bcb3881cd0d94","951c7103f1394631931a8badd48b5975",
            "96050fae0d674f3998ee81a3556b3164","9a6d1f16e306467bb96166de6cc80159","9f911a785e2d49c682dc22d0379e0455","af1c183e6b9e4ee3970aecb62670ebf8",
            "b52942bcd7d54a378dc4b61661ba1a4b","b6b8d3c1a18c4165b5070fdf91684815","b71ead0d2db841e4b3b925d57e2b638c","c700dcd3cab441148c81c07ce3c1ef69",
            "cb38c7fad3eb45bfbf9422cc24b62178","d130850df56e44329bd24814bf2f8902","d219c621546c4468901f38ab3b8ce317","dbabc974a6344a90b6da0c7f88c9e424",
            "df88a57d5ab84bc58899a3ebcdbf3c4e","f11f28ac9f744eb69b05be651d10f5ff","f72f39e2b35c4e4ba26dd02d9603f7a2","f7facbd19fcb4ae79c021f1355d03cb9",
            "f887432193e9422c90ea1ad25247b877","f9582a6d01834919a6cd20315ae6e883","ffbfa1599ff34cb8bbdbcdec6fe46b61"
    );
    /**
     * 不可删除部门
     */
    public final static String CANNOT_DELETED_ORGANIZATION = "headquarters";
    public final static String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /**
     * 用户删除事件
     */
    public final static String USER_DELETE_EVENT = "userDelete";
    /**
     * 管理端权限类型
     */
    public final static String AUTH_WEBADMIN = "webadmin";
    /**
     * 质量小站权限类型
     */
    public final static String AUTH_ZLXZ = "zlxz";
    /**
     * 政企互动权限类型
     */
    public final static String AUTH_ZQHD = "zqhd";


    ///文件目录结构

    public final static String TEMP_ROOT = "." + File.separator + "temp" + File.separator;
    public final static String FILES_ROOT = "." + File.separator + "files" + File.separator;

    public final static String USERS = FILES_ROOT + "users" + File.separator;

    /** 存储图片 **/
    public final static String FILES_IMAGES = FILES_ROOT + "images" + File.separator;
    /** 存储附件 **/
    public final static String FILES_ATTACHMENT = FILES_ROOT + "attachment" + File.separator;


    /**
     * 提示语
     **/
    public static final String SUCCESS = "成功";
    public static final String FIND_SUCCESS = "查询成功";
    public static final String SYNCHRONOUS_SUCCESS = "同步成功";
    public static final String ADD_SUCCESS = "添加成功";
    public static final String APPLY_SUCCESS = "申请成功";
    public static final String UPDATE_SUCCESS = "编辑成功";
    public static final String EVALUATE_SUCCESS = "评价成功";
    public static final String DELETE_SUCCESS = "删除成功";
    public static final String IMPORT_SUCCESS = "导入成功";
    public static final String OPERATION_SUCCESS = "操作成功";
    public static final String RELEASE_SUCCESS = "发布成功";
    public static final String UPLOAD_SUCCESS = "上传成功";
    public static final String SUBMIT_SUCCESS = "提交成功";
    public static final String REPLY_SUCCESS = "回复成功";
    public static final String SEND_SUCCESS = "发送成功";
    public static final String EXAMINE_SUCCESS = "审核成功";

    public static final String FIND_FAIL = "查询失败";
    public static final String ADD_FAIL = "添加失败";
    public static final String APPLY_FAIL = "申请失败";
    public static final String UPDATE_FAIL = "编辑失败";
    public static final String EVALUATE_FAIL = "评价失败";
    public static final String DELETE_FAIL = "删除失败";
    public static final String DELETE_ALL_FAIL = "批量删除失败";
    public static final String IMPORT_FAIL = "导入失败";
    public static final String OPERATION_FAIL = "操作失败";
    public static final String RELEASE_FAIL = "发布失败";
    public static final String UPLOAD_FAIL = "上传失败";
    public static final String SUBMIT_FAIL = "提交失败";
    public static final String REPLY_FAIL = "回复失败";
    public static final String SEND_FAIL = "发送失败";
    public static final String EXAMINE_FAIL = "审核失败";

    public static final String REPLY_STATUS_0 = "待回复";
    public static final String REPLY_STATUS_1 = "已回复";

    public static final String READ_0 = "未读";
    public static final String READ_1 = "已读";
    /** 禁用 **/
    public static final Integer ENABLE_0 = 0;
    /** 启用 **/
    public static final Integer ENABLE_1 = 1;
    /** 未认证 **/
    public static final Integer IDENTIFICATION_0 = 0;
    /** 认证中 **/
    public static final Integer IDENTIFICATION_1 = 1;
    /** 认证通过 **/
    public static final Integer IDENTIFICATION_2 = 2;
    /** 认证驳回 **/
    public static final Integer IDENTIFICATION_3 = 3;
    /** 催办-否 **/
    public static final Integer URGE_0 = 0;
    /** 催办-是 **/
    public static final Integer URGE_1 = 1;
    /** 工单状态：待处理 **/
    public static final String WORK_STATUS_0 = "待处理";
    /** 工单状态：待评价 **/
    public static final String WORK_STATUS_1 = "待评价";
    /** 工单状态：已评价 **/
    public static final String WORK_STATUS_2 = "已评价";
    /** 入驻企业状态：未申请 **/
    public static final String SETTLED_ENTERPRISE_0 = "未申请";
    /** 入驻企业状态：申请中 **/
    public static final String SETTLED_ENTERPRISE_1 = "申请中";
    /** 入驻企业状态：已入驻 **/
    public static final String SETTLED_ENTERPRISE_2 = "已入驻";
    /** 入驻企业状态：已驳回 **/
    public static final String SETTLED_ENTERPRISE_3 = "已驳回";
    /** 入驻企业状态：已退园 **/
    public static final String SETTLED_ENTERPRISE_4 = "已退园";
    /** 企业是否入驻：未入驻 **/
    public static final int ENTERPRISE_IS_SETTLED_0 = 0;
    /** 企业是否入驻：已入驻 **/
    public static final int ENTERPRISE_IS_SETTLED_1 = 1;

}
