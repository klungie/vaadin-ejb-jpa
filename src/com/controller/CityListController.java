package com.controller;

//~--- non-JDK imports --------------------------------------------------------

import com.bs.CityService;
import com.model.City;
import com.vaadin.data.util.BeanContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

//~--- JDK imports ------------------------------------------------------------

@SuppressWarnings("UnusedDeclaration")
public class CityListController {
    private static final Logger                      log      =
        (Logger) LoggerFactory.getLogger(CityListController.class);
    public static final BeanContainer<Integer, City> cityBean = new BeanContainer<Integer, City>(City.class);

    public static synchronized void doRefresh(final CityService cityService) {
        log.info("CityListController doRefresh");

        try {
            final List list = cityService.findAll();

            cityBean.removeAllItems();

            // noinspection unchecked
            cityBean.addAll(list);
            cityBean.setBeanIdProperty("cityid");
        } catch (Exception e) {}
    }
}
