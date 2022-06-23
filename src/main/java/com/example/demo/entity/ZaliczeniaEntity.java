package com.example.demo.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "zaliczenia", schema = "wirtualna_uczelnia", catalog = "")
public class ZaliczeniaEntity {
    private Integer idZaliczenia;
    private Integer idZajec;
    private Integer idStudenta;
    private String ocena;
    private Date data;
    private String ocenaPoprawa;
    private Date dataPoprawa;
    private String ocenaKomis;
    private Date dataKomis;
    private ZajeciaEntity zajeciaByIdZajec;
    private StudenciEntity studenciByIdStudenta;

    @Id
    @Column(name = "id_zaliczenia")
    public Integer getIdZaliczenia() {
        return idZaliczenia;
    }

    public void setIdZaliczenia(Integer idZaliczenia) {
        this.idZaliczenia = idZaliczenia;
    }

    @Basic
    @Column(name = "id_zajec" ,insertable = false, updatable = false)
    public Integer getIdZajec() {
        return idZajec;
    }

    public void setIdZajec(Integer idZajec) {
        this.idZajec = idZajec;
    }

    @Basic
    @Column(name = "id_studenta",insertable = false, updatable = false)
    public Integer getIdStudenta() {
        return idStudenta;
    }

    public void setIdStudenta(Integer idStudenta) {
        this.idStudenta = idStudenta;
    }

    @Basic
    @Column(name = "ocena")
    public String getOcena() {
        return ocena;
    }

    public void setOcena(String ocena) {
        this.ocena = ocena;
    }

    @Basic
    @Column(name = "data")
    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Basic
    @Column(name = "ocena_poprawa")
    public String getOcenaPoprawa() {
        return ocenaPoprawa;
    }

    public void setOcenaPoprawa(String ocenaPoprawa) {
        this.ocenaPoprawa = ocenaPoprawa;
    }

    @Basic
    @Column(name = "data_poprawa")
    public Date getDataPoprawa() {
        return dataPoprawa;
    }

    public void setDataPoprawa(Date dataPoprawa) {
        this.dataPoprawa = dataPoprawa;
    }

    @Basic
    @Column(name = "ocena_komis")
    public String getOcenaKomis() {
        return ocenaKomis;
    }

    public void setOcenaKomis(String ocenaKomis) {
        this.ocenaKomis = ocenaKomis;
    }


    @Basic
    @Column(name = "data_komis")
    public Date getDataKomis() {
        return dataKomis;
    }

    public void setDataKomis(Date dataKomis) {
        this.dataKomis = dataKomis;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ZaliczeniaEntity that = (ZaliczeniaEntity) o;
        return Objects.equals(idZaliczenia, that.idZaliczenia) && Objects.equals(idZajec, that.idZajec) && Objects.equals(idStudenta, that.idStudenta) && Objects.equals(ocena, that.ocena) && Objects.equals(data, that.data) && Objects.equals(ocenaPoprawa, that.ocenaPoprawa) && Objects.equals(dataPoprawa, that.dataPoprawa) && Objects.equals(ocenaKomis, that.ocenaKomis) && Objects.equals(dataKomis, that.dataKomis);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idZaliczenia, idZajec, idStudenta, ocena, data, ocenaPoprawa, dataPoprawa, ocenaKomis, dataKomis);
    }

    @ManyToOne
    @JoinColumn(name = "id_zajec", referencedColumnName = "id_zajec", nullable = false)
    public ZajeciaEntity getZajeciaByIdZajec() {
        return zajeciaByIdZajec;
    }

    public void setZajeciaByIdZajec(ZajeciaEntity zajeciaByIdZajec) {
        this.zajeciaByIdZajec = zajeciaByIdZajec;
    }

    @ManyToOne
    @JoinColumn(name = "id_studenta", referencedColumnName = "id_studenta", nullable = false)
    public StudenciEntity getStudenciByIdStudenta() {
        return studenciByIdStudenta;
    }

    public void setStudenciByIdStudenta(StudenciEntity studenciByIdStudenta) {
        this.studenciByIdStudenta = studenciByIdStudenta;
    }
}
