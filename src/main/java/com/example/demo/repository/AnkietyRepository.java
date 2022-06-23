package com.example.demo.repository;

import com.example.demo.entity.AnkietyEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AnkietyRepository {
    private EntityManager em;

    public AnkietyRepository() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        this.em = em;
    }

    public AnkietyEntity getAnkietaById(Integer id) {
        return em.find(AnkietyEntity.class, id);
    }

    public void insertAnkieta(Integer idZestawuPytan, Integer idZajec) {
        em.getTransaction().begin();
        em.createNativeQuery("insert into ankiety (id_zestawu_pytan, id_zajec ) values (:idZestawuPytan,:idZajec)")
                .setParameter("idZestawuPytan",idZestawuPytan)
                .setParameter("idZajec", idZajec)
                .executeUpdate();
        em.getTransaction().commit();
    }

    public Integer insertZestawPytan() {
        em.getTransaction().begin();
        em.createNativeQuery("insert into zestawy_pytan () values ()").executeUpdate();
        em.getTransaction().commit();
        return Integer.valueOf((String.valueOf(em.createNativeQuery("select LAST_INSERT_ID()").getResultList().get(0))));
    }

    public void insertPytanie(Integer idZestawuPytan, String tresc) {
        em.getTransaction().begin();
        em.createNativeQuery("insert into pytania (id_zestawu_pytan, tresc) values (:idZestawu, :tresc)")
                .setParameter("idZestawu", idZestawuPytan)
                .setParameter("tresc", tresc)
                .executeUpdate();
        em.getTransaction().commit();
    }

    public Integer insertZestawOdpowiedzi(Integer idStudenta, Integer idAnkiety) {
        em.getTransaction().begin();
        em.createNativeQuery("insert into zestaw_odpowiedzi (id_studenta, id_ankiety) values (:idStudenta,:idAnkiety)")
            .setParameter("idStudenta", idStudenta)
                .setParameter("idAnkiety", idAnkiety)
                .executeUpdate();
        em.getTransaction().commit();
        return Integer.valueOf((String.valueOf(em.createNativeQuery("select LAST_INSERT_ID()").getResultList().get(0))));
    }

    public void insertOdpowiedz(Integer idPytania, Integer idZestawuOdpowiedzi, String tresc) {
        em.getTransaction().begin();
        em.createNativeQuery("insert into odpowiedzi (tresc, id_pytania, id_zestawu_odpowiedzi) values (:tresc, :idPytania, :idZestawu)")
                        .setParameter("tresc", tresc)
                        .setParameter("idPytania", idPytania)
                        .setParameter("idZestawu", idZestawuOdpowiedzi)
                        .executeUpdate();
        em.getTransaction().commit();
    }

    public void insertAnkietaForAll(int idZestawu) {
        em.getTransaction().begin();
        em.createNativeQuery("insert into ankiety (id_zestawu_pytan, id_zajec)" +
                        "select distinct :idZestawu, z.id_zajec from zajecia z where z.forma_zaliczenia !=:egz")
                .setParameter("egz", "Egzamin")
                .setParameter("idZestawu", idZestawu)
                .executeUpdate();
        em.getTransaction().commit();
    }

    public void insertAnkietaForKierunek(int idZestawu, Integer idKierunku) {
        em.getTransaction().begin();
        em.createNativeQuery("insert into ankiety (id_zestawu_pytan, id_zajec)" +
                        "select distinct :idZestawu, z.id_zajec from zajecia z where z.id_kierunku =:idKierunku and z.forma_zaliczenia !=:egz")
                .setParameter("egz", "Egzamin")
                .setParameter("idKierunku", idKierunku)
                .setParameter("idZestawu", idZestawu)
                .executeUpdate();
        em.getTransaction().commit();
    }

    public boolean zestawOdpowiedziExists(Integer idStudenta, Integer idAnkiety) {
        return em.createQuery("select zo from ZestawOdpowiedziEntity zo where zo.idStudenta=:idStudenta and zo.idAnkiety=:idAnkiety")
                .setParameter("idStudenta", idStudenta)
                .setParameter("idAnkiety", idAnkiety)
                .getResultList().size() > 0;
    }
}
