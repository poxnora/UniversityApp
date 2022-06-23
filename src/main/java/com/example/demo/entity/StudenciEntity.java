package com.example.demo.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "studenci", schema = "wirtualna_uczelnia")
public class StudenciEntity {
    private Integer idStudenta;
    private Integer idKierunku;
    private String imie;
    private String nazwisko;
    private Integer semestr;
    private UzytkownicyEntity uzytkownicyByIdStudenta;
    private KierunkiEntity kierunkiByIdKierunku;
    private Collection<ZaliczeniaEntity> zaliczeniasByIdStudenta;
    private Collection<ZestawOdpowiedziEntity> zestawOdpowiedzisByIdStudenta;

    @Id
    @Column(name = "id_studenta")
    public Integer getIdStudenta() {
        return idStudenta;
    }

    public void setIdStudenta(Integer idStudenta) {
        this.idStudenta = idStudenta;
    }

    @Basic
    @Column(name = "id_kierunku", insertable = false, updatable = false)
    public Integer getIdKierunku() {
        return idKierunku;
    }

    public void setIdKierunku(Integer idKierunku) {
        this.idKierunku = idKierunku;
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

    @Basic
    @Column(name = "semestr")
    public Integer getSemestr() {
        return semestr;
    }

    public void setSemestr(Integer semestr) {
        this.semestr = semestr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudenciEntity that = (StudenciEntity) o;
        return Objects.equals(idStudenta, that.idStudenta) && Objects.equals(idKierunku, that.idKierunku) && Objects.equals(imie, that.imie) && Objects.equals(nazwisko, that.nazwisko) && Objects.equals(semestr, that.semestr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idStudenta, idKierunku, imie, nazwisko, semestr);
    }

    @OneToOne
    @JoinColumn(name = "id_studenta", referencedColumnName = "id_uzytkownika", nullable = false)
    public UzytkownicyEntity getUzytkownicyByIdStudenta() {
        return uzytkownicyByIdStudenta;
    }

    public void setUzytkownicyByIdStudenta(UzytkownicyEntity uzytkownicyByIdStudenta) {
        this.uzytkownicyByIdStudenta = uzytkownicyByIdStudenta;
    }

    @ManyToOne
    @JoinColumn(name = "id_kierunku", referencedColumnName = "id_kierunku", nullable = false)
    public KierunkiEntity getKierunkiByIdKierunku() {
        return kierunkiByIdKierunku;
    }

    public void setKierunkiByIdKierunku(KierunkiEntity kierunkiByIdKierunku) {
        this.kierunkiByIdKierunku = kierunkiByIdKierunku;
    }

    @OneToMany(mappedBy = "studenciByIdStudenta")
    public Collection<ZaliczeniaEntity> getZaliczeniasByIdStudenta() {
        return zaliczeniasByIdStudenta;
    }

    public void setZaliczeniasByIdStudenta(Collection<ZaliczeniaEntity> zaliczeniasByIdStudenta) {
        this.zaliczeniasByIdStudenta = zaliczeniasByIdStudenta;
    }

    @OneToMany(mappedBy = "studenciByIdStudenta")
    public Collection<ZestawOdpowiedziEntity> getZestawOdpowiedzisByIdStudenta() {
        return zestawOdpowiedzisByIdStudenta;
    }

    public void setZestawOdpowiedzisByIdStudenta(Collection<ZestawOdpowiedziEntity> zestawOdpowiedzisByIdStudenta) {
        this.zestawOdpowiedzisByIdStudenta = zestawOdpowiedzisByIdStudenta;
    }

    public String numerAlbumu() {
        return String.format("%06d",this.getIdStudenta());
    }
}

