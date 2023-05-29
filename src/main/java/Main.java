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
        int peopleInt = scanner.nextInt();
        if(peopleInt <= 1){
            System.out.println("Ошибка. Нет сысла в счете.");
            return;
        }
        LogicCalc item = new LogicCalc();
        while(true) {
            out.println("Напишите название товара?");
            String nameItem = scanner.next();
            out.println("Напишите стоимость товара? Формат: 10.45, где 10 - руб, 45 - копейки");
            float cost = scanner.nextFloat();
            item.addItem(nameItem,cost);
            out.println("Товар успешно добавлен");
            out.println("Хотите ли добавить ещё один товар?");
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

        int lastDigit = (int) (item.outputAllCost()/peopleInt * 10) % 10;
        int lastTwoDigits = (int) (item.outputAllCost()/peopleInt * 100) % 100;

        if (lastTwoDigits >= 11 && lastTwoDigits <= 19) {
            rubleSuffix = "рублей";
        } else if (lastDigit == 1) {
            rubleSuffix = "рубль";
        } else if (lastDigit >= 2 && lastDigit <= 4) {
            rubleSuffix = "рубля";
        } else {
            rubleSuffix = "рублей";
        }


        String template = "\nКаждый человек должен заплатить по %.2f %s";
        out.println(String.format(template, item.outputAllCost()/peopleInt, rubleSuffix));
    }
}