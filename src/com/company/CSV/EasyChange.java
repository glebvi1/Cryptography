package com.company.CSV;

import com.company.CSV.CSVAndRussian;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class EasyChange {

    private static int k, b;
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
/*
        FileReader csv = new FileReader("CSVText.txt");
        Scanner scanner = new Scanner(csv);
        System.out.println("Введите k: ");
        k = in.nextInt();
        System.out.println("Введите b: ");
        b = in.nextInt();

        String temp = CSVAndRussian.toCSV(new File("RussianText.txt"), new File("CSVText.txt"));
        String [] arrayString = temp.split(";");
        int [] answer = new int[arrayString.length];
        for (int i = 0; i < arrayString.length; i++) {
            answer[i] = ((Integer.parseInt(arrayString[i]) * k) + b) % 37;
        }
        for (int e : answer) {
            System.out.print(e + " ");
        }*/

        String a = CSVAndRussian.toRussian(new File("CSVText.txt"), new File("RussianText.txt"));
        System.out.println(a);
/*        String text = "1;2;3;4;5;0";
        String a[] = text.split(";");
        for (String e : a) {
            System.out.println(e);
        }*/

    }

}
