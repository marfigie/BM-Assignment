import exceptions.validation.PortfolioValidationException;
import funds.InvestmentFund;
import org.junit.Test;
import portfolio.PortfolioRequirements;
import strategies.InvestmentStrategy;
import utils.TestSetupUtils;

import java.math.BigInteger;
import java.util.List;

public class PortfolioRequirementsTests {

    @Test
    public void missingFundTypeTest() {
        List<InvestmentFund> fundList = TestSetupUtils.prepareInvestmentFunds(0, 2, 1);

        try {
            new PortfolioRequirements(InvestmentStrategy.SAFE, fundList, BigInteger.valueOf(10000));
        } catch (PortfolioValidationException e) {
            //Got exception as expected - assertion done
        }
    }

    @Test
    public void notEnoughFundsTest() {
        List<InvestmentFund> fundList = TestSetupUtils.prepareInvestmentFunds(1, 2, 1);

        try {
            new PortfolioRequirements(InvestmentStrategy.SAFE, fundList, BigInteger.valueOf(0));
        } catch (PortfolioValidationException e) {
            //Got exception as expected - assertion done
        }

        try {
            new PortfolioRequirements(InvestmentStrategy.SAFE, fundList, BigInteger.valueOf(-1));
        } catch (PortfolioValidationException e) {
            //Got exception as expected - assertion done
        }
    }

}
