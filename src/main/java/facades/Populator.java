/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

/**
 *
 * @author tha
 */
public class Populator {
    public static void populate(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        User user = new User("user", "test123");
        ToDoList toDoList = new ToDoList("MyToDoListName", false);
        ToDoListItem toDoListItem = new ToDoListItem("MyToDoListItemName", false);
        PackingList packingList = new PackingList("MyPackingListName", false);
        PackingListItem packingListItem = new PackingListItem("MyPackingListItemName", false);

        user.addToDoList(toDoList);
        user.addPackingList(packingList);
        toDoList.addToDoListItem(toDoListItem);
        packingList.addPackingListItem(packingListItem);
        em.persist(user);
        em.persist(toDoList);
        em.persist(packingList);
        em.getTransaction().commit();
    }

    public static void main(String[] args) {
        populate();
    }
}
