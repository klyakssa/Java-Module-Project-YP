import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        PrintStream out = null;
        Integer peopleInt;
        LogicCalc item = new LogicCalc();

        try {
            out = new PrintStream(System.out, true, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        while(true) {
            Scanner scanner = new Scanner(System.in);
            out.println("На скольких человек необходимо разделить счёт?");
            try {
                peopleInt = scanner.nextInt();
                if(peopleInt > 1){
                    break;
                }else{
                    out.println("Ошибка. Нет сысла в счете. Попробуйте заново.\n");
                }
            }catch (Exception e){
                out.println("Ошибка. Введите число.\n");
                scanner.reset();
            }
        }
        Scanner scanner = new Scanner(System.in);

        while(true) {
            out.println("Напишите название товара?");
            String nameItem = scanner.next();
            out.println("Напишите стоимость товара? Формат: 10.45, где 10 - руб, 45 - копейки");
            if (scanner.hasNextFloat()) {
                float cost = scanner.nextFloat();
                if(cost >= 0) {
                    item.addItem(nameItem, cost);
                    out.println("Товар успешно добавлен");
                    out.println("Хотите ли добавить ещё один товар? Если хотите завершить программу напишите: \"Завершить\"");
                    String exit = scanner.next();
                    if (exit.equalsIgnoreCase("Завершить")) {
                        break;
                    }
                }else{
                    out.println("Отрицательная или нулевая стоимость товара. Попробуйте снова!\n");
                }
            } else {
                out.println("Извините, но это явно не число. Попробуйте снова!\n");
            }
        }

        scanner.close();
        ArrayList<String> allItems = item.outputAllItem();
        for(int i = 0; i < allItems.size(); i++){
            out.println("Добавленные товары: " + allItems.get(i));
        }

        String rubleSuffix;

        Double allcostDouble = item.outputAllCost()/peopleInt;
        rubleSuffix = rubleRight(allcostDouble);

        String template = "\nКаждый человек должен заплатить по %.2f %s";
        out.println(String.format(template, allcostDouble, rubleSuffix));


    }

    private static boolean rightCost() {

        return true;
    }


    private static String rubleRight(Double allcost){
        allcost = Math.floor(allcost);
        String allc = String.valueOf(allcost);
        allc = allc.substring(0,allc.length()-2);
        int lastDigit = Integer.parseInt(String.valueOf(allc.charAt(allc.length()-1)));
        if (lastDigit == 0 && allc.length() == 1){
            String da =  String.valueOf(allc.charAt(allc.lastIndexOf(1)));
            String str = String.valueOf(allc.charAt(allc.lastIndexOf(0)));
            da.concat(str);
            lastDigit = Integer.parseInt(da);

        }
        String rubleSuffix;
        if (lastDigit == 1) {
            rubleSuffix = "рубль";
        }else if (allcost >= 11 && allcost <= 19) {
            rubleSuffix = "рублей";
        }else if (lastDigit >= 2 && lastDigit <= 4) {
            rubleSuffix = "рубля";
        } else {
            rubleSuffix = "рублей";
        }
        return rubleSuffix;
    }
}