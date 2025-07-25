import org.junit.Before;
import org.junit.Test;


public class MyCalendarTest {
    private MyCalendar<DataTest> calendar;
    @Before
    public void setUp()  {
        calendar = new MyCalendar<>(DataTest.class);
    }

    @Test
    public void getMonth() {
        for (DayView<DataTest> day : calendar.getMonth(1))
            System.out.println("day " + day.getKey().getDay()
                    + " month " + day.getKey().getMonth()
                    + " steps " + day.getData().getSteps()
                    + " distance " + day.getData().getDistance()
                    + " Speed " + day.getData().getSpeed());
    }
    @Test
    public void setDay(){
        DataTest data = new DataTest();
        data.setSteps(2250);
        data.setDistance(132);
        data.setSpeed(85.8);
        calendar.setData(new Key(2,28),data);
        //calendar.setData(1,1,data);
    }
}