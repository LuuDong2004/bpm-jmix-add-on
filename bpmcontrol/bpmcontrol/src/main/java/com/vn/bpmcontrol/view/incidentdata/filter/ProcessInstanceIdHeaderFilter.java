/*
 * Copyright (c) Haulmont 2024. All Rights Reserved.
 * Use is subject to license terms.
 */

package com.vn.bpmcontrol.view.incidentdata.filter;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.router.QueryParameters;
import com.vn.bpmcontrol.facet.urlqueryparameters.HasFilterUrlParamHeaderFilter;
import io.jmix.flowui.component.grid.DataGridColumn;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.model.InstanceContainer;
import io.micrometer.common.util.StringUtils;
import com.vn.bpmcontrol.entity.filter.IncidentFilter;
import com.vn.bpmcontrol.entity.incident.IncidentData;
import com.vn.bpmcontrol.view.incidentdata.IncidentHeaderFilter;

import java.util.HashMap;
import java.util.Map;

import static com.vn.bpmcontrol.facet.urlqueryparameters.IncidentListQueryParamBinder.*;
import static com.vn.bpmcontrol.view.util.FilterQueryParamUtils.getStringParam;

public class ProcessInstanceIdHeaderFilter extends IncidentHeaderFilter implements HasFilterUrlParamHeaderFilter {

    protected TypedTextField<String> processInstanceIdField;

    public ProcessInstanceIdHeaderFilter(Grid<IncidentData> dataGrid,
                                         DataGridColumn<IncidentData> column,
                                         InstanceContainer<IncidentFilter> filterDc) {
        super(dataGrid, column, filterDc);
    }


    @Override
    protected Component createFilterComponent() {
        return createProcessInstanceIdFilter();
    }

    @Override
    protected void resetFilterValues() {
        processInstanceIdField.clear();
    }

    @Override
    public void apply() {
        String value = processInstanceIdField.getTypedValue();
        boolean notEmptyValue = StringUtils.isNotEmpty(value);
        if (notEmptyValue) {
            filterDc.getItem().setProcessInstanceId(value);
        } else {
            filterDc.getItem().setProcessInstanceId(null);
        }


        filterButton.getElement().setAttribute(COLUMN_FILTER_BUTTON_ACTIVATED_ATTRIBUTE_NAME, notEmptyValue);
    }

    @Override
    public void updateComponents(QueryParameters queryParameters) {
        String paramValue = getStringParam(queryParameters, PROCESS_INSTANCE_ID_FILTER_PARAM);

        processInstanceIdField.setTypedValue(paramValue);
        apply();
    }

    @Override
    public Map<String, String> getQueryParamValues() {
        Map<String, String> paramValues = new HashMap<>();
        paramValues.put(PROCESS_INSTANCE_ID_FILTER_PARAM, processInstanceIdField.getTypedValue());

        return paramValues;
    }

    protected TypedTextField<String> createProcessInstanceIdFilter() {
        processInstanceIdField = uiComponents.create(TypedTextField.class);
        processInstanceIdField.setWidthFull();
        processInstanceIdField.setMinWidth("30em");
        processInstanceIdField.setClearButtonVisible(true);
        processInstanceIdField.setLabel(messages.getMessage(IncidentFilter.class, "IncidentFilter.processInstanceId"));
        processInstanceIdField.setPlaceholder(messages.getMessage(getClass(), "processInstanceId.placeholder"));

        return processInstanceIdField;
    }
}
