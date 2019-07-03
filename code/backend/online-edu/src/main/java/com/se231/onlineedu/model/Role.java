package com.se231.onlineedu.model;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

/**
 * Role Class
 *
 * ORM to roles table
 *
 * @author Yuxuan Liu
 *
 * @date 2019/07/01
 */
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    private RoleType role;

    public Role() {
    }

    public Role(RoleType role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }
}
