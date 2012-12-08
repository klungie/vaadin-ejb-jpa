package com.ui;

import com.controller.ProvinceController;
import com.model.Province;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Form;
import com.vaadin.ui.Window;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//~--- CLASSES --------------------------------------------------------------------------------------------------------------------------------------

@SuppressWarnings("UnusedDeclaration")
public class ProvinceFormWindow extends Window {
    private static final Logger log = (Logger) LoggerFactory.getLogger(ProvinceFormWindow.class);

    //~--- FIELDS -----------------------------------------------------------------------------------------------------------------------------------

    private final Form               form    = new Form();
    private final Button             btnSave = new Button("Save");
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
        this.addComponent(btnSave);
        this.setWidth(400, UNITS_PIXELS);
        this.setHeight(400, UNITS_PIXELS);
        form.setItemDataSource(new BeanItem<Province>(province));
        form.setWriteThrough(false);
        form.setVisibleItemProperties(new Object[] { "provincename" });
        app.getMainWindow().addWindow(this);
    }

    private void initListener() {
        btnSave.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(final Button.ClickEvent clickEvent) {
                form.commit();

                // noinspection unchecked
                provinceController.doSave(((BeanItem<Province>) form.getItemDataSource()).getBean());
                ((ProvinceFormWindow) getWindow()).close();
            }
        });
    }
}
