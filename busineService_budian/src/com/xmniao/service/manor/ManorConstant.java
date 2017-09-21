package com.xmniao.service.manor;

/**
 * Created by yang.qiang on 2017/6/13.
 */
public class ManorConstant {

    /** 庄园花类型 */
    public static final int FLOWER_TYPE_SEEDING = 0;    // 花苗
    public static final int FLOWER_TYPE_FLOWER = 1;     // 花朵
    public static final int FLOWER_TYPE_SELF = 2;       // 充值赠送的花朵(只计算自己)


    /** 庄园花位置 */
    public static final int FLOWER_LOCATION_LEFT = 0;   // 左(玫瑰田)
    public static final int FLOWER_LOCATION_MIDDLE = 1; // 中(兰花田)
    public static final int FLOWER_LOCATION_RIGHT = 2;  // 右(葵花田)


    /** 黄金庄园操作记录类型 */
    public static final int MANOR_INFO_OPERATE_TYPE_OPENED = 1;          // 开通庄园
    public static final int MANOR_INFO_OPERATE_TYPE_ACTIVATED = 2;       // 激活庄园
    public static final int MANOR_INFO_OPERATE_TYPE_UPDATE_LEVEL = 3;    // 更新庄园等级
    public static final int MANOR_INFO_OPERATE_TYPE_PAY_SERVICE_FAILED = 4; // 支付服务报错
    public static final int MANOR_INFO_OPERATE_TYPE_UPDATE_LOCATION = 5;    // 更新庄园节点位置

    /** 黄金庄园花朵关系操作记录类型 */
    public static final int MANOR_FLOWER_LOG_TYPE_PLANT_TASK = 1;       // 1:种植任务(种植/迁移关系链/重置枯萎花朵)
    public static final int MANOR_FLOWER_LOG_TYPE_INIT_NODE = 2;        // 2:插入初始节点
    public static final int MANOR_FLOWER_LOG_TYPE_MIGRATE = 3;          // 3:迁移花朵关系链
    public static final int MANOR_FLOWER_LOG_TYPE_UPDATE_TYPE = 4;      // 4:更新节点类型
    public static final int MANOR_FLOWER_LOG_TYPE_PLANT= 5;             // 5:种植花朵
    public static final int MANOR_FLOWER_LOG_TYPE_AUTO_PLANT= 6;        // 6:自动种植任务(种植/迁移关系链/重置枯萎花朵)
    public static final int MANOR_FLOWER_LOG_TYPE_WITH_GIVE = 7;        // 7:种植园友赠送的花苗


    /** 花朵种植状态*/
    public static final int MANOR_FLOWER_LOG_STATE_PUSH = 1;            // 1:推送消息
    public static final int MANOR_FLOWER_LOG_STATE_CONSUMED = 2;        // 2:消费消息
    public static final int MANOR_FLOWER_LOG_STATE_FAILURE = 3;         // 3:消费失败
    public static final int MANOR_FLOWER_LOG_STATE_PAY_SERVICE_FAILURE = 4; //4:支付服务报错



    /** 黄金庄园Redis 相关key */
    public static final String PREFIX_MANOR_FLOWER = "manor:flower:";    // 黄金庄园用户庄园信息key前缀
    public static final String MANOR_PUSH_NWE_FRIEND_LIST = "manor:push.message:new.friend.list";  // 新用户推送信息列表
    public static final String MANOR_REDIS_KEY_COUPONS = "coupons";                         // Redis字段新优惠劵
    public static final String MANOR_REDIS_KEY_PUSH_COUPONS = "push.coupons";               // 推送消息新优惠劵
    public static final String MANOR_REDIS_KEY_COUPONS_NEWFRIEND = "newFriend";             // 新园友
    public static final String MANOR_REDIS_KEY_COUPONS_PUSH_NEWFRIEND = "push.newFriend";   // 推送消息新园友
    public static final String MANOR_REDIS_KEY_FIELD_PREFIX = "manor:field:";               // 黄金庄园花田开启庄园key



    /** 黄金庄园默认基础生命周期 */
    public static final int FLOWER_DEFAULT_DAYS = 30;    // 默认花朵 基础有效期天数
    public static final int MANOR_FLOWER_DAYS = 30;      // 默认庄园 基础有效期天数
    public static final int AUTO_PLANT_HOURS = 24;       // 自动种花超时时间


    public static final int DEFAULT_LOCATIONS = 3;      // 黄金庄园默认花田(位置)数量


    /** 黄金庄园花朵分享类型*/
    public static final int MANOR_FLOWER_SHARE_TYPE_SUBCHAIN = 1;   // 子链
    public static final int MANOR_FLOWER_SHARE_TYPE_SELF = 2;       // 个人独享
    
    
    public static final int MANOR_URS_EARNINGS_OBJCET_ORIENTD = 9; //庄园用户关系链类型
    
}


