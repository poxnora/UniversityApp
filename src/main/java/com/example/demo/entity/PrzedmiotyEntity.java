package com.example.demo.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "przedmioty", schema = "wirtualna_uczelnia")
public class PrzedmiotyEntity {
    private Integer idPrzedmiotu;
    private String nazwa;
    private Integer ects;
    private Collection<ZajeciaEntity> zajeciasByIdPrzedmiotu;

    @Id
    @Column(name = "id_przedmiotu")
    public Integer getIdPrzedmiotu() {
        return idPrzedmiotu;
    }

    public void setIdPrzedmiotu(Integer idPrzedmiotu) {
        this.idPrzedmiotu = idPrzedmiotu;
    }

    @Basic
    @Column(name = "nazwa")
    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    @Basic
    @Column(name = "ects")
    public Integer getEcts() {
        return ects;
    }

    public void setEcts(Integer ects) {
        this.ects = ects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrzedmiotyEntity that = (PrzedmiotyEntity) o;
        return Objects.equals(idPrzedmiotu, that.idPrzedmiotu) && Objects.equals(nazwa, that.nazwa) && Objects.equals(ects, that.ects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPrzedmiotu, nazwa, ects);
    }

    @OneToMany(mappedBy = "przedmiotyByIdPrzedmiotu")
    public Collection<ZajeciaEntity> getZajeciasByIdPrzedmiotu() {
        return zajeciasByIdPrzedmiotu;
    }

    public void setZajeciasByIdPrzedmiotu(Collection<ZajeciaEntity> zajeciasByIdPrzedmiotu) {
        this.zajeciasByIdPrzedmiotu = zajeciasByIdPrzedmiotu;
    }
}
