package com.model;

import javax.persistence.*;
import java.util.List;

//~--- CLASSES --------------------------------------------------------------------------------------------------------------------------------------

@SuppressWarnings({ "JpaDataSourceORMInspection", "UnusedDeclaration" })
@NamedNativeQueries({ @NamedNativeQuery(
    name           = "provinceAll",
    query          = "select * from test.tblprovince",
    resultClass    = Province.class
) })
@SequenceGenerator(
    name           = "seqprovince",
    schema         = "test",
    sequenceName   = "test.seqprovince",
    allocationSize = 1
)
@Entity
@Table(name = "tblprovince", schema = "test")
public class Province {
    @OneToMany(
        mappedBy = "province",
        cascade  = {},
        fetch    = FetchType.LAZY
    )
    private List<City> cityList;
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqprovince")
    @Id
    private Integer    provinceid;
    private String     provincename;

    //~--- METHODS ----------------------------------------------------------------------------------------------------------------------------------

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if ((o == null) || (getClass() != o.getClass())) {
            return false;
        }

        final Province province = (Province) o;

        if ((provinceid != null) ? !provinceid.equals(province.provinceid) : province.provinceid != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return (provinceid != null) ? provinceid.hashCode() : 0;
    }

    @Override
    public String toString() {
        return this.getProvincename();
    }

    //~--- GET METHODS ------------------------------------------------------------------------------------------------------------------------------

    public List<City> getCityList() {
        return cityList;
    }

    public Integer getProvinceid() {
        return provinceid;
    }

    public String getProvincename() {
        return provincename;
    }

    //~--- SET METHODS ------------------------------------------------------------------------------------------------------------------------------

    public void setCityList(final List<City> cityList) {
        this.cityList = cityList;
    }

    public void setProvinceid(final Integer provinceid) {
        this.provinceid = provinceid;
    }

    public void setProvincename(final String provincename) {
        this.provincename = provincename;
    }
}
