package com.example.demo.domain.user;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(schema = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private int age;
}