package pl.coderslab;

import java.util.Random;

public class DiceSimulator {

    public static void main(String[] args) {
        System.out.println(simulate("D100"));
    }

    static int simulate(String s) {
        System.out.println(s);
        int diceType = 2, addOrSubtract = 2, result = 0;
        Random random = new Random();
        String caseInsensitive = s.toLowerCase();
        String[] parts = caseInsensitive.split("d");
        int howManyDices = countDices(parts[0]);
        if (parts[1].contains("+")) {
            String[] evenSmallerParts = parts[1].split("\\+");
            diceType = getDiceType(evenSmallerParts[0]);
            addOrSubtract = Integer.parseInt(evenSmallerParts[1]);
        } else if (parts[1].contains("-")) {
            String[] evenSmallerParts = parts[1].split("-");
            diceType = getDiceType(evenSmallerParts[0]);
            int temp = Integer.parseInt(evenSmallerParts[1]);
            addOrSubtract = -temp;
        } else {
            addOrSubtract = 0;
            diceType = getDiceType(parts[1]);
        }
        for (int i = 0; i < howManyDices; i++) {
            result += random.nextInt(diceType) + 1;
        }
        System.out.println("Ile kostek: "+howManyDices);
        System.out.println("Jakie kostki: "+diceType);
        System.out.println("Ile dodać/odjąć: "+addOrSubtract);
        return result + addOrSubtract;
    }

    static int countDices(String s) {
        if (s.isEmpty()) {
            return 1;
        } else {
            return Integer.parseInt(s);
        }
    }

    static int getDiceType(String s) {
        return Integer.parseInt(s);
    }

}
