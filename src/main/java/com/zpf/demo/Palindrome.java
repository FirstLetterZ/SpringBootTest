package com.zpf.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Palindrome {

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;
        try {
            str = br.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(test(str, 1));
    }

    public static String test(String msg, int changeTime) {
        if (msg == null || msg.length() < 2) {
            return "YES";
        }
        int last = msg.length() - 1;
        char[] diff = new char[4 * changeTime];
        int index = 0;
        for (int i = 0; i <= last - i; i++) {
            if (i == last - i) {
                if (index >= diff.length - 1) {
                    return "NO";
                } else {
                    diff[index] = msg.charAt(i);
                    index = index + 1;
                }
            } else if (msg.charAt(i) != msg.charAt(last - i)) {
                if (index >= diff.length - 1) {
                    return "NO";
                } else {
                    diff[index] = msg.charAt(i);
                    diff[index + 1] = msg.charAt(last - i);
                    index = index + 2;
                }
            }
        }
        if (index < 2) {
            return "YES";
        } else {
            boolean willRemain;
            int remainCount = 0;
            for (int i = 0; i < diff.length - 1; i++) {
                if (diff[i] != '\u0000') {
                    willRemain = true;
                    for (int j = i + 1; j < diff.length; j++) {
                        if (diff[i] == diff[j]) {
                            diff[j] = '\u0000';
                            willRemain = false;
                            break;
                        }
                    }
                    if (willRemain) {
                        remainCount++;
                    }
                }

            }
            return remainCount < 2 ? "YES" : "NO";
        }
    }

}