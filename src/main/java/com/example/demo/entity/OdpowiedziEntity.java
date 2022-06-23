package com.example.demo.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "odpowiedzi", schema = "wirtualna_uczelnia")
public class OdpowiedziEntity {
    private Integer idOdpowiedzi;
    private String tresc;
    private Integer idPytania;
    private Integer idZestawuOdpowiedzi;
    private PytaniaEntity pytaniaByIdPytania;
    private ZestawOdpowiedziEntity zestawOdpowiedziByIdZestawuOdpowiedzi;

    @Id
    @Column(name = "id_odpowiedzi")
    public Integer getIdOdpowiedzi() {
        return idOdpowiedzi;
    }

    public void setIdOdpowiedzi(Integer idOdpowiedzi) {
        this.idOdpowiedzi = idOdpowiedzi;
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
    @Column(name = "id_pytania", insertable = false, updatable = false)
    public Integer getIdPytania() {
        return idPytania;
    }

    public void setIdPytania(Integer idPytania) {
        this.idPytania = idPytania;
    }

    @Basic
    @Column(name = "id_zestawu_odpowiedzi", insertable = false, updatable = false)
    public Integer getIdZestawuOdpowiedzi() {
        return idZestawuOdpowiedzi;
    }

    public void setIdZestawuOdpowiedzi(Integer idZestawuOdpowiedzi) {
        this.idZestawuOdpowiedzi = idZestawuOdpowiedzi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OdpowiedziEntity that = (OdpowiedziEntity) o;
        return Objects.equals(idOdpowiedzi, that.idOdpowiedzi) && Objects.equals(tresc, that.tresc) && Objects.equals(idPytania, that.idPytania) && Objects.equals(idZestawuOdpowiedzi, that.idZestawuOdpowiedzi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOdpowiedzi, tresc, idPytania, idZestawuOdpowiedzi);
    }

    @ManyToOne
    @JoinColumn(name = "id_pytania", referencedColumnName = "id_pytania", nullable = false)
    public PytaniaEntity getPytaniaByIdPytania() {
        return pytaniaByIdPytania;
    }

    public void setPytaniaByIdPytania(PytaniaEntity pytaniaByIdPytania) {
        this.pytaniaByIdPytania = pytaniaByIdPytania;
    }

    @ManyToOne
    @JoinColumn(name = "id_zestawu_odpowiedzi", referencedColumnName = "id_zestawu_odpowiedzi", nullable = false)
    public ZestawOdpowiedziEntity getZestawOdpowiedziByIdZestawuOdpowiedzi() {
        return zestawOdpowiedziByIdZestawuOdpowiedzi;
    }

    public void setZestawOdpowiedziByIdZestawuOdpowiedzi(ZestawOdpowiedziEntity zestawOdpowiedziByIdZestawuOdpowiedzi) {
        this.zestawOdpowiedziByIdZestawuOdpowiedzi = zestawOdpowiedziByIdZestawuOdpowiedzi;
    }
}
