package org.eu.bk201sama.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class UserSetting {
    @Id
//    @GenericGenerator(name = "system-uuid", strategy ="uuid")
//    @GeneratedValue(generator = "system-uuid")
    private String id;
}
