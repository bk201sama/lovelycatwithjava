package org.eu.bk201sama.handler;

import org.eu.bk201sama.dto.AnswerDTO;
import org.eu.bk201sama.dto.LovelyCatMessageDTO;

import java.util.Set;

public interface KeyWordHandler {
    /**
     * execute and return answer
     * @return answer
     */
    AnswerDTO execute(LovelyCatMessageDTO lovelyCatMessageDTO, Set<String> msgSet);
}
