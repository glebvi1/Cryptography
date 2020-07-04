package com.company;

import java.util.Random;

public class Table {

    public static int[][] rotateToRight(int[][] mas) {
        int SIDE = mas.length;
        int[][] rezult = new int[SIDE][SIDE];

        for (int i = 0; i < SIDE; i++) {
            for (int j = 0; j < SIDE; j++) {
                rezult[i][j] = mas[SIDE - j - 1][i];
            }
        }
        return rezult;
    }
    public static char[][] rotateToRight1(char[][] mas) {
        int SIDE = mas.length;
        char[][] rezult = new char[SIDE][SIDE];

        for (int i = 0; i < SIDE; i++) {
            for (int j = 0; j < SIDE; j++) {
                rezult[i][j] = mas[SIDE - j - 1][i];
            }
        }
        return rezult;
    }

    public static char[][] fillTable(int size, String text1) {
        String answer = "";
        char [][] textTable = new char[size][size];
        int [][] intTable = new int[size][size];
        int [] random = new int[2*size];
        int [] indexRandom = new int[2*size];
        int [] int1 = new int[2*size];
        String text = "";
        String [] array = text1.split(" ");
        Random r = new Random();

        for (int i = 0; i < array.length; i++) {
            text += array[i];
        }

        for (int i = 0; i < indexRandom.length; i++) {
            indexRandom[i] = r.nextInt(4)+1;
        }


        for (int i = 0; i < 2*size; i++) {
            int1[i] = i+1;
        }
        int k = 0;
        int count = 0;
        while (k < 2*size){
            boolean flag = false;
            int r1 = r.nextInt(2*size)+1;
            for (int j = 0; j < 2*size; j++) {
                if (int1[j] == r1) {
                    flag = true;
                    int1[j] = 0;
                    count++;
                }
            }
            if (flag){
                random[k] = r1;
                k++;
            }
            // TODO: сделано для size = 8
            if (count == 12) {
                for (int j = 0; j < 2*size; j++) {
                    if (int1[j] != 0) {
                        random[k] = int1[j];
                        k++;
                    }
                }
            }
        }

        for (int i = 0; i < size*2; i++) {
            for (int j = 0; j < 2*size-1; j++) {
                if (random[j] > random[j+1]) {
                    int temp = random[j];
                    random[j] = random[j+1];
                    random[j+1] = temp;
                    temp = indexRandom[j];
                    indexRandom[j] = indexRandom[j+1];
                    indexRandom[j+1] = temp;
                }
            }
        }

        for (int i = 0; i < 2*size; i++) {
            System.out.printf("%3d", random[i]);
        }
        System.out.println();
        for (int i = 0; i < indexRandom.length; i++) {
            System.out.printf("%3d", indexRandom[i]);
        }

        // fill intTable (2 координатная четверть)
        System.out.println();
        System.out.println();

        // 2
        int first = 1;
        for (int i = 0; i < size/2; i++) {
            for (int j = 0; j < size/2; j++) {
                intTable[i][j] = first;
                first++;
            }
        }

        // 1 координатная четверть
        first = 1;
        for (int j = size-1; j > size/2-1; j--) {
            for (int i = 0; i < size/2; i++) {
                intTable[i][j] = first;
                first++;
            }
        }

        // 4 координатная четверть
        first = 1;
        for (int i = size-1; i > size/2-1; i--) {
            for (int j = size-1; j > size/2-1; j--) {
                intTable[i][j] = first;
                first++;
            }
        }

        // 3 координатная четверть
        first = 1;
        for (int j = 0; j < size/2; j++) {
            for (int i = size-1; i > size/2-1; i--) {
                intTable[i][j] = first;
                first++;
            }
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.printf("%4d", intTable[i][j]);
            }
            System.out.println();
        }

        for (int i = 0; i < textTable.length; i++) {
            for (int j = 0; j < textTable.length; j++) {
                textTable[i][j] = '0';
            }
        }

        for (int sym = 0; sym < size*size; sym=sym) {

            if (sym != 0) {
                intTable = rotateToRight(intTable);
                textTable = rotateToRight1(textTable);
            }

            for (int ind = 0; ind < indexRandom.length; ind++) {

                if (indexRandom[ind] == 1) {

                    for (int i = 0; i < size/2; i++) {
                        for (int j = 0; j < size/2; j++) {
                            if (intTable[i][j] == random[ind]) {
                                textTable[i][j] = text.charAt(sym);
                                sym++;
                                break;
                            }
                        }
                    }
                }

                else if (indexRandom[ind] == 2) {
                    for (int i = 0; i < size/2; i++) {
                        for (int j = size/2; j < size; j++) {
                            if (intTable[i][j] == random[ind]) {
                                textTable[i][j] = text.charAt(sym);
                                sym++;
                                break;
                            }
                        }
                    }
                }
                else if (indexRandom[ind] == 3) {
                    for (int i = size/2; i < size; i++) {
                        for (int j = 0; j < size/2; j++) {
                            if (intTable[i][j] == random[ind]) {
                                textTable[i][j] = text.charAt(sym);
                                sym++;
                                break;
                            }
                        }
                    }
                } else if (indexRandom[ind] == 4) {
                    for (int i = size/2; i < size; i++) {
                        for (int j = size / 2; j < size; j++) {
                            if (intTable[i][j] == random[ind]) {
                                textTable[i][j] = text.charAt(sym);
                                sym++;
                                break;
                            }
                        }
                    }
                }
            }

        }

        for (int i = 0; i < textTable.length; i++) {
            for (int j = 0; j < textTable.length; j++) {
                System.out.print(textTable[i][j]);
            }
            System.out.println();
        }

        return textTable;
    }

    public static void main(String[] args) {
        fillTable(8, "Так говорила в июле 1805 года известная Анна Павловна Шерер, фрейлина и приближенная императрицы Марии Феодоровны, встречая важного и чиновного кня");
    }

}
