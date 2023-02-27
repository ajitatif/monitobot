package com.monitobot.authentication;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@gmail.com)
 */
@Entity
@Table(name = "api_keys")
public class ApiKeyEntity extends PanacheEntityBase {

    @Id
    public String key;
    @Column(name = "enabled")
    public Boolean enabled;
    @Column(name = "created_on_utc")
    public LocalDateTime createdOnUtc;
    @Column(name = "updated_on_utc")
    public LocalDateTime updatedOnUtc;
    @Column(name = "update_comment")
    public String updateComment;
}
