package com.controller;

//~--- non-JDK imports --------------------------------------------------------

import com.model.City;
import com.vaadin.data.util.BeanItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CityFormController {
    private static final Logger  log = (Logger) LoggerFactory.getLogger(CityFormController.class);
//    private BeanItem<City> beanItem = new BeanItem<City>();

    public CityFormController(final BeanItem<City> beanItem) {
//        this.beanItem = beanItem;
    }
}
