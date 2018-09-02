import org.junit.Test;
import strategies.InvestmentStrategy;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class InvestmentStrategyTests {

    @Test
    public void totalPercentageEqualsOneTest(){
        for (InvestmentStrategy strategy: InvestmentStrategy.values()) {
            BigDecimal sumOfStrategyPercentages = new BigDecimal(0)
                    .add(strategy.getCashPercentage())
                    .add(strategy.getDomesticPercentage())
                    .add(strategy.getForeignPercentage());
            assertTrue(new BigDecimal("1.0").compareTo(sumOfStrategyPercentages) == 0);
        }
    }
}
