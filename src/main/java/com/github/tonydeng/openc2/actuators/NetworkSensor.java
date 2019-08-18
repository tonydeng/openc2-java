package com.github.tonydeng.openc2.actuators;

import com.github.tonydeng.openc2.utilities.Keys;
import com.github.tonydeng.openc2.utilities.OpenC2Map;

/**
 * Sample implementation of a single attribute actuator
 * This is a sample because the spec does not have specific
 * definition for actuators at this time
 *
 * @author Tony Deng
 * @version V1.0
 * @date 2019-08-18 20:45
 **/
public class NetworkSensor extends OpenC2Map<ActuatorType> {
    /**
     * Constructor
     */
    public NetworkSensor() {
        super(ActuatorType.NETWORK_SENSOR);
    }

    public NetworkSensor setName(String name) {
        super.put(Keys.NAME, name);
        return this;
    }

    public NetworkSensor setPath(String path) {
        super.put(Keys.PATH, path);
        return this;
    }

    public String getName() {
        return super.get(Keys.NAME).toString();
    }

    public String getPath() {
        return super.get(Keys.PATH).toString();
    }

}
