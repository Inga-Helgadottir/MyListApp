package dtos;

import entities.PackingList;
import entities.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PackingListDTO {
    private int id;
    private String listName;
    private boolean template;
    private UserDTO user;
    private List<PackingListItemDTO> packingListItems = new ArrayList<>();

    public PackingListDTO(String listName, boolean template) {
        this.listName = listName;
        this.template = template;
    }

    public static List<PackingListDTO> getDtos(List<PackingList> packingLists){
        List<PackingListDTO> pldtos = new ArrayList<>();
        packingLists.forEach(packingList -> pldtos.add(new PackingListDTO(packingList)));
        return pldtos;
    }

    public PackingListDTO(PackingList pl){
        if(pl != null){
            this.id = pl.getId();
            this.listName = pl.getListName();
            this.template = pl.isTemplate();
            this.user = new UserDTO(pl.getUser());
            this.packingListItems = PackingListItemDTO.getDtos(pl.getPackingListItems());
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

    public List<PackingListItemDTO> getPackingListItems() {
        return packingListItems;
    }

    public void setPackingListItems(List<PackingListItemDTO> packingListItems) {
        this.packingListItems = packingListItems;
        for (PackingListItemDTO p : packingListItems) {
            p.setPackingList(this);
        }  }

    public void addPackingListItem(PackingListItemDTO packingListItem) {
        this.packingListItems.add(packingListItem);
        packingListItem.setPackingList(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PackingListDTO that = (PackingListDTO) o;
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
