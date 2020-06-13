package com.company;

import java.io.*;
import java.util.Random;

public class Main2 {

    private static char firstLetter = '\u0430';
    private static char lastLetter = '\u044f';

    public static void main(String[] args) throws IOException {

        File russian = new File("RussianText.txt");
        File csv = new File("CSVText.txt");
        String cod = CSVAndRussian.toCSV(russian, csv);
        FileWriter fileWriter = new FileWriter("send.txt");

        String[] array = cod.split(";");

        Random random = new Random();
        int big = random.nextInt((int) Math.pow(array.length, 10));
        for (String e : array) {
            int temp = Integer.parseInt(e);
            temp += big;
            e = String.valueOf(temp);
            fileWriter.write(e + " ");
        } fileWriter.close();
    }
}
