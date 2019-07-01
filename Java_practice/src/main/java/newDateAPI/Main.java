package newDateAPI;

import java.time.*;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.TemporalAdjusters.firstInMonth;

import static java.time.temporal.TemporalAdjusters.next;

import static java.time.temporal.ChronoUnit.DAYS;

public class Main {
    public static void main(String[] args) {

        LocalDateTime timeNow = LocalDateTime.now();

/** Вернуть текущую дату с временем */
        System.out.println("\n\nВернуть текущую дату с временем:\n "+ timeNow.format(DateTimeFormatter.ofPattern("YYYY:MM:dd HH:mm")));

/** Вернуть текущее время */
        System.out.println("\nВернуть текущее время:\n "+ timeNow.format(DateTimeFormatter.ofPattern("HH:mm")));

/** Вернуть текущую дату */
        System.out.println("\nВернуть текущую дату:\n "+ timeNow.format(DateTimeFormatter.ofPattern("YYYY:MM:dd")));

/** Вернуть текущую дату с временем, изменить месяц на февраль, год на 2012 */
        System.out.println("\nВернуть текущую дату с временем, изменить месяц на февраль, год на 2012:\n "+ timeNow.format(DateTimeFormatter.ofPattern("2012:02:dd")));

/** Создать дату 23 Марта 1974 года. Посчитать сколько дней назад это было */
        LocalDate tempDate = LocalDate.of(1974, 03, 23);
        System.out.println("\nСоздать дату 23 Марта 1974 года. Посчитать сколько дней назад это было:\n "+ DAYS.between(tempDate,timeNow));

/** Создать время из строки */
        System.out.println("\nСоздать время из строки:\n " + LocalTime.parse("13:50:20"));

/** Создать ZonedDateTime из строки в формате ISO 8601 */
        ZonedDateTime tempZonedDateTime = ZonedDateTime.parse("2007-12-03T10:15:30+03:00");
        System.out.println("\nСоздать ZonedDateTime из строки в формате ISO 8601:\n " + tempZonedDateTime);

/** Получить строку в формате ISO 8601 для текущей даты и времени для региона UTC */
        System.out.println("\nПолучить строку в формате ISO 8601 для текущей даты и времени для региона UTC:\n "+ ZonedDateTime.now(ZoneId.of("UTC")));

/** Получить строку в формате ISO 8601 для текущей даты и времени для региона Europe/Minsk */
        System.out.println("\nПолучить строку в формате ISO 8601 для текущей даты и времени для региона Europe/Minsk:\n "+ ZonedDateTime.now(ZoneId.of("Europe/Minsk")));

/** Добавить к текущей дате для региона UTC одну декаду */
        System.out.println("\nДобавить к текущей дате для региона UTC одну декаду:\n "+ ZonedDateTime.now(ZoneId.of("UTC")).plusDays(10));

/** Получить Duration между текущей датой для регионов UTC и Europe/Minsk */
        System.out.println("\nПолучить Duration между текущей датой для регионов UTC и Europe/Minsk:\n "+ Duration.between(ZonedDateTime.now(ZoneId.of("UTC")),ZonedDateTime.now(ZoneId.of("Europe/Minsk"))));

/** Получить Duration между текущей датой для регионов UTC и GMT */
        System.out.println("\nПолучить Duration между текущей датой для регионов UTC и GMT:\n "+ Duration.between(ZonedDateTime.now(ZoneId.of("GMT")),ZonedDateTime.now(ZoneId.of("UTC"))));

/** Получить Period между текущей датой и вашим днем рождения (с годом) */
        System.out.println("\nПолучить Period между текущей датой и вашим днем рождения (с годом):\n "+ Period.between(LocalDate.now(), LocalDate.of(1995,07,07)));

/** Получить дату следующего четверга */
        System.out.println("\nПолучить дату следующего четверга:\n "+ LocalDate.now().with(next(DayOfWeek.THURSDAY)));

/** Получить дату второй субботы текущего месяца текущего года */
        System.out.println("\nПолучить дату второй субботы текущего месяца текущего года:\n "+ LocalDate.now().with(firstInMonth(DayOfWeek.SATURDAY)).plusDays(7));

/** Преобразовать ZonedDateTime дял ващего региона в LocalDateTime для региона UTC */
        System.out.println("\nПреобразовать ZonedDateTime дял ващего региона в LocalDateTime для региона UTC:\n "+ ZonedDateTime.now(ZoneId.of("Europe/Minsk")).toLocalDateTime().atZone(ZoneId.of("UTC")));

    }
}
