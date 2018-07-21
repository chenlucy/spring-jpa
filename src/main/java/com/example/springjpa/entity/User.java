package com.example.springjpa.entity;


import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.util.Date;

/**
 * User
 * @author corey
 * @date 2018/7/9
 */
@Setter
@Getter
@Entity
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "birthday")
    private Date birthday;
    @Column(name = "mobile")
    private String mobile;
    @Column(name = "remark")
    private String remark;
    @Column(name = "is_enable")
    private Boolean isEnable;

}