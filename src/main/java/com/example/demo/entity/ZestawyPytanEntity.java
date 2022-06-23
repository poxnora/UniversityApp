package com.example.demo.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "zestawy_pytan", schema = "wirtualna_uczelnia")
public class ZestawyPytanEntity {
    private Integer idZestawuPytan;
    private Collection<AnkietyEntity> ankietiesByIdZestawuPytan;
    private Collection<PytaniaEntity> pytaniasByIdZestawuPytan;

    @Id
    @Column(name = "id_zestawu_pytan")
    public Integer getIdZestawuPytan() {
        return idZestawuPytan;
    }

    public void setIdZestawuPytan(Integer idZestawuPytan) {
        this.idZestawuPytan = idZestawuPytan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ZestawyPytanEntity that = (ZestawyPytanEntity) o;
        return Objects.equals(idZestawuPytan, that.idZestawuPytan);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idZestawuPytan);
    }

    @OneToMany(mappedBy = "zestawyPytanByIdZestawuPytan")
    public Collection<AnkietyEntity> getAnkietiesByIdZestawuPytan() {
        return ankietiesByIdZestawuPytan;
    }

    public void setAnkietiesByIdZestawuPytan(Collection<AnkietyEntity> ankietiesByIdZestawuPytan) {
        this.ankietiesByIdZestawuPytan = ankietiesByIdZestawuPytan;
    }

    @OneToMany(mappedBy = "zestawyPytanByIdZestawuPytan")
    public Collection<PytaniaEntity> getPytaniasByIdZestawuPytan() {
        return pytaniasByIdZestawuPytan;
    }

    public void setPytaniasByIdZestawuPytan(Collection<PytaniaEntity> pytaniasByIdZestawuPytan) {
        this.pytaniasByIdZestawuPytan = pytaniasByIdZestawuPytan;
    }
}
