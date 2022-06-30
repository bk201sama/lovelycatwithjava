package org.eu.bk201sama.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eu.bk201sama.constant.LovelyCatResponseEventEnum;

/**
 * {
 *     "event":"GetappInfo",//获取插件信息只需要event即可，所以其他都可以传空
 *     "to_wxid":"",
 *     "msg":"",
 *     "robot_wxid":"",
 *     "group_wxid":"",
 *     "member_wxid":""
 * }
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LovelyCatMessageVO {
    private LovelyCatResponseEventEnum event;
    @JsonProperty("to_wxid")
    private String toId;
    private String msg;
    @JsonProperty("robot_wxid")
    private String robotId;
    @JsonProperty("group_wxid")
    private String groupId;
    @JsonProperty("member_wxid")
    private String memberId;
}
