package com.example.mvc.chap04.repository;

import com.example.mvc.chap04.entity.Score;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Repository("jdbc")
public class ScoreJdbcRepository implements ScoreRepository{

    private String url = "jdbc:mariadb://localhost:3307/spring";
    private String username = "root";
    private String password = "1234";

    private final JdbcTemplate jdbcTemplate;

    public ScoreJdbcRepository() {
        jdbcTemplate = null;
    }

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
        List<Score> scoreList = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(url,username,password)){
            String sql = "delete FROM tbl_score where stu_num=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,stuNum);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()){
                scoreList.add(new Score(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public Score findScoreByStuNum(int stuNum) {
        Score score = null;
        try(Connection conn = DriverManager.getConnection(url,username,password)){
            String sql = "SELECT * FROM tbl_score where stu_num=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,stuNum);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){
                score = new Score(rs);
                return score;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Score> findAll(String sort) {
        List<Score> scoreList = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(url,username,password)){
            String sql = "SELECT * FROM tbl_score";
            switch (sort) {
            case "num":
                sql += " ORDER BY stu_num";
                break;
            case "name":
                sql += " ORDER BY name";
                break;
            case "avg":
                sql += " ORDER BY average DESC";
                break;
        }
            PreparedStatement pstmt = conn.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()){
                scoreList.add(new Score(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return scoreList;
    }
//    @Override
//    public List<Score> findAll(String sort) {
//        String sql = "SELECT * FROM tbl_score";
//        switch (sort) {
//            case "num":
//                sql += " ORDER BY stu_num";
//                break;
//            case "name":
//                sql += " ORDER BY stu_name";
//                break;
//            case "avg":
//                sql += " ORDER BY average DESC";
//                break;
//        }
//
//        return jdbcTemplate.query(sql,
//                (rs, n) -> new Score(rs));
//
//    }

}
