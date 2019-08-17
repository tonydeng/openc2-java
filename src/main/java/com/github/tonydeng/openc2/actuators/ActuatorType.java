package com.github.tonydeng.openc2.actuators;

/**
 * Definition of all the allowed actuator types as of the 03/27/2017 Openc2 spec
 *
 * @author tonydeng
 */
public enum ActuatorType {
  /**
   *
   */
  ENDPOINT("endpoint"),
  ENDPOINT_WORKSTATION("endpoint_workstation"),
  ENDPOINT_SERVER("endpoint_server"),
  NETWORK("network"),
  NETWORK_FIREWALL("network_firewall"),
  NETWORK_ROUTER("network_router"),
  NETWORK_PROXY("network_proxy"),
  NETWORK_SENSOR("network_sensor"),
  NETWORK_HIPS("network_hips"),
  NETWORK_SENSE_MAKING("network_sense_making"),
  PROCESS("process"),
  PROCESS_ANTI_VIRUS_SCANNER("process_anti_virus_scanner"),
  PROCESS_AAA_SERVICE("process_aaa_service"),
  PROCESS_VIRTUALIZATION_SERVICE("process_virtualization_service"),
  PROCESS_SANDBOX("process_sandbox"),
  PROCESS_EMAIL_SERVICE("process_email_service"),
  PROCESS_DIRECTORY_SERVICE("process_directory_service"),
  PROCESS_REMEDIATION_SERVICE("process_remediation_service"),
  PROCESS_LOCATION_SERVICE("process_location_service");

  private String type;

  ActuatorType(String type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return type;
  }
}
