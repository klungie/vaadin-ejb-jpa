package com.model;

import javax.persistence.*;
import java.util.Date;

//~--- CLASSES --------------------------------------------------------------------------------------------------------------------------------------

@SuppressWarnings({ "JpaDataSourceORMInspection", "UnusedDeclaration" })
@NamedNativeQueries({ @NamedNativeQuery(
    name           = "cityAll",
    query          = "select * from test.tblcity",
    resultClass    = City.class
) })
@SequenceGenerator(
    name           = "seqcity",
    schema         = "test",
    sequenceName   = "test.seqcity",
    allocationSize = 1
)
@Entity
@Table(name = "tblcity", schema = "test")
public class City {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqcity")
    @Id
    private Integer  cityid;
    private String   cityname;
    private Integer  createby;
    private Date     createdatetime;
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinColumn(name = "provinceid", referencedColumnName = "provinceid")
    private Province province;
    private Integer  updateby;
    private Date     updatedatetime;

    //~--- METHODS ----------------------------------------------------------------------------------------------------------------------------------

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if ((o == null) || (getClass() != o.getClass())) {
            return false;
        }

        final City city = (City) o;

        if ((cityid != null) ? !cityid.equals(city.cityid) : city.cityid != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return (cityid != null) ? cityid.hashCode() : 0;
    }

    @Override
    public String toString() {
        return this.getCityname();
    }

    //~--- GET METHODS ------------------------------------------------------------------------------------------------------------------------------

    public Integer getCityid() {
        return cityid;
    }

    public String getCityname() {
        return cityname;
    }

    public Integer getCreateby() {
        return createby;
    }

    public Date getCreatedatetime() {
        return createdatetime;
    }

    public Province getProvince() {
        return province;
    }

    public Integer getUpdateby() {
        return updateby;
    }

    public Date getUpdatedatetime() {
        return updatedatetime;
    }

    //~--- SET METHODS ------------------------------------------------------------------------------------------------------------------------------

    public void setCityid(final Integer cityid) {
        this.cityid = cityid;
    }

    public void setCityname(final String cityname) {
        this.cityname = cityname;
    }

    public void setCreateby(final Integer createby) {
        this.createby = createby;
    }

    public void setCreatedatetime(final Date createdatetime) {
        this.createdatetime = createdatetime;
    }

    public void setProvince(final Province province) {
        this.province = province;
    }

    public void setUpdateby(final Integer updateby) {
        this.updateby = updateby;
    }

    public void setUpdatedatetime(final Date updatedatetime) {
        this.updatedatetime = updatedatetime;
    }

    //~--- METHODS ----------------------------------------------------------------------------------------------------------------------------------

    @PrePersist
    private void prePersist() {
        createby       = 777;
        createdatetime = new Date();
    }

    @PreUpdate
    private void preUpdate() {
        updateby       = 777;
        updatedatetime = new Date();
    }
}
