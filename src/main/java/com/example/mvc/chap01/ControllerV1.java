package com.example.mvc.chap01;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

// 어떤요청들을 처리할지 공통 url을 설계
@RequestMapping("/spring/*")
//이 클래스의 객체를 스프링이 관리하도록 빈을 등록한다
@Controller //@Component와 같은개념
public class ControllerV1 {

    // 세부요청들은 메서드를 통해 처리
    @RequestMapping("/hello") //http://localhost:8181/spring/hello
    public String hello(){
        System.out.println("\n========헬로 요청이 들어옴========\n");
        //return "/WEB-INF/views/hello.jsp"; //어떤 jsp를 열어줄지 경로를 적습니다.
        return "hello";
    }

    // /spring/food 요청이오면 food.jsp를 열어주세요

    @RequestMapping("/food") //http://localhost:8181/spring/hello
    public String food(){
        System.out.println("\n========food 요청이 들어옴========\n");
        //return "/WEB-INF/views/chap01/food.jsp"; //어떤 jsp를 열어줄지 경로를 적습니다.
        return "chap01/food";
    }

    //===========요청 파라미터 읽기(Query String parameter)==========/
    // == 1. HttpServletRequest 사용하기
    // ==> ex) /spring/person?name=kim&age=30
    @RequestMapping("/person")
    public String person(HttpServletRequest request){
        String name = request.getParameter("name");
        String age = request.getParameter("age");

        System.out.println("name = " + name);
        System.out.println("age = " + age);

        return "";
    }


    // == 2. @RequestParam 사용하기
    // ==> ex) /spring/major?stu=kim&major=business&grade=3
    @RequestMapping("/major")
    public String major(
            String stu,
            @RequestParam ("major") String mj,
            @RequestParam (defaultValue = "1")int grade
    ){
        String major = "전공전공";

        System.out.println("stu = " + stu);
        System.out.println("mj = " + mj);
        System.out.println("grade = " +grade);
        return "";
    }

    // == 3. 커맨드 객체 이용하기
    // == 쿼리스트링의 양이 너무 많을 경우 또는 연관성이 있을경우
    // ==> ex) /spring/order?oNum=20230419007-p&goods=구두&amount=3&price=50000.....
    @RequestMapping("/order")
    public String order(OrderRequestDTO dto){
        System.out.println("dto = " + dto);
        return "";
    }

    // == 4. url에 경로로 붙어있는 데이터 읽기
    // ==> /spring/member/hong/107
    // hong이라는 유저의 107번 게시글을 읽고싶음
    @RequestMapping("/member/{userName}/{bNo}")
    public String member(
            @PathVariable String userName,
            @PathVariable long bNo
    ){
        System.out.println("userName = " + userName);
        System.out.println("bNo = " + bNo);
        return "";
    }

    //음식 선택 요청 처리
    //@RequestMapping(value = "/food-select",method = RequestMethod.POST)
    @PostMapping("/food-select")
    public String foodSelect(
            String foodName,
            String category
    ){
        System.out.println("foodName = " + foodName);
        System.out.println("category = " + category);
        return "hello";
    }

}
