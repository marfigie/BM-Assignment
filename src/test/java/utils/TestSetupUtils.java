package utils;

import funds.FundType;
import funds.InvestmentFund;
import funds.InvestmentFundFactory;

import java.util.ArrayList;
import java.util.List;

public class TestSetupUtils {

    public static List<InvestmentFund> prepareInvestmentFunds(int numOfDomesticFunds, int numOfForeignFunds, int numOfCashFunds) {
        InvestmentFundFactory fundFactory = new InvestmentFundFactory();
        List<InvestmentFund> fundsList = new ArrayList<>();
        for (int i = 0; i < numOfDomesticFunds; i++) {
            fundsList.add(fundFactory.getFund(FundType.DOMESTIC));
        }
        for (int i = 0; i < numOfForeignFunds; i++) {
            fundsList.add(fundFactory.getFund(FundType.FOREIGN));
        }
        for (int i = 0; i < numOfCashFunds; i++) {
            fundsList.add(fundFactory.getFund(FundType.CASH));
        }
        return fundsList;
    }

}
