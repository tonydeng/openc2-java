package com.github.tonydeng.openc2.actuators;

import com.github.tonydeng.openc2.utilities.OpenC2Map;

/**
 * Sample implementation of a single attribute actuator
 * This is a sample because the spec does not have specific definition for actuators at this time
 *
 * @author Tony Deng
 * @version V1.0
 **/
public class Network extends OpenC2Map<ActuatorType> {
    /**
     * Constructor
     */
    public Network() {
        super(ActuatorType.NETWORK);
    }

    /**
     * Constructor
     *
     * @param endpoint sample network to assign to the object
     */
    public Network(String endpoint) {
        super(ActuatorType.NETWORK);
        setNetwork(endpoint);
    }

    public void setNetwork(String endpoint) {
        super.put(ActuatorType.NETWORK.toString(), endpoint);
    }

    public String getNetwork() {
        return super.get(ActuatorType.NETWORK.toString()).toString();
    }

}
