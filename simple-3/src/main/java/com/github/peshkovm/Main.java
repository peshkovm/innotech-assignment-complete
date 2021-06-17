package com.github.peshkovm;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.validator.GenericValidator;

public class Main {
  public static void main(String[] args) {}

  private static List<LocalDate> getCorrespondingDates(String str, String datePattern) {
    return Arrays.stream(str.split(" "))
        .peek(System.out::println)
        .filter(word -> GenericValidator.isDate(word, datePattern, true))
        .map(date -> LocalDate.parse(date, DateTimeFormatter.ofPattern(datePattern)))
        .collect(Collectors.toList());
  }

  //  private static DateTimeFormatter getDateOrTimeFormatter(String datePattern) {
  //    return new DateTimeFormatterBuilder()
  //        .appendPattern("yyyy-MM-dd")
  //        .optionalStart()
  //        .appendPattern(" HH:mm")
  //        .optionalEnd()
  //        .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
  //        .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
  //        .toFormatter();
  //  }
}
