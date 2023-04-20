package com.example.mvc.chap04.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/*
* # 요청 url
* 1. 학생 성적정보 등록화면을 보여주고 및  목록조회 처리
* -/score/list : GET
*
* 2. 성적 정보 등록 처리 요청
* -/score/register : POST
*
* 3. 성적정보 삭제 요청
* -/score/remove : POST
*
* 4. 성적정보 상세 조회 요청
*  -score/detail : GET
*
* */
@Controller
@RequestMapping("/score")
public class ScoreController {
    //성적 등록화면 띄우기 + 정보목록 조회
    @GetMapping("/list")
    public String list(){
        System.out.println("/score/list : GET!");
        return "";
    }

    //성적정보 등록 처리 요청
    @PostMapping("/register")
    public String register(){
        System.out.println("/score/register : POST!");
        return "";
    }

    //성적 정보 삭제 요청
    @PostMapping("/remove")
    public  String remove(){
        System.out.println("/score/remove : POST!");
        return "";
    }

    //성적정보 상세조회 요청
    @GetMapping("/detail")
    public String detail(){
        System.out.println("/score/detail : GET!");
        return "";
    }

}
