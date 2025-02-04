import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.HashMap;

public class DateCounter{
    public static void main(String[] args) throws Exception{
        // TEST
        assert DateCounter.daysBetweenDates(2021, 9, 1) == ChronoUnit.DAYS.between(LocalDate.now(), LocalDate.of(2021, 9, 1));
        // TEST_END

        // TEST
        assert DateCounter.daysBetweenDates(2020, 3, 1) == ChronoUnit.DAYS.between(LocalDate.now(), LocalDate.of(2020, 3, 1));
        // TEST_END

        // TEST
        assert DateCounter.daysBetweenDates(2024, 3, 1) == ChronoUnit.DAYS.between(LocalDate.now(), LocalDate.of(2024, 3, 1));
        // TEST_END

        // TEST
        assert DateCounter.daysBetweenDates(2024, 4, 19) == ChronoUnit.DAYS.between(LocalDate.now(), LocalDate.of(2024, 4, 19));
        // TEST_END

        // TEST
        assert DateCounter.daysBetweenDates(2024, 5, 19) == ChronoUnit.DAYS.between(LocalDate.now(), LocalDate.of(2024, 5, 19));
        // TEST_END

        // TEST
        assert DateCounter.daysBetweenDates(2030, 12, 31) == ChronoUnit.DAYS.between(LocalDate.now(), LocalDate.of(2030, 12, 31));
        // TEST_END


        // TEST
        try{
            DateCounter.daysBetweenDates(1581, 1, 1);
                assert false;
            }catch(IllegalArgumentException e){
                assert e.getMessage().equals("Year cannot be less than 1582");
            }
        // TEST_END

        // TEST
        try{
            DateCounter.daysBetweenDates(1990, 0, 1);
                assert false;
            }catch(IllegalArgumentException e){
                assert e.getMessage().equals("Month cannot equal or less than 0 or greater than 12");
            }
        // TEST_END

        // TEST
        try{
            DateCounter.daysBetweenDates(1990, -1, 1);
                assert false;
            }catch(IllegalArgumentException e){
                assert e.getMessage().equals("Month cannot equal or less than 0 or greater than 12");
            }
        // TEST_END

        // TEST
        try{
            DateCounter.daysBetweenDates(1990, 13, 1);
                assert false;
            }catch(IllegalArgumentException e){
                assert e.getMessage().equals("Month cannot equal or less than 0 or greater than 12");
            }
        // TEST_END

        // TEST
        try{
            DateCounter.daysBetweenDates(1990, 3, 0);
                assert false;
            }catch(IllegalArgumentException e){
                assert e.getMessage().equals("Actual Day cannot be equal or less than 0 or greater than 31");
            }
        // TEST_END

        // TEST
        try{
            DateCounter.daysBetweenDates(1990, 3, -1);
                assert false;
            }catch(IllegalArgumentException e){
                assert e.getMessage().equals("Actual Day cannot be equal or less than 0 or greater than 31");
            }
        // TEST_END

        // TEST
        try{
            DateCounter.daysBetweenDates(1990, 3, 32);
                assert false;
            }catch(IllegalArgumentException e){
                assert e.getMessage().equals("Actual Day cannot be equal or less than 0 or greater than 31");
            }
        // TEST_END

        // TEST
        try{
            DateCounter.daysBetweenDates(1990, 2, 32);
                assert false;
            }catch(IllegalArgumentException e){
                assert e.getMessage().equals("Actual Day cannot be equal or less than 0 or greater than 31");
            }
        // TEST_END

        // TEST
        try{
            DateCounter.daysBetweenDates(1990, 2, 30);
                assert false;
            }catch(IllegalArgumentException e){
                assert e.getMessage().equals("February cannot have more than 29 days");
            }
        // TEST_END

        // TEST
        try{
            DateCounter.daysBetweenDates(1991, 2, 29);
                assert false;
            }catch(IllegalArgumentException e){
                assert e.getMessage().equals("That year was not  a leap year, day cannot be 29 or greater");
            }
        // TEST_END

        // TEST
        try{
            DateCounter.daysBetweenDates(1991, 4, 31);
                assert false;
            }catch(IllegalArgumentException e){
                assert e.getMessage().equals("Invalid Day");
            }
        // TEST_END
    }

