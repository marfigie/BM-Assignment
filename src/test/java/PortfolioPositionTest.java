import funds.FundType;
import funds.InvestmentFund;
import funds.InvestmentFundFactory;
import org.junit.Test;
import portfolio.PortfolioPosition;
import java.math.BigDecimal;

import static org.junit.Assert.*;

public class PortfolioPositionTest {

    @Test
    public void portfolioPercentageFormatterTest(){
        InvestmentFund investmentFund = new InvestmentFundFactory().getFund(FundType.CASH);
        PortfolioPosition portfolioPosition = new PortfolioPosition(investmentFund,new BigDecimal(1000),new BigDecimal("0.0668"));

        assertEquals(portfolioPosition.getFormattedPercentage(),"6.68%");
    }
}
