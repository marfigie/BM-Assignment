package funds;

public class InvestmentFundImpl implements InvestmentFund {

    private FundType fundType;
    private String description;
    private Long fundId;

    public InvestmentFundImpl(FundType fundType, String description, Long fundId) {
        this.fundType = fundType;
        this.description = description;
        this.fundId = fundId;
    }

    public FundType getFundType() {
        return fundType;
    }

    public String getDescription() {
        return description;
    }

    public Long getFundId() {
        return fundId;
    }
}
