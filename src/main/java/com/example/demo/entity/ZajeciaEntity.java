package com.example.demo.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "zajecia", schema = "wirtualna_uczelnia")
public class ZajeciaEntity {
    private Integer idZajec;
    private Integer idPrzedmiotu;
    private Integer idProwadzacego;
    private Integer idKierunku;
    private String formaZajec;
    private String formaZaliczenia;
    private Integer liczbaGodzin;
    private Integer semestr;
    private Collection<AnkietyEntity> ankietiesByIdZajec;
    private Collection<OgloszeniaZajeciaEntity> ogloszeniaZajeciasByIdZajec;
    private PrzedmiotyEntity przedmiotyByIdPrzedmiotu;
    private ProwadzacyEntity prowadzacyByIdProwadzacego;
    private KierunkiEntity kierunkiByIdKierunku;
    private Collection<ZaliczeniaEntity> zaliczeniasByIdZajec;

    @Id
    @Column(name = "id_zajec")
    public Integer getIdZajec() {
        return idZajec;
    }

    public void setIdZajec(Integer idZajec) {
        this.idZajec = idZajec;
    }

    @Basic
    @Column(name = "id_przedmiotu", insertable = false, updatable = false)
    public Integer getIdPrzedmiotu() {
        return idPrzedmiotu;
    }

    public void setIdPrzedmiotu(Integer idPrzedmiotu) {
        this.idPrzedmiotu = idPrzedmiotu;
    }

    @Basic
    @Column(name = "id_prowadzacego", insertable = false, updatable = false)
    public Integer getIdProwadzacego() {
        return idProwadzacego;
    }

    public void setIdProwadzacego(Integer idProwadzacego) {
        this.idProwadzacego = idProwadzacego;
    }

    @Basic
    @Column(name = "id_kierunku", insertable = false, updatable = false)
    public Integer getIdKierunku() {
        return idKierunku;
    }

    public void setIdKierunku(Integer idKierunku) {
        this.idKierunku = idKierunku;
    }

    @Basic
    @Column(name = "forma_zajec")
    public String getFormaZajec() {
        return formaZajec;
    }

    public void setFormaZajec(String formaZajec) {
        this.formaZajec = formaZajec;
    }

    @Basic
    @Column(name = "forma_zaliczenia")
    public String getFormaZaliczenia() {
        return formaZaliczenia;
    }

    public void setFormaZaliczenia(String formaZaliczenia) {
        this.formaZaliczenia = formaZaliczenia;
    }

    @Basic
    @Column(name = "liczba_godzin")
    public Integer getLiczbaGodzin() {
        return liczbaGodzin;
    }

    public void setLiczbaGodzin(Integer liczbaGodzin) {
        this.liczbaGodzin = liczbaGodzin;
    }

    @Basic
    @Column(name = "semestr")
    public Integer getSemestr() {
        return semestr;
    }

    public void setSemestr(Integer semestr) {
        this.semestr = semestr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ZajeciaEntity that = (ZajeciaEntity) o;
        return Objects.equals(idZajec, that.idZajec) && Objects.equals(idPrzedmiotu, that.idPrzedmiotu) && Objects.equals(idProwadzacego, that.idProwadzacego) && Objects.equals(idKierunku, that.idKierunku) && Objects.equals(formaZajec, that.formaZajec) && Objects.equals(formaZaliczenia, that.formaZaliczenia) && Objects.equals(liczbaGodzin, that.liczbaGodzin) && Objects.equals(semestr, that.semestr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idZajec, idPrzedmiotu, idProwadzacego, idKierunku, formaZajec, formaZaliczenia, liczbaGodzin, semestr);
    }

    @OneToMany(mappedBy = "zajeciaByIdZajec")
    public Collection<AnkietyEntity> getAnkietiesByIdZajec() {
        return ankietiesByIdZajec;
    }

    public void setAnkietiesByIdZajec(Collection<AnkietyEntity> ankietiesByIdZajec) {
        this.ankietiesByIdZajec = ankietiesByIdZajec;
    }

    @OneToMany(mappedBy = "zajeciaByIdZajec")
    public Collection<OgloszeniaZajeciaEntity> getOgloszeniaZajeciasByIdZajec() {
        return ogloszeniaZajeciasByIdZajec;
    }

    public void setOgloszeniaZajeciasByIdZajec(Collection<OgloszeniaZajeciaEntity> ogloszeniaZajeciasByIdZajec) {
        this.ogloszeniaZajeciasByIdZajec = ogloszeniaZajeciasByIdZajec;
    }

    @ManyToOne
    @JoinColumn(name = "id_przedmiotu", referencedColumnName = "id_przedmiotu", nullable = false)
    public PrzedmiotyEntity getPrzedmiotyByIdPrzedmiotu() {
        return przedmiotyByIdPrzedmiotu;
    }

    public void setPrzedmiotyByIdPrzedmiotu(PrzedmiotyEntity przedmiotyByIdPrzedmiotu) {
        this.przedmiotyByIdPrzedmiotu = przedmiotyByIdPrzedmiotu;
    }

    @ManyToOne
    @JoinColumn(name = "id_prowadzacego", referencedColumnName = "id_prowadzacego", nullable = false)
    public ProwadzacyEntity getProwadzacyByIdProwadzacego() {
        return prowadzacyByIdProwadzacego;
    }

    public void setProwadzacyByIdProwadzacego(ProwadzacyEntity prowadzacyByIdProwadzacego) {
        this.prowadzacyByIdProwadzacego = prowadzacyByIdProwadzacego;
    }

    @ManyToOne
    @JoinColumn(name = "id_kierunku", referencedColumnName = "id_kierunku", nullable = false)
    public KierunkiEntity getKierunkiByIdKierunku() {
        return kierunkiByIdKierunku;
    }

    public void setKierunkiByIdKierunku(KierunkiEntity kierunkiByIdKierunku) {
        this.kierunkiByIdKierunku = kierunkiByIdKierunku;
    }

    @OneToMany(mappedBy = "zajeciaByIdZajec")
    public Collection<ZaliczeniaEntity> getZaliczeniasByIdZajec() {
        return zaliczeniasByIdZajec;
    }

    public void setZaliczeniasByIdZajec(Collection<ZaliczeniaEntity> zaliczeniasByIdZajec) {
        this.zaliczeniasByIdZajec = zaliczeniasByIdZajec;
    }
}
