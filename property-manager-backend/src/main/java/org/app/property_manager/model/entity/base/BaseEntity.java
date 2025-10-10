package org.app.property_manager.model.entity.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.app.property_manager.common.util.UserInfo;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @Column(name = "createdDate")
    private Date createdDate;

    @Column(name = "updatedDate")
    private Date updatedDate;

    @JsonIgnore
    @Column(name = "isDeleted", nullable = false)
    private Long isDeleted;

    @JsonIgnore
    @Column(name = "updateInsertUserId")
    private Long updateInsertUserId;

    @PrePersist
    public void setPrePersist(){
        setUpdateInsertValues();
        this.createdDate= new Date();
        this.isDeleted=0L;
    }

    @PreUpdate
    public void setPreUpdate(){
        setUpdateInsertValues();
        this.updatedDate= new Date();
    }


    public void setUpdateInsertValues() {
        this.updateInsertUserId= UserInfo.getUserInfo().getUserId();
    }

}
