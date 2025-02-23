package com.eclock.repository;

import com.eclock.model.TimesheetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimesheetRepository extends JpaRepository<TimesheetEntity, Long> {
}
