package com.miloszsosnowski.helpfinder.task.db;

import com.miloszsosnowski.helpfinder.task.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskJpaRepository extends JpaRepository<Task, Long> {
}