package com.example.demo.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "pytania", schema = "wirtualna_uczelnia")
public class PytaniaEntity {
    private Integer idPytania;
    private Integer idZestawuPytan;
    private String tresc;
    private Collection<OdpowiedziEntity> odpowiedzisByIdPytania;
    private ZestawyPytanEntity zestawyPytanByIdZestawuPytan;

    @Id
    @Column(name = "id_pytania")
    public Integer getIdPytania() {
        return idPytania;
    }

    public void setIdPytania(Integer idPytania) {
        this.idPytania = idPytania;
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
    @Column(name = "tresc")
    public String getTresc() {
        return tresc;
    }

    public void setTresc(String tresc) {
        this.tresc = tresc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PytaniaEntity that = (PytaniaEntity) o;
        return Objects.equals(idPytania, that.idPytania) && Objects.equals(idZestawuPytan, that.idZestawuPytan) && Objects.equals(tresc, that.tresc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPytania, idZestawuPytan, tresc);
    }

    @OneToMany(mappedBy = "pytaniaByIdPytania")
    public Collection<OdpowiedziEntity> getOdpowiedzisByIdPytania() {
        return odpowiedzisByIdPytania;
    }

    public void setOdpowiedzisByIdPytania(Collection<OdpowiedziEntity> odpowiedzisByIdPytania) {
        this.odpowiedzisByIdPytania = odpowiedzisByIdPytania;
    }

    @ManyToOne
    @JoinColumn(name = "id_zestawu_pytan", referencedColumnName = "id_zestawu_pytan", nullable = false)
    public ZestawyPytanEntity getZestawyPytanByIdZestawuPytan() {
        return zestawyPytanByIdZestawuPytan;
    }

    public void setZestawyPytanByIdZestawuPytan(ZestawyPytanEntity zestawyPytanByIdZestawuPytan) {
        this.zestawyPytanByIdZestawuPytan = zestawyPytanByIdZestawuPytan;
    }
}
