package org.example;

public class Calendar {
    /**
     * calculateDaysInMonth
     *   params:
     *      int monthNum - the number of the month. 1 is January. 12 is December. Etc.
     *      int year - the Gregorian year (for example, 2022)
     *
     *   returns:
     *      number of days in the month
     */

    public int calculateDaysInMonth(int monthNum, int year) {
        switch (monthNum) {
            case 1:
                return 31;
            case 2:
                if (year%4 == 0 && (year%400 == 0 || year%100!=0)) {
                    return 29;
                }
                return 28;
            case 4:
                return 30;
            default:

                throw new IllegalArgumentException("month number must be between 1 and 12 inclusive");

        }
    }
}
