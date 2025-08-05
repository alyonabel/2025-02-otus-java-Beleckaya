import java.util.*;

class Cells {
    private  Map<Denomination, Integer> denominationMap = new HashMap<>();

    public  void putToCellsByUser(Bunch bunch) {
        for (int i = 0; i < bunch.getList().size(); i++) {
            int finalI = i;
            denominationMap.compute(bunch.getList().get(i), (key, val) -> (val == null) ? 1 : val + bunch.sumDenomination(bunch.getList().get(finalI)));
        }
    }
    public void fillFirstlyCells (int count) {
        denominationMap.put(Denomination.FIFTY, count);
        denominationMap.put(Denomination.HUNDRED, count);
        denominationMap.put(Denomination.TWOHUNDRED, count);
        denominationMap.put(Denomination.FIVEHUNDRED, count);
        denominationMap.put(Denomination.THOUSAND, count);
        denominationMap.put(Denomination.TWOTHOUSAND, count);
        denominationMap.put(Denomination.FIVETHOUSAND, count);
    }
    public int getBalance() {
        int sum = 0;
        for (Map.Entry<Denomination, Integer> entry : denominationMap.entrySet()) {
            sum += entry.getValue()*entry.getKey().getBanknote();
        }  return sum;
    }
    public int showCell(Denomination denomination){
        return denominationMap.get(denomination);
    }
    public void deleteBanknoteFromCells(Denomination d, int i) {
        denominationMap.compute(d, (key, val) -> (val == null) ? 1 : val - i);
    }

}



