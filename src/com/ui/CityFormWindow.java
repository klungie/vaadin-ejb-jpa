package com.ui;

import com.controller.CityController;
import com.controller.ProvinceController;
import com.model.City;
import com.model.Province;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//~--- CLASSES --------------------------------------------------------------------------------------------------------------------------------------

public class CityFormWindow extends Window {
    private static final Logger log = (Logger) LoggerFactory.getLogger(CityFormWindow.class);

    //~--- FIELDS -----------------------------------------------------------------------------------------------------------------------------------

    private final Form            form    = new Form();
    private final Button          btnSave = new Button("Save");
    private final MainApplication app;
    private final CityController  cityController;

    //~--- CONSTRUCTORS -----------------------------------------------------------------------------------------------------------------------------

    /**
     * Constructs ...
     *
     *
     * @param app
     * @param cityController
     * @param city
     */
    public CityFormWindow(final MainApplication app, final CityController cityController, final City city) {
        this.app            = app;
        this.cityController = cityController;

        buildUI(city);
        initListener();
    }

    //~--- METHODS ----------------------------------------------------------------------------------------------------------------------------------

    private void buildUI(final City city) {
        this.setCaption("Form City");
        this.addComponent(form);
        this.addComponent(btnSave);
        this.setWidth(400, UNITS_PIXELS);
        this.setHeight(400, UNITS_PIXELS);
        form.setWriteThrough(false);
        form.setVisibleItemProperties(new Object[] { "cityname", "province" });
        form.setFormFieldFactory(new FormFieldFactory() {
            @Override
            public Field createField(final Item item, final Object o, final Component component) {
                final String pid = (String) o;

                if ("cityname".equals(pid)) {
                    final Field field = new TextField("City Name");

                    return field;
                } else if ("province".equals(pid)) {
                    final ComboBox                    field = new ComboBox("Province");
                    final BeanItemContainer<Province> bic   = ProvinceController.constructBeanItemContainer();

                    field.setContainerDataSource(bic);
                    field.setItemCaptionPropertyId("provincename");
                    field.setTextInputAllowed(true);
                    field.setInputPrompt("please select one");
                    field.setNewItemsAllowed(true);
                    field.setFilteringMode(AbstractSelect.Filtering.FILTERINGMODE_CONTAINS);
                    field.setImmediate(true);
                    field.setNewItemHandler(new AbstractSelect.NewItemHandler() {
                        @Override
                        public void addNewItem(String s) {
                            Province p = new Province();

                            p.setProvincename(s);
                            bic.addBean(p);
                            field.setValue(p);
                        }
                    });

                    return field;
                }

                return null;
            }
        });
        form.setItemDataSource(new BeanItem<City>(city));
        app.getMainWindow().addWindow(this);
    }

    private void initListener() {
        btnSave.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(final Button.ClickEvent clickEvent) {
                form.commit();

                // noinspection unchecked
                cityController.doSave(((BeanItem<City>) form.getItemDataSource()).getBean());
                ((CityFormWindow) getWindow()).close();
            }
        });
    }
}
