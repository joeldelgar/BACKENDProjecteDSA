package edu.upc.dsa;

import edu.upc.dsa.DAO.GameSession;
import edu.upc.dsa.DAO.Session;
import edu.upc.dsa.models.User;
import edu.upc.dsa.UserManagerImpl;
import edu.upc.dsa.DAO.utils.ObjectHelper;
import edu.upc.dsa.DAO.utils.QueryHelper;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class QueryHelperTest {

    /*
    @Test
    public void testSetter() throws NoSuchMethodException {
        User u = new User("Miguel", "miguel.mateos@estudiant.upc.edu", "1234");
        ObjectHelper.setter(u,"name","Miguel");
        Assert.assertEquals(u.getName(),"Miguel");
    }
    @Test
    public void testGetter() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        User u = new User("Miguel", "miguel.mateos@estudiant.upc.edu", "1234");
        String name = (String) ObjectHelper.getter(u,"name");
        Assert.assertEquals(u.getName(),name);
    }
    @Test
    public void testInsert() throws SQLException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        User u = new User("Maria", "maria.garcia@estudiant.upc.edu", "qwerty");
        GameSession gs = new GameSession();
        Session s = gs.openSession();
        s.save(u);
    }

    @Test
    public static void setup() {
        User = new User("Miguel", "miguel.mateos@estudiant.upc.edu", "1234");
    }

    @Test
    public void testUser() {
        Assert.assertNotEquals(null, User.getId());
    }

    @Test
    public void testDAOAdd() {
        UserManagerImpl db = new UserManagerImpl();
        db.addUser("Arnau","arnau.millan@estudiant.upc.edu","trollo");
    }

    @Test
    public void testDaOGet() {
        UserManagerImpl db = new UserManagerImpl();
        User u = db.getUser("ID...");
        Assert.assertEquals(null, u);
        User u2 = db.getUser("ID...");
        Assert.assertEquals("Arnau", u2.getName());
    }

    @Test
    public void testDAOUpdate() {
        UserManagerImpl db = new UserManagerImpl();
        User u = db.getUser("BhZBkzOf");
        db.updateUser("Maria","maria.garcia@estudiant.upc.edu","qwerty", "ID-Maria");

        u = db.getUser("ID-Maria");
        Assert.assertEquals("Maria", u.getName());
    }

    @Test
    public void testQueryINSERT() {
        Assert.assertEquals("INSERT INTO User (ID, name, mail, password) VALUES (?, ?, ?, ?)",
                QueryHelper.createQueryINSERT(new User("Joel","joel.delgado@estudiant.upc.edu","asdfg")));
    }
     */

/*
    @Test
    public void testQueryINSERT2() {
        Assert.assertEquals("INSERT INTO Deparment (ID, name, description) VALUES (?, ?, ?)",
              //  QueryHelper.createQueryINSERT(new Game()));
    }

    @Test
    public void testQuerySELECT() {
        Assert.assertEquals("SELECT * FROM Employee WHERE ID = ?",
              //  QueryHelper.createQuerySELECT(new Employee("Juan", "lopez", 333333)));
    }

    @Test
    public void testQuerySELECT2() {
        Assert.assertEquals("SELECT * FROM Deparment WHERE ID = ?",
             //   QueryHelper.createQuerySELECT(new Deparment("ENTEL", "ENGINYERIA TELEMÀTICA")));
    }

    @Test
    public void testQueryDELETE() {
        Assert.assertEquals("DELETE * FROM Employee WHERE ID = ?",
                QueryHelper.createQueryDELETE(new Employee("Juan","lopez", 333333)));
    }

    @Test
    public void testQueryUPDATE(){
        Assert.assertEquals("UPDATE Employee SET name = ?, surname = ?, salary = ? WHERE ID = ?",
                QueryHelper.createQueryUPDATE(new Employee("Juan", "lopez", 333333)));
    }
*/
}
