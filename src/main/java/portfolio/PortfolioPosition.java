package portfolio;

import funds.InvestmentFund;
import java.math.BigDecimal;

public class PortfolioPosition {

    private InvestmentFund investmentFund;
    private BigDecimal cashAmount;
    private BigDecimal percentage;

    public PortfolioPosition(InvestmentFund investmentFund, BigDecimal cashAmount, BigDecimal percentage) {
        this.investmentFund = investmentFund;
        this.cashAmount = cashAmount;
        this.percentage = percentage;
    }

    public InvestmentFund getInvestmentFund() {
        return investmentFund;
    }

    public BigDecimal getCashAmount() {
        return cashAmount;
    }

    public BigDecimal getPercentage() {
        return percentage;
    }

    public String getFormattedPercentage(){
        return percentage.multiply(new BigDecimal(100)).stripTrailingZeros().toString() + "%";
    }

}
