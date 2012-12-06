package com.ui;

//~--- non-JDK imports --------------------------------------------------------

import com.bs.CityService;
import com.controller.CityListController;
import com.vaadin.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({ "UnusedDeclaration", "FieldCanBeLocal" })
public class CityListWindow extends Window {
    private static final Logger      log        = (Logger) LoggerFactory.getLogger(CityListWindow.class);
    private final Button             btnRefresh = new Button("Refresh");
    private final Button             btnAdd     = new Button("Add");
    private final Table              table      = new Table("");
    private final MainApplication    app;
    private final CityListController cityListController;
    private final CityService        cityService;

    /**
     * Constructs ...
     *
     *
     * @param app
     */
    public CityListWindow(final MainApplication app) {
        this.app           = app;
        cityService        = MainApplication.grabCityService();
        cityListController = new CityListController();
        this.setCaption("List City");
        buildUI();
    }

    private void buildUI() {
        final String status = "cityService.buildUI";

        try {
            table.setContainerDataSource(CityListController.cityBean);
            table.setSelectable(true);
            table.setImmediate(true);
            table.setVisibleColumns(new Object[] { "cityname" });
            table.setColumnHeader("cityname", "Nama Kota");
            btnRefresh.addListener(new Button.ClickListener() {
                public void buttonClick(final Button.ClickEvent event) {
                    CityListController.doRefresh(cityService);
                }
            });

            // add to main window
            final HorizontalLayout hLay = new HorizontalLayout();

            hLay.addComponent(btnRefresh);
            hLay.addComponent(btnAdd);
            this.addComponent(hLay);
            this.addComponent(table);

            // set sizes
            table.setHeight(200, UNITS_PIXELS);
            this.setHeight(330, UNITS_PIXELS);
            this.setWidth(270, UNITS_PIXELS);
        } catch (Exception e) {
            final Label label = new Label("There is no data");

            this.addComponent(label);
        }
    }
}
