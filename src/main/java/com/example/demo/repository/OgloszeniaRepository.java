package com.example.demo.repository;

import com.example.demo.entity.KierunkiEntity;
import com.example.demo.entity.OgloszeniaEntity;
import com.example.demo.entity.StudenciEntity;
import com.example.demo.entity.ZajeciaEntity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class OgloszeniaRepository {
    private EntityManager em;

    public OgloszeniaRepository() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        this.em = em;
    }

    public OgloszeniaEntity getOgloszenieById(Integer id) {return em.find(OgloszeniaEntity.class, id);}

    public Integer insertOgloszenie(String tytul, String tresc, Integer idUzytkownika, Byte dlaWszystkich) {
        em.getTransaction().begin();
        em.createNativeQuery("INSERT INTO ogloszenia (tytul, tresc, id_uzytkownika, dla_wszystkich) VALUES (:tytul, :tresc, :idUzytkownika, :dlaWszystkich)").setParameter("tytul", tytul)
                .setParameter("tresc", tresc)
                .setParameter("idUzytkownika", idUzytkownika)
                .setParameter("dlaWszystkich", dlaWszystkich)
                .executeUpdate();
        em.getTransaction().commit();
    return Integer.valueOf((String.valueOf(em.createNativeQuery("select LAST_INSERT_ID()").getResultList().get(0))));
    }

    public void insertOgloszeniaKierunki(Integer ogloszenieId, Integer kierunekId) {
        em.getTransaction().begin();
        em.createNativeQuery("insert into ogloszenia_kierunki (id_ogloszenia, id_kierunku) values (:ogloszenieId, :kierunekId)")
                .setParameter("ogloszenieId", ogloszenieId)
                .setParameter("kierunekId", kierunekId)
                .executeUpdate();
        em.getTransaction().commit();;
    }

    public void insertOgloszeniaZajecia(Integer ogloszenieId, Integer zajeciaId)  {
        em.getTransaction().begin();
        em.createNativeQuery("insert into ogloszenia_zajecia (id_ogloszenia, id_zajec) values (:ogloszenieId, :zajeciaId)")
                .setParameter("ogloszenieId", ogloszenieId)
                .setParameter("zajeciaId", zajeciaId)
                .executeUpdate();
        em.getTransaction().commit();;
    }

    public ArrayList<OgloszeniaEntity> getOgloszeniaByKierunek(String nazwaKierunku, Integer rocznik) {
        KierunkiRepository kr = new KierunkiRepository();
        KierunkiEntity kierunek = kr.getKierunekByNazwaAndRocznik(nazwaKierunku, rocznik);
        List queryResult = em.createQuery("select o from OgloszeniaEntity o inner join o.ogloszeniaKierunkisByIdOgloszenia ok where ok.idKierunku =:id order by o.idOgloszenia desc")
                .setParameter("id", kierunek.getIdKierunku())
                .getResultList();
        return new ArrayList<OgloszeniaEntity>(queryResult);
    }

    public ArrayList<OgloszeniaEntity> getOgloszeniaOgolnouczelniane() {
        List queryResult = em.createQuery("select o from OgloszeniaEntity o where o.dlaWszystkich = 1").getResultList();
        return new ArrayList<OgloszeniaEntity>(queryResult);
    }

    public ArrayList<OgloszeniaEntity> getOgloszeniaByZajeciaId(Integer zajeciaId) {
        List queryResult = em.createQuery("select o from OgloszeniaEntity o inner join o.ogloszeniaZajeciasByIdOgloszenia oz where oz.idZajec=:id order by o.idOgloszenia desc")
                .setParameter("id", zajeciaId)
                .getResultList();
        return new ArrayList<OgloszeniaEntity>(queryResult);
    }

    public ArrayList<OgloszeniaEntity> getOgloszeniaByUzytkownikId(Integer userId) {
        List queryResult = em.createQuery("select o from OgloszeniaEntity o where o.idUzytkownika=:id").setParameter("id", userId).getResultList();
        return new  ArrayList<>(queryResult);
    }

    public ArrayList<OgloszeniaEntity> getOgloszeniaByStudentId(Integer studentId) {
        StudenciEntity student = new StudentRepository().getStudentById(studentId);
        ArrayList<OgloszeniaEntity> ogloszenia = new ArrayList<>();
        KierunkiEntity kierunek = student.getKierunkiByIdKierunku();
        ogloszenia.addAll(getOgloszeniaOgolnouczelniane());
        ogloszenia.addAll(getOgloszeniaByKierunek(kierunek.getNazwa(), kierunek.getRocznik()));

        ZajeciaRepository zr = new ZajeciaRepository();
        ArrayList<ZajeciaEntity> zajecia = zr.getZajeciaByStudentId(student.getIdStudenta());

        for(ZajeciaEntity z: zajecia) {
            ogloszenia.addAll(getOgloszeniaByZajeciaId(z.getIdZajec()));
        }

        ogloszenia.sort((o1, o2) -> {
            if(o1.getIdOgloszenia() > o2.getIdOgloszenia()) {
                return -1;
            }
            return 1;
        });

        return ogloszenia;

    }

}
