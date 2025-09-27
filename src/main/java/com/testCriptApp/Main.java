package com.testCriptApp;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.Random;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
//        int[] arr = {1, 5, 7, -1, 5};
//        int k = 6;
//        findPairs(arr, k);

        int[] arr2 = {4, 9, 1, 32, 13};
//        findMinDifferencePair(arr2);

//        int[] arr3 = {1, 60, -10, 70, -80, 85};
//        findClosestToZero(arr3);

        findSubarrayWithSum(arr2, 14);
    }

    public static void findPairs(int[] arr, int k) {
        Arrays.sort(arr);

        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int sum = arr[left] + arr[right];

            if (sum == k) {
                System.out.println("(" + arr[left] + ", " + arr[right] + ")");

                // Пропускаем дубликаты
                int leftVal = arr[left];
                int rightVal = arr[right];
                while (left < right && arr[left] == leftVal) left++;
                while (left < right && arr[right] == rightVal) right--;
            } else if (sum < k) {
                left++;
            } else {
                right--;
            }
        }
    }

    public static void findMinDifferencePair(int[] arr) {
        Arrays.sort(arr);

        int minDiff = Integer.MAX_VALUE;
        int first = -1, second = -1;

        // Смотрим только соседние пары
        for (int i = 0; i < arr.length - 1; i++) {
            int diff = arr[i + 1] - arr[i];
            if (diff < minDiff) {
                minDiff = diff;
                first = arr[i];
                second = arr[i + 1];
            }
        }

        System.out.println("Минимальная разница: " + minDiff);
        System.out.println("Пара: (" + first + ", " + second + ")");
    }

    public static void findClosestToZero(int[] arr) {
        Arrays.sort(arr);

        int left = 0;
        int right = arr.length - 1;

        int minSum = Integer.MAX_VALUE;
        int bestLeft = arr[left], bestRight = arr[right];

        // Два указателя
        while (left < right) {
            int sum = arr[left] + arr[right];

            // если эта сумма ближе к 0 — обновляем ответ
            if (Math.abs(sum) < Math.abs(minSum)) {
                minSum = sum;
                bestLeft = arr[left];
                bestRight = arr[right];
            }

            // двигаем указатели
            if (sum < 0) {
                left++;
            } else {
                right--;
            }
        }

        System.out.println("Пара: (" + bestLeft + ", " + bestRight + ")");
        System.out.println("Сумма = " + minSum);
    }

    public static void findSubarrayWithSum(int[] arr, int k) {
        int left = 0, sum = 0;

        for (int right = 0; right < arr.length; right++) {
            // добавляем текущий элемент в окно
            sum += arr[right];

            // если сумма превысила k → уменьшаем окно слева
            while (sum > k && left < right) {
                sum -= arr[left];
                left++;
            }

            // проверка: нашли ли сумму k
            if (sum == k) {
                System.out.println("Подмассив найден: " +
                        Arrays.toString(Arrays.copyOfRange(arr, left, right + 1)));
                return;
            }
        }
        System.out.println("Нет подмассива с суммой " + k);
    }
}