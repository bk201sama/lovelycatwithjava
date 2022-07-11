package org.eu.bk201sama.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KeyWord {
    @Id
    private String id;
    @Column(name = "handler_bean_name")
    private String handlerBeanName;
    @Column(name = "creator_id")
    private String creatorId;
    @Column(name = "creator_name")
    private String creatorName;
}
