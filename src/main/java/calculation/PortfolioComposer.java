package calculation;

import funds.FundType;
import funds.InvestmentFund;
import portfolio.PortfolioPosition;
import portfolio.PortfolioRequirements;
import strategies.InvestmentStrategy;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class PortfolioComposer {

        public CalculationOutcome calculatePositionsInPortfolio(PortfolioRequirements requirements){
            List<PortfolioPosition> portfolioPositions = new ArrayList<>();

            BigDecimal totalCash = new BigDecimal(requirements.getTotalCash());
            InvestmentStrategy strategy = requirements.getInvestmentStrategy();

            BigInteger domesticAmount = totalCash.multiply(strategy.getDomesticPercentage()).toBigInteger();
            BigInteger foreignAmount = totalCash.multiply(strategy.getForeignPercentage()).toBigInteger();
            BigInteger cashAmount = totalCash.multiply(strategy.getCashPercentage()).toBigInteger();
            BigInteger totalInvestedCash = domesticAmount.add(foreignAmount).add(cashAmount);

            BigInteger remainder = totalCash.toBigInteger().subtract(totalInvestedCash);

            portfolioPositions.addAll(calcAmountForEachFund(domesticAmount, requirements.getFundsOfType(FundType.DOMESTIC)));
            portfolioPositions.addAll(calcAmountForEachFund(foreignAmount, requirements.getFundsOfType(FundType.FOREIGN)));
            portfolioPositions.addAll(calcAmountForEachFund(cashAmount, requirements.getFundsOfType(FundType.CASH)));

            calculatePercentagesForEachPortfolioPosition(portfolioPositions,totalInvestedCash);

            return new CalculationOutcome(portfolioPositions,remainder);
        }

        private List<PortfolioPosition> calcAmountForEachFund(BigInteger amountForThisType, List<InvestmentFund> fundsOfGivenType){
            List<PortfolioPosition> portfolioPositions = new ArrayList<>();
            BigInteger fundsCount = BigInteger.valueOf(fundsOfGivenType.size());

            BigInteger amoutPerFund = amountForThisType.divide(fundsCount);
            BigInteger remainder = amountForThisType.remainder(fundsCount);

            for(InvestmentFund fund: fundsOfGivenType) {
                PortfolioPosition portfolioPosition = new PortfolioPosition(fund,amoutPerFund);
                portfolioPositions.add(portfolioPosition);
            }

            portfolioPositions.get(0).setCashAmount(amoutPerFund.add(remainder));

            return portfolioPositions;
        }

        private void calculatePercentagesForEachPortfolioPosition(List<PortfolioPosition> portfolioPositions, BigInteger totalAssignedAmount) {
            BigDecimal totalAmount = new BigDecimal(totalAssignedAmount);
            for(PortfolioPosition position: portfolioPositions){
                position.setPercentage(new BigDecimal(position.getCashAmount()).divide(totalAmount,4,BigDecimal.ROUND_HALF_EVEN));
            }
        }
}
