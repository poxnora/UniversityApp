package com.example.demo.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "uzytkownicy", schema = "wirtualna_uczelnia")
public class UzytkownicyEntity {
    private Integer idUzytkownika;
    private String email;
    private String haslo;
    private Byte zarchiwizowany;
    private AdministratorzyEntity administratorzyByIdUzytkownika;
    private Collection<OgloszeniaEntity> ogloszeniasByIdUzytkownika;
    private ProwadzacyEntity prowadzacyByIdUzytkownika;
    private StudenciEntity studenciByIdUzytkownika;

    @Id
    @Column(name = "id_uzytkownika")
    public Integer getIdUzytkownika() {
        return idUzytkownika;
    }

    public void setIdUzytkownika(Integer idUzytkownika) {
        this.idUzytkownika = idUzytkownika;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "haslo")
    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    @Basic
    @Column(name = "zarchiwizowany")
    public Byte getZarchiwizowany() {
        return zarchiwizowany;
    }

    public void setZarchiwizowany(Byte zarchiwizowany) {
        this.zarchiwizowany = zarchiwizowany;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UzytkownicyEntity that = (UzytkownicyEntity) o;
        return Objects.equals(idUzytkownika, that.idUzytkownika) && Objects.equals(email, that.email) && Objects.equals(haslo, that.haslo) && Objects.equals(zarchiwizowany, that.zarchiwizowany);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUzytkownika, email, haslo, zarchiwizowany);
    }

    @OneToOne(mappedBy = "uzytkownicyByIdAdministratora")
    public AdministratorzyEntity getAdministratorzyByIdUzytkownika() {
        return administratorzyByIdUzytkownika;
    }

    public void setAdministratorzyByIdUzytkownika(AdministratorzyEntity administratorzyByIdUzytkownika) {
        this.administratorzyByIdUzytkownika = administratorzyByIdUzytkownika;
    }

    @OneToMany(mappedBy = "uzytkownicyByIdUzytkownika")
    public Collection<OgloszeniaEntity> getOgloszeniasByIdUzytkownika() {
        return ogloszeniasByIdUzytkownika;
    }

    public void setOgloszeniasByIdUzytkownika(Collection<OgloszeniaEntity> ogloszeniasByIdUzytkownika) {
        this.ogloszeniasByIdUzytkownika = ogloszeniasByIdUzytkownika;
    }

    @OneToOne(mappedBy = "uzytkownicyByIdProwadzacego")
    public ProwadzacyEntity getProwadzacyByIdUzytkownika() {
        return prowadzacyByIdUzytkownika;
    }

    public void setProwadzacyByIdUzytkownika(ProwadzacyEntity prowadzacyByIdUzytkownika) {
        this.prowadzacyByIdUzytkownika = prowadzacyByIdUzytkownika;
    }

    @OneToOne(mappedBy = "uzytkownicyByIdStudenta")
    public StudenciEntity getStudenciByIdUzytkownika() {
        return studenciByIdUzytkownika;
    }

    public void setStudenciByIdUzytkownika(StudenciEntity studenciByIdUzytkownika) {
        this.studenciByIdUzytkownika = studenciByIdUzytkownika;
    }

    public String numerAlbumu() {
        return String.format("%06d",this.getIdUzytkownika());
    }
}
