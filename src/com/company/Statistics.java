package com.company;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Statistics {

    static int[] countSymbol = new int[32];
    static Map<String, String> accordance = new HashMap<>();
    static String [] translation = new String[3001];
    static ArrayList<Integer> arrayCount1 = new ArrayList<>();
    static String arrayWords1[] = new String[32];
    static int [] statisticOfCSV = new int[32];
    static String [] sortWords = new String[32];
    static String [] currentSortWords = new String[32];

    static char firstLetter = '\u0430';
    static char lastLetter = '\u044f';
    static String[] temp;
    static Scanner in = new Scanner(System.in);

    static int sumStatistic() {
        int sumStatistic1 = 0;
        for (int i = 0; i < translation.length-1; i++) {
            String words = translation[i] + translation[i+1];
            if (statistic2.get(words) != null) {
                sumStatistic1 += statistic2.get(words);
            }
        }
        return sumStatistic1;
    }

    static void swap(String a, String b) {
        String temp = a;
        a = b;
        b = temp;
    }

    static Map<String, Integer> statistic2 = StatisticTwoWords.statistic();

    public static Map<String, Integer> getRussianStatistic(File fileRussian, File fileStatistic) throws IOException {

        FileWriter output = new FileWriter(fileStatistic.getName());
        FileReader input = new FileReader(fileRussian.getName());
        Scanner scanner = new Scanner(input);
        StringBuilder text = new StringBuilder();
        Map<String, Integer> statistic = new HashMap<>();

        while (scanner.hasNextLine()) {
            text.append(scanner.nextLine().toLowerCase());
            for (int i=1; i<text.length(); i++) {
                String word = text.substring(i-1, i);
                if (text.charAt(i-1) >= firstLetter && text.charAt(i-1) <= lastLetter) {

                 if (statistic.containsKey(word)) {
                     int count = statistic.get(word);
                     count++;
                     statistic.replace(word, count);
                 } else {
                     statistic.put(word, 1);
                 }

                }
            }
            text = new StringBuilder();
        } input.close();

        StringBuilder answer = new StringBuilder();
        Iterator<Map.Entry<String, Integer>> iterator = statistic.entrySet().iterator();
        while (iterator.hasNext()) {
            //получение «пары» элементов
            Map.Entry<String, Integer> pair = iterator.next();
            String key = pair.getKey();
            int value = pair.getValue();
            answer.append(key).append(": ").append(value).append("\n");
        }

        output.write(answer+"");
        output.close();
        return statistic;

    }

    public static Map<String, Integer> getCSVStatistic(File fileCSV, File fileStatistic) throws IOException {

        FileReader fr = new FileReader(fileCSV);
        Scanner scanner1 = new Scanner(fr);
        StringBuilder textCSV = new StringBuilder();
        Map<String, Integer> statistic = new HashMap<>();
        FileWriter fw = new FileWriter(fileStatistic.getName());

        while (scanner1.hasNextLine()) {
            textCSV.append(scanner1.nextLine());
            String [] temp = textCSV.toString().split(";");

            for (int i = 0; i < temp.length; i++) {
                if (Integer.parseInt(temp[i]) >= 1 && Integer.parseInt(temp[i]) <= 32) {

                    if (statistic.containsKey(temp[i])) {
                        int count = statistic.get(temp[i]);
                        count++;
                        statistic.replace(temp[i], count);
                    } else {
                        statistic.put(temp[i], 1);
                    }

                }
            }
        }

        StringBuilder answer = new StringBuilder();
        Iterator<Map.Entry<String, Integer>> iterator = statistic.entrySet().iterator();

        while (iterator.hasNext()) {
            //получение «пары» элементов
            Map.Entry<String, Integer> pair = iterator.next();
            String key = pair.getKey();
            int value = pair.getValue();
            answer.append(key).append(": ").append(value).append("\n");
        }

        fw.write(answer+"");
        return statistic;

    }

    private static void replaceSymbol() {

        System.out.println("\n1. Делать замену самому.\n2. Делать замену с помощью компьютера.");
        int qu = in.nextInt();
        if (qu == 1) {
            System.out.println("\nКакие буквы заменить?");
            String firstW = in.next();
            String secondW = in.next();
/*            System.out.println(firstW.charAt(0));
            if (!(firstW.charAt(0) >= firstLetter && firstW.charAt(0) <= lastLetter
                    && secondW.charAt(0) >= firstLetter && secondW.charAt(0) <= lastLetter)) {
                return;
            }*/

            int firstI = Integer.parseInt(accordance.get(firstW)); // элемент в CSV
            int secondI = Integer.parseInt(accordance.get(secondW));

            for (int i = 0; i < currentSortWords.length; i++) {
                if (currentSortWords[i].equals(firstW)) {
                    currentSortWords[i] = secondW;
                } else if (currentSortWords[i].equals(secondW)) {
                    currentSortWords[i] = firstW;
                }
            }

            for (int i = 0; i < 3000; i++) {
                //System.out.println(temp[i]);
                if (temp[i].equals("0")) {
                    translation[i] = " ";
                } else {
                    int curInt = Integer.parseInt(temp[i]); // "11" -> 11 - элемент
                    int sortInt = statisticOfCSV[curInt - 1]; // кол-во раз, которое встречается этот элемент
                    int count = 0;
                    for (int e : statisticOfCSV) {
                        if (e > sortInt) {
                            count++;
                        }
                    }
                    translation[i] = currentSortWords[count]; // curInt (елемент в CSV) ->  sortWords[count] (букве русского алфавита)
                    accordance.put(currentSortWords[count], curInt + "");
                }
            }

            int sum = sumStatistic();
            for (String s : translation) {
                System.out.print(s);
            }
        System.out.println("Сумма в статистике: " + sum);

        } else {
            System.out.println("Делать замену?");
            String query = in.nextLine();
            if (query.equals("нет")) {
                return;
            }
            for (int i = 0; i < sortWords.length - 1; i++) {

                System.out.println(i + ": " + currentSortWords[i] + " " + currentSortWords[i + 1]);
                for (int k = 0; k < 3000; k++) {
                    if (temp[k].equals("0")) {
                        translation[k] = " ";
                    } else {
                        int curInt = Integer.parseInt(temp[k]); // "11" -> 11 - элемент
                        int sortInt = statisticOfCSV[curInt - 1]; // кол-во раз, которое встречается этот элемент
                        int count = 0;
                        for (int e : statisticOfCSV) {
                            if (e > sortInt) {
                                count++;
                            }
                        }
                        translation[k] = currentSortWords[count]; // curInt (елемент в CSV) ->  sortWords[count] (букве русского алфавита)
                        accordance.put(currentSortWords[count], curInt + "");
                    }
                }
                int pastSum = sumStatistic();
                String temp10 = currentSortWords[i];
                currentSortWords[i] = currentSortWords[i + 1];
                currentSortWords[i + 1] = temp10;

                // меняем
                for (int k = 0; k < 3000; k++) {
                    if (temp[k].equals("0")) {
                        translation[k] = " ";
                    } else {
                        int curInt = Integer.parseInt(temp[k]); // "11" -> 11 - элемент
                        int sortInt = statisticOfCSV[curInt - 1]; // кол-во раз, которое встречается этот элемент
                        int count = 0;
                        for (int e : statisticOfCSV) {
                            if (e > sortInt) {
                                count++;
                            }
                        }
                        translation[k] = currentSortWords[count]; // curInt (елемент в CSV) ->  sortWords[count] (букве русского алфавита)
                        accordance.put(currentSortWords[count], curInt + "");
                    }
                }
                int currentSum = sumStatistic();

                if (currentSum < pastSum) {
                    String temp11 = currentSortWords[i];
                    currentSortWords[i] = currentSortWords[i + 1];
                    currentSortWords[i + 1] = temp11;
                }

                System.out.println(i + ": " + pastSum + " " + currentSum);
                System.out.println(i + ": " + currentSortWords[i] + " " + currentSortWords[i + 1]);
            }

            for (String s : translation) {
                System.out.print(s);
            }
        }

    }

    private static String max(ArrayList<Integer> a) {
        int max = 0;
        int index = 0;
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i) > max) {
                max  = a.get(i);
                index = i;
            }
        }
        a.remove(index);
        a.add(index, -1);
        return max + " " + index;
    }

    public static void main(String[] args) throws IOException {

        FileWriter output = new FileWriter("output.txt");
        FileReader input = new FileReader("input.txt");
        Scanner scanner = new Scanner(input);
        String text = "";

        Arrays.fill(countSymbol, 0);

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
            arrayCount1.add(countSymbol[32-i-1]);
            arrayWords1[i] = Character.toString(firstLetter + i);
        } output.close();



        // статистика по CSV файлу. начало
        File russian = new File("RussianText.txt");
        File csv = new File("CSVText.txt");
        CSVAndRussian.toCSV(russian, csv);
        FileReader fr = new FileReader("CSVText.txt");
        Scanner scanner1 = new Scanner(fr);
        String textCSV = "";
        Arrays.fill(statisticOfCSV, 0);

        while (scanner1.hasNextLine()) {
            textCSV += scanner1.nextLine();
            temp = textCSV.split(";");
            for (int i = 0; i < temp.length; i++) {
                if (Integer.parseInt(temp[i]) >= 1 && Integer.parseInt(temp[i]) <= 32) {
                    statisticOfCSV[Integer.parseInt(temp[i]) -1] += 1;
                }
            }
        }

        ArrayList<Integer> copy = arrayCount1;
        int[] sortMax = new int[32];

        for (int i = 0; i < arrayCount1.size(); i++) {

            String [] array = max(arrayCount1).split(" ");
            sortMax[i] = Integer.parseInt(array[0]);
            sortWords[i] = arrayWords1[Integer.parseInt(array[1])];
            //System.out.println(Integer.parseInt(array[1]) + " " + arrayWords1[Integer.parseInt(array[1])] + " " + sortWords[i]);

        }

