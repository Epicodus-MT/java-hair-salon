import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public class Client {
  private String name;
  private String details;
  private int stylistId;
  private int id;

  public Client(String name, String details, int stylistId) {
    this.name = name;
    this.details = details;
    this.stylistId = stylistId;
  }

  public String getName() {
    return name;
  }

  public String getDetails() {
    return details;
  }

  public int getId() {
    return id;
  }

  public int getStylistId(){
    return stylistId;
  }


  @Override
  public boolean equals(Object otherClient){
    if (!(otherClient instanceof Client)) {
      return false;
    } else {
      Client newClient = (Client) otherClient;
      return this.getName().equals(newClient.getName())
          && this.getDetails().equals(newClient.getDetails());
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO clients (name, details, stylistId) VALUES (:name, :details, :stylistId)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("details", this.details)
        .addParameter("stylistId", this.stylistId)
        .executeUpdate()
        .getKey();
    }
  }

  public static List<Client> all() {
    String sql = "SELECT id, name, details FROM clients";
    try(Connection con = DB.sql2o.open()) {
     return con.createQuery(sql).executeAndFetch(Client.class);
    }
  }

  public List<Object> getClients() {
    List<Object> allClients = new ArrayList<Object>();

    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients WHERE clientId=:id;";
      List<Client> clients = con.createQuery(sql)
        .addParameter("id", this.id)
        .throwOnMappingFailure(false)
        .executeAndFetch(Client.class);
      allClients.addAll(clients);
    }
    return allClients;
  }

  public List<Stylist> getStylists() {
    try(Connection con = DB.sql2o.open()){
      String joinQuery = "SELECT stylist_id FROM stylists_clients WHERE client_id = :client_id";
      List<Integer> stylistIds = con.createQuery(joinQuery)
        .addParameter("client_id", this.getId())
        .executeAndFetch(Integer.class);

      List<Stylist> stylists = new ArrayList<Stylist>();

      for (Integer stylistId : stylistIds) {
        String stylistQuery = "SELECT * FROM stylists WHERE id = :stylistId";
        Stylist stylist = con.createQuery(stylistQuery)
          .addParameter("stylistId", stylistId)
          .executeAndFetchFirst(Stylist.class);
        stylists.add(stylist);
      }
      return stylists;
    }
  }

  public void leaveStylist(Stylist stylist){
    try(Connection con = DB.sql2o.open()){
      String joinRemovalQuery = "DELETE FROM stylists_clients WHERE stylist_id = :stylistId AND client_id = :clientId;";
      con.createQuery(joinRemovalQuery)
        .addParameter("stylistId", stylist.getId())
        .addParameter("clientId", this.getId())
        .executeUpdate();
    }
  }

  public static Client find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients where id=:id";
      Client client = con.createQuery(sql)
        .addParameter("id", id)
        .throwOnMappingFailure(false)
        .executeAndFetchFirst(Client.class);
    return client;
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
    String sql = "DELETE FROM clients WHERE id = :id;";
    con.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

  public void update(String name, String details) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE clients SET name = :name, details = :details WHERE id = :id";
      con.createQuery(sql)
        .addParameter("name", name)
        .addParameter("details", details)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

}
