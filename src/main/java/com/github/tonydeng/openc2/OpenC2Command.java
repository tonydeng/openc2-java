package com.github.tonydeng.openc2;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.tonydeng.openc2.action.ActionType;
import com.github.tonydeng.openc2.actuators.ActuatorType;
import com.github.tonydeng.openc2.header.Header;
import com.github.tonydeng.openc2.json.OpenC2CommandDeserializer;
import com.github.tonydeng.openc2.json.OpenC2CommandSerializer;
import com.github.tonydeng.openc2.targets.TargetType;
import com.github.tonydeng.openc2.utilities.OpenC2Map;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * OpenC2Command is the base object that should be used when working with OpenC2 request messages.
 * At a minimum all OpenC2 messages must have an action and a target.  Id, actuator and args are
 * optional.
 *
 * @author dengtao
 **/
@Getter
@JsonSerialize(using = OpenC2CommandSerializer.class)
@JsonDeserialize(using = OpenC2CommandDeserializer.class)
public class OpenC2Command implements OpenC2Message {

    private String id;
    private Header header;
    private ActionType action;
    private OpenC2Map<TargetType> target;
    private OpenC2Map<ActuatorType> actuator;
    private OpenC2Map<String> args;

    /**
     * This constructor on exists for Jackson processing and should not be use directly.
     */
    public OpenC2Command() {
    }

    /**
     * Constructor to assign an action and target to the message
     *
     * @param action ActionType that describes the OpenC2 message
     * @param target Target object for the message
     */
    public OpenC2Command(ActionType action, OpenC2Map<TargetType> target) {
        this.action = action;
        this.target = target;
    }

    /**
     * Constructor to assign the id, action and target to the message
     *
     * @param id     UUID to uniquely identify the message
     * @param action ActionType that describes the OpenC2 message
     * @param target Target object for the message
     */
    public OpenC2Command(String id, ActionType action, OpenC2Map<TargetType> target) {
        this(action, target);
        this.id = id;
    }

    /**
     * Get Action Type Value, Not Type Name
     *
     * @return
     */
    public String getAction() {
        return action.toString();
    }


    public OpenC2Command setHeader(Header header) {
        this.header = header;
        return this;
    }

    public OpenC2Command setId(String id) {
        this.id = id;
        return this;
    }

    public OpenC2Command setAction(String action) {
        this.action = ActionType.valueOf(action.toUpperCase());
        return this;
    }

    public OpenC2Command setTarget(OpenC2Map<TargetType> target) {
        this.target = target;
        return this;
    }

    public OpenC2Command setActuator(OpenC2Map<ActuatorType> actuator) {
        this.actuator = actuator;
        return this;
    }

    public OpenC2Command setArgs(OpenC2Map<String> args) {
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

    /**
     * Check if the id value has been set
     *
     * @return true if the id value has been set
     */
    public boolean hasId() {
        return (StringUtils.isNotEmpty(id));
    }

    /**
     * Check if the actuator object has been created
     *
     * @return true if the actuator object has been set
     */
    public boolean hasActuator() {
        return (actuator != null && actuator.size() > 0);
    }

    /**
     * Check if the args object has been created
     *
     * @return true if the args object has been set
     */
    public boolean hasArgs() {
        return (args != null && args.size() > 0);
    }
}
