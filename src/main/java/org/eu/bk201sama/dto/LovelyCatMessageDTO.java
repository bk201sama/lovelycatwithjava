package org.eu.bk201sama.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.eu.bk201sama.constant.LovelyCatRequestEventEnum;

import java.math.BigDecimal;

/**
 *     "event":"EventGroupMsgEvent",//事件标示(当前值为群消息事件)
 *     "robot_wxid":"wxid_5hxa04j4z6pg22",//机器人wxid
 *     "robot_name":"",//机器人昵称，一般为空
 *     "type":1,//1/文本消息 3/图片消息 34/语音消息  42/名片消息  43/视频 47/动态表情 48/地理位置  49/分享链接  2000/转账 2001/红包  2002/小程序  2003/群邀请
 *     "from_wxid":"18900134932@chatroom",//群id，群消息事件才有
 *     "from_name":"微群测",//群名字
 *     "final_from_wxid":"sundreamer",//发该消息的用户微信id
 *     "final_from_name":"遗忘悠剑o",//微信昵称
 *     "to_wxid":"wxid_5hxa04j4z6pg22",//接收消息的人id，（一般是机器人收到了，也有可能是机器人发出的消息，别人收到了，那就是别人）
 *     "msg":"图片https://b3logfile.com/bing/20201024.jpg",//消息内容(string/array) 使用时候根据不同的事件标示来定义这个值，字符串类型或者数据类型
 *     "money":0.01 //金额，只有"EventReceivedTransfer"事件才有该参数
 */
@Data
public class LovelyCatMessageDTO {
    private LovelyCatRequestEventEnum event;
    @JsonProperty("robot_wxid")
    private String robotId;
    @JsonProperty("robot_name")
    private String robotName;
    private Integer type;
    @JsonProperty("from_wxid")
    private String fromId;
    @JsonProperty("from_name")
    private String fromName;
    @JsonProperty("final_from_wxid")
    private String fromUserId;
    @JsonProperty("final_from_name")
    private String fromUserName;
    @JsonProperty("to_wxid")
    private String toId;
    private String msg;
    private BigDecimal money;

}
