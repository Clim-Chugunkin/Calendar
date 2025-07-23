import java.util.HashMap;
import java.util.Map;

public interface DataSource {

    HashMap<Key, HashMap<String,String>> getData(int month);

    void setData(Key key, HashMap<String, String> data);

    void createTable(String tableName, Map<String, String> data);
}
