package com.mertyalcin.creditcenter.business.abstracts;

public abstract class BaseCreditCalculatorFactory {
   public abstract double calculateCreditAmount(int creditScore,double monthlySalary);
}
