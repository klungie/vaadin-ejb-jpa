package com.bs;

import com.model.City;
import com.model.Province;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.constraints.NotNull;
import java.util.List;

//~--- CLASSES --------------------------------------------------------------------------------------------------------------------------------------

@SuppressWarnings("UnusedDeclaration")
@Stateless
public class CityService {
    private static final Logger log = (Logger) LoggerFactory.getLogger(CityService.class);

    //~--- FIELDS -----------------------------------------------------------------------------------------------------------------------------------

    @PersistenceContext(unitName = "testPU")
    private EntityManager em;

    //~--- CONSTRUCTORS -----------------------------------------------------------------------------------------------------------------------------

    /**
     * Constructs ...
     *
     */
    public CityService() {
        log.info("CityService is constructed");
    }

    //~--- METHODS ----------------------------------------------------------------------------------------------------------------------------------

    public List findAll() {
        log.info("CityService findAll");

        final Query q = em.createNamedQuery("cityAll");

        return q.getResultList();
    }

    public City findById(@NotNull final Integer id) {
        log.info("CityService findById");

        final Query query = em.createQuery("SELECT c FROM City c JOIN FETCH c.province WHERE c.cityid = :id").setParameter("id", id);
        final List  list  = query.getResultList();
        final City  city  = list.size() == 0 ? em.find(City.class, id) : (City) list.get(0);

        return city;
    }

    public City reloadProvince(final City city) {
        log.info("CityService refresh");

        final City     cityPersist = em.merge(city);
        final Province p           = cityPersist.getProvince();

        if (p != null) {
            p.getProvincename();

            final Province newP = new Province();

            newP.setProvinceid(p.getProvinceid());
            newP.setProvincename(p.getProvincename());
            em.detach(cityPersist);
            cityPersist.setProvince(newP);
        }

        return cityPersist;
    }

    public void remove(final City city) {
        log.info("CityService remove");

        final City c = em.find(City.class, city.getCityid());

        em.remove(c);
    }

    public boolean save(final City city) {
        log.info("CityService save");

        if (city.getCityid() == null) {
            final Province province = city.getProvince();

            city.setProvince(null);
            em.persist(city);

            if (province != null) {
                city.setProvince(province);
                em.merge(city);
            }

            return true;
        } else {
            em.merge(city);

            return false;
        }
    }
}
