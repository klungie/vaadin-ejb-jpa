package com.ui;

//~--- non-JDK imports --------------------------------------------------------

import com.bs.CityService;
import com.controller.CityFormController;
import com.vaadin.ui.Form;
import com.vaadin.ui.Window;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CityFormWindow extends Window {
    private static final Logger      log  = (Logger) LoggerFactory.getLogger(CityFormWindow.class);
    private final Form               form = new Form();
    private final MainApplication    app;
    private final CityFormController cityFormController;
    private final CityService        cityService;

    public CityFormWindow(final MainApplication app) {
        this.app                = app;
        this.cityService        = MainApplication.grabCityService();
        this.cityFormController = new CityFormController(null);
        this.setCaption("Form City");
        buildUI();
    }

    private void buildUI() {
//        form.setItemDataSource(bean);
        form.setImmediate(true);
        form.setWriteThrough(false);

    }
}
