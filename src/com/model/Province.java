package com.model;

//~--- JDK imports ------------------------------------------------------------

import javax.persistence.*;

@SuppressWarnings({ "JpaDataSourceORMInspection", "UnusedDeclaration" })
@Entity
@Table(name = "tblprovince", schema = "test")
public class Province {
    @ManyToOne
    @JoinColumn(name = "cityid", referencedColumnName = "cityid")
    private City    city;
    @Id
    private Integer provinceid;
    private String  provincename;

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if ((o == null) || (getClass() != o.getClass())) {
            return false;
        }

        final Province province = (Province) o;

        if ((provinceid != null)
            ? !provinceid.equals(province.provinceid)
            : province.provinceid != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return (provinceid != null)
               ? provinceid.hashCode()
               : 0;
    }

    public City getCity() {
        return city;
    }

    public void setCity(final City city) {
        this.city = city;
    }

    public Integer getProvinceid() {
        return provinceid;
    }

    public void setProvinceid(final Integer provinceid) {
        this.provinceid = provinceid;
    }

    public String getProvincename() {
        return provincename;
    }

    public void setProvincename(final String provincename) {
        this.provincename = provincename;
    }
}
