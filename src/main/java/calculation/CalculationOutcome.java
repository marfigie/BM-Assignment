package calculation;

import portfolio.PortfolioPosition;
import java.math.BigDecimal;
import java.util.List;

public class CalculationOutcome {

    private List<PortfolioPosition> portfolioPositions;
    private BigDecimal remainder;

    public CalculationOutcome(List<PortfolioPosition> portfolioPositions, BigDecimal remainder) {
        this.portfolioPositions = portfolioPositions;
        this.remainder = remainder;
    }

    public List<PortfolioPosition> getPortfolioPositions() {
        return portfolioPositions;
    }

    public BigDecimal getRemainder() {
        return remainder;
    }
}
