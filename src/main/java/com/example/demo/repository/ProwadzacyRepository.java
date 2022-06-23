package com.example.demo.repository;

import com.example.demo.entity.ProwadzacyEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class ProwadzacyRepository {
    private EntityManager em;

    public ProwadzacyRepository() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        this.em = em;
    }

    public void insertProwadzacy(Integer id, String imie, String nazwisko) {
        em.getTransaction().begin();
        em.createNativeQuery("insert into prowadzacy (id_prowadzacego, imie, nazwisko) values (:id,:imie,:nazwisko)")
                .setParameter("imie", imie)
                .setParameter("nazwisko", nazwisko)
                .setParameter("id", id).executeUpdate();
        em.getTransaction().commit();
    }

    public void updateProwadzacy(Integer id, String imie, String nazwisko) {
        em.getTransaction().begin();
        em.createQuery("update ProwadzacyEntity a set a.imie =: imie, a.nazwisko =: nazwisko where a.idProwadzacego=: id")
                .setParameter("imie",imie)
                .setParameter("nazwisko", nazwisko)
                .setParameter("id" ,id).executeUpdate();
        em.getTransaction().commit();
    }
    public ProwadzacyEntity getProwadzacyById(Integer id) {
        return em.find(ProwadzacyEntity.class, id);
    }

    public ArrayList<ProwadzacyEntity> getAllProwadzacy() {
        List queryResult = em.createQuery("select p from ProwadzacyEntity p").getResultList();
        return new ArrayList<>(queryResult);
    }

    public String getFullNameById(Integer id) {
        ProwadzacyEntity entity = getProwadzacyById(id);
        return entity.getImie() + " " + entity.getNazwisko();
    }
}