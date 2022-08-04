package dtos;

import entities.PackingList;
import entities.ToDoListItem;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ToDoListItemDTO {
    private int id;
    private String itemName;
    private boolean checked;
    private ToDoListDTO toDoList;

    public ToDoListItemDTO(String itemName, boolean checked) {
        this.itemName = itemName;
        this.checked = checked;
    }

    public static List<ToDoListItemDTO> getDtos(List<ToDoListItem> toDoListItems){
        List<ToDoListItemDTO> tdldtos = new ArrayList<>();
        toDoListItems.forEach(toDoListItem -> tdldtos.add(new ToDoListItemDTO(toDoListItem)));
        return tdldtos;
    }

    public ToDoListItemDTO(ToDoListItem ti){
        if(ti != null){
            this.id = ti.getId();
            this.checked = ti.isChecked();
            this.itemName = ti.getItemName();
        }
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public ToDoListDTO getToDoList() {
        return toDoList;
    }

    public void setToDoList(ToDoListDTO toDoList) {
        this.toDoList = toDoList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToDoListItemDTO that = (ToDoListItemDTO) o;
        return id == that.id && checked == that.checked && itemName.equals(that.itemName) && toDoList.equals(that.toDoList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, itemName, checked, toDoList);
    }

    @Override
    public String toString() {
        return "ToDoListItem{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", checked=" + checked +
                '}';
    }
}
