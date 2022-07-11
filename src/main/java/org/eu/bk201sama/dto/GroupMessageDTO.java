package org.eu.bk201sama.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eu.bk201sama.constant.LovelyCatResponseEventEnum;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupMessageDTO {
    private String msg;
    private LovelyCatResponseEventEnum msgType;
}
