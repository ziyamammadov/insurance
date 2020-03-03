package com.azericard.insurance.util;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Utils {
    public static String randomString() {
        StringBuilder randomStr = new StringBuilder();
        List<Integer> collect = new Random().ints(5, 0, 11).boxed().collect(Collectors.toList());
        for (int n : collect) {
            randomStr.append(n);
        }
        return randomStr.toString();
    }
}
