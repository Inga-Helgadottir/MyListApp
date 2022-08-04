package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "PackingList")
public class PackingList {
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

    @OneToMany(mappedBy = "packingList", cascade = CascadeType.PERSIST)
    private List<PackingListItem> packingListItems = new ArrayList<>();

    public PackingList() {
    }

    public PackingList(String listName, boolean template) {
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

    public List<PackingListItem> getPackingListItems() {
        return packingListItems;
    }

    public void setPackingListItems(List<PackingListItem> packingListItems) {
        this.packingListItems = packingListItems;
        for (PackingListItem p : packingListItems) {
            p.setPackingList(this);
        }  }

    public void addPackingListItem(PackingListItem packingListItem) {
        this.packingListItems.add(packingListItem);
        packingListItem.setPackingList(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PackingList that = (PackingList) o;
        return id == that.id && template == that.template && listName.equals(that.listName) && user.equals(that.user) && packingListItems.equals(that.packingListItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, listName, template, user, packingListItems);
    }

    @Override
    public String toString() {
        return "PackingList{" +
                "id=" + id +
                ", listName='" + listName + '\'' +
                ", template=" + template +
                ", packingListItems=" + packingListItems +
                '}';
    }
}
