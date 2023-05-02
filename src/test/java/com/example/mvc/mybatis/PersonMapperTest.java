package com.example.mvc.mybatis;

import com.example.mvc.jdbc.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonMapperTest {
    @Autowired
    PersonMapper mapper;
    
    @Test
    @DisplayName("마이바티스 매퍼로 사람정보 저장에 성공해야 한다.")
    void saveTest(){
        //given
        Person p = Person.builder().personName("김마바").personAge(44).build();

        //when
        boolean flag = mapper.save(p);

        //then
        assertTrue(flag);
    }


    @Test
    @DisplayName("마이바티스 매퍼로 사람정보 수정에 성공해야 한다.")
    void changeTest(){
        //given
        Person p = Person.builder().personName("손인욱").personAge(29).id(2L).build();

        //when
        boolean flag = mapper.change(p);

        //then
        assertTrue(flag);
    }



    @Test
    @DisplayName("마이바티스 매퍼로 사람정보 삭제에 성공해야 한다.")
    void removeTest(){
        //given
        //when
        boolean flag = mapper.remove(1L);

        //then
        assertTrue(flag);
    }


    @Test
    @DisplayName("마이바티스 매퍼로 사람정보 전부가져오기에 성공해야 한다.")
    void findAllTest(){
        //given
        //when
        List<Person> people = mapper.findAll();

        //then
        people.forEach(System.out::println);
        assertEquals(4,people.size());
    }



    @Test
    @DisplayName("마이바티스 매퍼로 사람정보 개별조회에 성공해야 한다.")
    void findOneTest() {
        //given
        long id = 2L;
        // when
        Person p = mapper.findOne(id);
        //then
        System.out.println("p = " + p);
        assertEquals("뽀로로", p.getPersonName());
        assertEquals(45, p.getPersonAge());
        assertNotNull(p);
    }

}