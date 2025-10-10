package org.app.property_manager.model.entity.app;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.app.property_manager.model.entity.base.BaseEntity;
import org.hibernate.annotations.*;

@Getter
@Setter
@Entity(name = "Users")
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email")
        })

@SQLDelete(sql = "UPDATE users SET is_deleted = id WHERE id = ?", check = ResultCheckStyle.COUNT)
@Where(clause = "is_deleted = 0")
public class User extends BaseEntity {

    @Column(name = "email")
    private String email;

    @Column(name = "userName")
    private String username;

    @NotNull(message = "Şifre alanı boş olamaz")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password")
    private String password;

    @Column(name = "isAdmin", nullable = false)
    private Boolean isAdmin = false;
}
