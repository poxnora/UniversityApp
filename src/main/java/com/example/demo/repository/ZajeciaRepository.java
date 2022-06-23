package com.example.demo.repository;

import com.example.demo.entity.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class ZajeciaRepository {

        private EntityManager em;

        public ZajeciaRepository() {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
            EntityManager em = emf.createEntityManager();
            this.em = em;
        }

        public void refresh() {
            this.em.close();
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
            EntityManager em = emf.createEntityManager();
            this.em = em;
        }

        public ZajeciaEntity getZajeciaById(Integer id) {
            return em.find(ZajeciaEntity.class, id);
        }

        public void insertZajecia(Integer idPrzedmiotu, Integer idProwadzacego, Integer idKierunku, String formaZajec, String formaZaliczenia, Integer liczbaGodzin, Integer semestr) {
            Query query = em.createNativeQuery("insert into zajecia (id_przedmiotu, id_prowadzacego, id_kierunku, forma_zajec, forma_zaliczenia, liczba_godzin, semestr) values (:idPrzedmiotu, :idProwadzacego, :idKierunku, :formaZajec,:formaZaliczenia,:liczbaGodzin,:semestr)")
                    .setParameter("idPrzedmiotu", idPrzedmiotu)
                    .setParameter("idProwadzacego", idProwadzacego)
                    .setParameter("idKierunku", idKierunku)
                    .setParameter("formaZajec", formaZajec)
                    .setParameter("formaZaliczenia", formaZaliczenia)
                    .setParameter("liczbaGodzin", liczbaGodzin)
                    .setParameter("semestr", semestr);
            em.getTransaction().begin();
            query.executeUpdate();
            em.getTransaction().commit();
            refresh();
        }

        public boolean checkIfZajecia(Integer idPrzedmiotu, Integer idProwadzacego, String formaZajec) {
            List queryResult = em.createQuery("select z from ZajeciaEntity z where z.idPrzedmiotu =: idPrzedmiotu and z.idProwadzacego =: idProwadzacego and z.formaZajec=:formaZajec")
                    .setParameter("idPrzedmiotu", idPrzedmiotu)
                    .setParameter("idProwadzacego", idProwadzacego)
                    .setParameter("formaZajec", formaZajec)
                    .getResultList();

            return queryResult.size() > 0;
        }


        public ArrayList<ZajeciaEntity> getZajeciaByStudentId(Integer studentId) {
            StudentRepository sr = new StudentRepository();
            StudenciEntity student = sr.getStudentById(studentId);
            KierunkiEntity kierunek = student.getKierunkiByIdKierunku();
            List queryResult = em.createQuery("select z from ZajeciaEntity z where z.idKierunku=:idKierunku ")
                    .setParameter("idKierunku", kierunek.getIdKierunku())
                    .getResultList();
            if(queryResult.size() < 1) return null;
            return new ArrayList<ZajeciaEntity>(queryResult);
        }

        public ArrayList<ZajeciaEntity> getZajeciaByKierunek(KierunkiEntity kierunek)
        {
            List queryResult = em.createQuery("select z from ZajeciaEntity z where z.idKierunku =:idKierunku")
                    .setParameter("idKierunku", kierunek.getIdKierunku())
                    .getResultList();
            return new ArrayList<>(queryResult);
        }

        public ArrayList<ZajeciaEntity> getZajeciaByProwadzacyId(Integer prowadzacyId) {
            ProwadzacyEntity prowadzacy = new ProwadzacyRepository().getProwadzacyById(prowadzacyId);
            return new ArrayList<>(prowadzacy.getZajeciasByIdProwadzacego());
        }

        public ArrayList<ZajeciaEntity> getZajeciaByStudentIdAndSemestr(Integer studentId, Integer semestr) {
            StudentRepository sr = new StudentRepository();
            StudenciEntity student = sr.getStudentById(studentId);
            KierunkiEntity kierunek = student.getKierunkiByIdKierunku();
            List queryResult = em.createQuery("select z from ZajeciaEntity z where z.idKierunku=:idKierunku and z.semestr=:semestr")
                    .setParameter("idKierunku", kierunek.getIdKierunku())
                    .setParameter("semestr", semestr)
                    .getResultList();
            if(queryResult.size() < 1) return null;
            return new ArrayList<ZajeciaEntity>(queryResult);
        }




}
