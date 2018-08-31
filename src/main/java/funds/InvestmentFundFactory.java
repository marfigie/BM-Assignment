package funds;

public class InvestmentFundFactory {

    private int numOfDomesticFunds = 0;
    private int numOfForeignFunds = 0;
    private int numOfCashFunds = 0;
    private long totalNumberOfFunds = 0L;

    public InvestmentFund getFund(FundType fundType) {
        switch (fundType) {
            case FOREIGN:
                return new InvestmentFundImpl(
                        FundType.FOREIGN,
                        "Fundusz Zagraniczny " + ++numOfForeignFunds,
                        ++totalNumberOfFunds
                );
            case DOMESTIC:
                return new InvestmentFundImpl(
                        FundType.DOMESTIC,
                        "Fundusz Polski " + ++numOfDomesticFunds,
                        ++totalNumberOfFunds
                );
            case CASH:
                return new InvestmentFundImpl(
                        FundType.CASH,
                        "Fundusz Zagraniczny " + ++numOfCashFunds,
                        ++totalNumberOfFunds
                );
            default:
                throw new UnsupportedOperationException("No matching fund has been found for given input: " + fundType.name());
        }
    }

}
