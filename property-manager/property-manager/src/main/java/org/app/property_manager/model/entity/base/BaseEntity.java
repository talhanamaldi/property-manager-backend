package org.app.property_manager.model.entity.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
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
    @CreationTimestamp
    @Column(name = "createdDate")
    private Date createdDate;

    @UpdateTimestamp
    @Column(name = "updatedDate")
    private Date updatedDate;

    @JsonIgnore
    @Column(name = "isDeleted", nullable = false)
    private Long isDeleted;

    @PrePersist
    public void setPrePersist() {
        this.isDeleted = 0L;
    }

}
