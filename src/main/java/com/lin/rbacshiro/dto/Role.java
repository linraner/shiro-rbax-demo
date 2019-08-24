package com.lin.rbacshiro.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Role {

    @Id
    private String id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne(optional = false)
    private User user;
}
