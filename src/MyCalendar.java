import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;

public class MyCalendar<T> {

    private MonthView<T> month;
    private DBClass db;
    private  Class<T> type;

    public static final String MONTH = "month";
    public static final String DAY = "day";


    public MyCalendar(Class<T> type){
        this.type = type;
    }

    public List<DayView<T>> getMonth(int monthNumber){

        //create and fill container month (occupied with day and month)
        month = new MonthView<>();
        month.setCurrentMonth(monthNumber);

        //get data from dataBase
        db = new DBClass("test","root","root");
        HashMap<Key, HashMap<String,String>> data = db.getData(monthNumber);
        db.closeDB();

        Key key = new Key(0,0);
        HashMap<String,String> row;
        for (DayView<T> dayView : month.getMonthView()){
            key.setDay(dayView.getKey().getDay());
            key.setMonth(dayView.getKey().getMonth());
            T clazz = null;
            try{
                clazz = type.getDeclaredConstructor().newInstance();
                if (data.containsKey(key)){
                    for (String field : data.get(key).keySet()){
                        Field classField = clazz.getClass().getDeclaredField(field);
                        classField.setAccessible(true);
                        classField.set(clazz,Integer.parseInt(data.get(key).get(field)));
                    }
                }

            }catch(Exception ex){
                System.out.println("unable to convert class");
            }finally {
                dayView.setData(clazz);
            }
        }

        return month.getMonthView();
    }
}
