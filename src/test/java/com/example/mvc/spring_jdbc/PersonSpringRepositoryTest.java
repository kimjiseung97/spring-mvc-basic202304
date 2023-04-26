package com.example.mvc.spring_jdbc;

import com.example.mvc.jdbc.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
class PersonSpringRepositoryTest {
    @Autowired
    PersonSpringRepository repository;

    @Test
    void savePersonTest(){
        //given
        Person p  = new Person();
        p.setPersonName("김스프링");
        p.setPersonAge(13);

        //when

        repository.savePerson(p);
    }


    @Test
    void deletePersonTest(){
        //given
        long id = 4L;
        //when
        repository.removePerson(id);
    }


    @Test
    void modifyPersonTest() {
        // given
        Person p = new Person();
        p.setId(1L);
        p.setPersonName("만지호");
        p.setPersonAge(10000);
        // when
        boolean flag = repository.modify(p);
        // then
        assertTrue(flag);
    }


    @Test
    void findAlltest(){
        List<Person> people = repository.findAll();
        for (Person p : people) {
            System.out.println("p = " + p);
        }
    }

    @Test
    void findOneTest(){
        //given
        long id = 3L;
        Person person = repository.findOne(id);

        System.out.println("person = " + person);
    }



}