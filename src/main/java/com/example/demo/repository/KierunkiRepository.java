package com.example.demo.repository;

import com.example.demo.entity.KierunkiEntity;
import com.example.demo.entity.ProwadzacyEntity;
import com.example.demo.entity.ZajeciaEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class KierunkiRepository {
        private EntityManager em;

        public KierunkiRepository() {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
            EntityManager em = emf.createEntityManager();
            this.em = em;
        }

        public void insertKierunek(String nazwa, int rocznik) {
            em.getTransaction().begin();
            em.createNativeQuery("insert into kierunki (nazwa,rocznik) values (:nazwa,:rocznik)")
                    .setParameter("nazwa", nazwa)
                    .setParameter("rocznik", rocznik)
                    .executeUpdate();
            em.getTransaction().commit();
        }

        public boolean kierunekExists(String nazwa, int rocznik) {
            return em.createQuery("select k from KierunkiEntity k where k.rocznik =: rocznik and k.nazwa =: nazwa")
                    .setParameter("rocznik", rocznik)
                    .setParameter("nazwa", nazwa)
                    .getResultList().size() > 0;
        }

        public void updateKierunek(String nazwa, int rocznik, int id) {
            em.getTransaction().begin();
            em.createQuery("update KierunkiEntity  k set k.rocznik = :rocznik, k.nazwa =: nazwa where k.idKierunku = :id")
                    .setParameter("nazwa", nazwa)
                    .setParameter("rocznik", rocznik)
                    .setParameter("id", id)
                    .executeUpdate();
            em.getTransaction().commit();
        }
        public KierunkiEntity getKierunekById(Integer id) {return em.find(KierunkiEntity.class, id);}

        public ArrayList<KierunkiEntity> getAllKierunki() {
            List queryResult = em.createQuery("Select k from KierunkiEntity k order by k.nazwa, k.rocznik").getResultList();
            if(queryResult.size() < 1) return null;
            return new ArrayList<>(queryResult);
        }

        public KierunkiEntity getKierunekByNazwaAndRocznik(String nazwa, Integer rocznik) {
            List queryResult = em.createQuery("select k from KierunkiEntity k where k.nazwa=:nazwa and k.rocznik=:rocznik")
                    .setParameter("nazwa", nazwa)
                    .setParameter("rocznik", rocznik)
                    .getResultList();
            if(queryResult.size() < 1) return null;
            return (KierunkiEntity) queryResult.get(0);
        }

        public ArrayList<KierunkiEntity> getKierunkiProwadzacego(Integer idProwadzacego) {
            ProwadzacyEntity prowadzacy = new ProwadzacyRepository().getProwadzacyById(idProwadzacego);
            HashSet<KierunkiEntity> kierunki = new HashSet();
            ArrayList<ZajeciaEntity> zajecia = new ArrayList<>(prowadzacy.getZajeciasByIdProwadzacego());
            for(ZajeciaEntity z: zajecia) {
                kierunki.add(z.getKierunkiByIdKierunku());
            }
            return new ArrayList<>(kierunki);
        }

    }
