package com.Grabsis.entity;


import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class Deleteable {

    private Boolean isDeleted=false;

    public void delete() {
        this.isDeleted = true;
    }
    public void activate() { this.isDeleted = false; }
}
