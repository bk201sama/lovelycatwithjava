package org.eu.bk201sama.core;

import lombok.Data;
import org.eu.bk201sama.dto.LovelyCatMessageDTO;


public class ThreadValuePool {
    public static ThreadLocal<LovelyCatMessageDTO> messageInfo = new ThreadLocal<>();
}
