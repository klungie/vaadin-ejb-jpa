package com.ui;

import com.bs.CityService;
import com.bs.ProvinceService;
import com.vaadin.Application;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Window;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.naming.InitialContext;
import javax.naming.NamingException;

//~--- CLASSES --------------------------------------------------------------------------------------------------------------------------------------

@SuppressWarnings("UnusedDeclaration")
public class MainApplication extends Application {
    private static final Logger log = (Logger) LoggerFactory.getLogger(MainApplication.class);

    //~--- CONSTRUCTORS -----------------------------------------------------------------------------------------------------------------------------

    /**
     * Constructs ...
     *
     */
    public MainApplication() {
        log.info("MainApplication is constructed");
    }

    //~--- METHODS ----------------------------------------------------------------------------------------------------------------------------------

    public static CityService grabCityService() {
        try {
            final InitialContext ctx = new InitialContext();

            return (CityService) ctx.lookup("java:global/vaadin_ejb_jpa_war_exploded/CityService!com.bs.CityService");
        } catch (NamingException e) {
            log.error(e.getMessage());

            return null;
        }
    }

    public static ProvinceService grabProvinceService() {
        try {
            final InitialContext ctx = new InitialContext();

            return (ProvinceService) ctx.lookup("java:global/vaadin_ejb_jpa_war_exploded/ProvinceService!com.bs.ProvinceService");
        } catch (NamingException e) {
            log.error(e.getMessage());

            return null;
        }
    }

    @Override
    public void init() {
        buildUI();
    }

    private void buildMenu() {
        final MenuBar menubar = new MenuBar();

        getMainWindow().addComponent(menubar);

        final MenuBar.MenuItem master = menubar.addItem("Master", null);

        buildMenuMaster(master);
    }

    private void buildMenuMaster(final MenuBar.MenuItem master) {
        final MainApplication app = this;

        master.addItem("City", new MenuBar.Command() {
            public void menuSelected(final MenuBar.MenuItem selectedItem) {
                app.getMainWindow().addWindow(new CityListWindow(app));
            }
        });
        master.addItem("Province", new MenuBar.Command() {
            public void menuSelected(final MenuBar.MenuItem selectedItem) {
                app.getMainWindow().addWindow(new ProvinceListWindow(app));
            }
        });
    }

    private void buildUI() {

        // final Window mainWindow = new Window("Gemblitz");
        final Window mainWindow = new Window("Testing framework Vaadin using EJB and JPA");

        setMainWindow(mainWindow);
        buildMenu();
    }
}
