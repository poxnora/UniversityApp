package com.example.demo.repository;

import com.example.demo.entity.PrzedmiotyEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class PrzedmiotRepository {
    private EntityManager em;

    public PrzedmiotRepository() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        this.em = em;
    }

    public PrzedmiotyEntity getPrzedmiotById(Integer id) {
        return em.find(PrzedmiotyEntity.class, id);
    }

    public void insertPrzedmiot(String nazwa, Integer ects) {
        em.getTransaction().begin();
        em.createNativeQuery("insert into przedmioty (nazwa,ects) values (:nazwa,:ects)")
                .setParameter("nazwa", nazwa)
                .setParameter("ects", ects).executeUpdate();
        em.getTransaction().commit();
    }

    public void updatePrzedmiot(String nazwa, Integer ects, Integer id) {
        em.getTransaction().begin();
        em.createQuery("update PrzedmiotyEntity p set p.nazwa =: nazwa, p.ects =: ects where p.idPrzedmiotu =: id")
                .setParameter("nazwa", nazwa)
                .setParameter("ects", ects)
                .setParameter("id", id)
                .executeUpdate();
        em.getTransaction().commit();
    }

    public ArrayList<PrzedmiotyEntity> getPrzedmiotyByIdProwadzacego(Integer idProwadzacego) {
        return new ArrayList<>(em.createQuery("select distinct p from PrzedmiotyEntity p inner join ZajeciaEntity z on p.idPrzedmiotu = z.idPrzedmiotu where z.idProwadzacego =: idProwadzacego")
                .setParameter("idProwadzacego", idProwadzacego)
                .getResultList());
    }

    public boolean przedmiotExists(String nazwa) {
        return em.createQuery("select p from PrzedmiotyEntity p where p.nazwa =: nazwa ")
                .setParameter("nazwa", nazwa)
                .getResultList().size() > 0;
    }

    public PrzedmiotyEntity getPrzedmiotByNazwa(String nazwa) {
        List<PrzedmiotyEntity> queryResult = em.createQuery("Select p from PrzedmiotyEntity p where p.nazwa = :nazwa")
                .setParameter("nazwa", nazwa)
                .getResultList();
        return queryResult.get(0);
    }


    public Integer getEctsByNazwa(String nazwa) {
        List<PrzedmiotyEntity> queryResult = em.createQuery("Select p from PrzedmiotyEntity p where p.nazwa = :nazwa")
                .setParameter("nazwa", nazwa)
                .getResultList();
        return queryResult.get(0).getEcts();
    }

    public ArrayList<PrzedmiotyEntity> getAllPrzedmioty() {
        List<PrzedmiotyEntity> queryResult = em.createQuery("Select p from PrzedmiotyEntity p").getResultList();
        return new ArrayList<>(queryResult);
    }

    public ArrayList<PrzedmiotyEntity> getPrzedmotyByKierunekId(Integer kierunekId) {
        List<PrzedmiotyEntity> queryResult = em.createQuery("Select distinct p from PrzedmiotyEntity p inner join ZajeciaEntity z on z.idPrzedmiotu = p.idPrzedmiotu and z.idKierunku =:idKierunku")
                .setParameter("idKierunku", kierunekId)
                .getResultList();
        return new ArrayList<>(queryResult);
    }



}

