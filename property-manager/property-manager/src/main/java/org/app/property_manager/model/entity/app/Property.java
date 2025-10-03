package org.app.property_manager.model.entity.app;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.app.property_manager.model.entity.base.BaseEntity;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.type.SqlTypes;

@Getter
@Setter
@Entity(name = "Property")
@Table
@SQLDelete(sql = "UPDATE property SET is_deleted = id WHERE id = ?", check = ResultCheckStyle.COUNT)
@Where(clause = "is_deleted = 0")
public class Property extends BaseEntity {

    @NotNull
    @Column(name = "name")
    private String fileName;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "content", columnDefinition = "jsonb", nullable = false)
    private JsonNode content;

}
