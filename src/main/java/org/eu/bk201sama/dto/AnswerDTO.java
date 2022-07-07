package org.eu.bk201sama.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eu.bk201sama.constant.LovelyCatRequestEventEnum;
import org.eu.bk201sama.constant.LovelyCatResponseEventEnum;

import java.math.BigDecimal;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDTO {
    private String msg;
    private LovelyCatResponseEventEnum msgType;
}
