package com.bs;

//~--- non-JDK imports --------------------------------------------------------

import com.model.City;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

//~--- JDK imports ------------------------------------------------------------

@SuppressWarnings("UnusedDeclaration")
@Stateless
public class CityService {
    private static final Logger log = (Logger) LoggerFactory.getLogger(CityService.class);
    @PersistenceContext(unitName = "testPU")
    private EntityManager       em;

    public CityService() {
        log.info("CityService is constructed");
    }

    public List findAll() {
        log.info("CityService findAll");

        final Query q = em.createNamedQuery("cityAll");

        return q.getResultList();
    }

    public City findById(final Integer id) {
        log.info("CityService findById");

        return em.find(City.class, id);
    }

    public void remove(final City city) {
        log.info("CityService remove");
        em.remove(city);
    }

    public void save(final City city) {
        log.info("CityService save");

        if (city.getCityid() == null) {
            em.persist(city);
        } else {
            em.merge(city);
        }
    }
}
