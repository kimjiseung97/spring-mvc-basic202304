package com.example.mvc.chap04.repository;

import com.example.mvc.chap04.entity.Score;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
@Repository("jdbc")
public class ScoreJdbcRepository implements ScoreRepository{

    private String url = "jdbc:mariadb://localhost:3307/spring";
    private String username = "root";
    private String password = "1234";

    @Override
    public boolean save(Score score){
        
        try (Connection conn = DriverManager.getConnection(url,username,password)){
            conn.setAutoCommit(false);
            String sql = "INSERT INTO tbl_score" +
                    "(name, kor, eng,math,total,average,grade)" +
                    "VALUES(?,?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,score.getName());
            pstmt.setInt(2,score.getKor());
            pstmt.setInt(3,score.getEng());
            pstmt.setInt(4,score.getMath());
            pstmt.setInt(5,score.getTotal());
            pstmt.setDouble(6,score.getAverage());
            pstmt.setString(7, String.valueOf(score.getGrade()));

            int result = pstmt.executeUpdate(); // 성공시 1 실패시0

            if (result == 1) {
                conn.commit();
                return true;
            }
            conn.rollback();
            return false;

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    
    @Override
    public List<Score> findAll() {
        return null;
    }

    


    @Override
    public boolean saveScore(Score score) {
        return false;
    }

    @Override
    public boolean deleteScoreByStuNum(int stuNum) {
        return false;
    }

    @Override
    public Score findScoreByStuNum(int stuNum) {
        return null;
    }

    @Override
    public List<Score> findAll(String sort) {
        return ScoreRepository.super.findAll(sort);
    }
}
