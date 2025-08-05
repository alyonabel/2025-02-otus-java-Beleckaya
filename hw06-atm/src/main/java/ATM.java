public class ATM {
    private int balance;
    private  Cells cells;

    ATM() {
        int initBanknotesCount = 10;
        cells = new Cells();
        cells.fillFirstlyCells( initBanknotesCount );
        balance = cells.getBalance();

    }
    void showBalance() {
        System.out.println(balance);
    }

    int getBalance() {
        return balance;
    }

    void showCells() {
        System.out.println("Count of FIFTY: " + cells.showCell(Denomination.FIFTY));
        System.out.println("Count of HUNDRED: " + cells.showCell(Denomination.HUNDRED));
        System.out.println("Count of TWOHUNDRED: " + cells.showCell(Denomination.TWOHUNDRED));
        System.out.println("Count of FIVEHUNDRED: " + cells.showCell(Denomination.FIVEHUNDRED));
        System.out.println("Count of THOUSAND: " + cells.showCell(Denomination.THOUSAND));
        System.out.println("Count of TWOTHOUSAND: " + cells.showCell(Denomination.TWOTHOUSAND));
        System.out.println("Count of FIVETHOUSAND: " + cells.showCell(Denomination.FIVETHOUSAND));
    }

    void putBunch(Bunch bunch){
        balance += bunch.countSum();
        cells.putToCellsByUser(bunch);
    }

    public  boolean checkPossibility(int summa){
        if(balance<summa){System.out.println("Sorry, the ATM hasn't enough money");
            return false;}
        else return true;
    }
    public void takeMoney(int summa) {
        if (checkPossibility(summa)) {
            balance -= summa;
        }
        Bunch bunch = new Bunch();
        if (summa > 50 & (summa % 50==0))   //The requested amount must be a multiple of 50
        {
            if (summa >= Denomination.FIVETHOUSAND.getBanknote()) {
                int count = summa / Denomination.FIVETHOUSAND.getBanknote();  //the amount is divided by 5000
                summa=summa-count*Denomination.FIVETHOUSAND.getBanknote();    //the amount after deduction of 5000 notes
                for (int i = 0; i < count; i++)
                    bunch.getList().add(Denomination.FIVETHOUSAND);  //Adding banknotes "5000" to the amount for the customer
                cells.deleteBanknoteFromCells(Denomination.FIVETHOUSAND,count);  //Delete banknotes "5000" from Cells this ATM
            }  if (summa >= Denomination.TWOTHOUSAND.getBanknote()) {
            int count = summa / Denomination.TWOTHOUSAND.getBanknote();
            summa=summa-count*Denomination.TWOTHOUSAND.getBanknote();
            for (int i = 0; i < count; i++)
                bunch.getList().add(Denomination.TWOTHOUSAND);
            cells.deleteBanknoteFromCells(Denomination.TWOTHOUSAND,count);
        } if (summa >= Denomination.THOUSAND.getBanknote()) {
            int count = summa / Denomination.THOUSAND.getBanknote();
            summa=summa-count*Denomination.THOUSAND.getBanknote();
            for (int i = 0; i < count; i++)
                bunch.getList().add(Denomination.THOUSAND);
            cells.deleteBanknoteFromCells(Denomination.THOUSAND,count);
        } if (summa >= Denomination.FIVEHUNDRED.getBanknote()) {
            int count = summa / Denomination.FIVEHUNDRED.getBanknote();
            summa=summa-count*Denomination.FIVEHUNDRED.getBanknote();
            for (int i = 0; i < count; i++)
                bunch.getList().add(Denomination.FIVEHUNDRED);
            cells.deleteBanknoteFromCells(Denomination.FIVEHUNDRED,count);
        } if (summa >= Denomination.TWOHUNDRED.getBanknote()) {
            int count = summa / Denomination.TWOHUNDRED.getBanknote();
            summa=summa-count*Denomination.TWOHUNDRED.getBanknote();
            for (int i = 0; i < count; i++)
                bunch.getList().add(Denomination.TWOHUNDRED);
            cells.deleteBanknoteFromCells(Denomination.TWOHUNDRED,count);
        } if (summa >= Denomination.HUNDRED.getBanknote()) {
            int count = summa / Denomination.HUNDRED.getBanknote();
            summa=summa-count*Denomination.HUNDRED.getBanknote();
            for (int i = 0; i < count; i++)
                bunch.getList().add(Denomination.HUNDRED);
            cells.deleteBanknoteFromCells(Denomination.HUNDRED,count);
        }  if (summa >= Denomination.FIFTY.getBanknote()) {
            int count = summa / Denomination.FIFTY.getBanknote();
            for (int i = 0; i < count; i++)
                bunch.getList().add(Denomination.FIFTY);
            cells.deleteBanknoteFromCells(Denomination.FIFTY,count);
        }
            System.out.print("Take your money, please: ");
            for (int i = 0; i< bunch.getList().size(); i++) {
                System.out.print(bunch.getList().get(i) + " "); }
            System.out.print("\n");
        }
        else  {System.out.println("Enter a multiple of 50, please");}
    }
}


