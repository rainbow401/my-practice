package com.practice.niuke;

import java.util.Scanner;

public class HJ2 {
    public static void main(String[] args) {
        int count = 0;
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine().toLowerCase();
        String charN = sc.nextLine().toLowerCase();
        char n = charN.charAt(0);

        for (int i = 0; i < str.length(); i++) {
            char temp = str.charAt(i);
            if (n == temp) {
                count++;
            }
        }
        System.out.println(count);
    }
}
