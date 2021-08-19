package com.rb.RB.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String userName;
    private String displayName;
    private String password;

}
