public enum Denomination {

    FIFTY(50),
    HUNDRED(100),
    TWOHUNDRED(200),
    FIVEHUNDRED(500),
    THOUSAND(1000),
    TWOTHOUSAND(2000),
    FIVETHOUSAND(5000);

    private final int banknote;

    Denomination(int banknote) {
        this.banknote = banknote;
    }

    public int getBanknote() {
        return banknote;
    }
}
