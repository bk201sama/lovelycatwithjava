package org.eu.bk201sama.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eu.bk201sama.constant.ProcessEnum;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String id;
    private Integer menuId;
    @Enumerated(EnumType.STRING)
    private ProcessEnum process;
}
