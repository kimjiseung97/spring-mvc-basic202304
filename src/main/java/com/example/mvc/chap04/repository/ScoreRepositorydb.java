package com.example.mvc.chap04.repository;

import com.example.mvc.chap04.entity.Grade;
import com.example.mvc.chap04.entity.Score;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Repository("a")
public class ScoreRepositorydb implements ScoreRepository{

    private String url = "jdbc:mariadb://localhost:3307/spring";
    private String username = "root";
    private String password = "1234";


    //db 에서 저장된 학생의 열 가져오는함수
    @Override
    public List<Score> findAll() {
        List<Score> scoreList = new ArrayList<>();
        //try - with - resource : close 자동화
        try (Connection conn = DriverManager.getConnection(url,username,password)){
            conn.setAutoCommit(false);

            String sql = "SELECT * FROM score";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            //포인터 커서로 첫번째 행부터 next호출시마다 매 다음행을 지목
            while (rs.next()) {
                //위치한 커서에서 데이터를 추출
                int kor = rs.getInt("korean");
                int math = rs.getInt("math");
                int eng = rs.getInt("english");
                double average = rs.getDouble("average");
                int total = rs.getInt("total");
                int stunum = rs.getInt("stunum");
                String name = rs.getString("name");
                Grade grade = Grade.valueOf(rs.getString("grade"));
                Score s = new Score(name,kor,eng,math,stunum,total,average,grade);
                scoreList.add(s);
            }
            System.out.println(scoreList.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return scoreList;
    }

    @Override
    public List<Score> findAll(String sort) {
        return ScoreRepository.super.findAll(sort);
    }

    @Override
    public boolean saveScore(Score score) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
            //트랜잭션 시작
            conn.setAutoCommit(false); //오토커밋 비활성화
            //SQL을 실행해주는 객체 얻기
            String sql = "INSERT INTO score (stunum,name,korean,english,math,total,average,grade) VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            //?값 세팅하기
            pstmt.setInt(1,score.getStuNum() +1);
            pstmt.setString(2, score.getName());
            pstmt.setInt(3,score.getKor());
            pstmt.setInt(4,score.getEng());
            pstmt.setInt(5,score.getMath());
            pstmt.setInt(6,score.getTotal());
            pstmt.setDouble(7,score.getAverage());
            pstmt.setString(8, String.valueOf(score.getGrade()));


            //sql실행하기
            // INSERT , UPDATE, DELETE : executeUpdate()
            // SELECT : executeQuery()
            // executeUpdate는 성공한 쿼리의 수를 알려줌
            int result = pstmt.executeUpdate();

            if (result == 1) conn.commit();
            else conn.rollback();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    @Override
    public boolean deleteScoreByStuNum(int stuNum) {
        return false;
    }

    @Override
    public Score findScoreByStuNum(int stuNum) {
        return null;
    }
}