/*        for (String s : sortWords) {
            System.out.println(s);
        }*/

        for (int i = 0; i < 3000; i++) {
            //System.out.println(temp[i]);
            if (temp[i].equals("0")) {
                translation[i] = " ";
            } else {
                int curInt = Integer.parseInt(temp[i]); // "11" -> 11 - элемент
                int sortInt = statisticOfCSV[curInt-1]; // кол-во раз, которое встречается этот элемент
                int count = 0;
                for (int e : statisticOfCSV) {
                    if (e > sortInt) {
                        count++;
                    }
                }
                translation[i] = sortWords[count]; // curInt (елемент в CSV) ->  sortWords[count] (букве русского алфавита)
                accordance.put(sortWords[count], curInt+"");
            }
        }

        for (String s : translation) {
            System.out.print(s);
        }
        System.out.println("--------------------------------------------------------------------");
        Iterator<Map.Entry<String, String>> iterator = accordance.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, String> pair = iterator.next();
            String key = pair.getKey();
            String value = pair.getValue();
            System.out.println(key + ": " + value);
        }
        for (int i = 0; i < sortWords.length; i++) {
            currentSortWords[i] = sortWords[i];
        }

        int sumStatistic = 0;
        for (int i = 0; i < translation.length-1; i++) {
            String words = translation[i] + translation[i+1];
            if (statistic2.get(words) != null) {
                sumStatistic += statistic2.get(words);
            }
        }
        System.out.println("\nСумма в статистике: " + sumStatistic);
        // сортировка массива частотности
        for (int i = 0; i < countSymbol.length; i++) {
            for (int j =0 ; j < countSymbol.length-1; j++) {
                if (countSymbol[j] < countSymbol[j+1]) {
                    int temp1 = countSymbol[j];
                    countSymbol[j] = countSymbol[j+1];
                    countSymbol[j+1] = temp1;
                }
            }
        }

        while (true) {
            replaceSymbol();

        }

    }

}
