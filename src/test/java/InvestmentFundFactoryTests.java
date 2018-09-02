import funds.FundType;
import funds.InvestmentFund;
import funds.InvestmentFundFactory;
import org.junit.Test;

import static org.junit.Assert.*;

public class InvestmentFundFactoryTests {

    @Test
    public void investmentFactoryIdGenerationTest(){
        InvestmentFundFactory fundFactory = new InvestmentFundFactory();

        for(long i=1L; i<100L; i++){
            InvestmentFund fund = fundFactory.getFund(FundType.CASH);
            assertTrue(fund.getFundId()==i);
        }
    }
}
