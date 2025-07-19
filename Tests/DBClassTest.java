import org.junit.Before;
import org.junit.Test;


public class DBClassTest {
    private DBClass db;

    @Before
    public void setUp()  {
        db = new DBClass("mydb","root","root");
    }

    @Test
    public void test(){
        //db.closeDB();
    }
}