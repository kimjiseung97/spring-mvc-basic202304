package com.example.mvc.chap04.entity;

import com.example.mvc.chap04.dto.ScoreRequestDTO;
import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;

@Setter @Getter
@ToString @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Score {

    private String stuName; //학생 이름

    private int kor, eng, math; // 국 영 수 점수

    private int stuNum; //학번

    private int total; //총점

    private double average; //평균

    private Grade grade; //학점


    public Score(ScoreRequestDTO dto) {
        this.stuName = dto.getName();
        this.kor = dto.getKor();
        this.math = dto.getMath();
        this.eng = dto.getEng();
        this.total = kor+eng+math;
        calcTotalAndAvg(); //총점 ,평균 계산
        calcGrade(); // 학점 계산
    }

    public Score(ResultSet rs) throws SQLException {
        this.stuNum = rs.getInt("stu_num");
        this.stuName = rs.getString("stu_name");
        this.kor = rs.getInt("kor");
        this.eng = rs.getInt("eng");
        this.math = rs.getInt("math");
        this.total = rs.getInt("total");
        this.average = rs.getDouble("average");
        this.grade = Grade.valueOf(rs.getString("grade"));
    }

    private void calcGrade() {
        if(average>=90) {
            this.grade = Grade.A;
        }else if(average>=80){
            this.grade = Grade.B;
        }else if(average>=70){
            this.grade = Grade.C;
        }else if(average>=60){
            this.grade = Grade.D;
        }else {
            this.grade = Grade.F;
        }
    }

    private void calcTotalAndAvg() {
        this.total = kor + eng + math;
        this.average = Math.round(total / 3.0);
    }

    public void changeScore(ScoreRequestDTO dto){
        this.kor = dto.getKor();
        this.math = dto.getMath();
        this.eng = dto.getEng();
        calcTotalAndAvg();
        calcGrade();
    }

}
