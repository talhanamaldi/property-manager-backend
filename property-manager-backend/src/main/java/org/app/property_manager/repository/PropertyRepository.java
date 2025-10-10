package org.app.property_manager.repository;

import org.app.property_manager.model.entity.app.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property, Long> {
}
