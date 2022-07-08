package org.eu.bk201sama.handler;

import org.eu.bk201sama.dto.AnswerDTO;
import org.eu.bk201sama.dto.LovelyCatMessageDTO;
import org.eu.bk201sama.entity.KeyWord;
import org.eu.bk201sama.repository.KeyWordRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Component("stockHandler")
@ConditionalOnBean(name = "keyWordRepository")
public class StockHandler implements KeyWordHandler, InitializingBean {
    @Resource
    private KeyWordRepository keyWordRepository;
    public static final String gupiao = "股票";


    @Override
    public AnswerDTO execute(LovelyCatMessageDTO lovelyCatMessageDTO, Set<String> msgSet) {
        for(String num : msgSet){


        }
        return AnswerDTO.builder()
                .msg("查询股票格式为:股票 600031")
                .build();
    }

    @Override
    public void afterPropertiesSet() {
        Set<KeyWord> keyWordSet = new HashSet<>();
        keyWordSet.add(KeyWord.builder()
                .id(gupiao)
                .handlerBeanName("stockHandler")
                .creatorName("stockHandler")
                .build());
        keyWordRepository.saveAll(keyWordSet);
    }
}
