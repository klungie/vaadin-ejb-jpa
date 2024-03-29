package com.ui;

import com.controller.CityController;
import com.model.City;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//~--- CLASSES --------------------------------------------------------------------------------------------------------------------------------------

@SuppressWarnings({ "UnusedDeclaration", "FieldCanBeLocal" })
public class CityListWindow extends Window {
    private static final Logger log = (Logger) LoggerFactory.getLogger(CityListWindow.class);

    //~--- FIELDS -----------------------------------------------------------------------------------------------------------------------------------

    private final Button          btnRefresh = new Button("Refresh");
    private final Button          btnAdd     = new Button("Add");
    private final Table           table      = new Table("");
    private final MainApplication app;
    private final CityController  cityController;

    //~--- CONSTRUCTORS -----------------------------------------------------------------------------------------------------------------------------

    /**
     * Constructs ...
     *
     *
     * @param app
     */
    public CityListWindow(final MainApplication app) {
        this.app       = app;
        cityController = new CityController();

        buildUI();
        initListener();
    }

    //~--- METHODS ----------------------------------------------------------------------------------------------------------------------------------

    private void buildUI() {
        final String status = "cityService.buildUI";

        try {
            this.setCaption("List City");
            table.setContainerDataSource(CityController.cityBeanContainer);
            table.setSelectable(true);
            table.setVisibleColumns(new Object[] { "cityname" });
            table.setColumnHeader("cityname", "City Name");

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

    private void initListener() {
        btnRefresh.addListener(new Button.ClickListener() {
            public void buttonClick(final Button.ClickEvent event) {
                cityController.doRefresh();
            }
        });
        btnAdd.addListener(new Button.ClickListener() {
            public void buttonClick(final Button.ClickEvent event) {
                new CityFormWindow(app, cityController, new City());
            }
        });
        table.addListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(final ItemClickEvent itemClickEvent) {
                if (itemClickEvent.isCtrlKey()) {
                    cityController.doDelete((Integer) itemClickEvent.getItemId());
                } else {
                    final City city = cityController.doEdit((Integer) itemClickEvent.getItemId());

                    new CityFormWindow(app, cityController, city);
                }
            }
        });
    }
}