    public static int daysBetweenDates(int year, int month, int day){
        LocalDate localDate = LocalDate.now();
        int localYear = localDate.getYear();
        int localMonth = localDate.getMonthValue();
        int localDay = localDate.getDayOfMonth();

        Map<Integer, Integer> daysOfMonths = new HashMap<Integer, Integer>(){{
            put(1, 31);
            put(2, 28);
            put(3, 31);
            put(4, 30);
            put(5, 31);
            put(6, 30);
            put(7, 31);
            put(8, 31);
            put(9, 30);
            put(10, 31);
            put(11, 30);
            put(12, 31);
        }};

        if(year <= 1582){
            throw new IllegalArgumentException("Year cannot be less than 1582");
        }

        if(month <= 0 || month > 12){
            throw new IllegalArgumentException("Month cannot equal or less than 0 or greater than 12");
        }

        dayIsCorrect(daysOfMonths, day, month, year);

        int yearDiferrences = Math.abs(localYear - year);
        boolean isYearBeforActualYear = year < localYear ? true : false;
        int totalYearDaysCounter = 0;

        for(int i = 1; i < yearDiferrences; ++i){
            int currentYear = isYearBeforActualYear ? year + i : localYear + i;
            totalYearDaysCounter += isLeapYear(currentYear) ? 366 : 365;
        }
        
        int counterUntilJulianLocalMonthAndDay = julianDaysCounter(localMonth, localYear, daysOfMonths) + localDay;
        int counterUntilJulianMonthAndDay = julianDaysCounter(month, year, daysOfMonths) + day;

        int realAmountOfLocalDays = isYearBeforActualYear || localYear == year ? counterUntilJulianLocalMonthAndDay : isLeapYear(localYear) ? 366 - counterUntilJulianLocalMonthAndDay : 365 - counterUntilJulianLocalMonthAndDay;
        int realAmountOfDays = !isYearBeforActualYear ? counterUntilJulianMonthAndDay : isLeapYear(year) ? 366 - counterUntilJulianMonthAndDay: 365 - counterUntilJulianMonthAndDay;

        int sumMonthsDays = 0;
        if (localYear == year) {
            if (localMonth < month){
                sumMonthsDays = Math.abs(realAmountOfDays - realAmountOfLocalDays);
            }else if(localMonth >= month){
                sumMonthsDays = Math.abs(realAmountOfLocalDays - realAmountOfDays);
            }
        }else{
            sumMonthsDays = realAmountOfDays + realAmountOfLocalDays;
        }

        Math.abs(realAmountOfLocalDays);
        Math.abs(realAmountOfDays);


        int totalDays = totalYearDaysCounter + sumMonthsDays;

        if (isYearBeforActualYear ){
            return totalDays * -1;
        } else if(localYear == year && (localMonth > month || (localMonth == month && localDay > day))){
            return totalDays * -1;
        }
        return totalDays;
    }

    public static boolean isLeapYear(int year){
        if (year % 4 != 0) return false;

        if (year % 100 == 0 && year % 400 != 0) return false;

        return true;
    }

    public static int julianDaysCounter(int month, int year, Map<Integer, Integer> daysOfMonths){
        int totalDays = 0;
        for(int i = 1; i < month; ++i){
            totalDays += (i == 2 && isLeapYear(year)) ? 29 : daysOfMonths.get(i);
        }
        return totalDays;
    }

    public static void dayIsCorrect(Map<Integer, Integer> daysOfMonths, int day, int month, int year){

        if (day <= 0 || day >= 32){
            throw new IllegalArgumentException("Actual Day cannot be equal or less than 0 or greater than 31");
        }

        if (month == 2){
            if (day > 29){
                throw new IllegalArgumentException("February cannot have more than 29 days");
            }else if(day == 29 && !isLeapYear(year)){
                throw new IllegalArgumentException("That year was not  a leap year, day cannot be 29 or greater");
            }
        }

        if (day > daysOfMonths.get(month)){
            throw new IllegalArgumentException("Invalid Day");
        }

    }
}