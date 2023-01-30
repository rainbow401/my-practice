package com.practice.niuke;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class HJ3 {

    public static void main(String[] args) {
        int count = 0;
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Set<Integer> data = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            data.add(sc.nextInt());
        }

        for (Integer temp : data) {
            System.out.println(temp);
        }
    }
}
