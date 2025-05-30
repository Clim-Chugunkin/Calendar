import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class MonthView<T> {
    private final ArrayList<DayView<T>> monthView = new ArrayList<>();

    private int currentMonth = 1;
    private final GregorianCalendar calendar = new GregorianCalendar();

    public int getCurrentMonth() {
        return currentMonth;
    }

    public void setCurrentMonth(int currentMonth)  {
        this.currentMonth = currentMonth;

        //filling monthDay
        calendar.set(Calendar.MONTH, currentMonth);
        calendar.set(Calendar.DAY_OF_MONTH,1);
        //current month start with day of week
        int startWith = changeWeekDaysToRUS(calendar.get(Calendar.DAY_OF_WEEK));
        //current month days number
        int daysPerMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        //fill days from previous month
        DayView<T> data ;
        Key key;
        if (startWith != 1){
            calendar.set(Calendar.MONTH,currentMonth-1);
            int daysPerPreviousMonth =calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            for (int i = 0; i < startWith-1; i++) {
                key = new Key(currentMonth-1,(daysPerPreviousMonth-(startWith-2))+i);
                data = new DayView<>();
                data.setKey(key);
                data.resetActive();
                monthView.add(data);
            }
        }
        //filling days for current month
        for (int i = 0;i<daysPerMonth;i++){
            data = new DayView<>();
            key = new Key(currentMonth,i+1);
            data.setKey(key);
            monthView.add(data);
        }
        //filling days for next month
        //fill monthView to round count (40 days should be)
        int currentMonthViewSize = monthView.size();
        for (int i = 1; i<42-currentMonthViewSize;i++){
            data = new DayView<>();
            key = new Key(currentMonth+1,i);
            data.setKey(key);
            data.resetActive();
            monthView.add(data);
        }


    }

    public List<DayView<T>> getMonthView(){
        return monthView;
    }
    public void setData(T data, int day){
        monthView.get(day).setData(data);
    }
    public T getData(int day){
        return monthView.get(day).getData();
    }
    private int changeWeekDaysToRUS(int day){
        if (day == 1) return 7; else return day-1;
    }
}
