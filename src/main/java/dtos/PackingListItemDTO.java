package dtos;

import entities.PackingListItem;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PackingListItemDTO {
    private int id;
    private String itemName;
    private boolean checked;
    private PackingListDTO packingList;

    public PackingListItemDTO(String itemName, boolean checked) {
        this.itemName = itemName;
        this.checked = checked;
    }

    public static List<PackingListItemDTO> getDtos(List<PackingListItem> packingListItem){
        List<PackingListItemDTO> plidtos = new ArrayList<>();
        packingListItem.forEach(packingListItem1 -> plidtos.add(new PackingListItemDTO(packingListItem1)));
        return plidtos;
    }

    public PackingListItemDTO(PackingListItem pli){
        if(pli != null){
            this.id = pli.getId();
            this.itemName = pli.getItemName();
            this.checked = pli.isChecked();
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

    public PackingListDTO getPackingList() {
        return packingList;
    }

    public void setPackingList(PackingListDTO packingList) {
        this.packingList = packingList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PackingListItemDTO that = (PackingListItemDTO) o;
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
