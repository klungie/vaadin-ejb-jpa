package com.model;

//~--- JDK imports ------------------------------------------------------------

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings({ "JpaDataSourceORMInspection", "UnusedDeclaration" })
@Entity
@Table(name = "tblcity")
public class City {
    @Id
    private Integer cityid;
    private String  cityname;

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if ((o == null) || (getClass() != o.getClass())) {
            return false;
        }

        final City city = (City) o;

        if ((cityid != null)
            ? !cityid.equals(city.cityid)
            : city.cityid != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return (cityid != null)
               ? cityid.hashCode()
               : 0;
    }

    public Integer getCityid() {
        return cityid;
    }

    public void setCityid(final Integer cityid) {
        this.cityid = cityid;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(final String cityname) {
        this.cityname = cityname;
    }
}
