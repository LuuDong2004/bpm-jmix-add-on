/*
 * Copyright (c) Haulmont 2025. All Rights Reserved.
 * Use is subject to license terms.
 */

package com.vn.bpmcontrol.configuration;

import feign.Client;
import feign.Contract;
import feign.RequestInterceptor;
import feign.Retryer;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.codec.ErrorDecoder;
import com.vn.bpmcontrol.restsupport.DynamicEngineUrlRequestInterceptor;
import com.vn.bpmcontrol.restsupport.FeignClientProvider;
import com.vn.bpmcontrol.restsupport.ObjectToStringConverter;
import com.vn.bpmcontrol.restsupport.camunda.CamundaFeignErrorDecoder;
import com.vn.bpmcontrol.service.engine.EngineService;
import org.camunda.community.rest.EnableCamundaRestClient;
import org.camunda.community.rest.client.FeignClientConfiguration;
import org.camunda.community.rest.config.CamundaRestClientProperties;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

import java.util.Optional;

@Configuration
@EnableCamundaRestClient
@Import({FeignClientsConfiguration.class, FeignClientConfiguration.class})
public class CamundaFeignConfiguration {

    @Bean("control_FeignClientProvider")
    public FeignClientProvider feignClientProvider(Encoder encoder,
                                                   Decoder decoder,
                                                   Retryer retryer,
                                                   ErrorDecoder errorDecoder,
                                                   Contract contract,
                                                   Optional<Client> client,
                                                   FeignClientProperties properties) {
        return new FeignClientProvider(encoder, decoder, retryer, errorDecoder, contract, client.orElse(null), properties);
    }

    @Bean("control_DynamicEngineUrlRequestInterceptor")
    public RequestInterceptor dynamicUrlInterceptor(EngineService engineService) {
        return new DynamicEngineUrlRequestInterceptor(engineService);
    }

    @Bean("control_ObjectToStringConverter")
    public ObjectToStringConverter objectToStringConverter() {
        return new ObjectToStringConverter();
    }


    @Bean("control_CamundaFeignErrorDecoder")
    @Primary
    public ErrorDecoder errorDecoder(CamundaRestClientProperties restClientProperties) {
        return new CamundaFeignErrorDecoder(restClientProperties);
    }
}