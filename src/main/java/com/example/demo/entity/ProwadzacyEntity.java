package com.example.demo.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "prowadzacy", schema = "wirtualna_uczelnia")
public class ProwadzacyEntity {
    private Integer idProwadzacego;
    private String imie;
    private String nazwisko;
    private UzytkownicyEntity uzytkownicyByIdProwadzacego;
    private Collection<ZajeciaEntity> zajeciasByIdProwadzacego;

    @Id
    @Column(name = "id_prowadzacego")
    public Integer getIdProwadzacego() {
        return idProwadzacego;
    }

    public void setIdProwadzacego(Integer idProwadzacego) {
        this.idProwadzacego = idProwadzacego;
    }

    @Basic
    @Column(name = "imie")
    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    @Basic
    @Column(name = "nazwisko")
    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProwadzacyEntity that = (ProwadzacyEntity) o;
        return Objects.equals(idProwadzacego, that.idProwadzacego) && Objects.equals(imie, that.imie) && Objects.equals(nazwisko, that.nazwisko);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProwadzacego, imie, nazwisko);
    }

    @OneToOne
    @JoinColumn(name = "id_prowadzacego", referencedColumnName = "id_uzytkownika", nullable = false)
    public UzytkownicyEntity getUzytkownicyByIdProwadzacego() {
        return uzytkownicyByIdProwadzacego;
    }

    public void setUzytkownicyByIdProwadzacego(UzytkownicyEntity uzytkownicyByIdProwadzacego) {
        this.uzytkownicyByIdProwadzacego = uzytkownicyByIdProwadzacego;
    }

    @OneToMany(mappedBy = "prowadzacyByIdProwadzacego")
    public Collection<ZajeciaEntity> getZajeciasByIdProwadzacego() {
        return zajeciasByIdProwadzacego;
    }

    public void setZajeciasByIdProwadzacego(Collection<ZajeciaEntity> zajeciasByIdProwadzacego) {
        this.zajeciasByIdProwadzacego = zajeciasByIdProwadzacego;
    }

    public String numerAlbumu() {
        return String.format("%06d",this.getIdProwadzacego());
    }
}
