package facades;

import dtos.*;
import entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class Facade {
    private static Facade instance;
    private static EntityManagerFactory emf;

    private Facade() {
    }

    public static Facade getFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new Facade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<ToDoListDTO> getAllUsersToDoLists(){
        //this function has not been tested
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<ToDoList> query = em.createQuery("SELECT t FROM ToDoList t", ToDoList.class);
            List<ToDoList> toDoLists = query.getResultList();
            List<ToDoListDTO> tdldtos = ToDoListDTO.getDtos(toDoLists);
            return tdldtos;
        }finally {
            em.close();
        }
    }

    public List<PackingListDTO> getAllUsersPackingLists(){
        //this function has not been tested
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<PackingList> query = em.createQuery("SELECT p FROM PackingList p", PackingList.class);
            List<PackingList> packingLists = query.getResultList();
            List<PackingListDTO> pldtos = PackingListDTO.getDtos(packingLists);
            return pldtos;
        }finally {
            em.close();
        }
    }

    public PackingListDTO getAPackingListsById(int id){
        //this function has not been tested
        EntityManager em = emf.createEntityManager();
        try{
            PackingList p = em.find(PackingList.class, id);
            return new PackingListDTO(p);
        }finally {
            em.close();
        }
    }

    public ToDoListDTO getAToDoListsById(int id){
        //this function has not been tested
        EntityManager em = emf.createEntityManager();
        try{
            ToDoList t = em.find(ToDoList.class, id);
            return new ToDoListDTO(t);
        }finally {
            em.close();
        }
    }

    public PackingListDTO getAPackingListsByName(String name){
        //this function has not been tested
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<PackingList> query = em.createQuery("SELECT p FROM PackingList p WHERE p.listName = :packingListName", PackingList.class);
            query.setParameter("packingListName", name);
            PackingList pl = query.getSingleResult();
            return new PackingListDTO(pl);
        }finally {
            em.close();
        }
    }

    public ToDoListDTO getAToDoListsByName(String name){
        //this function has not been tested
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<ToDoList> query = em.createQuery("SELECT t FROM ToDoList t WHERE t.listName = :toDoListName", ToDoList.class);
            query.setParameter("toDoListName", name);
            ToDoList tdl = query.getSingleResult();
            return new ToDoListDTO(tdl);
        }finally {
            em.close();
        }
    }

    /*
    public String checkUncheckById(int id){}

    public String makeOrRemoveTemplate(int id){}

    public PackingListDTO makePakingList(PackingList p){}

    public ToDoListDTO makeToDoList(ToDoList t){}

    public UserDTO makeUser(User u){}

    public PackingListItemDTO makePackingListItem(PackingListItem pi){}

    public ToDoListItemDTO makeToDoListItem(ToDoListItem tdi){}

    public List<User> getAllUsers(){}

    public User makeUser(){}

    public PackingListDTO makePackingList(){}

    public ToDoListDTO makeToDoList(){}

    public PackingListDTO changePackingList(){}

    public ToDoListDTO changeToDoList(){}

    public UserDTO makeUser(){}

    public UserDTO changeUser(){}

    public PackingListItemDTO addToPackingList(){}

    public ToDoListItemDTO addToToDoList(){}

    public PackingListDTO dublicatePackingList(){}

    public ToDoListDTO dublicateToDoList(){}

    public PackingListDTO uncheckAllInPackingList(){}

    public ToDoListDTO uncheckAllInToDoList(){}

    */
/*
    public ManySideDTO create(ManySide newManySide){
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            ManySide ms = new ManySide(newManySide.getName());
            ms.setOtherManySides(newManySide.getOtherManySides());
            OneSide os = new OneSide(newManySide.getOneSide().getName());
            OtherOneSide oos = new OtherOneSide(newManySide.getOneSide().getOtherOneSide().getName());

            ms.setOneSide(os);
            os.setOtherOneSide(oos);

            em.persist(ms);

            em.getTransaction().commit();
            return new ManySideDTO(ms);
        }finally {
            em.close();
        }
    }

    public List<ManySideDTO> read(){
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<ManySide> query = em.createQuery("SELECT m FROM ManySide m", ManySide.class);
            List<ManySide> manySides = query.getResultList();
            List<ManySideDTO> msdtos = ManySideDTO.getDtos(manySides);
            return msdtos;
        }finally {
            em.close();
        }
    }

    public List<ManySideDTO> readWhere(String nameOfOneSide){
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<ManySide> query = em.createQuery("SELECT m FROM ManySide m WHERE m.oneSide.name = :oneSideName", ManySide.class);
            query.setParameter("oneSideName", nameOfOneSide);
            List<ManySide> manySides = query.getResultList();
            List<ManySideDTO> msdtos = ManySideDTO.getDtos(manySides);
            return msdtos;
        }finally {
            em.close();
        }
    }

    public ManySideDTO update(ManySide newManySide){
        EntityManager em = emf.createEntityManager();
        try{
            ManySide ms = em.find(ManySide.class, newManySide.getId());
            ms.setName(newManySide.getName());
            ms.setOtherManySides(newManySide.getOtherManySides());
            ms.setOneSide(newManySide.getOneSide());
            em.persist(ms);
            return new ManySideDTO(ms);
        }finally {
            em.close();
        }
    }

    public ManySideDTO delete(int id){
        EntityManager em = emf.createEntityManager();
        try{
            ManySide ms = em.find(ManySide.class, id);
            em.getTransaction().begin();
            em.remove(ms);
            em.getTransaction().commit();
            return new ManySideDTO(ms);
        }finally {
            em.close();
        }
    }

    public ManySideDTO getById(int id){
        EntityManager em = emf.createEntityManager();
        try{
            ManySide c = em.find(ManySide.class, id);
            return new ManySideDTO(c);
        }finally {
            em.close();
        }
    }*/
}
