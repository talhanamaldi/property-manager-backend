package org.app.property_manager.repository;

import org.app.property_manager.model.entity.app.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository extends JpaRepository<Branch, Long> {
}
