package com.epam.esm.model.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Column(name = "email")
    @Getter
    @Setter
    private String email;

    @Column(name = "password")
    @Getter
    @Setter
    private String password;

    @Column(name = "first_name")
    @Getter
    @Setter
    private String firstName;

    @Column(name = "last_name")
    @Getter
    @Setter
    private String lastName;

    @Column(name = "create_date")
    @Getter
    @Setter
    private Timestamp createDate;

    @Column(name = "last_update_date")
    @Getter
    @Setter
    private Timestamp lastUpdateDate;

    @OneToMany(mappedBy = "user")
    @Getter
    @Setter
    private Set<Order> orders;

}
