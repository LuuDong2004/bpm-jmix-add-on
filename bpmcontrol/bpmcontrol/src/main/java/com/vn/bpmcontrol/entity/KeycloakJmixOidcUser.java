package com.vn.bpmcontrol.entity;

import io.jmix.oidc.user.DefaultJmixOidcUser;
import lombok.Getter;
import lombok.Setter;

/**
 * Extension of {@link DefaultJmixOidcUser} that adds support for a user-specific locale.
 * <p>
 * The locale value can then be used by {@link com.vn.bpmcontrol.configuration.ControlOidcLocaleResolver}
 * to localize the user interface.
 * </p>
 */
@Getter
@Setter
public class KeycloakJmixOidcUser extends DefaultJmixOidcUser {

    private String locale;

}