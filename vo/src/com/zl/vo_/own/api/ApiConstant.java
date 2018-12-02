package com.zl.vo_.own.api;

/**
 * Created by Administrator on 2018/8/23.
 * 常量
 */

public class ApiConstant {
    public static String APP_ID="wx10fbd6006733af9b";
    public static String APP_SECRET="a6a067a1a86100b844734c024c8351c5";
    //下载链接
    public static final String DOWN_LOAD_URL = "http://api.ykhswl.net/vo_admin_system/html5/2.php";
    //功能介绍
    public static final String FUNCTION_INTRODUCE = "http://api.ykhswl.net/vo_admin_system/list.php";
    //VO介绍
    public static final String VO_INTRODUCE = "http://api.ykhswl.net/vo_admin_system/view.php?id=10";
    //VO会员用户协议
    public static final String VO_VIP_AGREEMENT="http://api.ykhswl.net/vo_admin_system/view.php?id=4";
    //服务器地址
   // public static final String BASE_URL="http://47.104.73.127:8080/news-0.0.1/api/user/login";
    public static final String SUCCESS_CODE="200000";
    public static final String OVERDUE_CODE="401002";
    public static final String BASE_URL="http://47.92.132.120";
    //注册
    public static final String REGISTER=BASE_URL+"/api/v1/register";
    //登录
    public static final String LOGIN=BASE_URL+"/api/v1/login";
    //获取个人信息
    public static final String GET_USERINFO=BASE_URL+"/api/v1/user/profile";
    //修改vo号
    public static final String CHANGE_VO_CODE=BASE_URL+"/api/v1/user/vo_code";
    //获取二维码

    public static final String GET_ERWEIMA=BASE_URL+"/api/v1/user/qrcode";
    //修改头像
    public static final String CHANGE_AVATAR=BASE_URL+"/api/v1/user/avatar";
    //搜索好友
    public static final String SEARCH_FRIEND=BASE_URL+"/api/v1/friend/search";
    //判断添加好友的方式
    public static final String ADD_FRIEND_DIRECTLY=BASE_URL+"/api/v1/friend/profile";
    //添加好友
    public static final String INSERT_FRIEND=BASE_URL+"/api/v1/friend/insert";
    //删除好友
    public static final String DELETE_FRIEND=BASE_URL+"/api/v1/friend/delete";
    //微信登录
    public static final String WECHAT_LOGIN=BASE_URL+"/api/v1/WeChatLogin";
    //获取验证码
    public static final String GET_CONFIRM_CODE=BASE_URL+"/api/v1/sendSms";
    //微信注册
    public static final String WX_REGISTER=BASE_URL+"/api/v1/WeChatRegister";
    //微信绑定
    public static final String WX_BIND=BASE_URL+"/api/v1/WeChatBind";







































































    /***
     * xzy:接口常量
     */

    //设置签名
    public static final String SET_SIGNATURE=BASE_URL+"/api/v1/user/signature";
    //设置地址
    public static final String SET_ADDRESS=BASE_URL+"/api/v1/user/address";
    //设置性别
    public static final String SET_SEX=BASE_URL+"/api/v1/user/sex";
    //设置昵称
    public static final String SET_NICK=BASE_URL+"/api/v1/user/name";
    //退出登录
    public static final String LOGOUT_SERVER = BASE_URL+"/api/v1/logout";
    //加我为好友时需要验证
    public static final String FriendAsMe_Verification = BASE_URL+"/api/v1/user/friend_verify";

    //获取通讯录好友
    public static final String GET_ADDFRIENDS = BASE_URL+"/api/v1/friend/book_friends";

    //通用设置里设置添加方式
    public static final String SET_WAYS = BASE_URL+"/api/v1/user/way";






}
