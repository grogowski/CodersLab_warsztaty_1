package pl.coderslab;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class LotterySimulator {

    public static final int RANGE_LOW=1;
    public static final int RANGE_HIGH=49;

    public static void main(String[] args) {
        simulator();
    }

    static void simulator() {
        int[] userNumbers = new int[6];
        Scanner sc = new Scanner(System.in);
        for (int i =0; i<userNumbers.length;i++) {
            userNumbers[i] = getNumberFromConsole(sc, userNumbers);
        }
        Arrays.sort(userNumbers);
        System.out.println("Podałeś następujące liczby:");
        System.out.println(Arrays.toString(userNumbers));
        int[] computerNumbers = new int[6];
        Random random = new Random();
        for (int i =0; i<computerNumbers.length;i++) {
            int nextNumber = -99;
            while (nextNumber == -99) {
                nextNumber = validateInt(random.nextInt(49) + 1, computerNumbers);
            }
            computerNumbers[i] = nextNumber;
        }
        System.out.println("Komputer wylosował następujące liczby");
        Arrays.sort(computerNumbers);
        System.out.println(Arrays.toString(computerNumbers));
        int counter = 0;
        for (int i = 0; i<userNumbers.length;i++) {
            if (arrayContainsNumbers(userNumbers[i],computerNumbers)) {
                counter++;
            }
        }
        switch (counter) {
            case 3:
                System.out.println("Trafiłeś trójkę");
                break;
            case 4:
                System.out.println("Trafiłeś czwórkę");
                break;
            case 5:
                System.out.println("Trafiłeś piątkę");
                break;
            case 6:
                System.out.println("Trafiłeś szóstkę");
                break;
                default:
                    System.out.println("Niestety przegrana");
        }


    }

    static int getNumberFromConsole (Scanner sc, int[] array) {
        int number = -99;
        while (number==-99) {
            System.out.println("Podaj liczbę");
            number = validateString(sc.nextLine(), array);
            if (number==-99) {
                System.out.println("Podany ciąg znaków nie spełnia warunków programu. "
                        +"Należy podać liczbę całkowitą z zakresu "+RANGE_LOW
                        +"-"+RANGE_HIGH+".\nLiczby nie mogą się powtarzać.");
            }
        }
        return number;
    }

    static int validateString (String s, int [] array) {
        try {
            return validateInt(Integer.parseInt(s), array);
        } catch (NumberFormatException e) {

        }
        return -99;
    }

    static int validateInt (int number, int[] array) {
        if (isWithinRange(number)&&!arrayContainsNumbers(number, array)) {
            return number;
        }
        return -99;
    }

    static boolean isWithinRange (int number) {
        if (number>=RANGE_LOW && number<=RANGE_HIGH) {
            return true;
        }
        return false;
    }

    static boolean arrayContainsNumbers (int number, int[] array){
        for (int i = 0; i < array.length; i++) {
            if (array[i]==number) {
                return true;
            }
        }
        return false;
    }
}
