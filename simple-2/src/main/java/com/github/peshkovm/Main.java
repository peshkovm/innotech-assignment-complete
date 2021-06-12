package com.github.peshkovm;

import java.util.HashMap;
import java.util.Map.Entry;

public class Main {
  public static void main(String[] args) {}

  private static String getMostPopularWordInString(final String str) {
    final HashMap<String, Long> map = new HashMap<>();

    for (String word : str.split("\\W+")) {
      map.merge(word, 1L, Long::sum);
    }

    final String mostPopularWord =
        map.entrySet().stream()
            .reduce(
                (entry1, entry2) -> {
                  final Long numOfOccurrences1 = entry1.getValue();
                  final Long numOfOccurrences2 = entry2.getValue();

                  return numOfOccurrences1 > numOfOccurrences2 ? entry1 : entry2;
                })
            .map(Entry::getKey)
            .orElseThrow();

    return mostPopularWord;
  }
}
