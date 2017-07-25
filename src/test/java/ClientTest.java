import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.sql.Timestamp;
import java.util.Date;
import java.text.DateFormat;

public class ClientTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void client_instantiatesCorrectly_true() {
    Client testClient = new Client("Johnny", "buzz cut", 2);
    assertEquals(true, testClient instanceof Client);
  }

  @Test
  public void Client_instantiatesWithName_String() {
    Client testClient = new Client("Johnny", "buzz cut", 2);
    assertEquals("Johnny", testClient.getName());
  }

  @Test
  public void equals_returnsTrueIfNameAndStylistIdAreSame_true() {
    Client testClient = new Client("Johnny", "buzz cut", 2);
    Client anotherClient = new Client("Johnny", "buzz cut", 2);
    assertTrue(testClient.equals(anotherClient));
  }

  @Test
  public void save_successfullyAddsClientToDatabase_List() {
    Client testClient = new Client("Johnny", "buzz cut", 2);
    testClient.save();
    assertTrue(Client.all().get(0).equals(testClient));
  }

  @Test
  public void save_assignsIdToClient() {
    Client testClient = new Client("Johnny", "buzz cut", 2);
    testClient.save();
    Client savedClient = Client.all().get(0);
    assertEquals(savedClient.getId(), testClient.getId());
  }

  @Test
  public void all_returnsAllInstancesOfClient_true() {
    Client firstClient = new Client("Johnny", "buzz cut", 2);
    firstClient.save();
    Client secondClient = new Client("Ben", "comb over", 4);
    secondClient.save();
    assertEquals(true, Client.all().get(0).equals(firstClient));
    assertEquals(true, Client.all().get(1).equals(secondClient));
  }

  @Test
  public void find_returnsClientWithSameId_secondClient() {
    Client firstClient = new Client("Johnny", "buzz cut", 2);
    firstClient.save();
    Client secondClient = new Client("Ben", "comb over", 4);
    secondClient.save();
    assertEquals(Client.find(secondClient.getId()), secondClient);
  }
}
