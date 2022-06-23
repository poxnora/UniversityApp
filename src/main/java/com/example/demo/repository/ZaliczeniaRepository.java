package com.example.demo.repository;

import com.example.demo.entity.ZajeciaEntity;
import com.example.demo.entity.ZaliczeniaEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.*;

public class ZaliczeniaRepository {
    private EntityManager em;

    public ZaliczeniaRepository() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        this.em = em;
    }

    public ZaliczeniaEntity getZaliczenieById(Integer id) {
        return em.find(ZaliczeniaEntity.class, id);
    }

    public ZajeciaEntity getZajeciaById(Integer id) {
        ZaliczeniaEntity zaliczenie = getZaliczenieById(id);
        return zaliczenie.getZajeciaByIdZajec();
    }

    public ArrayList<ZaliczeniaEntity> getZaliczeniaByStudentIdAndZajeciaId(Integer studentId, Integer zajeciaId) {
        em.getEntityManagerFactory().getCache().evictAll();
        List queryResult =  em.createQuery("SELECT z from ZaliczeniaEntity z where z.idZajec = :idZajec and z.idStudenta = :idStudenta")
                .setParameter("idZajec", zajeciaId)
                .setParameter("idStudenta", studentId)
                .getResultList();
        return new ArrayList<>(queryResult);
    }

    public Integer insertZaliczenieGetId(Integer idZajec, Integer idStudenta, String ocena, Date data, String ocenaPoprawa, Date dataPoprawa, String ocenaKomis, Date dataKomis) {
        em.getTransaction().begin();
        em.createNativeQuery("insert into zaliczenia (id_zajec, id_studenta, ocena, data, ocena_poprawa, data_poprawa, ocena_komis, data_komis) values (:idZajec,:idStudenta,:ocena,:data,:ocenaPoprawa,:dataPoprawa,:ocenaKomis,:dataKomis)") .setParameter("idZajec", idZajec)
                .setParameter("idStudenta", idStudenta)
                .setParameter("ocena", ocena)
                .setParameter("data", data)
                .setParameter("ocenaPoprawa", ocenaPoprawa)
                .setParameter("dataPoprawa", dataPoprawa)
                .setParameter("ocenaKomis", ocenaKomis)
                .setParameter("dataKomis", dataKomis)
                        .executeUpdate();
        em.flush();
        em.getEntityManagerFactory().getCache().evictAll();
        em.getTransaction().commit();
        return Integer.valueOf((String.valueOf(em.createNativeQuery("select LAST_INSERT_ID()").getResultList().get(0))));
    }

    public void updateZaliczenie(Integer zaliczenieId, String ocena, Date data, String ocenaPoprawa, Date dataPoprawa, String ocenaKomis, Date dataKomis) {
        Query query = em.createQuery("update ZaliczeniaEntity z set z.ocena =: ocena, z.data =: data, z.ocenaPoprawa =: ocenaPoprawa, z.dataPoprawa =: dataPoprawa ,z.ocenaKomis =: ocenaKomis, z.dataKomis =: dataKomis where z.idZaliczenia =: idZaliczenia")
                .setParameter("ocena", ocena)
                .setParameter("data", data)
                .setParameter("ocenaPoprawa", ocenaPoprawa)
                .setParameter("dataPoprawa", dataPoprawa)
                .setParameter("ocenaKomis", ocenaKomis)
                .setParameter("dataKomis", dataKomis)
                .setParameter("idZaliczenia", zaliczenieId);
        em.getTransaction().begin();
        query.executeUpdate();
        em.flush();
        em.getTransaction().commit();
    }

    /*
    public ArrayList<ZaliczeniaEntity> getZaliczeniaByStudentIdAndZajeciaArray(Integer studentId, ArrayList<ZajeciaEntity> zajecia) {
        Set<ZaliczeniaEntity> zaliczenia = null;
        for(ZajeciaEntity z: zajecia) {
            Integer zajeciaId = z.getIdZajec();
            zaliczenia.addAll(getZaliczeniaByStudentIdAndZajeciaId(studentId, zajeciaId));
        }
        return new ArrayList<>(zaliczenia);
    }
    */
}
