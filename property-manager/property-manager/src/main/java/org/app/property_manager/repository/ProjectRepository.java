package org.app.property_manager.repository;

import org.app.property_manager.model.entity.app.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
