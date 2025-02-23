package com.eclock.model;

import com.eclock.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.Data;


import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "task_table")
@Data
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime createdDate;

    @Column(nullable = false)
    private LocalDateTime dueDate;

    private String description;

    @Column(nullable = false)
    private TaskStatus taskStatus;

    @ManyToMany(mappedBy = "tasks", fetch = FetchType.EAGER)
    private List<UserEntity> users;

}

