package com.example.mvc.chap04.repository;

import com.example.mvc.chap04.entity.Score;

import java.util.List;

public class ScoreRepositoryImpl  implements ScoreRepository{
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
}
