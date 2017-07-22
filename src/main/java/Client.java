import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public class Client {
  private String name;
  private String note;
  private int stylistId;
  private int id;

  public Client(String name, String note, int stylistId) {
    this.name = name;
    this.note = note;
    this.stylistId = stylistId;
  }

  public String getName() {
    return name;
  }

  public String getNote() {
    return note;
  }

  public int getId() {
    return id;
  }

  public int getStylistId(){
    return stylistId;
  }


}
