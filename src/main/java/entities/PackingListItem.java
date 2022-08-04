package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "PackingListItem")
public class PackingListItem {
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
    @JoinColumn(name = "packingList_id", referencedColumnName = "id", nullable = false)
    private PackingList packingList;

    public PackingListItem() {
    }

    public PackingListItem(String itemName, boolean checked) {
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

    public PackingList getPackingList() {
        return packingList;
    }

    public void setPackingList(PackingList packingList) {
        this.packingList = packingList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PackingListItem that = (PackingListItem) o;
        return id == that.id && checked == that.checked && itemName.equals(that.itemName) && packingList.equals(that.packingList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, itemName, checked, packingList);
    }

    @Override
    public String toString() {
        return "PackingListItem{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", checked=" + checked +
                '}';
    }
}
