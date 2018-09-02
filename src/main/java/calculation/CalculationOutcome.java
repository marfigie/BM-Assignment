package calculation;

import portfolio.PortfolioPosition;
import java.math.BigInteger;
import java.util.List;

public class CalculationOutcome {

    private List<PortfolioPosition> portfolioPositions;
    private BigInteger remainder;

    public CalculationOutcome(List<PortfolioPosition> portfolioPositions, BigInteger remainder) {
        this.portfolioPositions = portfolioPositions;
        this.remainder = remainder;
    }

    public List<PortfolioPosition> getPortfolioPositions() {
        return portfolioPositions;
    }

    public BigInteger getRemainder() {
        return remainder;
    }
}
