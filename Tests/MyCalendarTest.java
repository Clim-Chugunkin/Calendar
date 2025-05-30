import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyCalendarTest {
    private MyCalendar<DataTest> calendar;
    @Before
    public void setUp()  {
        calendar = new MyCalendar<>(DataTest.class);
    }

    @Test
    public void getMonth() {
        for (DayView<DataTest> day : calendar.getMonth(3))
            System.out.println("day " + day.getKey().getDay()
                    + " month " + day.getKey().getMonth()
                    + " steps " + day.getData().getSteps()
                    + "distance " + day.getData().getDistance());
    }
}