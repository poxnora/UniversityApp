package com.example.demo.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "administratorzy", schema = "wirtualna_uczelnia")
public class AdministratorzyEntity {
    private Integer idAdministratora;
    private String imie;
    private String nazwisko;
    private UzytkownicyEntity uzytkownicyByIdAdministratora;

    @Id
    @Column(name = "id_administratora")
    public Integer getIdAdministratora() {
        return idAdministratora;
    }

    public void setIdAdministratora(Integer idAdministratora) {
        this.idAdministratora = idAdministratora;
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
        AdministratorzyEntity that = (AdministratorzyEntity) o;
        return Objects.equals(idAdministratora, that.idAdministratora) && Objects.equals(imie, that.imie) && Objects.equals(nazwisko, that.nazwisko);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAdministratora, imie, nazwisko);
    }

    @OneToOne
    @JoinColumn(name = "id_administratora", referencedColumnName = "id_uzytkownika", nullable = false)
    public UzytkownicyEntity getUzytkownicyByIdAdministratora() {
        return uzytkownicyByIdAdministratora;
    }

    public void setUzytkownicyByIdAdministratora(UzytkownicyEntity uzytkownicyByIdAdministratora) {
        this.uzytkownicyByIdAdministratora = uzytkownicyByIdAdministratora;
    }
}
