import java.util.ArrayList;
import java.util.HashMap;

public class LogicCalc {
    ArrayList<String> nameItems = new ArrayList<>();
    ArrayList<Float> allOfCost = new ArrayList<>();
    float allCosts = 0;



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
    float outputAllCost() {
        return this.allCosts;
    }
}
