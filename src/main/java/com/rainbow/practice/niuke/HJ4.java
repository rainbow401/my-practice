package com.rainbow.practice.niuke;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class HJ4 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder src = new StringBuilder(sc.nextLine());
        int length = src.length();
        int a = length / 8;
        int b = length % 8;
        if (b != 0) {
            for (int i = 0; i < 8 - b; i++) {
                src.append("0");
            }
            a = a + 1;
        }


        for (int i = 0; i < a; i++) {
            int nowIndex = i * 8;
            System.out.println(src.substring(nowIndex, nowIndex + 8));
        }
    }
}
