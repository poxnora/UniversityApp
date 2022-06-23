package com.example.demo.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "ankiety", schema = "wirtualna_uczelnia")
public class AnkietyEntity {
    private Integer idAnkiety;
    private Integer idZestawuPytan;
    private Integer idZajec;
    private ZestawyPytanEntity zestawyPytanByIdZestawuPytan;
    private ZajeciaEntity zajeciaByIdZajec;
    private Collection<ZestawOdpowiedziEntity> zestawOdpowiedzisByIdAnkiety;

    @Id
    @Column(name = "id_ankiety")
    public Integer getIdAnkiety() {
        return idAnkiety;
    }

    public void setIdAnkiety(Integer idAnkiety) {
        this.idAnkiety = idAnkiety;
    }

    @Basic
    @Column(name = "id_zestawu_pytan", insertable = false, updatable = false)
    public Integer getIdZestawuPytan() {
        return idZestawuPytan;
    }

    public void setIdZestawuPytan(Integer idZestawuPytan) {
        this.idZestawuPytan = idZestawuPytan;
    }

    @Basic
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
        AnkietyEntity that = (AnkietyEntity) o;
        return Objects.equals(idAnkiety, that.idAnkiety) && Objects.equals(idZestawuPytan, that.idZestawuPytan) && Objects.equals(idZajec, that.idZajec);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAnkiety, idZestawuPytan, idZajec);
    }

    @ManyToOne
    @JoinColumn(name = "id_zestawu_pytan", referencedColumnName = "id_zestawu_pytan", nullable = false)
    public ZestawyPytanEntity getZestawyPytanByIdZestawuPytan() {
        return zestawyPytanByIdZestawuPytan;
    }

    public void setZestawyPytanByIdZestawuPytan(ZestawyPytanEntity zestawyPytanByIdZestawuPytan) {
        this.zestawyPytanByIdZestawuPytan = zestawyPytanByIdZestawuPytan;
    }

    @ManyToOne
    @JoinColumn(name = "id_zajec", referencedColumnName = "id_zajec", nullable = false)
    public ZajeciaEntity getZajeciaByIdZajec() {
        return zajeciaByIdZajec;
    }

    public void setZajeciaByIdZajec(ZajeciaEntity zajeciaByIdZajec) {
        this.zajeciaByIdZajec = zajeciaByIdZajec;
    }

    @OneToMany(mappedBy = "ankietyByIdAnkiety")
    public Collection<ZestawOdpowiedziEntity> getZestawOdpowiedzisByIdAnkiety() {
        return zestawOdpowiedzisByIdAnkiety;
    }

    public void setZestawOdpowiedzisByIdAnkiety(Collection<ZestawOdpowiedziEntity> zestawOdpowiedzisByIdAnkiety) {
        this.zestawOdpowiedzisByIdAnkiety = zestawOdpowiedzisByIdAnkiety;
    }
}
