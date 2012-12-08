package com.controller;

import com.bs.ProvinceService;
import com.model.Province;
import com.ui.MainApplication;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

//~--- CLASSES --------------------------------------------------------------------------------------------------------------------------------------

@SuppressWarnings("UnusedDeclaration")
public class ProvinceController {
    private static final Logger                          log                   = (Logger) LoggerFactory.getLogger(ProvinceController.class);
    public static final BeanContainer<Integer, Province> provinceBeanContainer = new BeanContainer<Integer, Province>(Province.class);
    private static final ProvinceService                 provinceService       = MainApplication.grabProvinceService();

    //~--- METHODS ----------------------------------------------------------------------------------------------------------------------------------

    public static BeanItemContainer<Province> constructBeanItemContainer() {
        final BeanItemContainer<Province> bic = new BeanItemContainer<Province>(Province.class);

        for (final Object id : provinceBeanContainer.getItemIds()) {
            final BeanItem<Province> bi = provinceBeanContainer.getItem(id);

            bic.addBean(bi.getBean());
        }

        return bic;
    }

    public Province doEdit(final Integer id) {
        final BeanItem<Province> bi       = provinceBeanContainer.getItem(id);
        final Province           province = provinceService.findById(bi.getBean().getProvinceid());

        return province;
    }

    public void doRefresh() {
        log.info("ProvinceController doRefresh");

        try {
            final List list = provinceService.findAll();

            provinceBeanContainer.setBeanIdProperty("provinceid");
            provinceBeanContainer.removeAllItems();

            // noinspection unchecked
            provinceBeanContainer.addAll(list);
        } catch (Exception e) {}
    }

    public void doSave(final Province province) {
        if (provinceService.save(province)) {
            provinceBeanContainer.addBean(province);
        } else {
            final int index = provinceBeanContainer.indexOfId(province.getProvinceid());

            provinceBeanContainer.removeItem(province.getProvinceid());
            provinceBeanContainer.addBeanAt(index, province);
        }
    }
}
