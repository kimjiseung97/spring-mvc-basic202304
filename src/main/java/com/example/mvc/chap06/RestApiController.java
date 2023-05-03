package com.example.mvc.chap06;

import com.example.mvc.jdbc.Person;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@Controller
@RequestMapping("rests")
//@ResponseBody
public class RestApiController {

    @GetMapping("/hello")
    public String hello(){
        return  "안녕하세요";
    }

    @GetMapping("foods")
    public List<String> foods(){
        //String[] foodList = {"탕수육","족발","마라탕"};
        List<String> foodList = List.of("탕수육","족발","마라탕");
        return foodList;
    }

    @GetMapping("/person")
    public Person person(){
        Person person = new Person(1L,"루피",3);

        return person;
    }

    @GetMapping("/person-list")
    public List<Person> personList(){
        Person person = new Person(1L,"루피",3);
        Person person2 = new Person(2L,"크롱",4);
        Person person3 = new Person(3L,"뽀로로",5);
        return List.of(person,person2,person3);
    }

    @GetMapping("/bmi")
    public ResponseEntity<?> bmi(@RequestParam(required = false) Double cm,
                               @RequestParam(required = false)  Double kg){
        if(cm == null || kg==null){
            return ResponseEntity.badRequest().body("키랑 몸무게를 보내주세요");
        }
        double bmi = kg / (cm / 100) * (cm / 100);
        HttpHeaders headers = new HttpHeaders();
        headers.add("fruits","melon");
        headers.add("hobby","soccer");
        return ResponseEntity.ok().headers(headers).body(bmi);
    }
}
