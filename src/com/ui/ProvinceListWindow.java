package com.ui;

import com.controller.ProvinceController;
import com.model.Province;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//~--- CLASSES --------------------------------------------------------------------------------------------------------------------------------------

@SuppressWarnings({ "UnusedDeclaration", "FieldCanBeLocal" })
public class ProvinceListWindow extends Window {
    private static final Logger log = (Logger) LoggerFactory.getLogger(ProvinceListWindow.class);

    //~--- FIELDS -----------------------------------------------------------------------------------------------------------------------------------

    private final Button             btnRefresh = new Button("Refresh");
    private final Button             btnAdd     = new Button("Add");
    private final Table              table      = new Table("");
    private final MainApplication    app;
    private final ProvinceController provinceController;

    //~--- CONSTRUCTORS -----------------------------------------------------------------------------------------------------------------------------

    /**
     * Constructs ...
     *
     *
     * @param app
     */
    public ProvinceListWindow(final MainApplication app) {
        this.app           = app;
        provinceController = new ProvinceController();

        buildUI();
        initListener();
    }

    //~--- METHODS ----------------------------------------------------------------------------------------------------------------------------------

    private void buildUI() {
        final String status = "provinceService.buildUI";

        try {
            this.setCaption("List Province");
            table.setContainerDataSource(ProvinceController.provinceBeanContainer);
            table.setSelectable(true);
            table.setVisibleColumns(new Object[] { "provincename" });
            table.setColumnHeader("provincename", "Province Name");

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
                provinceController.doRefresh();
            }
        });
        btnAdd.addListener(new Button.ClickListener() {
            public void buttonClick(final Button.ClickEvent event) {
                new ProvinceFormWindow(app, provinceController, new Province());
            }
        });
        table.addListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(final ItemClickEvent itemClickEvent) {
                if (itemClickEvent.isCtrlKey()) {
                    provinceController.doDelete((Integer) itemClickEvent.getItemId());
                } else {
                    final Province province = provinceController.doEdit((Integer) itemClickEvent.getItemId());

                    new ProvinceFormWindow(app, provinceController, province);
                }
            }
        });
    }
}
