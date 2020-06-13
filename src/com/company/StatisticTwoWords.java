package com.company;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class StatisticTwoWords {

    static Scanner in = new Scanner(System.in);
    static Map<String, Integer> statistic = new HashMap<>();
    private static char firstLetter = '\u0430';
    private static char lastLetter = '\u044f';

    public static Map<String, Integer> statistic() {

        FileReader fr = null;
        try {
            fr = new FileReader("input.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        FileReader fr1 = null;
        try {
            fr1 = new FileReader("input2.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(fr);
        Scanner scanner1 = new Scanner(fr1);
        StringBuilder text = new StringBuilder();

        while (scanner.hasNextLine()) {
            text.append(scanner.nextLine().toLowerCase());
            for (int i = 1; i < text.length(); i++) {
                String words = text.substring(i-1, i+1);
                boolean wasSymbol = statistic.containsKey(words);

                if (text.charAt(i-1) >= firstLetter && text.charAt(i-1) <= lastLetter
                        && text.charAt(i) >= firstLetter && text.charAt(i) <= lastLetter
                || words.contains(" ")) {
                    if (wasSymbol) {
                        int count = statistic.get(words);
                        count++;
                        statistic.replace(words, count);
                    } else {
                        statistic.put(words, 1);
                    }
                }
            }

            text = new StringBuilder();
        }
        try {
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (scanner1.hasNextLine()) {
            text.append(scanner1.nextLine().toLowerCase());
            for (int i = 1; i < text.length(); i++) {
                String words = text.substring(i-1, i+1);
                boolean wasSymbol = statistic.containsKey(words);

                if (text.charAt(i-1) >= firstLetter && text.charAt(i-1) <= lastLetter
                        && text.charAt(i) >= firstLetter && text.charAt(i) <= lastLetter
                || words.contains(" ")) {
                    if (wasSymbol) {
                        int count = statistic.get(words);
                        count++;
                        statistic.replace(words, count);
                    } else {
                        statistic.put(words, 1);
                    }
                }
            }

            text = new StringBuilder();
        }
        try {
            fr1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // TODO: understand this code
        List<Map.Entry<String, Integer>> list =
                new LinkedList<>(statistic.entrySet());
        Collections.sort( list, new Comparator<Map.Entry<String, Integer>>()
        {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2)
            {
                return (o1.getValue()).compareTo( o2.getValue() );
            }
        } );

        Map<String, Integer> result = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : list)
        {
            result.put(entry.getKey(), entry.getValue());
        }

        Iterator<Map.Entry<String, Integer>> iterator = result.entrySet().iterator();

        while (iterator.hasNext()) {
            //получение «пары» элементов
            Map.Entry<String, Integer> pair = iterator.next();
            String key = pair.getKey();
            int value = pair.getValue();
            //System.out.println(key + ": " + value);
        }

        return result;
    }

    public static void main(String[] args) throws IOException {

        FileReader fr = new FileReader("input.txt");
        FileReader fr1 = new FileReader("input2.txt");
        Scanner scanner = new Scanner(fr);
        Scanner scanner1 = new Scanner(fr1);
        StringBuilder text = new StringBuilder();

        while (scanner.hasNextLine()) {
            text.append(scanner.nextLine().toLowerCase());
            for (int i = 1; i < text.length(); i++) {
                String words = text.substring(i-1, i+1);
                boolean wasSymbol = statistic.containsKey(words);

                if (text.charAt(i-1) >= firstLetter && text.charAt(i-1) <= lastLetter
                        && text.charAt(i) >= firstLetter && text.charAt(i) <= lastLetter
                || words.toString().contains(" ")) {
                    if (wasSymbol) {
                        int count = statistic.get(words);
                        count++;
                        statistic.replace(words, count);
                    } else {
                        statistic.put(words, 1);
                    }
                }
            }

            text = new StringBuilder();
        } fr.close();

        while (scanner1.hasNextLine()) {
            text.append(scanner1.nextLine().toLowerCase());
            for (int i = 1; i < text.length(); i++) {
                String words = text.substring(i-1, i+1);
                boolean wasSymbol = statistic.containsKey(words);

                if (text.charAt(i-1) >= firstLetter && text.charAt(i-1) <= lastLetter
                        && text.charAt(i) >= firstLetter && text.charAt(i) <= lastLetter) {
                    if (wasSymbol) {
                        int count = statistic.get(words);
                        count++;
                        statistic.replace(words, count);
                    } else {
                        statistic.put(words, 1);
                    }
                }
            }

            text = new StringBuilder();
        } fr1.close();

        // TODO: understand this code
        List<Map.Entry<String, Integer>> list =
                new LinkedList<>(statistic.entrySet());
        Collections.sort( list, new Comparator<Map.Entry<String, Integer>>()
        {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2)
            {
                return (o1.getValue()).compareTo( o2.getValue() );
            }
        } );

        Map<String, Integer> result = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : list)
        {
            result.put(entry.getKey(), entry.getValue());
        }

        Iterator<Map.Entry<String, Integer>> iterator = result.entrySet().iterator();

        while (iterator.hasNext()) {
            //получение «пары» элементов
            Map.Entry<String, Integer> pair = iterator.next();
            String key = pair.getKey();
            int value = pair.getValue();
            System.out.println(key + ": " + value);
        }

    }

}
