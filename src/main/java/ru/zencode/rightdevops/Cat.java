package ru.zencode.rightdevops;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cat")
public class Cat extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;
}
