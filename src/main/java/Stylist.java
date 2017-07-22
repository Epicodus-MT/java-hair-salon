import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class Stylist {
  private String name;
  private int id;

  public Stylist(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public int getId() {
    return id;
  }


}
