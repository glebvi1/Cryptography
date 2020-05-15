package com.company;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Scanner;
import java.util.SortedMap;

public class Main {



    public static void main(String[] args) throws IOException {

        FileWriter output = new FileWriter("output.txt");
        FileReader input = new FileReader("input.txt");
        Scanner scanner = new Scanner(input);
        String text = "";
        int countSymbol [] = new int[32];

        Arrays.fill(countSymbol, 0);
        char firstLetter = '\u0430';
        char lastLetter = '\u044f';

        while (scanner.hasNextLine()) {
            text = scanner.nextLine().toLowerCase();
            for (int i=0; i<text.length(); i++) {

                if (text.charAt(i) >= firstLetter && text.charAt(i) <= lastLetter) {
                    countSymbol[lastLetter - text.charAt(i)] = 1 + countSymbol[lastLetter-text.charAt(i)];
                }
            }
        } input.close();

        FileReader input2 = new FileReader("input2.txt");
        Scanner scanner2 = new Scanner(input2);
        while (scanner2.hasNextLine()) {
            text = scanner2.nextLine().toLowerCase();
            for (int i=0; i<text.length(); i++) {
                if (text.charAt(i) >= firstLetter && text.charAt(i) <= lastLetter) {
                    countSymbol[lastLetter - text.charAt(i)] = 1 + countSymbol[lastLetter-text.charAt(i)];
                }
            }
        } input2.close();


        output.write("1 том Война и мир. Лев Толстой" + "\n" + "Весь Архипелаг ГУЛаг. Солженицын Александр" + "\n");
        for (int i = 0; i<countSymbol.length; i++) {
            output.write(Character.toString(firstLetter + i) + ": " + countSymbol[32-i-1] + "\n");
        } output.close();

    }
}
