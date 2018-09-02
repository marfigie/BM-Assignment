package portfolio;

import funds.InvestmentFund;

import java.math.BigDecimal;
import java.math.BigInteger;

public class PortfolioPosition {

    private InvestmentFund investmentFund;
    private BigInteger cashAmount;
    private BigDecimal percentage;

    public PortfolioPosition(InvestmentFund investmentFund, BigInteger cashAmount, BigDecimal percentage) {
        this.investmentFund = investmentFund;
        this.cashAmount = cashAmount;
        this.percentage = percentage;
    }

    public PortfolioPosition(InvestmentFund investmentFund, BigInteger cashAmount) {
        this.investmentFund = investmentFund;
        this.cashAmount = cashAmount;
        this.percentage = BigDecimal.ZERO;
    }

    public InvestmentFund getInvestmentFund() {
        return investmentFund;
    }

    public BigInteger getCashAmount() {
        return cashAmount;
    }

    public BigDecimal getPercentage() {
        return percentage;
    }

    public String getFormattedPercentage() {
        return percentage.multiply(new BigDecimal(100)).stripTrailingZeros().toString() + "%";
    }

    public void setCashAmount(BigInteger cashAmount) {
        this.cashAmount = cashAmount;
    }

    public void setPercentage(BigDecimal percentage) {
        this.percentage = percentage;
    }
}
