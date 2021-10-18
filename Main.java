package com.company;

import java.util.Scanner;

public class Main {

    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        showTask();
        int number = enterNumber();
        int sum = calculateSumOfDigits(number);
        showSumOfDigits(sum);
    }

    public static void showTask() {
        System.out.println("Данная программа вычисляет сумму цифр натурального числа P.");
    }

    public static int enterNumber() {
        final int MIN_VALUE_NUMBER = 0;
        final int MAX_VALUE_NUMBER = 1000000;
        boolean isIncorrect;
        int number = 0;

        System.out.print("Введите натуральное число P: ");

        do {
            isIncorrect = false;

            try {
                number = Integer.parseInt(scan.nextLine());
            } catch (NumberFormatException e) {
                System.err.print("Введены некорректные данные!\nВведите целое натуральное число: ");
                isIncorrect = true;
            }

            if (!isIncorrect && (number < MIN_VALUE_NUMBER || number > MAX_VALUE_NUMBER)) {
                System.err.print("Ошибка ввода!\nВведите число в диапазоне от " + MIN_VALUE_NUMBER + " до " + MAX_VALUE_NUMBER + ": ");
                isIncorrect = true;
            }
        } while (isIncorrect);

        scan.close();
        return number;
    }

    public static int calculateSumOfDigits(int number) {
        int sumOfDigits = 0;
        int digit = 0;

        while (number != 0) {
            digit = number % 10;
            sumOfDigits += digit;
            number /= 10;
        }

        return sumOfDigits;
    }

    public static void showSumOfDigits(final int sum) {
        System.out.println("Сумма цифр натурального числа P: " + sum);
    }
}



