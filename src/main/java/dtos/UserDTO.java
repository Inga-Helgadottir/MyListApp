package dtos;

import entities.User;
import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserDTO {
  private int id;
  private String userName;
  private String userPass;
  private List<ToDoListDTO> toDoLists = new ArrayList<>();
  private List<PackingListDTO> packingLists = new ArrayList<>();

  public UserDTO(String userName, String userPass) {
    this.userName = userName;

    this.userPass = BCrypt.hashpw(userPass, BCrypt.gensalt());
  }

  public static List<UserDTO> getDtos(List<User> user){
    List<UserDTO> usdto = new ArrayList<>();
    user.forEach(u -> usdto.add(new UserDTO(u)));
    return usdto;
  }

  public UserDTO(User user){
    if(user != null){
      this.id = user.getId();
      this.userName = user.getUserName();
      this.userPass = user.getUserPass();
      this.toDoLists = ToDoListDTO.getDtos(user.getToDoLists());
      this.packingLists = PackingListDTO.getDtos(user.getPackingLists());

    }
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public List<ToDoListDTO> getToDoLists() {
    return toDoLists;
  }

  public void addToDoList(ToDoListDTO toDoLists) {
    this.toDoLists.add(toDoLists);
    toDoLists.setUser(this);
  }

  public void setToDoLists(List<ToDoListDTO> toDoLists) {
    this.toDoLists = toDoLists;
    for (ToDoListDTO t : toDoLists){
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

  public List<PackingListDTO> getPackingLists() {
    return packingLists;
  }

  public void setPackingLists(List<PackingListDTO> packingLists) {
    this.packingLists = packingLists;
    for (PackingListDTO p : packingLists){
      p.setUser(this);
    }
  }

  public void addPackingList(PackingListDTO packingList) {
    this.packingLists.add(packingList);
    packingList.setUser(this);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserDTO user = (UserDTO) o;
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
