package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class OgloszeniaKierunkiEntityPK implements Serializable {
    private Integer idOgloszenia;
    private Integer idKierunku;

    @Column(name = "id_ogloszenia",insertable = false, updatable = false)
    @Id
    public Integer getIdOgloszenia() {
        return idOgloszenia;
    }

    public void setIdOgloszenia(Integer idOgloszenia) {
        this.idOgloszenia = idOgloszenia;
    }

    @Column(name = "id_kierunku", insertable = false, updatable = false)
    @Id
    public Integer getIdKierunku() {
        return idKierunku;
    }

    public void setIdKierunku(Integer idKierunku) {
        this.idKierunku = idKierunku;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OgloszeniaKierunkiEntityPK that = (OgloszeniaKierunkiEntityPK) o;
        return Objects.equals(idOgloszenia, that.idOgloszenia) && Objects.equals(idKierunku, that.idKierunku);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOgloszenia, idKierunku);
    }
}
