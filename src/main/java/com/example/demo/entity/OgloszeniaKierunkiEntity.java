package com.example.demo.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ogloszenia_kierunki", schema = "wirtualna_uczelnia")
@IdClass(OgloszeniaKierunkiEntityPK.class)
public class OgloszeniaKierunkiEntity {
    private Integer idOgloszenia;
    private Integer idKierunku;
    private OgloszeniaEntity ogloszeniaByIdOgloszenia;
    private KierunkiEntity kierunkiByIdKierunku;

    @Id
    @Column(name = "id_ogloszenia", insertable = false, updatable = false)
    public Integer getIdOgloszenia() {
        return idOgloszenia;
    }

    public void setIdOgloszenia(Integer idOgloszenia) {
        this.idOgloszenia = idOgloszenia;
    }

    @Id
    @Column(name = "id_kierunku", insertable = false, updatable = false)
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
        OgloszeniaKierunkiEntity that = (OgloszeniaKierunkiEntity) o;
        return Objects.equals(idOgloszenia, that.idOgloszenia) && Objects.equals(idKierunku, that.idKierunku);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOgloszenia, idKierunku);
    }

    @ManyToOne
    @JoinColumn(name = "id_ogloszenia", referencedColumnName = "id_ogloszenia", nullable = false)
    public OgloszeniaEntity getOgloszeniaByIdOgloszenia() {
        return ogloszeniaByIdOgloszenia;
    }

    public void setOgloszeniaByIdOgloszenia(OgloszeniaEntity ogloszeniaByIdOgloszenia) {
        this.ogloszeniaByIdOgloszenia = ogloszeniaByIdOgloszenia;
    }

    @ManyToOne
    @JoinColumn(name = "id_kierunku", referencedColumnName = "id_kierunku", nullable = false)
    public KierunkiEntity getKierunkiByIdKierunku() {
        return kierunkiByIdKierunku;
    }

    public void setKierunkiByIdKierunku(KierunkiEntity kierunkiByIdKierunku) {
        this.kierunkiByIdKierunku = kierunkiByIdKierunku;
    }
}
