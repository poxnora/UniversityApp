package com.example.demo.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "kierunki", schema = "wirtualna_uczelnia")
public class KierunkiEntity {
    private Integer idKierunku;
    private String nazwa;
    private Integer rocznik;
    private Collection<OgloszeniaKierunkiEntity> ogloszeniaKierunkisByIdKierunku;
    private Collection<StudenciEntity> studencisByIdKierunku;
    private Collection<ZajeciaEntity> zajeciasByIdKierunku;

    @Id
    @Column(name = "id_kierunku")
    public Integer getIdKierunku() {
        return idKierunku;
    }

    public void setIdKierunku(Integer idKierunku) {
        this.idKierunku = idKierunku;
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
    @Column(name = "rocznik")
    public Integer getRocznik() {
        return rocznik;
    }

    public void setRocznik(Integer rocznik) {
        this.rocznik = rocznik;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KierunkiEntity that = (KierunkiEntity) o;
        return Objects.equals(idKierunku, that.idKierunku) && Objects.equals(nazwa, that.nazwa) && Objects.equals(rocznik, that.rocznik);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idKierunku, nazwa, rocznik);
    }

    @OneToMany(mappedBy = "kierunkiByIdKierunku")
    public Collection<OgloszeniaKierunkiEntity> getOgloszeniaKierunkisByIdKierunku() {
        return ogloszeniaKierunkisByIdKierunku;
    }

    public void setOgloszeniaKierunkisByIdKierunku(Collection<OgloszeniaKierunkiEntity> ogloszeniaKierunkisByIdKierunku) {
        this.ogloszeniaKierunkisByIdKierunku = ogloszeniaKierunkisByIdKierunku;
    }

    @OneToMany(mappedBy = "kierunkiByIdKierunku")
    public Collection<StudenciEntity> getStudencisByIdKierunku() {
        return studencisByIdKierunku;
    }

    public void setStudencisByIdKierunku(Collection<StudenciEntity> studencisByIdKierunku) {
        this.studencisByIdKierunku = studencisByIdKierunku;
    }

    @OneToMany(mappedBy = "kierunkiByIdKierunku")
    public Collection<ZajeciaEntity> getZajeciasByIdKierunku() {
        return zajeciasByIdKierunku;
    }

    public void setZajeciasByIdKierunku(Collection<ZajeciaEntity> zajeciasByIdKierunku) {
        this.zajeciasByIdKierunku = zajeciasByIdKierunku;
    }
}
