package ru.job4j.coffeemachine;

public class Givechange {

    int value;

    int price;

    Givechange(int value, int price) {
        this.value = value;
        this.price = price;
    }

    public static void main(String[] args) {

        Givechange givechange = new Givechange(4800, 35);

        int[] change = givechange.change(4800, 35);

        int[] vaveChange = givechange.vaveChange(4800, 35);

        int a = 0;

    }

    int[] change(int value, int price) {

        int returnSum = 0;
        int[] returnChange = new int[100];
        int returnChangeIndex = 0;
        int[] change = {1000, 500, 100, 50, 10, 5, 1};

        if (value < price) {
            System.out.println("Not enough value");
            returnSum = value;
        } else {

            returnSum = value - price;
        }

        for (int index = 0; index < change.length; index++) {
            int nominal = change[index];
            int reminder = returnSum % change[index];
            if (reminder == returnSum) {
                continue;
            } else {
                int localCharge = returnSum - reminder;
                int numberOfnominals = localCharge / nominal;
                for (int k = 0; k < numberOfnominals; k++) {
                    returnChange[returnChangeIndex] = nominal;
                    returnChangeIndex = returnChangeIndex + 1;
                }
                returnSum = returnSum - localCharge;
            }
        }

        int quontityOfElements = 0;

        for (int i = 0; i < returnChange.length; i++) {
            if (returnChange[i] != 0) {
                quontityOfElements = quontityOfElements + 1;
            }
        }

        int[] result = new int[quontityOfElements];
        int k = 0;

        for (int i = 0; i < quontityOfElements; i++) {
            if (returnChange[i] != 0) {
                result[k] = returnChange[i];
                k = k + 1;
            }
        }
        return result;
    }

    int[] vaveChange(int value, int price) {

        int[] returnChange = new int[100];
        int indChange = 0;

        if (value < price) {
            System.out.println("Not enough value");
        }
        else {
            value = value - price;
        }

        int[] change = {1000, 500, 100, 50, 10, 5, 1};

        int indNominal = 0;

        while (value > 0) {
                int nominal = change[indNominal];
                if (value - nominal >= 0) {
                    value = value - nominal;
                    returnChange[indChange] = nominal;
                    indChange++;
                } else {
                    indNominal++;
                }
        }

        return returnChange;
    }
}


