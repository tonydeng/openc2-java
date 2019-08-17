package com.github.tonydeng.openc2.action;

/**
 * Definition of all the allowed action types as of the 03/27/2017 Openc2 spec
 *
 * @author Tony Deng
 * @version V1.0
 * @date 2019-08-17 21:56
 */
public enum ActionType {

  // Actions that control information
  /**
   * Systematic examination of some aspect of the entity or it's environment in order to obtain
   * information
   */
  SCAN("scan"),
  /**
   * Find an object either physically, logically, functionally or by organization
   */
  LOCATE("locate"),
  /**
   * Add a new entity of a known type (e.g., data, files, directories)
   */
  CREATE("create"),
  /**
   * Initiate a request for information
   */
  QUERY("query"),
  /**
   * Change a value, configuration, or state of a managed entity
   */
  SET("set"),
  /**
   * Remove an entity (e.g., data, files, flows)
   */
  DELETE("delete"),
  /**
   * Task an entity to provide information to a designated recipient of the information
   */
  REPORT("report"),
  /**
   * Set an entity's alerting preferences.
   */
  NOTIFY("notify"),

  // Actions that control access
  /**
   * Prevent a certain event or action from completion, such as preventing a flow from reaching a
   * destination (e.g., block) or preventing access
   */
  DENY("deny"),
  /**
   * Isolate a file, process, or entity such that it cannot modify or access assets or processes
   */
  CONTAIN("contain"),
  /**
   * Permit access to or execution of a target
   */
  ALLOW("allow"),

  // Actions that control activities/devices
  /**
   * Initiate a process, application, system, or some other activity
   */
  START("start"),
  /**
   * Halt a system or ends an activity
   */
  STOP("stop"),
  /**
   * Stop then start a system or an activity
   */
  RESTART("restart"),
  /**
   * Cease a system or activity while maintaining state
   */
  PAUSE("pause"),
  /**
   * Start a system or activity from a paused state
   */
  RESUME("resume"),
  /**
   * Invalidate a previously issued action
   */
  CANCEL("cancel"),
  /**
   * Instruct a component to retrieve, install, process, and operate in accordance with a software
   * update, reconfiguration or some other update
   */
  UPDATE("update"),
  /**
   * Change the location of a file, subnet, network, or process
   */
  MOVE("move"),
  /**
   * Change the flow to a particular destination other than its original intended destination
   */
  REDIRECT("redirect"),
  /**
   * Record and store the state of a target at an instant in time
   */
  SNAPSHOT("snapshot"),
  /**
   * Execute and observe the behavior of a target (e.g. file, hyperlink) in an isolated environment
   */
  DETONATE("detonate"),
  /**
   * Return a system to a previously known site
   */
  RESTORE("restore"),
  /**
   * Commit data or system state to memory
   */
  SAVE("save"),
  /**
   * adjust the rate of a process, function or activity
   */
  THROTTLE("throttle"),
  /**
   * Stop or hold up an activity or data transmittal
   */
  DELAY("delay"),
  /**
   * Replace all or part of the payload
   */
  SUBSTITUTE("substitute"),
  /**
   * Duplicate a file or data flow
   */
  COPY("copy"),
  /**
   * Synchronize a sensor or actuator with other system components
   */
  SYNC("sync"),

  // Events based actions
  /**
   * Task the recipient to aggregate and report information as it pertains to a security event or
   * incident
   */
  INVESTIGATE("investigate"),
  /**
   * Task the recipient to circumvent a problem without necessarily eliminating the vulnerability or
   * attack point
   */
  MITIGATE("mitigate"),
  /**
   * Task the recipient to eliminate a vulnerability or attack point
   */
  REMEDIATE("remediate");


  private String type;

  private ActionType(String type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return type;
  }
}
