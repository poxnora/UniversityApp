package com.example.demo.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ogloszenia_zajecia", schema = "wirtualna_uczelnia")
@IdClass(OgloszeniaZajeciaEntityPK.class)
public class OgloszeniaZajeciaEntity {
    private Integer idOgloszenia;
    private Integer idZajec;
    private OgloszeniaEntity ogloszeniaByIdOgloszenia;
    private ZajeciaEntity zajeciaByIdZajec;

    @Id
    @Column(name = "id_ogloszenia", insertable = false, updatable = false)
    public Integer getIdOgloszenia() {
        return idOgloszenia;
    }

    public void setIdOgloszenia(Integer idOgloszenia) {
        this.idOgloszenia = idOgloszenia;
    }

    @Id
    @Column(name = "id_zajec", insertable = false, updatable = false)
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
        OgloszeniaZajeciaEntity that = (OgloszeniaZajeciaEntity) o;
        return Objects.equals(idOgloszenia, that.idOgloszenia) && Objects.equals(idZajec, that.idZajec);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOgloszenia, idZajec);
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
    @JoinColumn(name = "id_zajec", referencedColumnName = "id_zajec", nullable = false)
    public ZajeciaEntity getZajeciaByIdZajec() {
        return zajeciaByIdZajec;
    }

    public void setZajeciaByIdZajec(ZajeciaEntity zajeciaByIdZajec) {
        this.zajeciaByIdZajec = zajeciaByIdZajec;
    }
}
