package pl.coderslab;

import java.util.Scanner;

public class NumberGuessGame2 {

    public static void main(String[] args) {
        game();
    }

    static void game() {
        System.out.println("Pomyśl liczbę od 1 do 1000 a ja ją zgadnę w max 10 próbach");
        int min = 0;
        int max = 1000;
        boolean success = false;
        int guess, feedback;
        Scanner sc = new Scanner(System.in);
        while (!success) {
            guess = (min + max) / 2;
            System.out.println("Zgaduję: " + guess + "\nWpisz z konsoli "
                    + "\"1\" jeśli liczba jest za duża, "
                    + "\"2\" jeśli za mała lub "
                    + "\"3\"jeśli zgadłem");
            feedback = getInput(sc);
            if (feedback == 1) {
                max = guess;
            } else if (feedback==2) {
                min = guess;
            } else {
                success=true;
            }
        }
        System.out.println("Wygrałem!");
    }

    static int getInput(Scanner sc) {
        int value = -99;
        while (value==-99) {
            value = validateInput(sc.nextLine());
            if (value != -99) {
                return value;
            } else {
                System.out.println("Podaj cyfrę z zakresu 1-3 zgodnie z instrukcją powyżej");
            }
        }
        return value;
    }

    static int validateInput(String s) {
        int input;
        try {
            input = Integer.parseInt(s);
            if (input > 0 && input < 4) {
                return input;
            }
        } catch (NumberFormatException e) {

        }
        return -99;
    }

}
