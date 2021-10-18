package com.company;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;

public class Main {

    static final Scanner sc = new Scanner(System.in);
    static final int MAX_VALUE_NUMBER = 10000000;
    static final int MIN_VALUE_NUMBER = 1;
    static final int MIN_VALUE_SIZE = 1;
    static final int MAX_VALUE_SIZE = 30;
    static final int FILE = 1;
    static final int CONSOLE = 2;

    public static void showTask() {
        System.out.println("Данная программа считывает массив А, состоящий из n-натуральных чисел,\n" +
                "выбирает элементы, которые повторяются и выводит их в другой файл.\n" +
                "Размер массива должен принадлежать диапазону от " + MIN_VALUE_SIZE + " до " + MAX_VALUE_SIZE + "\n" +
                "Числа массива должны принадлежать диапазону от " + MIN_VALUE_NUMBER + " до " + MAX_VALUE_NUMBER);
    }

    public static String checkFileExtension(File file) {
        String fileName = file.getName();

        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        } else {
            return "";
        }
    }

    public static String enterPathToFile() {
        boolean isIncorrect;
        String path;

        do {
            isIncorrect = false;
            path = sc.nextLine();
            File file = new File(path);

            if (!file.exists()) {
                System.out.print("Ошибка! Файл не найден.\nВведите корректную ссылку на существующий файл: ");
                isIncorrect = true;
            }

            if (!isIncorrect && !checkFileExtension(file).equals("txt")) {
                System.out.print("Ошибка! Файл не является текстовым!\nВведите корректную ссылку на текстовый файл: ");
                isIncorrect = true;
            }
        } while (isIncorrect);

        return path;
    }

    public static int inputNumberFromConsole(final int min, final int max) {
        boolean isIncorrect;
        int number = 0;

        do {
            isIncorrect = false;

            try {
                number = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("""

                        Ошибка при ввода числа с консоли!
                        Введите целое натуральное число:\s""");
                isIncorrect = true;
            }

            if (!isIncorrect && (number > max || number < min)) {
                System.out.print("\nОшибка при ввода числа с консоли!" +
                        "\nВведите целое число в диапазоне от " + min + " до " + max + ": ");
                isIncorrect = true;
            }
        } while (isIncorrect);

        return number;
    }

    public static int readSizeOfArrayFromFile(final String path) {
        int size = 0;

        try (Scanner readFile = new Scanner(new File(path))) {
            try {
                size = readFile.nextInt();
            } catch (Exception e) {
                System.out.println("\nОшибка при чтении размера массива из файла!\n" +
                        "Значение размера массива не является целым натуральным числом!");
                System.out.print("Введите размер массива с консоли: ");
                size = inputNumberFromConsole(MIN_VALUE_SIZE, MAX_VALUE_SIZE);
            }

            if (size < MIN_VALUE_SIZE || size > MAX_VALUE_SIZE) {
                System.out.println("\nОшибка при чтении размера массива из файла!" +
                        "\nРазмер массива не входит в диапазон от " + MIN_VALUE_SIZE + " до " + MAX_VALUE_SIZE + "!");
                System.out.print("Введите размер массива в диапазоне от " + MIN_VALUE_SIZE +
                        " до " + MAX_VALUE_SIZE + " с консоли: ");
                size = inputNumberFromConsole(MIN_VALUE_SIZE, MAX_VALUE_SIZE);
            }
        } catch (IOException e) {
            System.out.println("Ошибка при считывания из файла!");
        }

        return size;
    }

    public static int[] readArrayFromFile(int size, final String path) {
        boolean isIncorrect;
        int[] arrayOfElements;
        arrayOfElements = new int[size];
        isIncorrect = false;

        try (Scanner readFile = new Scanner(new File(path))) {
            readFile.nextLine();

            for (int i = 0; i < size; i++) {
                try {
                    arrayOfElements[i] = readFile.nextInt();
                } catch (Exception e) {
                    System.out.println("\nОшибка при чтении массива из файла! Некорректные значения в файле!");
                    System.out.println("Введите новый массив с консоли!");
                    size = readSizeOfArrayFromConsole();
                    arrayOfElements = createArrayOfElements(size);
                    isIncorrect = true;
                    i = size;
                }

                if (!isIncorrect && (arrayOfElements[i] < MIN_VALUE_NUMBER || arrayOfElements[i] > MAX_VALUE_NUMBER)) {
                    System.out.println("\nОшибка при чтении массива из файла! Значения массива выходят из указанного диапазона!");
                    System.out.print("Введите массив с консоли: ");
                    size = readSizeOfArrayFromConsole();
                    arrayOfElements = createArrayOfElements(size);
                    i = size;
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка при считывания из файла!");
        }

        return arrayOfElements;
    }

    public static int readSizeOfArrayFromConsole() {
        System.out.print("Введите размер массива: ");

        return inputNumberFromConsole(MIN_VALUE_SIZE, MAX_VALUE_SIZE);
    }

    public static int[] createArrayOfElements(final int size) {
        int[] array = new int[size];

        for (int i = 0; i < array.length; i++) {
            System.out.print("Введите " + (i + 1) + "-е число массива: ");
            array[i] = inputNumberFromConsole(MIN_VALUE_NUMBER, MAX_VALUE_NUMBER);
        }

        return array;
    }


    public static int findAmountOfIdenticalElements(final int[] array) {
        int amount = 0;

        for (int i = 0; i < array.length; i++) {

            for (int j = 0; j < array.length; j++) {

                if (array[i] == array[j] && i != j) {
                    amount++;
                    j = array.length;
                }
            }
        }
        return amount;
    }

    public static int[] createArrayOfIdenticalElements(final int amount, final int[] arrayOfElements) {
        int position = 0;
        int[] emptyArray = new int[amount];

        if (amount != 0) {
            for (int i = 0; i < arrayOfElements.length; i++) {

                for (int j = 0; j < arrayOfElements.length; j++) {

                    if (arrayOfElements[i] == arrayOfElements[j] && i != j) {
                        emptyArray[position] = arrayOfElements[i];
                        j = arrayOfElements.length;
                        position++;
                    }
                }
            }
        }

        return emptyArray;
    }

    public static void outputToFile(final int[] array, String path) {
        boolean isIncorrect;

        do {
            isIncorrect = false;
            File file = new File(path);

            try (FileWriter writer = new FileWriter(file)) {
                if (file.createNewFile()) {
                    System.out.println("Файл успешно создан!");
                }

                if (array.length == 0) {
                    writer.write("В введенном массиве нету повторяющихся элементов.");
                    System.out.println("\nРезультат успешно записан в файл!");
                } else {
                    for (int j : array) {
                        writer.write(String.valueOf(j));
                        writer.write("\t");
                    }

                    System.out.println("\nМассив с элементами, встречающимися более одного раза, успешно записан в файл!");
                }
            } catch (IOException e ) {
                System.out.println("Ошибка! Отказано в доступе!\nИзмените параметры файла или введите ссылку другого файла!");
                System.out.print("Введите ссылку на файл, в который вам нужно записать файл: ");
                path = enterPathToFile();
                isIncorrect = true;
            }
        } while (isIncorrect);

    }

    public static void outputToConsole(final  int[] array) {
        if (array.length == 0) {
            System.out.println("В введенном массиве нету повторяющихся элементов.");
        } else {
            for (int i : array) {
                System.out.print(i + "\t");
            }
        }
    }

    public static int readIOMethod() {
        boolean isIncorrect;
        int choice = 0;

        System.out.println("\nФайл: 1\nКонсоль: 2 ");
        System.out.print("Выберите способ: ");

        do {
            isIncorrect = false;

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("\nОшибка! Введенно некорректное значение!\nПовторите ввод: ");
                isIncorrect = true;
            }

            if (!isIncorrect && choice != FILE && choice != CONSOLE) {
                System.out.print("\nОшибка! Введите 1 или 2: ");
                isIncorrect = true;
            }
        } while (isIncorrect);

        return choice;
    }

    public static int[] processInputMethod(final int choice) {
        int[] arrayOfElements = new int[0];
        String path;
        int size;

        if (choice == 1) {
            System.out.println("\nВВОД МАССИВА ЧЕРЕЗ ФАЙЛ");
            System.out.print("Введите ссылку на файл, который содержит массив из n-натуральных чисел: ");
            path = enterPathToFile();
            size = readSizeOfArrayFromFile(path);
            arrayOfElements = readArrayFromFile(size, path);
        }

        if (choice == 2) {
            System.out.println("\nВВОД МАССИВА ЧЕРЕЗ КОНСОЛЬ");
            size = readSizeOfArrayFromConsole();
            arrayOfElements = createArrayOfElements(size);
        }

        return arrayOfElements;
    }

    public static void processOutputMethod(final int choice, final int[] arrayOfIdenticalElements) {
        String path;

        if (choice == 1) {
            System.out.println("\nВЫВОД РЕЗУЛЬТАТА В ФАЙЛ");
            System.out.print("Введите ссылку на уже существующий файл или ссылку,\n" +
                    "куда желаете создать файл, в который вам нужно записать результат: ");
            path = enterPathToFile();
            outputToFile(arrayOfIdenticalElements, path);
        }

        if (choice == 2) {
            System.out.println("\nВЫВОД РЕЗУЛЬТАТА В КОНСОЛЬ");
            outputToConsole(arrayOfIdenticalElements);
        }
    }

    public static void main(String[] args) {
        showTask();
        System.out.println("Ввод массива:");
        int choice = readIOMethod();
        int[] arrayOfElements = processInputMethod(choice);
        int size = findAmountOfIdenticalElements(arrayOfElements);
        int[] arrayOfIdenticalElements = createArrayOfIdenticalElements(size, arrayOfElements);
        System.out.println("Вывод результата:");
        choice = readIOMethod();
        processOutputMethod(choice, arrayOfIdenticalElements);
        sc.close();
    }
}
