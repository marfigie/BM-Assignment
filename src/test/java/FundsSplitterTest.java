import calculation.CalculationOutcome;
import calculation.PortfolioFundsSplitter;
import funds.FundType;
import funds.InvestmentFund;
import funds.InvestmentFundFactory;
import org.junit.Test;

import static org.junit.Assert.*;

import strategies.InvestmentStrategy;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FundsSplitterTest {

    @Test
    public void tenThousandPlnSafeStyle231() {

        List<InvestmentFund> fundList = prepareInvestmentFunds(2, 3, 1);

        PortfolioFundsSplitter portfolioFundsSplitter = new PortfolioFundsSplitter();
        CalculationOutcome outcome = portfolioFundsSplitter.splitFundsIntoPortfolioPositions(
                InvestmentStrategy.SAFE,
                fundList,
                new BigDecimal(10000));

        assertEquals(outcome.getPortfolioPositions().size(), 6);

        //Fund Type Assertion
        assertEquals(outcome.getPortfolioPositions().get(0).getInvestmentFund().getFundType(), FundType.DOMESTIC);
        assertEquals(outcome.getPortfolioPositions().get(1).getInvestmentFund().getFundType(), FundType.DOMESTIC);
        assertEquals(outcome.getPortfolioPositions().get(2).getInvestmentFund().getFundType(), FundType.FOREIGN);
        assertEquals(outcome.getPortfolioPositions().get(3).getInvestmentFund().getFundType(), FundType.FOREIGN);
        assertEquals(outcome.getPortfolioPositions().get(4).getInvestmentFund().getFundType(), FundType.FOREIGN);
        assertEquals(outcome.getPortfolioPositions().get(5).getInvestmentFund().getFundType(), FundType.CASH);

        //Fund Amount Assertion
        assertTrue(outcome.getPortfolioPositions().get(0).getCashAmount().compareTo(new BigDecimal(1000)) == 0);
        assertTrue(outcome.getPortfolioPositions().get(1).getCashAmount().compareTo(new BigDecimal(1000)) == 0);
        assertTrue(outcome.getPortfolioPositions().get(2).getCashAmount().compareTo(new BigDecimal(2500)) == 0);
        assertTrue(outcome.getPortfolioPositions().get(3).getCashAmount().compareTo(new BigDecimal(2500)) == 0);
        assertTrue(outcome.getPortfolioPositions().get(4).getCashAmount().compareTo(new BigDecimal(2500)) == 0);
        assertTrue(outcome.getPortfolioPositions().get(5).getCashAmount().compareTo(new BigDecimal(500)) == 0);

        //Fund Percentage Assertion
        assertTrue(outcome.getPortfolioPositions().get(0).getPercentage().compareTo(new BigDecimal("0.1")) == 0);
        assertTrue(outcome.getPortfolioPositions().get(1).getPercentage().compareTo(new BigDecimal("0.1")) == 0);
        assertTrue(outcome.getPortfolioPositions().get(2).getPercentage().compareTo(new BigDecimal("0.25")) == 0);
        assertTrue(outcome.getPortfolioPositions().get(3).getPercentage().compareTo(new BigDecimal("0.25")) == 0);
        assertTrue(outcome.getPortfolioPositions().get(4).getPercentage().compareTo(new BigDecimal("0.25")) == 0);
        assertTrue(outcome.getPortfolioPositions().get(5).getPercentage().compareTo(new BigDecimal("0.05")) == 0);

        assertTrue(outcome.getRemainder().compareTo(BigDecimal.ZERO) == 0);

    }

    @Test
    public void tenThousandPlnSafeStyle321() {

        List<InvestmentFund> fundList = prepareInvestmentFunds(3, 2, 1);

        PortfolioFundsSplitter portfolioFundsSplitter = new PortfolioFundsSplitter();
        CalculationOutcome outcome = portfolioFundsSplitter.splitFundsIntoPortfolioPositions(
                InvestmentStrategy.SAFE,
                fundList,
                new BigDecimal(10000));

        assertEquals(outcome.getPortfolioPositions().size(), 6);

        //Fund Type Assertion
        assertEquals(outcome.getPortfolioPositions().get(0).getInvestmentFund().getFundType(), FundType.DOMESTIC);
        assertEquals(outcome.getPortfolioPositions().get(1).getInvestmentFund().getFundType(), FundType.DOMESTIC);
        assertEquals(outcome.getPortfolioPositions().get(2).getInvestmentFund().getFundType(), FundType.DOMESTIC);
        assertEquals(outcome.getPortfolioPositions().get(3).getInvestmentFund().getFundType(), FundType.FOREIGN);
        assertEquals(outcome.getPortfolioPositions().get(4).getInvestmentFund().getFundType(), FundType.FOREIGN);
        assertEquals(outcome.getPortfolioPositions().get(5).getInvestmentFund().getFundType(), FundType.CASH);

        //Fund Amount Assertion
        assertTrue(outcome.getPortfolioPositions().get(0).getCashAmount().compareTo(new BigDecimal(667)) == 0);
        assertTrue(outcome.getPortfolioPositions().get(1).getCashAmount().compareTo(new BigDecimal(667)) == 0);
        assertTrue(outcome.getPortfolioPositions().get(2).getCashAmount().compareTo(new BigDecimal(666)) == 0);
        assertTrue(outcome.getPortfolioPositions().get(3).getCashAmount().compareTo(new BigDecimal(3750)) == 0);
        assertTrue(outcome.getPortfolioPositions().get(4).getCashAmount().compareTo(new BigDecimal(3750)) == 0);
        assertTrue(outcome.getPortfolioPositions().get(5).getCashAmount().compareTo(new BigDecimal(500)) == 0);

        //Fund Percentage Assertion
        assertTrue(outcome.getPortfolioPositions().get(0).getPercentage().compareTo(new BigDecimal("0.0667")) == 0);
        assertTrue(outcome.getPortfolioPositions().get(1).getPercentage().compareTo(new BigDecimal("0.0667")) == 0);
        assertTrue(outcome.getPortfolioPositions().get(2).getPercentage().compareTo(new BigDecimal("0.0666")) == 0);
        assertTrue(outcome.getPortfolioPositions().get(3).getPercentage().compareTo(new BigDecimal("0.375")) == 0);
        assertTrue(outcome.getPortfolioPositions().get(4).getPercentage().compareTo(new BigDecimal("0.375")) == 0);
        assertTrue(outcome.getPortfolioPositions().get(5).getPercentage().compareTo(new BigDecimal("0.05")) == 0);

        assertTrue(outcome.getRemainder().compareTo(BigDecimal.ZERO) == 0);

    }

    @Test
    public void remainderTest(){
        List<InvestmentFund> fundList = prepareInvestmentFunds(2, 3, 1);

        PortfolioFundsSplitter portfolioFundsSplitter = new PortfolioFundsSplitter();
        CalculationOutcome outcome = portfolioFundsSplitter.splitFundsIntoPortfolioPositions(
                InvestmentStrategy.SAFE,
                fundList,
                new BigDecimal(10000));

        assertEquals(outcome.getPortfolioPositions().size(), 6);
        assertTrue(outcome.getRemainder().compareTo(BigDecimal.ZERO) == 0);
    }

    private List<InvestmentFund> prepareInvestmentFunds(int numOfDomesticFunds, int numOfForeignFunds, int numOfCashFunds) {
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
