/*
 *  Copyright (c) 2014-2017 Kumuluz and/or its affiliates
 *  and other contributors as indicated by the @author tags and
 *  the contributor list.
 *
 *  Licensed under the MIT License (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  https://opensource.org/licenses/MIT
 *
 *  The software is provided "AS IS", WITHOUT WARRANTY OF ANY KIND, express or
 *  implied, including but not limited to the warranties of merchantability,
 *  fitness for a particular purpose and noninfringement. in no event shall the
 *  authors or copyright holders be liable for any claim, damages or other
 *  liability, whether in an action of contract, tort or otherwise, arising from,
 *  out of or in connection with the software or the use or other dealings in the
 *  software. See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.kumuluz.ee.jwt.auth;

import com.kumuluz.ee.common.Extension;
import com.kumuluz.ee.common.config.EeConfig;
import com.kumuluz.ee.common.dependencies.*;
import com.kumuluz.ee.common.wrapper.KumuluzServerWrapper;
import com.kumuluz.ee.configuration.utils.ConfigurationUtil;
import com.kumuluz.ee.jwt.auth.feature.FeatureDisabledSingleton;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

/**
 * KumuluzEE extension definition.
 *
 * @author Benjamin Kastelic
 * @since 1.0.0
 */
@EeExtensionDef(name = "jwt-auth", group = EeExtensionGroup.SECURITY)
@EeComponentDependencies({
        @EeComponentDependency(EeComponentType.CDI),
        @EeComponentDependency(EeComponentType.JSON_P),
        @EeComponentDependency(EeComponentType.JAX_RS)
})
public class JWTAuthExtension implements Extension {

    private static final Logger log = Logger.getLogger(JWTAuthExtension.class.getName());

    @Override
    public void init(KumuluzServerWrapper kumuluzServerWrapper, EeConfig eeConfig) {
        log.info("Initialising JWT auth extension.");
        FeatureDisabledSingleton.init(true);
    }

    @Override
    public void load() {
        log.info("Initialised JWT auth extension.");
    }

    @Override
    public List<String> scanLibraries() {
        return Collections.singletonList("kumuluzee-jwt-auth");
    }

    @Override
    public boolean isEnabled() {
        return ConfigurationUtil.getInstance().getBoolean("kumuluzee.jwt-auth.enabled").orElse(true);
    }
}
