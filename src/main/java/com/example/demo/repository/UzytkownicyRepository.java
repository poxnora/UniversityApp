package com.example.demo.repository;

import com.example.demo.entity.ProwadzacyEntity;
import com.example.demo.entity.StudenciEntity;
import com.example.demo.entity.UzytkownicyEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class UzytkownicyRepository {
    private EntityManager em;

    public void updateUzytkownik(UzytkownicyEntity uzytkownik, String hasloVal, String email) {
        em.getTransaction().begin();
        em.createQuery("update UzytkownicyEntity u set u.email =: email, u.haslo =: hasloVal where u.idUzytkownika =: idUzytkownika")
                .setParameter("hasloVal", hasloVal)
                .setParameter("idUzytkownika", uzytkownik.getIdUzytkownika())
                .setParameter("email", email)
                .executeUpdate();
        em.getTransaction().commit();

    }


    public void archiwizuj(Integer id) {
        em.getTransaction().begin();
        em.createQuery("update UzytkownicyEntity u set u.zarchiwizowany=1 where u.idUzytkownika =: idUzytkownika")
                .setParameter("idUzytkownika", id)
                .executeUpdate();
        em.getTransaction().commit();
    }

    public enum UserRole {
        ADMIN, STUDENT, PROWADZACY
    }

    public UzytkownicyEntity getUzytkownikById(Integer id) {return em.find(UzytkownicyEntity.class, id);}

    public UzytkownicyEntity getUzytkownikByEmail(String email) {
        List queryResult =  em.createQuery("select u from UzytkownicyEntity u where u.email=:email")
                .setParameter("email", email)
                .getResultList();
        if(queryResult.isEmpty()) return null;
        return (UzytkownicyEntity) queryResult.get(0);
    }

    public ArrayList<UzytkownicyEntity> getAllUzytkownicy() {
        return new ArrayList<>(em.createQuery("select u from UzytkownicyEntity u").getResultList());
    }

    public UzytkownicyRepository() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        this.em = em;
    }

    public Integer insertUzytkownikGetId(String email, String haslo) {
        Query query = em.createNativeQuery("insert into uzytkownicy (email, haslo, zarchiwizowany) values (:email, :haslo, 0)")
                        .setParameter("email", email)
                        .setParameter("haslo", haslo);
        em.getTransaction().begin();
        query.executeUpdate();
        em.getTransaction().commit();
        return Integer.valueOf((String.valueOf(em.createNativeQuery("select LAST_INSERT_ID()").getResultList().get(0))));
    }

    public int getNextId() {
        return (Integer) em.createQuery("Select max(u.idUzytkownika) from UzytkownicyEntity  u").getResultList().get(0) + 1;
    }

    public String getImie(UzytkownicyEntity user) {
        return switch(getRole(user)) {
            case ADMIN ->  user.getAdministratorzyByIdUzytkownika().getImie();
            case STUDENT -> user.getStudenciByIdUzytkownika().getImie();
            case PROWADZACY -> user.getProwadzacyByIdUzytkownika().getImie();
        }
        ;
    }

    public String getNazwisko(UzytkownicyEntity user) {
        return switch(getRole(user)) {
        case ADMIN ->  user.getAdministratorzyByIdUzytkownika().getNazwisko();
        case STUDENT -> user.getStudenciByIdUzytkownika().getNazwisko();
        case PROWADZACY -> user.getProwadzacyByIdUzytkownika().getNazwisko();
        };
    }

    public UserRole getRole(UzytkownicyEntity user) {
        if(user.getProwadzacyByIdUzytkownika() != null) {
            return UserRole.PROWADZACY;
        } else if(user.getAdministratorzyByIdUzytkownika() != null) {
            return UserRole.ADMIN;
        } else {
            return UserRole.STUDENT;
        }
    }

    public String getSingature(UzytkownicyEntity user) {
        if(user.getAdministratorzyByIdUzytkownika() != null) {
            return "Uczelnia";
        } else if (user.getStudenciByIdUzytkownika() != null) {
            StudenciEntity student = user.getStudenciByIdUzytkownika();
            return student.getImie() + " " + student.getNazwisko();
        }
        else {
            ProwadzacyEntity prowadzacy = user.getProwadzacyByIdUzytkownika();
            return prowadzacy.getImie() + " " + prowadzacy.getNazwisko();
        }
    }

    public void changePassword(Integer userId,String newPassword) {
        Query query = em.createQuery("update UzytkownicyEntity u set u.haslo=:haslo where u.idUzytkownika=:id").setParameter("haslo", newPassword).setParameter("id", userId);
        em.getTransaction().begin();
        query.executeUpdate();
        em.getTransaction().commit();
    }

}
