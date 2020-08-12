package com.huiztech.muyq.leaf.rpc.domain;


import com.huiztech.leaf.common.annotation.UuidKeyAnnotation;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author [muyuanqiang]
 * @version [1.0.0]
 * @date: [2020/8/10 23:06]
 * @description []
 */
@Entity
@Table(name = "user")
@DynamicInsert
@DynamicUpdate
@UuidKeyAnnotation(value = "user_key")
public class User {
    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "com.huiztech.muyq.leaf.rpc.utils.RpcUuidGenerator",
            parameters = {@Parameter(name = "dataCenterID", value = "20"), @Parameter(name = "idLength", value = "10")})
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
