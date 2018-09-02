import calculation.CalculationOutcome;
import calculation.PortfolioComposer;
import funds.FundType;
import funds.InvestmentFund;
import org.junit.Test;

import static org.junit.Assert.*;

import portfolio.PortfolioPosition;
import portfolio.PortfolioRequirements;
import strategies.InvestmentStrategy;
import utils.TestSetupUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

public class PortfolioComposerTests {

    /*Test name describes parameters as follows: investing_style invested_amount num_of_funds(domestic,foreign,cash)*/

    @Test
    public void SafeStyle10000pln231() {

        List<InvestmentFund> fundList = TestSetupUtils.prepareInvestmentFunds(2, 3, 1);
        PortfolioComposer portfolioComposer = new PortfolioComposer();
        PortfolioRequirements requirements = new PortfolioRequirements(InvestmentStrategy.SAFE, fundList, BigInteger.valueOf(10000));
        CalculationOutcome outcome = portfolioComposer.calculatePositionsInPortfolio(requirements);

        assertEquals(outcome.getPortfolioPositions().size(), 6);

        //Fund Type Assertion
        List<FundType> types = Arrays.asList(FundType.DOMESTIC,FundType.DOMESTIC,FundType.FOREIGN,
                FundType.FOREIGN,FundType.FOREIGN,FundType.CASH);
        assertFundTypes(outcome.getPortfolioPositions(),types);

        //Fund Amount Assertion
        List<Integer> amounts = Arrays.asList(1000,1000,2500,2500,2500,500);
        assertAmountsEqual(outcome.getPortfolioPositions(),amounts);

        //Fund Percentage Assertion
        List<String> percentages = Arrays.asList("0.1","0.1","0.25","0.25","0.25","0.05");
        assertPercentagesEqual(outcome.getPortfolioPositions(),percentages);

        assertTrue(outcome.getRemainder().compareTo(BigInteger.ZERO) == 0);
    }

    @Test
    public void SafeStyle10000pln321() {

        List<InvestmentFund> fundList = TestSetupUtils.prepareInvestmentFunds(3, 2, 1);

        PortfolioComposer portfolioComposer = new PortfolioComposer();
        PortfolioRequirements requirements = new PortfolioRequirements(InvestmentStrategy.SAFE, fundList, BigInteger.valueOf(10000));
        CalculationOutcome outcome = portfolioComposer.calculatePositionsInPortfolio(requirements);

        assertEquals(outcome.getPortfolioPositions().size(), 6);

        //Fund Type Assertion
        List<FundType> types = Arrays.asList(FundType.DOMESTIC,FundType.DOMESTIC,FundType.DOMESTIC,
                FundType.FOREIGN,FundType.FOREIGN,FundType.CASH);
        assertFundTypes(outcome.getPortfolioPositions(),types);

        //Fund Amount Assertion
        List<Integer> amounts = Arrays.asList(668,666,666,3750,3750,500);
        assertAmountsEqual(outcome.getPortfolioPositions(),amounts);

        //Fund Percentage Assertion
        List<String> percentages = Arrays.asList("0.0668","0.0666","0.0666","0.375","0.375","0.05");
        assertPercentagesEqual(outcome.getPortfolioPositions(),percentages);

        assertTrue(outcome.getRemainder().compareTo(BigInteger.ZERO) == 0);
    }

    @Test
    public void remainderTest() {
        List<InvestmentFund> fundList = TestSetupUtils.prepareInvestmentFunds(2, 3, 1);

        PortfolioComposer portfolioComposer = new PortfolioComposer();
        PortfolioRequirements requirements = new PortfolioRequirements(InvestmentStrategy.SAFE, fundList, BigInteger.valueOf(10001));
        CalculationOutcome outcome = portfolioComposer.calculatePositionsInPortfolio(requirements);

        assertEquals(outcome.getPortfolioPositions().size(), 6);
        assertTrue(outcome.getRemainder().compareTo(BigInteger.ONE) == 0);
    }


    /*----------------- Helper methods --------------------------*/

    private void assertAmountsEqual(List<PortfolioPosition> portfolioPositions, List<Integer> amounts) {
        assertEquals(portfolioPositions.size(), amounts.size());
        for (int i = 0; i < amounts.size(); i++) {
            assertTrue(portfolioPositions.get(i).getCashAmount().compareTo(BigInteger.valueOf(amounts.get(i))) == 0);
        }
    }

    private void assertPercentagesEqual(List<PortfolioPosition> portfolioPositions, List<String> percentages){
        assertEquals(portfolioPositions.size(), percentages.size());
        for (int i = 0; i < percentages.size(); i++) {
            assertTrue(portfolioPositions.get(i).getPercentage().compareTo(new BigDecimal(percentages.get(i))) == 0);
        }
    }

    private void assertFundTypes(List<PortfolioPosition> portfolioPositions, List<FundType> fundTypes){
        assertEquals(portfolioPositions.size(), fundTypes.size());
        for (int i = 0; i < fundTypes.size(); i++) {
            assertEquals(portfolioPositions.get(i).getInvestmentFund().getFundType(),fundTypes.get(i));
        }
    }
}
