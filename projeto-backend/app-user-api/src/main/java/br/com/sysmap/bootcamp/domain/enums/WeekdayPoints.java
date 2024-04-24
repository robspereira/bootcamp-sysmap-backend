package br.com.sysmap.bootcamp.domain.enums;

import java.time.DayOfWeek;

public enum WeekdayPoints {
    SUNDAY(25, DayOfWeek.SUNDAY),
    MONDAY(7, DayOfWeek.MONDAY),
    TUESDAY(6, DayOfWeek.TUESDAY),
    WEDNESDAY(2, DayOfWeek.WEDNESDAY),
    THURSDAY(10, DayOfWeek.THURSDAY),
    FRIDAY(15, DayOfWeek.FRIDAY),
    SATURDAY(20, DayOfWeek.SATURDAY);

    private final int points;
    private final DayOfWeek dayOfWeek;

    WeekdayPoints(int points, DayOfWeek dayOfWeek) {
        this.points = points;
        this.dayOfWeek = dayOfWeek;
    }

    public int getPoints() {
        return points;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public static WeekdayPoints fromDayOfWeek(DayOfWeek dayOfWeek) {
        WeekdayPoints dayPoints = null;
        for (WeekdayPoints point : values()) {
            if (point.getDayOfWeek() == dayOfWeek) {
                dayPoints = point;
                break;
            }
        }
        return dayPoints;
    }
}