package com.example.demo.repository;

import com.example.demo.entity.AdministratorzyEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;

public class AdministratorzyRepository {
    private EntityManager em;

    public AdministratorzyRepository() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        this.em = em;
    }

    public AdministratorzyEntity getAministratorById(Integer id) {
        return em.find(AdministratorzyEntity.class, id);
    }

    public ArrayList<AdministratorzyEntity> getAllAdministratorzy() {
        return new ArrayList<>(
                em.createQuery("select a from AdministratorzyEntity  a").getResultList()
        );
    }

    public void updateAdministrator(Integer id, String imie, String nazwisko) {
        em.getTransaction().begin();
        em.createQuery("update AdministratorzyEntity a set a.imie =: imie, a.nazwisko =: nazwisko where a.idAdministratora =: id")
                .setParameter("imie",imie)
                .setParameter("nazwisko", nazwisko)
                .setParameter("id" ,id).executeUpdate();
        em.getTransaction().commit();
    }

    public void insertAdministrator(Integer id, String imie, String nazwisko) {
        em.getTransaction().begin();
        em.createNativeQuery("insert into administratorzy (id_administratora, imie, nazwisko) values (:id,:imie,:nazwisko)")
                        .setParameter("imie", imie)
                                .setParameter("nazwisko", nazwisko)
                                        .setParameter("id", id).executeUpdate();
        em.getTransaction().commit();
    }
}
