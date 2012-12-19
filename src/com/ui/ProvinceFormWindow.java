package com.ui;

import com.controller.ProvinceController;
import com.model.City;
import com.model.Province;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Form;
import com.vaadin.ui.Table;
import com.vaadin.ui.Window;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.Collection;

//~--- CLASSES --------------------------------------------------------------------------------------------------------------------------------------

@SuppressWarnings("UnusedDeclaration")
public class ProvinceFormWindow extends Window {
    private static final Logger log = (Logger) LoggerFactory.getLogger(ProvinceFormWindow.class);

    //~--- FIELDS -----------------------------------------------------------------------------------------------------------------------------------

    private final Form               form    = new Form();
    private final Button             btnSave = new Button("Save");
    private final Button             btnAdd  = new Button("Add City");
    private final Table              table   = new Table("");
    private final MainApplication    app;
    private final ProvinceController provinceController;

    //~--- CONSTRUCTORS -----------------------------------------------------------------------------------------------------------------------------

    /**
     * Constructs ...
     *
     *
     * @param app
     * @param provinceController
     * @param province
     */
    public ProvinceFormWindow(final MainApplication app, final ProvinceController provinceController, final Province province) {
        this.app                = app;
        this.provinceController = provinceController;

        buildUI(province);
        initListener();
    }

    //~--- METHODS ----------------------------------------------------------------------------------------------------------------------------------

    private void buildUI(final Province province) {
        this.setCaption("Form Province");
        this.addComponent(form);
        this.addComponent(btnAdd);
        this.addComponent(table);
        this.addComponent(btnSave);
        this.setWidth(400, UNITS_PIXELS);
        this.setHeight(400, UNITS_PIXELS);
        form.setItemDataSource(new BeanItem<Province>(province));
        form.setWriteThrough(false);
        form.setVisibleItemProperties(new Object[] { "provincename" });

        // set table city
        BeanItemContainer<City> bic = new BeanItemContainer<City>(City.class);

        try {
            if (province.getCityList() != null) {
                bic.addAll(province.getCityList());
            }
        } catch (Exception ex) {

            // ignore lazy exception
        }

        table.setContainerDataSource(bic);
        table.setSelectable(true);
        table.setVisibleColumns(new Object[] { "cityname" });
        table.setColumnHeader("cityname", "City Name");
        table.setHeight(200, UNITS_PIXELS);
        table.setEditable(true);
        app.getMainWindow().addWindow(this);
    }

    private void initListener() {
        btnSave.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(final Button.ClickEvent clickEvent) {
                form.commit();

                // noinspection unchecked
                final Province province = ((BeanItem<Province>) form.getItemDataSource()).getBean();
                Collection     list     = table.getItemIds();

                if (province.getCityList() != null) {
                    province.getCityList().clear();
                    province.getCityList().addAll(list);
                } else if (list != null && list.size() > 0) {

                    // adding city for the first time to province
                    province.setCityList(new ArrayList<City>());
                    province.getCityList().addAll(list);
                }

                provinceController.doSave(province);
                ((ProvinceFormWindow) getWindow()).close();
            }
        });
        btnAdd.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                final BeanItemContainer<City> bic = (BeanItemContainer<City>) table.getContainerDataSource();

                bic.addBean(new City());
            }
        });
        table.addListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent itemClickEvent) {
                if (itemClickEvent.isDoubleClick()) {
                    table.removeItem(itemClickEvent.getItemId());
                }
            }
        });
    }
}
