import java.util.HashMap;

public interface DataSource {

    HashMap<Key, HashMap<String,String>> getData(int month);

    void setData(Key key, HashMap<String, String> data);
}
