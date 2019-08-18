package com.github.tonydeng.openc2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.tonydeng.openc2.action.ActionType;
import com.github.tonydeng.openc2.actuators.ActuatorType;
import com.github.tonydeng.openc2.header.Header;
import com.github.tonydeng.openc2.json.OpenC2MessageDeserializer;
import com.github.tonydeng.openc2.json.OpenC2MessageSerializer;
import com.github.tonydeng.openc2.targets.TargetType;
import com.github.tonydeng.openc2.utilities.OpenC2Map;
import com.github.tonydeng.openc2.json.JsonFormatter;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * OpenC2Message is the base object that should be used when working with OpenC2 request messages.
 * At a minimum all OpenC2 messages must have an action and a target.  Id, actuator and args are
 * optional.
 *
 * @author dengtao
 **/
@Getter
@JsonSerialize(using = OpenC2MessageSerializer.class)
//@JsonDeserialize(using = OpenC2MessageDeserializer.class);
@JsonDeserialize
public class OpenC2Message {

    private String id;
    private Header header;
    private ActionType action;
    private OpenC2Map<TargetType> target;
    private OpenC2Map<ActuatorType> actuator;
    private OpenC2Map<String> args;

    /**
     * This constructor on exists for Jackson processing and should not be use directly.
     */
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

    /**
     * Constructor to assign the id, action and target to the message
     *
     * @param id     UUID to uniquely identify the message
     * @param action ActionType that describes the OpenC2 message
     * @param target Target object for the message
     */
    public OpenC2Message(String id, ActionType action, OpenC2Map<TargetType> target) {
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

    /**
     * Convert the OpenC2Message object to a JSON string
     *
     * @return String containing the JSON representation of the object
     * @throws JsonProcessingException Exception thrown by the Jackson library
     */
    public String toJson() throws JsonProcessingException {
        return JsonFormatter.getJson(this, false);
    }

    /**
     * Convert the OpenC2Message object to a JSON string that is more
     * reader friendly.
     *
     * @return String containing the JSON representation of the object in human
     * readable format
     * @throws JsonProcessingException Exception thrown by the Jackson library
     */
    public String toPrettyJson() throws JsonProcessingException {
        return JsonFormatter.getJson(this, true);
    }
}
