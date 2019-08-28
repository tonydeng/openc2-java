package com.github.tonydeng.openc2.args;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.tonydeng.openc2.utilities.OpenC2Map;

import java.util.Map;

/**
 * Args object holds the command level args that are to be associated with
 * the ActionType of the message.
 *
 * @author Tony Deng
 * @version V1.0
 **/
public class Args extends OpenC2Map<String> {

    public Args() {
        super("args");
    }

    /**
     * Add a key/value pair to the args object
     *
     * @param key   the arg key assigned to the value
     * @param value value for the key
     * @return Args object to allow for method chaining
     */
    public Args addArg(String key, Object value) {
        super.put(key, value);
        return this;
    }

    /**
     * Set a key/value pair in the args object.
     * This method is a helper method for the Jackson library
     *
     * @param key   the arg key assigned to the value
     * @param value value for the key
     */
    @JsonAnySetter
    public void setArg(String key, Object value) {
        addArg(key, value);
    }

    /**
     * Get a list of all the args that were passed with the ActionType
     *
     * @return Map object containing the key/value pairs assigned
     */
    public Map<String, Object> getArgs() {
        return super.getAll();
    }

    /**
     * Get a specific value assigned to a key in the args object
     *
     * @param key the arg key assigned to the value
     * @return Object that was stored with the key in the map
     */
    @JsonIgnore
    public Object getArg(String key) {
        return super.get(key);
    }
}
