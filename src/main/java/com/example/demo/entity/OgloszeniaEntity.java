package com.example.demo.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "ogloszenia", schema = "wirtualna_uczelnia")
public class OgloszeniaEntity {
    private Integer idOgloszenia;
    private String tytul;
    private String tresc;
    private Integer idUzytkownika;
    private Byte dlaWszystkich;
    private UzytkownicyEntity uzytkownicyByIdUzytkownika;
    private Collection<OgloszeniaKierunkiEntity> ogloszeniaKierunkisByIdOgloszenia;
    private Collection<OgloszeniaZajeciaEntity> ogloszeniaZajeciasByIdOgloszenia;

    @Id
    @Column(name = "id_ogloszenia")
    @GeneratedValue
    public Integer getIdOgloszenia() {
        return idOgloszenia;
    }

    public void setIdOgloszenia(Integer idOgloszenia) {
        this.idOgloszenia = idOgloszenia;
    }

    @Basic
    @Column(name = "tytul")
    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    @Basic
    @Column(name = "tresc")
    public String getTresc() {
        return tresc;
    }

    public void setTresc(String tresc) {
        this.tresc = tresc;
    }

    @Basic
    @Column(name = "id_uzytkownika", insertable = false, updatable = false)
    public Integer getIdUzytkownika() {
        return idUzytkownika;
    }

    public void setIdUzytkownika(Integer idUzytkownika) {
        this.idUzytkownika = idUzytkownika;
    }

    @Basic
    @Column(name = "dla_wszystkich")
    public Byte getDlaWszystkich() {
        return dlaWszystkich;
    }

    public void setDlaWszystkich(Byte dlaWszystkich) {
        this.dlaWszystkich = dlaWszystkich;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OgloszeniaEntity that = (OgloszeniaEntity) o;
        return Objects.equals(idOgloszenia, that.idOgloszenia) && Objects.equals(tytul, that.tytul) && Objects.equals(tresc, that.tresc) && Objects.equals(idUzytkownika, that.idUzytkownika) && Objects.equals(dlaWszystkich, that.dlaWszystkich);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOgloszenia, tytul, tresc, idUzytkownika, dlaWszystkich);
    }

    @ManyToOne
    @JoinColumn(name = "id_uzytkownika", referencedColumnName = "id_uzytkownika", nullable = false)
    public UzytkownicyEntity getUzytkownicyByIdUzytkownika() {
        return uzytkownicyByIdUzytkownika;
    }

    public void setUzytkownicyByIdUzytkownika(UzytkownicyEntity uzytkownicyByIdUzytkownika) {
        this.uzytkownicyByIdUzytkownika = uzytkownicyByIdUzytkownika;
    }

    @OneToMany(mappedBy = "ogloszeniaByIdOgloszenia")
    public Collection<OgloszeniaKierunkiEntity> getOgloszeniaKierunkisByIdOgloszenia() {
        return ogloszeniaKierunkisByIdOgloszenia;
    }

    public void setOgloszeniaKierunkisByIdOgloszenia(Collection<OgloszeniaKierunkiEntity> ogloszeniaKierunkisByIdOgloszenia) {
        this.ogloszeniaKierunkisByIdOgloszenia = ogloszeniaKierunkisByIdOgloszenia;
    }

    @OneToMany(mappedBy = "ogloszeniaByIdOgloszenia")
    public Collection<OgloszeniaZajeciaEntity> getOgloszeniaZajeciasByIdOgloszenia() {
        return ogloszeniaZajeciasByIdOgloszenia;
    }

    public void setOgloszeniaZajeciasByIdOgloszenia(Collection<OgloszeniaZajeciaEntity> ogloszeniaZajeciasByIdOgloszenia) {
        this.ogloszeniaZajeciasByIdOgloszenia = ogloszeniaZajeciasByIdOgloszenia;
    }

}
