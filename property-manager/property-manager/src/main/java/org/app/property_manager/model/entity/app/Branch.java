package org.app.property_manager.model.entity.app;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.app.property_manager.model.entity.base.BaseEntity;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Getter
@Setter
@Entity(name = "Branch")
@Table
@SQLDelete(sql = "UPDATE branch SET is_deleted = id WHERE id = ?", check = ResultCheckStyle.COUNT)
@Where(clause = "is_deleted = 0")
public class Branch extends BaseEntity {

    @NotNull
    @Column(name = "name")
    private String name;

    @ManyToOne
    private Project project;
}
