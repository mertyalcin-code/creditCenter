package com.mertyalcin.creditcenter.core.externalServices;

public class FakeCreditScoreService {

    public int getPersonsCreditScore(Long NationalityNo){
        int score;
        score= (int) (Math.random()*2000);
        return score;
    }
}
