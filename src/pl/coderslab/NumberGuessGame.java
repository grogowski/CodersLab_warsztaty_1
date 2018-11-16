package pl.coderslab;

import java.util.Random;
import java.util.Scanner;

public class NumberGuessGame {

    public static void main(String[] args) {
        game();
    }

    static void game() {
        Random random = new Random();
        int numberToGuess = random.nextInt(100) + 1;
        int guessedNumber = -99;
        while (numberToGuess != guessedNumber) {
            System.out.println("Zgadnij liczbę");
            Scanner sc = new Scanner(System.in);
            guessedNumber = numValidation(sc.nextLine());
            if (guessedNumber != -99) {
                if (guessedNumber>numberToGuess) {
                    System.out.println("Za dużo!");
                } else if (guessedNumber<numberToGuess) {
                    System.out.println("Za mało!");
                }
            } else {
                System.out.println("To nie jest liczba");
            }
        }
        System.out.println("Zgadłeś!");
    }

    static int numValidation(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return -99;
        }
    }
}
