package com.vn.autoconfigure.bpmcontrol;

import com.vn.bpmcontrol.BpmctlConfiguration;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Import;

@AutoConfiguration
@Import({BpmctlConfiguration.class})
public class BpmctlAutoConfiguration {
}

