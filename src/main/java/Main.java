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

        while(true) {
            Scanner scanner = new Scanner(System.in);
            String nameItem = "";
            out.println("Напишите название товара?");
            nameItem = scanner.next();
            scanner.reset();
            out.println("Напишите стоимость товара? Формат: 10.45, где 10 - руб, 45 - копейки");
            if (scanner.hasNextFloat()) {
                float cost = scanner.nextFloat();
                if(cost > 0) {
                    item.addItem(nameItem, cost);
                    out.println("Товар успешно добавлен");
                    out.println("Хотите ли добавить ещё один товар? Если хотите завершить программу напишите: \"Завершить\"");
                    String exit = scanner.next();
                    if (exit.equalsIgnoreCase("Завершить")) {
                        break;
                    }
                }else{
                    out.println("Ошибка. Отрицательная или нулевая стоимость товара. Попробуйте снова!\n");
                }
            } else {
                out.println("Извините, но это явно не число. Попробуйте снова!\n");
            }
        }


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
        //String allc = String.valueOf(allcost);
        String template = "%.0f";
        String allc = String.format(template, allcost);
        int lastDigit;
        if(allc.length() == 1){
            lastDigit = Integer.parseInt(allc);
            if(lastDigit == 0){
                return "рублей";
            }else if(lastDigit == 1){
                return "рубль";
            }else if(lastDigit > 1 && lastDigit < 5){
                return "рубля";
            }else{
                return "рублей";
            }
        }else if (allc.length() > 1){
            lastDigit = Integer.parseInt(allc.substring(allc.length() - 2));
            if(lastDigit < 21){
                return "рублей";
            }else{
                lastDigit = lastDigit % 10;
                if(lastDigit == 0){
                    return "рублей";
                }else if(lastDigit == 1){
                    return "рубль";
                }else if(lastDigit > 1 && lastDigit < 5){
                    return "рубля";
                }else{
                    return "рублей";
                }
            }
        }
        return "";
    }
}