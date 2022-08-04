package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.mindrot.jbcrypt.BCrypt;

@Entity
@Table(name = "users")
public class User implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @NotNull
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column(name = "useName", length = 25)
  private String userName;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 255)
  @Column(name = "user_pass")
  private String userPass;

  @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
  private List<ToDoList> toDoLists = new ArrayList<>();

  @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
  private List<PackingList> packingLists = new ArrayList<>();

  public User() {}

   public boolean verifyPassword(String pw){
        return(pw.equals(userPass));
    }

  public User(String userName, String userPass) {
    this.userName = userName;

    this.userPass = BCrypt.hashpw(userPass, BCrypt.gensalt());
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public List<ToDoList> getToDoLists() {
    return toDoLists;
  }

  public void addToDoList(ToDoList toDoLists) {
    this.toDoLists.add(toDoLists);
    toDoLists.setUser(this);
  }

  public void setToDoLists(List<ToDoList> toDoLists) {
    this.toDoLists = toDoLists;
    for (ToDoList t : toDoLists){
      t.setUser(this);
    }
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserPass() {
    return this.userPass;
  }

  public void setUserPass(String userPass) {
    this.userPass = userPass;
  }

  public List<PackingList> getPackingLists() {
    return packingLists;
  }

  public void setPackingLists(List<PackingList> packingLists) {
    this.packingLists = packingLists;
    for (PackingList p : packingLists){
      p.setUser(this);
    }
  }

  public void addPackingList(PackingList packingList) {
    this.packingLists.add(packingList);
    packingList.setUser(this);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return id == user.id && userName.equals(user.userName) && userPass.equals(user.userPass) && toDoLists.equals(user.toDoLists) && packingLists.equals(user.packingLists);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, userName, userPass, toDoLists, packingLists);
  }

  @Override
  public String toString() {
    return "User{" +
            "id=" + id +
            ", userName='" + userName + '\'' +
            ", userPass='" + userPass + '\'' +
            ", toDoLists=" + toDoLists +
            ", packingLists=" + packingLists +
            '}';
  }
}
