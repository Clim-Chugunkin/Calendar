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
        for (DayView<T> dayView : month.getMonthView()){
            key.setDay(dayView.getKey().getDay());
            key.setMonth(dayView.getKey().getMonth());
            T clazz = null;

            try{
                clazz = type.getDeclaredConstructor().newInstance();
                Field [] fields = clazz.getClass().getDeclaredFields();
                if (data.containsKey(key)){
                    for (Field field : fields){
                        if (data.get(key).containsKey(field.getName())) {
                            field.setAccessible(true);
                            //check field type

                            if (field.getType().equals(int.class))
                                field.set(clazz,Integer.parseInt(data.get(key).get(field.getName())));
                            else if (field.getType().equals(double.class))
                                field.set(clazz,Double.parseDouble(data.get(key).get(field.getName())));
                            else field.set(clazz,data.get(key).get(field.getName()));
                        }
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
    public HashMap<String,String> setData(int day,int month, T data){
        db = new DBClass("test","root","root");
        HashMap<String,String> dataDB = new HashMap<>();
        Field[] fields = data.getClass().getDeclaredFields();
        String fieldValue;
        for (Field field : fields){
            try{
                field.setAccessible(true);
                fieldValue = field.get(data).toString();
                dataDB.put(field.getName(),fieldValue);
            }catch(Exception ex){
                ;
            }
        }
        return dataDB;
    }
}
