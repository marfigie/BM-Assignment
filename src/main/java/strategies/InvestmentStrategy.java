package strategies;

import funds.FundType;
import java.math.BigDecimal;

public enum InvestmentStrategy {
    SAFE(new BigDecimal("0.2"),new BigDecimal("0.75"),new BigDecimal("0.05")),
    BALANCED(new BigDecimal("0.3"),new BigDecimal("0.6"),new BigDecimal("0.1")),
    AGGRESIVE(new BigDecimal("0.4"),new BigDecimal("0.2"),new BigDecimal("0.4"));

    private BigDecimal domesticPercentage;
    private BigDecimal foreignPercentage;
    private BigDecimal cashPercentage;

    InvestmentStrategy(BigDecimal domesticPercentage, BigDecimal foreignPercentage, BigDecimal cashPercentage) {
        this.domesticPercentage = domesticPercentage;
        this.foreignPercentage = foreignPercentage;
        this.cashPercentage = cashPercentage;
    }

    public BigDecimal getDomesticPercentage() {
        return domesticPercentage;
    }

    public BigDecimal getForeignPercentage() {
        return foreignPercentage;
    }

    public BigDecimal getCashPercentage() {
        return cashPercentage;
    }

    public BigDecimal getPercentageOfGivenType(FundType fundType){
        switch (fundType){
            case DOMESTIC:
                return domesticPercentage;
            case FOREIGN:
                return foreignPercentage;
            case CASH:
                return cashPercentage;
            default:
                return BigDecimal.ZERO;
        }
    }

}
