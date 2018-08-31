package calculation;

import funds.FundType;
import funds.InvestmentFund;
import portfolio.PortfolioPosition;
import strategies.InvestmentStrategy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PortfolioFundsSplitter {

    public CalculationOutcome splitFundsIntoPortfolioPositions(InvestmentStrategy investmentStrategy, List<InvestmentFund> investmentFundList, BigDecimal totalCash) {
        ArrayList<PortfolioPosition> portfolioPositions = new ArrayList<PortfolioPosition>();
        BigDecimal remainder = calculateRemainder(investmentStrategy, totalCash);

        for (FundType type: FundType.values()) {
            portfolioPositions.addAll(calculateSubFundPercentage(investmentFundList, investmentStrategy, type, totalCash));
        }

        return new CalculationOutcome(portfolioPositions, remainder);
    }

    private BigDecimal calculateRemainder(InvestmentStrategy investmentStrategy, BigDecimal totalCash) {
        BigDecimal domesticAmount = totalCash.multiply(investmentStrategy.getDomesticPercentage()).setScale(0, BigDecimal.ROUND_FLOOR);
        BigDecimal foreginAmount = totalCash.multiply(investmentStrategy.getForeginPercentage()).setScale(0, BigDecimal.ROUND_FLOOR);
        BigDecimal cashAmount = totalCash.multiply(investmentStrategy.getCashPercentage()).setScale(0, BigDecimal.ROUND_FLOOR);
        return totalCash.subtract(domesticAmount).subtract(foreginAmount).subtract(cashAmount);
    }

    private List<PortfolioPosition> calculateSubFundPercentage(List<InvestmentFund> investmentFundList, InvestmentStrategy strategy, FundType fundType, BigDecimal totalCash) {
        List<PortfolioPosition> portfolioPositions = new ArrayList<>();
        BigDecimal totalPercentageOfGivenType = strategy.getPercentageOfGivenType(fundType);

        List<InvestmentFund> fundOfGivenType = getFundOfType(investmentFundList, fundType);
        for (int i = fundOfGivenType.size(); i > 0; i--) {
            BigDecimal percentage = getNextPercentage(totalPercentageOfGivenType, i);
            totalPercentageOfGivenType = totalPercentageOfGivenType.subtract(percentage);
            InvestmentFund fund = fundOfGivenType.get(fundOfGivenType.size() - i); // Get funds in actual order - from first to last
            PortfolioPosition portfolioPosition = new PortfolioPosition(fund, totalCash.multiply(percentage), percentage);
            portfolioPositions.add(portfolioPosition);
        }
        return portfolioPositions;
    }

    private BigDecimal getNextPercentage(BigDecimal currentPercentage, int numberOfRemainingFunds) {
        return currentPercentage.divide(new BigDecimal(numberOfRemainingFunds), 4, BigDecimal.ROUND_CEILING);
    }

    private List<InvestmentFund> getFundOfType(List<InvestmentFund> investmentFundList, FundType type) {
        return investmentFundList.stream().filter(i -> i.getFundType().equals(type)).collect(Collectors.toList());
    }

}
