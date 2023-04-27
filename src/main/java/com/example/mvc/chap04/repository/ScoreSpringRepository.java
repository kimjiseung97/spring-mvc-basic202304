package com.example.mvc.chap04.repository;

import com.example.mvc.chap04.entity.Score;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("spring")
@AllArgsConstructor
public class ScoreSpringRepository implements ScoreRepository{

    private final JdbcTemplate jdpJdbcTemplate;
    @Override
    public boolean save(Score score) {
        String sql = "INSERT INTO tbl_score"+ " (name, kor, eng, math, total, average, grade) " +
                " VALUES (?, ?, ?, ?, ?, ?, ?)";

        return jdpJdbcTemplate.update(sql,score.getName(),score.getKor(),score.getEng(),score.getMath(),score.getTotal(),score.getAverage(),String.valueOf(score.getGrade()))==1;
    }

    @Override
    public List<Score> findAll() {
        return findAll("num");
    }

    @Override
    public List<Score> findAll(String sort) {
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

        return jdpJdbcTemplate.query(sql,
                (rs, n) -> new Score(rs));

    }

    @Override
    public boolean saveScore(Score score) {

        return false;
    }

    @Override
    public boolean deleteScoreByStuNum(int stuNum) {
        String sql = "DELETE FROM tbl_score WHERE stu_num=?";
        return jdpJdbcTemplate.update(sql, stuNum) == 1;

    }

    @Override
    public Score findScoreByStuNum(int stuNum) {
        String sql = "SELECT * FROM tbl_score WHERE stu_num=?";
        return jdpJdbcTemplate.queryForObject(sql,
                (rs , n) -> new Score(rs)
                , stuNum
        );

    }
}
