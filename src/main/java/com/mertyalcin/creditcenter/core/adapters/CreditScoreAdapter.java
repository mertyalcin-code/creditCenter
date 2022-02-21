package com.mertyalcin.creditcenter.core.adapters;

import com.mertyalcin.creditcenter.core.externalServices.FakeCreditScoreService;
import org.springframework.stereotype.Service;

@Service
public class CreditScoreAdapter implements CreditScoreService{
    @Override
   public int getPersonsCreditScore(String NationalityNo){
       FakeCreditScoreService fakeCreditScoreService = new FakeCreditScoreService();
             return fakeCreditScoreService.getPersonsCreditScore(Long.parseLong(NationalityNo));
    }
}
