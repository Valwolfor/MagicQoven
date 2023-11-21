package com.magicqoven.entity;

import com.magicqoven.entity.util.UserRole;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "super_user")
public  class SuperUser extends User {

    @Column(name = "super_username", nullable = false)
    private String superUsername;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole superUserRole;

    public boolean isAdmin() {
        return superUserRole.equals(UserRole.ADMIN);
    }
}
