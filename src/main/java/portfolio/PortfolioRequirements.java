package portfolio;

import exceptions.validation.PortfolioValidationException;
import funds.FundType;
import funds.InvestmentFund;
import strategies.InvestmentStrategy;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

public class PortfolioRequirements {
    private InvestmentStrategy investmentStrategy;
    private List<InvestmentFund> fundList;
    private BigInteger totalCash;

    public PortfolioRequirements(InvestmentStrategy investmentStrategy, List<InvestmentFund> fundList,
                                 BigInteger totalCash) throws PortfolioValidationException {

        this.investmentStrategy = investmentStrategy;
        this.fundList = fundList;
        this.totalCash = totalCash;
        validateCashAmount();
        validateFundsInput();
    }

    public List<InvestmentFund> getFundsOfType(FundType type) {
        return fundList.stream().filter(i -> i.getFundType().equals(type)).collect(Collectors.toList());
    }

    public InvestmentStrategy getInvestmentStrategy() {
        return investmentStrategy;
    }

    public List<InvestmentFund> getFundList() {
        return fundList;
    }

    public BigInteger getTotalCash() {
        return totalCash;
    }

    private void validateFundsInput() throws PortfolioValidationException {
        for(FundType type: FundType.values()) {
            List<InvestmentFund> fundOfType = getFundsOfType(type);
            if(fundOfType.size()<1){
                throw new PortfolioValidationException("No Funds of "+type.name()+" type provided - unable to allocate cash");
            }
        }
    }

    private void validateCashAmount() throws PortfolioValidationException{
        if(totalCash.compareTo(BigInteger.ONE)<1){
            throw new PortfolioValidationException("Provided cash amount is equal to or less than zero");
        }
    }
}
