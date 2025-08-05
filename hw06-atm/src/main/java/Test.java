
public class Test {
    public static void main(String[] args) {
        Bunch b = new Bunch(Denomination.FIFTY,Denomination.FIVETHOUSAND);
        Bunch b2 = new Bunch(Denomination.TWOTHOUSAND,Denomination.TWOHUNDRED);
        Bunch b3 = new Bunch(Denomination.FIFTY,Denomination.HUNDRED);
        ATM atm = new ATM();

        System.out.println("Balance ATM:");
        atm.showBalance();
        System.out.println("Cells ATM:");
        atm.showCells();

        atm.putBunch(b);
        atm.putBunch(b2);
        atm.putBunch(b3);

        System.out.println("Balance ATM after depositing three bunches of money (95900-88500 = 7400 = 50+5000+2000+200+50+100) :");
        atm.showBalance();
        System.out.println("Cells ATM after depositing three bunches of money (FIFTY +2, HUNDRED +1, TWOHUNDRED +1, TWOTHOUSAND +1, FIVETHOUSAND+1) :");
        atm.showCells();


        atm.takeMoney(550);
        atm.takeMoney(2400);

        System.out.println("Cells ATM after withdrawal of two amounts :");
        atm.showCells();


        ATM atm1 = new ATM();
        ATM atm2 = new ATM();

        System.out.println("Balance ATM1 before take money:");
        atm1.showBalance();
        System.out.println("Cells ATM1 before take money:");
        atm1.showCells();

        System.out.println("Balance ATM2 :");
        atm2.showBalance();
        System.out.println("Cells ATM2 :");
        atm2.showCells();

        atm1.takeMoney(10000);

        System.out.println("Balance ATM1 after take money :");
        atm1.showBalance();
        System.out.println("Cells ATM1 after take money:");
        atm1.showCells();
        System.out.println("Balance ATM2 :");
        atm2.showBalance();
        System.out.println("Cells ATM2 :");
        atm2.showCells();

    }
}
