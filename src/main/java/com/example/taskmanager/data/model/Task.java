package com.example.taskmanager.data.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "tasks")
@Data
@NoArgsConstructor
public class Task extends BaseEntity {

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime dueDate;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime resolvedAt;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private TaskPriority priority;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Task task)) {
            return false;
        }
        return Objects.equals(getDescription(), task.getDescription()) &&
                Objects.equals(getPriority(), task.getPriority()) &&
                Objects.equals(getTitle(), task.getTitle()) &&
                Objects.equals(getCreatedAt(), task.getCreatedAt()) &&
                Objects.equals(getUpdatedAt(), task.getUpdatedAt()) &&
                Objects.equals(getStatus(), task.getStatus()) &&
                Objects.equals(getDueDate(), task.getDueDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDescription(), getCreatedAt(), getPriority(), getStatus(), getTitle(), getDueDate(),
                getResolvedAt(), getUpdatedAt());
    }
}
