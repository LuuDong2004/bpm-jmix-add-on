/*
 * Copyright (c) Haulmont 2024. All Rights Reserved.
 * Use is subject to license terms.
 */

package com.vn.bpmcontrol.configuration;

import com.vn.bpmcontrol.uicomponent.grid.ControlDataGridLoader;
import com.vn.bpmcontrol.uicomponent.menu.ControlListMenu;
import com.vn.bpmcontrol.uicomponent.menu.ControlListMenuLoader;
import com.vn.bpmcontrol.uicomponent.spinner.SpinnerLoader;
import com.vn.bpmcontrol.uicomponent.treedatagrid.NoClickTreeDataGridLoader;
import com.vn.bpmcontrol.uicomponent.treedatagrid.NoClickTreeGrid;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.sys.registration.ComponentRegistration;
import io.jmix.flowui.sys.registration.ComponentRegistrationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.vaadin.addons.componentfactory.spinner.Spinner;

@Configuration
public class UiComponentRegistrationConfiguration {

    @Bean
    public ComponentRegistration noClickTreeDataGrid() {
        return ComponentRegistrationBuilder.create(NoClickTreeGrid.class)
                .withComponentLoader("noClickTreeDataGrid", NoClickTreeDataGridLoader.class)
                .build();
    }

    @Bean
    public ComponentRegistration spinner() {
        return ComponentRegistrationBuilder.create(Spinner.class)
                .withComponentLoader("spinner", SpinnerLoader.class)
                .build();
    }

    @Bean
    public ComponentRegistration controlListMenu() {
        return ComponentRegistrationBuilder.create(ControlListMenu.class)
                .withComponentLoader("listMenu", ControlListMenuLoader.class)
                .build();
    }

    @Bean
    public ComponentRegistration controlDataGrid() {
        return ComponentRegistrationBuilder
                .create(DataGrid.class)
                .withComponentLoader("dataGrid", ControlDataGridLoader.class)
                .build();
    }
}
