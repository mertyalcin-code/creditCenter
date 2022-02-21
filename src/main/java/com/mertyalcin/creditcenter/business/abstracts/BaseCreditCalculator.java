package com.mertyalcin.creditcenter.business.abstracts;

public abstract class BaseCreditCalculator {
    public abstract double calculateCreditAmount(int creditScore,double monthlySalary);
}
