package com.vn.bpmcontrol.view.processinstance.runtime.job;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vn.bpmcontrol.entity.job.JobData;
import io.jmix.flowui.fragment.FragmentDescriptor;
import io.jmix.flowui.fragmentrenderer.FragmentRenderer;
import io.jmix.flowui.fragmentrenderer.RendererItemContainer;

@FragmentDescriptor("job-failed-activity-id-column-fragment.xml")
@RendererItemContainer("jobDc")
public class JobFailedActivityIdColumnFragment extends FragmentRenderer<HorizontalLayout, JobData> {

}