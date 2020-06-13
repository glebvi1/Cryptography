package com.company;

public class Sorted {

    private static void swap(int a[], int i, int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static void swapString(String [] a, int i, int j) {
        String temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static int partition(int [] array, int start, int end, String [] arrayString) {
        int marker = start; // end

        for (int i=marker; i<=end; i++) {
            if (array[i] <= array[end]) {
                swap(array, i, marker);
                swapString(arrayString, i, marker);
                marker += 1;
            }
        }

        return marker-1;
    }

    // Худний вариант - O(n^2)
    // Средний вариант - O(n^2)
    // Без улучшений: лучший вариант - O(n^2)
    // С улучшением: лучший вариан - O(1)
    public void bubble_sorted(int[] array) { // С улучшением
        boolean flag = true;

        int n = array.length;

        for (int i=0; i<n && flag; i++) {
            flag = false;

            for (int j=0; j<n-1; j++) {

                if (array[j] > array[j+1]) {
                    swap(array, j, j+1);
                }

            }
        }

        for (var element : array) {
            System.out.println(element);
        }

    }

    // Худший вариант - O(n^2)
    // Средний вариант - O(n*log n)
    // Лучший вариант - O(n*log n)
    public static void quick_sorted(int [] arrayInteger, int start, int end, String [] arrayString) {

        if (start >= end) return;
        int pivot = partition(arrayInteger, start, end, arrayString);
        quick_sorted(arrayInteger, start, pivot-1, arrayString);
        quick_sorted(arrayInteger, pivot+1, end, arrayString);

        quick_sorted(arrayInteger, start, end, arrayString);

    }

}

