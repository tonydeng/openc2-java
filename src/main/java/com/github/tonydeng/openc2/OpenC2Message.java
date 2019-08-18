package com.github.tonydeng.openc2;

import com.github.tonydeng.openc2.action.ActionType;
import com.github.tonydeng.openc2.actuators.ActuatorType;
import com.github.tonydeng.openc2.header.Header;
import com.github.tonydeng.openc2.targets.TargetType;
import com.github.tonydeng.openc2.utilities.OpenC2Map;
import lombok.Getter;

/**
 * OpenC2Message is the base object that should be used when working with OpenC2 request messages.
 * At a minimum all OpenC2 messages must have an action and a target.  Id, actuator and args are
 * optional.
 *
 * @author dengtao
 **/

@Getter
public class OpenC2Message {

    private String id;
    private Header header;
    private ActionType action;
    private OpenC2Map<TargetType> target;
    private OpenC2Map<ActuatorType> actuator;
    private OpenC2Map<String> args;

    public OpenC2Message() {
    }

    /**
     * Constructor to assign an action and target to the message
     *
     * @param action ActionType that describes the OpenC2 message
     * @param target Target object for the message
     */
    public OpenC2Message(ActionType action, OpenC2Map<TargetType> target) {
        this.action = action;
        this.target = target;
    }

    public OpenC2Message setHeader(Header header) {
        this.header = header;
        return this;
    }

    public OpenC2Message setId(String id) {
        this.id = id;
        return this;
    }

    public OpenC2Message setAction(String action) {
        this.action = ActionType.valueOf(action.toUpperCase());
        return this;
    }

    public OpenC2Message setTarget(OpenC2Map<TargetType> target) {
        this.target = target;
        return this;
    }

    public OpenC2Message setActuator(OpenC2Map<ActuatorType> actuator) {
        this.actuator = actuator;
        return this;
    }

    public OpenC2Message setArgs(OpenC2Map<String> args) {
        this.args = args;
        return this;
    }

    /**
     * Check if the id value has been set
     *
     * @return true if the id value has been set
     */
    public boolean hasHeader() {
        return (header != null && !header.isEmpty());
    }

    public String toJson() {
//    return JSON.toJSONString(this);
        return null;
    }

    public String toPrettyJson() {
//    return JSON.toJSONString(this, SerializerFeature.PrettyFormat);
        return null;
    }
}
