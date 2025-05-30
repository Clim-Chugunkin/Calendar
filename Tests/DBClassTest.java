import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DBClassTest {
    private DBClass db;

    @Before
    public void setUp() throws Exception {
        db = new DBClass("mydb","root","root");
    }

    @Test
    public void test(){
        System.out.println(db.execSQL());
        db.closeDB();
    }
}