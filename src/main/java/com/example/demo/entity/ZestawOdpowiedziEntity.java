package com.example.demo.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "zestaw_odpowiedzi", schema = "wirtualna_uczelnia")
public class ZestawOdpowiedziEntity {
    private Integer idZestawuOdpowiedzi;
    private Integer idStudenta;
    private Integer idAnkiety;
    private Collection<OdpowiedziEntity> odpowiedzisByIdZestawuOdpowiedzi;
    private StudenciEntity studenciByIdStudenta;
    private AnkietyEntity ankietyByIdAnkiety;

    @Id
    @Column(name = "id_zestawu_odpowiedzi")
    public Integer getIdZestawuOdpowiedzi() {
        return idZestawuOdpowiedzi;
    }

    public void setIdZestawuOdpowiedzi(Integer idZestawuOdpowiedzi) {
        this.idZestawuOdpowiedzi = idZestawuOdpowiedzi;
    }

    @Basic
    @Column(name = "id_studenta", insertable = false, updatable = false)
    public Integer getIdStudenta() {
        return idStudenta;
    }

    public void setIdStudenta(Integer idStudenta) {
        this.idStudenta = idStudenta;
    }

    @Basic
    @Column(name = "id_ankiety",insertable = false, updatable = false)
    public Integer getIdAnkiety() {
        return idAnkiety;
    }

    public void setIdAnkiety(Integer idAnkiety) {
        this.idAnkiety = idAnkiety;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ZestawOdpowiedziEntity that = (ZestawOdpowiedziEntity) o;
        return Objects.equals(idZestawuOdpowiedzi, that.idZestawuOdpowiedzi) && Objects.equals(idStudenta, that.idStudenta) && Objects.equals(idAnkiety, that.idAnkiety);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idZestawuOdpowiedzi, idStudenta, idAnkiety);
    }

    @OneToMany(mappedBy = "zestawOdpowiedziByIdZestawuOdpowiedzi")
    public Collection<OdpowiedziEntity> getOdpowiedzisByIdZestawuOdpowiedzi() {
        return odpowiedzisByIdZestawuOdpowiedzi;
    }

    public void setOdpowiedzisByIdZestawuOdpowiedzi(Collection<OdpowiedziEntity> odpowiedzisByIdZestawuOdpowiedzi) {
        this.odpowiedzisByIdZestawuOdpowiedzi = odpowiedzisByIdZestawuOdpowiedzi;
    }

    @ManyToOne
    @JoinColumn(name = "id_studenta", referencedColumnName = "id_studenta", nullable = false)
    public StudenciEntity getStudenciByIdStudenta() {
        return studenciByIdStudenta;
    }

    public void setStudenciByIdStudenta(StudenciEntity studenciByIdStudenta) {
        this.studenciByIdStudenta = studenciByIdStudenta;
    }

    @ManyToOne
    @JoinColumn(name = "id_ankiety", referencedColumnName = "id_ankiety", nullable = false)
    public AnkietyEntity getAnkietyByIdAnkiety() {
        return ankietyByIdAnkiety;
    }

    public void setAnkietyByIdAnkiety(AnkietyEntity ankietyByIdAnkiety) {
        this.ankietyByIdAnkiety = ankietyByIdAnkiety;
    }
}
