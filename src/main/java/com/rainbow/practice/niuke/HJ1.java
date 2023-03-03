package com.rainbow.practice.niuke;

import java.util.Scanner;

public class HJ1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int length = s.length();
        int count = 0;
        for (int i = 0; i < length; i++) {
            char currentChar = s.charAt(i);
            if (' '== currentChar) {
                count = 0;
            } else {
                count++;
            }
        }
        System.out.println(count);
    }
}
