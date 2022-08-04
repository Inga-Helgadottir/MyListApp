package dtos;

import entities.ToDoList;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ToDoListDTO {
    private int id;
    private String listName;
    private boolean template;
    private UserDTO user;
    private List<ToDoListItemDTO> toDoListItems = new ArrayList<>();

    public ToDoListDTO(String listName, boolean template) {
        this.listName = listName;
        this.template = template;
    }

    public static List<ToDoListDTO> getDtos(List<ToDoList> toDoLists){
        List<ToDoListDTO> tdldtos = new ArrayList<>();
        toDoLists.forEach(toDoList -> tdldtos.add(new ToDoListDTO(toDoList)));
        return tdldtos;
    }

    public ToDoListDTO(ToDoList tdl){
        if(tdl != null){
            this.id = tdl.getId();
            this.listName = tdl.getListName();
            this.template = tdl.isTemplate();
            this.user = new UserDTO(tdl.getUser());
            this.toDoListItems = ToDoListItemDTO.getDtos(tdl.getToDoListItems());
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public boolean isTemplate() {
        return template;
    }

    public void setTemplate(boolean template) {
        this.template = template;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public List<ToDoListItemDTO> getToDoListItems() {
        return toDoListItems;
    }

    public void setToDoListItems(List<ToDoListItemDTO> toDoListItems) {
        this.toDoListItems = toDoListItems;
        for(ToDoListItemDTO t : toDoListItems){
            t.setToDoList(this);
        }
    }

    public void addToDoListItem(ToDoListItemDTO toDoListItem) {
        this.toDoListItems.add(toDoListItem);
        toDoListItem.setToDoList(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToDoListDTO toDoList = (ToDoListDTO) o;
        return id == toDoList.id && template == toDoList.template && listName.equals(toDoList.listName) && user.equals(toDoList.user) && toDoListItems.equals(toDoList.toDoListItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, listName, template, user, toDoListItems);
    }

    @Override
    public String toString() {
        return "ToDoList{" +
                "id=" + id +
                ", listName='" + listName + '\'' +
                ", template=" + template +
                ", toDoListItems=" + toDoListItems +
                '}';
    }
}
