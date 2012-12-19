package com.bs;

import com.model.City;
import com.model.Province;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

//~--- CLASSES --------------------------------------------------------------------------------------------------------------------------------------

@SuppressWarnings("UnusedDeclaration")
@Stateless
public class ProvinceService {
    private static final Logger log = (Logger) LoggerFactory.getLogger(ProvinceService.class);

    //~--- FIELDS -----------------------------------------------------------------------------------------------------------------------------------

    @PersistenceContext(unitName = "testPU")
    private EntityManager em;

    //~--- CONSTRUCTORS -----------------------------------------------------------------------------------------------------------------------------

    /**
     * Constructs ...
     *
     */
    public ProvinceService() {
        log.info("ProvinceService is constructed");
    }

    //~--- METHODS ----------------------------------------------------------------------------------------------------------------------------------

    public List findAll() {
        log.info("ProvinceService findAll");

        final Query q = em.createNamedQuery("provinceAll");

        return q.getResultList();
    }

    public Province findById(final Integer id) {
        log.info("ProvinceService findById");

        final Province province = em.find(Province.class, id);

        province.getCityList().size();    // access lazy

        return province;
    }

    public void remove(final Province province) {
        log.info("ProvinceService remove");

        final Province p = em.find(Province.class, province.getProvinceid());

        em.remove(p);
    }

    public boolean save(final Province province) {
        log.info("ProvinceService save");

        final List<City> cityList = province.getCityList();

        if (cityList != null) {
            for (final City c : cityList) {
                c.setProvince(province);
            }
        }

        if (province.getProvinceid() == null) {
            em.persist(province);

            return true;
        } else {
            em.merge(province);

            return false;
        }
    }
}
