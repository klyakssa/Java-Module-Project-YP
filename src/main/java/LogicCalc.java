import java.util.ArrayList;
import java.util.HashMap;

public class LogicCalc {
    ArrayList<String> nameItems = new ArrayList<>();
    ArrayList<Float> allOfCost = new ArrayList<>();
    Double allCosts = 0.0;



    void addItem(String nameItem, float account) {
        nameItems.add(nameItem);
        allOfCost.add(account);
        allCosts += account;
    }

    //
    ArrayList<String> outputAllItem() {
        return nameItems;
    }

    //
    Double outputAllCost() {
        return this.allCosts;
    }
}
