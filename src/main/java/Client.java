import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public class Product {
  private String name;
  private String detail;
  private int stylistId;
  private int id;

  public Product(String name, String detail, int stylistId) {
    this.name = name;
    this.detail = detail;
    this.stylistId = stylistId;
  }

  public String getName() {
    return name;
  }

  public String getDetail() {
    return detail;
  }

  public int getId() {
    return id;
  }

  public int getStylistId(){
    return stylistId;
  }


  @Override
  public boolean equals(Object otherProduct){
    if (!(otherProduct instanceof Product)) {
      return false;
    } else {
      Product newProduct = (Product) otherProduct;
      return this.getName().equals(newProduct.getName()) &&
             this.getDetail()==(newProduct.getDetail());
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO clients (name, detail, stylistId) VALUES (:name, :detail, :stylistId)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("detail", this.detail)
        .addParameter("stylistId", this.stylistId)
        .executeUpdate()
        .getKey();
    }
  }

  public static List<Product> all() {
    String sql = "SELECT id, name, detail FROM clients";
    try(Connection con = DB.sql2o.open()) {
     return con.createQuery(sql).executeAndFetch(Product.class);
    }
  }

  public List<Object> getProducts() {
    List<Object> allProducts = new ArrayList<Object>();

    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients WHERE clientId=:id;";
      List<Product> clients = con.createQuery(sql)
        .addParameter("id", this.id)
        .throwOnMappingFailure(false)
        .executeAndFetch(Product.class);
      allProducts.addAll(clients);
    }
    return allProducts;
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

  public static Product find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients where id=:id";
      Product client = con.createQuery(sql)
        .addParameter("id", id)
        .throwOnMappingFailure(false)
        .executeAndFetchFirst(Product.class);
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

  public void update(String name, String detail) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE clients SET name = :name, detail = :detail WHERE id = :id";
      con.createQuery(sql)
        .addParameter("name", name)
        .addParameter("detail", detail)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

}
