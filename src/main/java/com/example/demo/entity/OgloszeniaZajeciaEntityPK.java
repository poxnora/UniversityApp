package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class OgloszeniaZajeciaEntityPK implements Serializable {
    private Integer idOgloszenia;
    private Integer idZajec;

    @Column(name = "id_ogloszenia",insertable = false, updatable = false)
    @Id
    public Integer getIdOgloszenia() {
        return idOgloszenia;
    }

    public void setIdOgloszenia(Integer idOgloszenia) {
        this.idOgloszenia = idOgloszenia;
    }

    @Column(name = "id_zajec",insertable = false, updatable = false)
    @Id
    public Integer getIdZajec() {
        return idZajec;
    }

    public void setIdZajec(Integer idZajec) {
        this.idZajec = idZajec;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OgloszeniaZajeciaEntityPK that = (OgloszeniaZajeciaEntityPK) o;
        return Objects.equals(idOgloszenia, that.idOgloszenia) && Objects.equals(idZajec, that.idZajec);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOgloszenia, idZajec);
    }
}
