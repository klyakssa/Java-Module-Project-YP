import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        PrintStream out = null;
        Scanner scanner = new Scanner(System.in);
        try {
            out = new PrintStream(System.out, true, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        out.println("На скольких человек необходимо разделить счёт?");
        String peopleInt = scanner.next();
        if(!isNumeric(peopleInt)){
            out.println("Ошибка. Нужно было ввести цифру. Перезапустите программу и попробуйте снова!");
            return;
        }

        Double people = Double.parseDouble(peopleInt);
        if(people <= 1){
            out.println("Ошибка. Нет сысла в счете.");
            return;
        }

        LogicCalc item = new LogicCalc();

        while(true) {
            out.println("Напишите название товара?");
            String nameItem = scanner.next();
            out.println("Напишите стоимость товара? Формат: 10.45, где 10 - руб, 45 - копейки");
            float cost = scanner.nextFloat();
            item.addItem(nameItem,Math.abs(cost));
            out.println("Товар успешно добавлен");
            out.println("Хотите ли добавить ещё один товар? Если хотите завершить программу напишите: \"Завершить\"");
            String exit = scanner.next();
            if (exit.equalsIgnoreCase("Завершить")){
                break;
            }
        }

        scanner.close();
        ArrayList<String> allItems = item.outputAllItem();
        for(int i = 0; i < allItems.size(); i++){
            out.println("Добавленные товары: " + allItems.get(i));
        }

        String rubleSuffix;

        Double allcost = item.outputAllCost()/people;
        rubleSuffix = rubleRight(allcost);

        String template = "\nКаждый человек должен заплатить по %.2f %s";
        out.println(String.format(template, allcost, rubleSuffix));


    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String rubleRight(Double allcost){
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
        }else if (lastDigit >= 11 && lastDigit <= 19) {
            rubleSuffix = "рублей";
        }else if (lastDigit >= 2 && lastDigit <= 4) {
            rubleSuffix = "рубля";
        } else {
            rubleSuffix = "рублей";
        }
        return rubleSuffix;
    }
}