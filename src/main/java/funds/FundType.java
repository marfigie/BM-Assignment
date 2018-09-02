package funds;

public enum FundType {
    DOMESTIC("Polski"),
    FOREIGN("Zagraniczny"),
    CASH("Pieniężny");

    private final String label;

    FundType(String label){
        this.label=label;
    }

    public String getLabel(){
        return this.label;
    }
}
