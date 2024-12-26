package com.example.demo;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "employee")
@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class EmpEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonProperty("EmpName")
    @Column(name = "EmpName", nullable = false)
    private String EmpName;

    @JsonProperty("EmpAddress")
    @Column(name = "EmpAddress", nullable = false)
    private String EmpAddress;

    @Column(name = "EmpSalary", nullable = false)
    private double EmpSalary;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "url", nullable = true)
    private String url;


    @Column(name = "fileName", nullable = true)
    private String fileName;

}
