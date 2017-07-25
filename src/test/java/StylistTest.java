import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.*;

public class StylistTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void stylist_instantiatesCorrectly_true() {
    Stylist testStylist = new Stylist("Johnny");
    assertEquals(true, testStylist instanceof Stylist);
  }

  @Test
  public void getName_stylistInstantiatesWithName_Johnny() {
    Stylist testStylist = new Stylist("Johnny");
    assertEquals("Johnny", testStylist.getName());
  }

  @Test
  public void save_insertsObjectIntoDatabase_Stylist() {
    Stylist testStylist = new Stylist("Johnny");
    testStylist.save();
    assertEquals(true, Stylist.all().get(0).equals(testStylist));
  }

  @Test
  public void save_assignsIdToObject() {
    Stylist testStylist = new Stylist("Johnny");
    testStylist.save();
    Stylist savedStylist = Stylist.all().get(0);
    assertEquals(testStylist.getId(), savedStylist.getId());
  }

  @Test
  public void all_returnsAllInstancesOfStylist_true() {
    Stylist firstStylist = new Stylist("Johnny");
    firstStylist.save();
    Stylist secondStylist = new Stylist("Benjamin");
    secondStylist.save();
    assertEquals(true, Stylist.all().get(0).equals(firstStylist));
    assertEquals(true, Stylist.all().get(1).equals(secondStylist));
  }

  // @Test
  // public void getClients_retrievesAllClientsFromDatabase_clientsList() {
  //   Stylist testStylist = new Stylist("Johnny");
  //   testStylist.save();
  //   Client firstClient = new Client("Izzy", testStylist.getId());
  //   firstClient.save();
  //   Client secondClient = new Client("Thomas", testStylist.getId());
  //   secondClient.save();
  //   Object[] clients = new Object[] { firstClient, secondClient };
  //   assertTrue(testStylist.getClients().containsAll(Arrays.asList(clients)));
  // }
}
