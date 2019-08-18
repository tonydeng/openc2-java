package com.github.tonydeng.openc2.actuators;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.github.tonydeng.openc2.utilities.Keys;
import com.github.tonydeng.openc2.utilities.OpenC2Map;

/**
 * Sample implementation of a single attribute actuator This is a sample because the spec does not
 * have specific definition for actuators at this time
 *
 * @author Tony Deng
 * @version V1.0
 * @date 2019-08-18 00:14
 **/
public class Endpoint extends OpenC2Map<ActuatorType> {

    /**
     * Constructor
     */
    public Endpoint() {
        super(ActuatorType.ENDPOINT);
    }

    /**
     * Constructor
     *
     * @param endpoint sample endpoint to assign to the object
     */
    public Endpoint(String endpoint) {
        super(ActuatorType.ENDPOINT);
        setEndpoint(endpoint);
    }

    @JsonSetter(Keys.ACTUATOR_ID)
    public void setEndpoint(String endpoint) {
        super.put(Keys.ACTUATOR_ID, endpoint);
    }

    public String getEndpoint() {
        return super.get(Keys.ACTUATOR_ID).toString();
    }
}
