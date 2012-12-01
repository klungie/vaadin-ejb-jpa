package com;

//~--- non-JDK imports --------------------------------------------------------

import com.vaadin.Application;
import com.vaadin.ui.Label;
import com.vaadin.ui.Window;

/**
 * Created with IntelliJ IDEA.
 * User: klungie
 * Date: 12/1/12
 * Time: 1:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class MySampleVaadinApplication extends Application {
    @Override
    public void init() {
        Window mainWindow = new Window("Vaadin Application");
        Label  label      = new Label("Hello Vaadin user");

        mainWindow.addComponent(label);
        setMainWindow(mainWindow);
    }
}
