package com.example.demo.repository;

import com.example.demo.entity.StudenciEntity;
import com.example.demo.entity.ZajeciaEntity;
import com.example.demo.entity.ZaliczeniaEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {
    private EntityManager em;

    public StudentRepository() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        this.em = em;
    }

    public StudenciEntity getStudentById(Integer id) {
        return em.find(StudenciEntity.class, id);
    }

    public ArrayList<ZaliczeniaEntity> getZaliczeniaById(Integer id) {
        StudenciEntity student = getStudentById(id);
        return new ArrayList<>(student.getZaliczeniasByIdStudenta());
    }

    public void awans(Integer id) {
        em.getTransaction().begin();
        em.createQuery("update StudenciEntity s set s.semestr = s.semestr + 1 where s.idStudenta =: id")
                .setParameter("id", id)
                .executeUpdate();
        em.getTransaction().commit();
    }

    public void insertStudent(Integer id, Integer idKierunku, String imie, String nazwisko, Integer semestr) {
        Query query = em.createNativeQuery("insert into studenci (id_studenta, id_kierunku, imie, nazwisko, semestr) values (:id, :idKierunku, :imie,:nazwisko,:semestr)")
                .setParameter("id", id)
                .setParameter("idKierunku", idKierunku)
                .setParameter("imie", imie)
                .setParameter("nazwisko", nazwisko)
                .setParameter("semestr", semestr);
        em.getTransaction().begin();
        query.executeUpdate();
        em.getTransaction().commit();

    }

    public void updateStudent(Integer id, String imie, String nazwisko) {
        em.getTransaction().begin();
        em.createQuery("update StudenciEntity a set a.imie =: imie, a.nazwisko =: nazwisko where a.idStudenta=: id")
                .setParameter("imie",imie)
                .setParameter("nazwisko", nazwisko)
                .setParameter("id" ,id).executeUpdate();
        em.getTransaction().commit();
    }

   public ArrayList<ZajeciaEntity> getZajeciaByIdAndSemestr(Integer id, Integer semestr) {
        ArrayList<ZaliczeniaEntity> zaliczenia = getZaliczeniaById(id);
        ArrayList<ZajeciaEntity> zajecia = new ArrayList<>();

        for(ZaliczeniaEntity zaliczenie : zaliczenia) {
            ZajeciaEntity zajeciaByZaliczenie = zaliczenie.getZajeciaByIdZajec();
            if(zajeciaByZaliczenie.getSemestr() == semestr) {
                zajecia.add(zajeciaByZaliczenie);
            }
        }

        return zajecia;
   }

   public ArrayList<StudenciEntity> getStudenciByKierunekId(Integer kierunekId) {
        return new ArrayList<>(em.createQuery("select s from StudenciEntity s  where s.idKierunku=:idKierunku")
                .setParameter("idKierunku", kierunekId)
                .getResultList()
        );
   }

   public ArrayList<StudenciEntity> getStudenciByIdZajec(Integer idZajec) {
       List<StudenciEntity> queryResult = em.createQuery("select s from StudenciEntity s inner join ZajeciaEntity z on z.idKierunku = s.idKierunku and z.idZajec =: idZajec")
               .setParameter("idZajec", idZajec)
               .getResultList();
       return new ArrayList<>(queryResult);
   }

}

