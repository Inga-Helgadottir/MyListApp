package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "ToDoListItem")
public class ToDoListItem {
    @Id
    @NotNull
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Column(name = "itemName")
    private String itemName;
    @NotNull
    @Column(name = "checked")
    private boolean checked;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "toDoList_id", referencedColumnName = "id", nullable = false)
    private ToDoList toDoList;

    public ToDoListItem() {
    }

    public ToDoListItem(String itemName, boolean checked) {
        this.itemName = itemName;
        this.checked = checked;
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

    public ToDoList getToDoList() {
        return toDoList;
    }

    public void setToDoList(ToDoList toDoList) {
        this.toDoList = toDoList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToDoListItem that = (ToDoListItem) o;
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
