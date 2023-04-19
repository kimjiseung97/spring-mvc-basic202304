package com.example.mvc.chap03;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/hw")
public class LoginController {

    @GetMapping("/s-login-form")
    public String loginOrder(){

        System.out.println("GET 요청 발생!");
        return "chap03/s-form";
    }

    @GetMapping("/s-login-check")
        public String sLoginCheck(
                String id, String pw,
                Model model) {

            String result;
            //검증
            if (id.equals("grape111")) {
                if (pw.equals("ggg9999")) {
                    result = "success";
                } else {
                    result = "f-pw";
                }
            } else {
                result = "f-id";
            }
            model.addAttribute("result", result);
            return "chap03/s-result";
    }
}
