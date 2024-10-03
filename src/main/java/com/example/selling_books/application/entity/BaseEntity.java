package com.example.selling_books.application.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class BaseEntity {

    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        createdAt = Timestamp.valueOf(now);
        updatedAt = Timestamp.valueOf(now);
    }

    @PreUpdate
    protected void onUpdate() {
        LocalDateTime now = LocalDateTime.now();
        updatedAt = Timestamp.valueOf(now);
    }

    public String getFormattedCreatedAt() {
        if(getCreatedAt() != null){
            return createdAt.toLocalDateTime().format(FORMATTER);
        }
        return null;
    }

    public String getFormattedUpdatedAt() {
        if(getUpdatedAt() != null)
        {
            return updatedAt.toLocalDateTime().format(FORMATTER);
        }
        return null;
    }

}
