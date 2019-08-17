package com.github.tonydeng.openc2.utilities;

import com.alibaba.fastjson.annotation.JSONField;
import com.github.tonydeng.openc2.actuators.ActuatorType;
import com.github.tonydeng.openc2.targets.TargetType;
import com.google.common.collect.Maps;
import java.util.Map;

/**
 * Map class that is used as a base for the target, actuator and args class
 *
 * @param <T> class type TargetType, ActuatorType or String (for args)
 * @author Tony Deng
 * @version V1.0
 * @date 2019-08-17 23:24
 **/
public class OpenC2Map<T> {

  protected String sectionType;
  protected String objectType;
  Map<String, T> map;

  /**
   * Constructor for creating a OpenC2Map object for a given type
   *
   * @param type The class type to assign to the object
   */
  protected OpenC2Map(T type) {
    this.sectionType = convertType(type);
    this.objectType = type.toString();
    map = Maps.newHashMap();
  }


  /**
   * Helper method to convert the type object into it's section field name for the JSON
   */
  private String convertType(T type) {
    if (type instanceof TargetType) {
      return Keys.TARGET;
    }
    if (type instanceof ActuatorType) {
      return Keys.ACTUATOR;
    }
    return Keys.ARGUMENTS;
  }

  /**
   * Put a key/value into the OpenC2 map object
   *
   * @param key the key for the JSON message
   * @param value The value object to assign to the key in the JSON message
   * @return the OpenC2Map object so users can method chain puts
   */
  protected OpenC2Map<T> put(String key, T value) {
    map.put(key, value);
    return this;
  }

  /**
   * Get a value from the OpenC2 map object based on a key
   *
   * @param key Key to lookup in the map.
   * @return Object value assigned to the key
   */
  protected T get(String key) {
    return map.get(key);
  }

  /**
   * Remove a give value from the map based on the key
   *
   * @param key Key to remove from the map
   * @return Object value assigned to the key
   */
  protected T remove(String key) {
    T value = get(key);
    map.remove(value);
    return value;
  }
  /**
   * Get the size of the internal map
   *
   * @return number of elements in the map
   */
  public int size(){
    return map.size();
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    return super.equals(obj);
  }

  /**
   * Get the section this map represents (target, actuator, args)
   *
   * @return String representing the section identifier
   */
  @JSONField(serialize = false)
  public String getSectionType() {
    return sectionType;
  }

  /**
   * Get the object type the map represents. i.e. ip-addr target object
   *
   * @return String representing the object identifier for this object
   */
  @JSONField(serialize = false)
  public String getObjectType() {
    return objectType;
  }

  /**
   * Get the whole hashmap of key/value pairs
   *
   * @return Map object of all attributes assigned
   */
  @JSONField(serialize = false)
  public Map<String, T> getAll() {
    return map;
  }
}
