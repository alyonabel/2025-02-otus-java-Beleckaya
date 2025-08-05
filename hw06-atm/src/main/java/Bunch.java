import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Bunch {
    private List<Denomination> list;

    Bunch(Denomination... d){
        list = new ArrayList<>(Arrays.asList(d));
    }

    public List<Denomination> getList() {
        return list;
    }

    public int sumDenomination(Denomination denomination){
        int count=0;
        count= Collections.frequency(list,denomination);
        return count;
    }
    public int countSum(){
        int sum=0;
        for (Denomination denomination : list) {
            sum += denomination.getBanknote();
        }
        return sum;
    }
}
