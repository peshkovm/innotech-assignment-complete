package com.github.peshkovm;

import java.util.Arrays;

public class Main {
  public static void main(String[] args) {}

  private static Long getSumOfNumsInString(final String str) {
    return Arrays.stream(str.strip().split("\\D+")).mapToLong(Long::valueOf).sum();
  }
}
