package com.github.peshkovm;

import java.util.List;
import java.util.stream.LongStream;

public class Main {
  public static void main(String[] args) {}

  private static LongStream getFreeIpAddresses(final List<Long> usedIps, final Integer netMask) {
    final long smallestIp = (long) Math.pow(2, netMask) & usedIps.get(0);
    final long largestIp = (long) Math.pow(2, 32);

    return LongStream.range(smallestIp, largestIp).filter(ip -> !usedIps.contains(ip));
  }
}
