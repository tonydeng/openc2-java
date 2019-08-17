package com.github.tonydeng.openc2.targets;

/**
 * Definition of all the allowed target types as of the 03/27/2017 Openc2 spec
 *
 * @author tonydeng
 */
public enum TargetType {
  /**
   * An array of bytes representing a file-like object or a link to that object
   */
  ARTIFACT("artifact"),
  /**
   * A reference to a previously issued OpenC2 Command
   */
  COMMAND("command"),
  /**
   * The properties of a hardware device
   */
  DEVICE("device"),
  /**
   * The properties common to a file system directory
   */
  DIRECTORY("directory"),
  /**
   * A disk drive
   */
  DISK("disk"),
  /**
   * A single partition of a disk drive
   */
  DISK_PARTITION("disk_partition"),
  /**
   * The properties of a network domain name
   */
  DOMAIN_NAME("domain_name"),
  /**
   * The single email address
   */
  EMAIL_ADDR("email_addr"),
  /**
   * An instance of an email message, coresponding to the internet message format described in
   * RFC5322 and related RFCs
   */
  EMAIL_MESSAGE("email_message"),
  /**
   * The properties of a file
   */
  FILE("file"),
  /**
   * The representation of one or more IP addresses (either version 4 or version 6) expressed using
   * CIDR notation
   */
  IP_ADDR("ip_addr"),
  /**
   * A single Media Access Control (MAC) address
   */
  MAC_ADDR("mac_addr"),
  /**
   * Memory objects
   */
  MEMORY("memory"),
  /**
   * A network connection that originates from a source and is addressed to a destination
   */
  IP_CONNECTION("ip_connection"),
  /**
   * The summation of the actions, targets and profiles supported by the actuator. The target is
   * used with the query action to determine an actuators capabilities
   */
  OPENC2("openc2"),
  /**
   * Common properties of an instance of a computer program as executed on a operating system
   */
  PROCESS("process"),
  /**
   * High-level propoerties associated with software, including software products
   */
  SOFTWARE("software"),
  /**
   * The properties of a uniform resource locator (URL)
   */
  URL("url"),
  /**
   * An instance of any type of user account, including but not limited to operating system, device,
   * messaging service and social media platform accounts
   */
  USER_ACCOUNT("user_account"),
  /**
   * A user session
   */
  USER_SESSION("user_session"),
  /**
   * A generic drive volume
   */
  VOLUME("volume"),
  /**
   * The properties of a Windows registry key
   */
  WINDOWS_REGISTRY_KEY("windows_registry_key"),
  /**
   * The properties of an X.509 certificate, as defined by ITU recommendation X.509
   */
  X509_CERTIFICATE("x509_certificate"),
  /**
   * Target specifiers as defined in the Stateless Packet Filtering Firewall profile
   */
  SLPFF("slpff");


  private String type;

  TargetType(String type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return type;
  }
}
