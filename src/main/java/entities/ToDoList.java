package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "ToDoList")
public class ToDoList {
    @Id
    @NotNull
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Column(name = "listName")
    private String listName;
    @NotNull
    @Column(name = "template")
    private boolean template;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "toDoList", cascade = CascadeType.PERSIST)
    private List<ToDoListItem> toDoListItems = new ArrayList<>();

    public ToDoList() {
    }

    public ToDoList(String listName, boolean template) {
        this.listName = listName;
        this.template = template;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<ToDoListItem> getToDoListItems() {
        return toDoListItems;
    }

    public void setToDoListItems(List<ToDoListItem> toDoListItems) {
        this.toDoListItems = toDoListItems;
        for(ToDoListItem t : toDoListItems){
            t.setToDoList(this);
        }
    }

    public void addToDoListItem(ToDoListItem toDoListItem) {
        this.toDoListItems.add(toDoListItem);
        toDoListItem.setToDoList(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToDoList toDoList = (ToDoList) o;
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
