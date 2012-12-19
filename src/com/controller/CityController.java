package com.controller;

import com.bs.CityService;
import com.model.City;
import com.ui.MainApplication;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

//~--- CLASSES --------------------------------------------------------------------------------------------------------------------------------------

@SuppressWarnings("UnusedDeclaration")
public class CityController {
    private static final Logger                      log               = (Logger) LoggerFactory.getLogger(CityController.class);
    private static final CityService                 cityService       = MainApplication.grabCityService();
    public static final BeanContainer<Integer, City> cityBeanContainer = new BeanContainer<Integer, City>(City.class);

    //~--- METHODS ----------------------------------------------------------------------------------------------------------------------------------

    public void doDelete(final Integer id) {
        final BeanItem<City> bi = cityBeanContainer.getItem(id);

        cityService.remove(bi.getBean());
    }

    public City doEdit(final Integer id) {
        final BeanItem<City> bi   = cityBeanContainer.getItem(id);
        final City           city = cityService.findById(bi.getBean().getCityid());

        return city;
    }

    public void doRefresh() {
        log.info("CityController doRefresh");

        try {
            final List list = cityService.findAll();

            cityBeanContainer.setBeanIdProperty("cityid");
            cityBeanContainer.removeAllItems();

            // noinspection unchecked
            cityBeanContainer.addAll(list);
        } catch (Exception e) {}
    }

    public void doSave(final City city) {
        cityService.save(city);
    }
}
