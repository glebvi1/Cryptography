package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringJoiner;

public class CSVAndRussian {

    private static char firstLetter = '\u0430';
    private static char lastLetter = '\u044f';
    private static String[] myWords = {"а", "б", "в", "г", "д", "е", "ж", "з", "и", "й", "к", "л", "м", "н", "о", "п", "р", "с", "т", "у", "ф", "х", "ц", "ч", "ш", "щ", "ъ", "ы", "ь", "э", "ю", "я"};

    static String toRussian(File fileCSV, File fileRussian) throws IOException {
        FileReader fr = new FileReader(fileCSV.getName());
        FileWriter fw = new FileWriter(fileRussian.getName());
        Scanner scanner = new Scanner(fr);
        String text = "";
        String answer = "";

        while (scanner.hasNextLine()) {
            text += scanner.nextLine();
        } fr.close();
        String [] stringArray = text.split(";");

        for (int i = 0; i< stringArray.length; i++) {
            if (stringArray[i].equalsIgnoreCase("0")) {
                answer += " ";
            } else if (Integer.parseInt(stringArray[i]) >= 1 && Integer.parseInt(stringArray[i]) <= 32) {
                answer += myWords[Integer.parseInt(stringArray[i])-1];
            }
        }

        fw.write(answer);
        fw.close();
        return answer;

    }

    static String toCSV(File fileRussian, File fileCSV) throws IOException {
        FileReader firstText = new FileReader(fileRussian.getName());
        FileWriter writerFirstText = new FileWriter(fileCSV.getName());
        Scanner scanner = new Scanner(firstText);
        //int answer [] = new int[37];
        String answer = "";

        String text = "";
        while (scanner.hasNextLine()) {
            text = scanner.nextLine().toLowerCase();

            for (int i = 0; i < text.length(); i++) {

                if (text.charAt(i) >= firstLetter && text.charAt(i) <= lastLetter) {
                    answer += (text.charAt(i) - firstLetter + 1) + ";";
                } else if (text.contains(" ")) {
                    answer += "0;";
                } else if (text.contains("\n")) {
                    answer += "\n";
                }
            }
        } firstText.close();

        writerFirstText.write(answer);
        writerFirstText.close();
        return answer;
    }

    static String easyChangeWithCSV(File fileCSV, int b, int k) throws FileNotFoundException {
        FileReader csv = new FileReader(fileCSV.getName());
        Scanner scanner = new Scanner(csv);
        String text = "";
        String ans = "";

        while (scanner.hasNextLine()) {
            text += scanner.nextLine();
        }

        String [] arrayString = text.split(";");
        int [] answer = new int[arrayString.length];
        for (int i = 0; i < arrayString.length; i++) {
            answer[i] = ((Integer.parseInt(arrayString[i]) * k) + b) % 37;
            ans += answer[i];
        }
        return ans;
    }

}
