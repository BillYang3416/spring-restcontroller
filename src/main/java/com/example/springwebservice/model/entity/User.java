package com.example.springwebservice.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// 使用 Lombok 加入 Getter, Setter, Constructor
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private int age;

    @Column
    private String gender;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Address address;

    // ref: https://vladmihalcea.com/orphanremoval-jpa-hibernate/
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Account> accountList = new ArrayList<>();

    // ref: https://asbnotebook.com/jpa-many-to-many-example-spring-boot/
    // if cascade don't use MERGE, update will not work
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roleList = new ArrayList<>();

    public void addRole(Role role) {
        roleList.add(role);
        role.getUserList().add(this);
    }

    public void removeRole(Role role) {
        roleList.remove(role);
        role.getUserList().remove(this);
    }

}
