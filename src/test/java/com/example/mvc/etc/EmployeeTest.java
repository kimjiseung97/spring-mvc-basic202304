package com.example.mvc.etc;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    @Test
    void tttt(){
        Employee build = Employee.builder().position("대리").empName("홍길동").hireDate(LocalDate.of(2019,3,15)).build();

        System.out.println("build = " + build);

        Actor actor = Actor.builder().actorAge(15).actorName("장동건").build();

        System.out.println("actor = " + actor);
    }

}