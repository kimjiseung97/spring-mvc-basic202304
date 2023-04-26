package com.example.mvc.chap04.controller;

import com.example.mvc.chap04.dto.ScoreRequestDTO;
import com.example.mvc.chap04.entity.Score;
import com.example.mvc.chap04.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
//@AllArgsConstructor : 모든 필드를 초기화하는 생성자
@RequiredArgsConstructor // : final 필드만 초기화하는 생성자
public class ScoreController {

    //저장소에 의존해야 데이터를 받아서 클라이언트에 전달가능
//    private final ScoreRepository repository;

    private final ScoreService scoreService;
    //만약에 클래스의 생성자가 단 한1개라면
    //자동으로 @Autowired를 써줌

//   @Autowired
//    public ScoreController(ScoreRepository repository){
//        this.repository = repository;
//    }
    //성적 등록화면 띄우기 + 정보목록 조회
    @GetMapping("/list")
    public String list(Model model, @RequestParam(defaultValue = "num") String sort){
        System.out.println("/score/list : GET!");
        System.out.println("sort = " + sort);
//        List<ScoreListResponseDTO> allScore = scoreService.getList(sort);

        //scoreList에서 원하는정보만 추출하고 이름은 마스킹해서
        //다시 DTO리스트로 변환해줘야 한다.
//        List<ScoreListResponseDTO> responseDTOList = new ArrayList<>();
//        for (Score s : allScore) {
//            ScoreListResponseDTO dto = new ScoreListResponseDTO(s);
//            responseDTOList.add(dto);
//        }
//        List<ScoreListResponseDTO> responsedtoList = allScore.stream().map(ScoreListResponseDTO::new).collect(Collectors.toList());
//        model.addAttribute("sList",allScore);
        return "chap04/score-list";
    }

    //성적정보 등록 처리 요청
    @PostMapping("/register")
    public String register(ScoreRequestDTO dto){

        //입력데이터(쿼리스트링) 읽기
        System.out.println("/score/register : POST! -"+dto);

        //dto(Score)를 entity(Score)로 변환해야함
        scoreService.insertScore(dto);
        //save명령
        /*
        * 등록요청에서 jsp뷰 포워딩을하면
        * 갱신된 목록을 다시한번 저장소에서 불러와
        * 모델에 담는 추가적인 코드가 필요하지만
        *
        *
        * 리다이렉트를 통해서 위에서 만든 /score/list : GET
        * 을 자동으로 다시 보낼 수  있다면 번거러운 코드가
        * 줄어들 수 있겠다.*/
        return "redirect:/score/list";
    }

    //성적 정보 삭제 요청
    @GetMapping("/remove")
    public  String remove(@RequestParam int stuNum){
        System.out.println("/score/remove : GET!");
        scoreService.delete(stuNum);
        return "redirect:/score/list";
    }

    //성적정보 상세조회 요청
    @GetMapping("/detail")
    public String detail(@RequestParam(required = true) int stuNum, Model model){
        Score scoreByStuNum = scoreService.retrieve(stuNum);
        System.out.println(scoreByStuNum); //자료 확인용
        model.addAttribute("s",scoreByStuNum);
        return "chap04/score-detail";
    }
//    @PostMapping("/modify")
//    public String modify(Model model){
//        return "chap04/score-modify";
//    }
    @GetMapping("/modifyview")
    public String modifyview(@RequestParam int stuNum,Model model){
        Score scoreByStuNum = scoreService.retrieve(stuNum);
        System.out.println(scoreByStuNum); //자료 확인용
        model.addAttribute("s",scoreByStuNum);
        return "chap04/score-modify";
    }

    @PostMapping("/modify")
    public String modify(int stuNum,ScoreRequestDTO dto){
        Score scoreByStuNum =scoreService.retrieve(stuNum);
        scoreByStuNum.changeScore(dto);
        return "redirect:/score/detail?stuNum="+stuNum;
    }

    



}
