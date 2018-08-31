package funds;

public enum FundType {
    DOMESTIC("Polski"),
    FOREIGN("Zagraniczny"),
    CASH("Pieniężne");


    private final String label;

    FundType(String label){
        this.label=label;
    }

    public String getLabel(){
        return this.label;
    }
}
