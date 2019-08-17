package com.github.tonydeng.openc2.utilities;

/**
 * The Keys class contains a list of static strings that are used to set the key portion of
 * key/value pairs that are pushed to the various sections of OpenC2 message.  This is done so if
 * values change for given keys as the spec matures, only need to change one class.
 *
 * @author Tony Deng
 * @version V1.0
 * @date 2019-08-17 23:21
 */
public class Keys {

  /**
   * Main message keys
   */
  public static final String HEADER = "header";
  public static final String BODY = "command";
  public static final String RESPONSE = "response";
  public static final String ID = "id";
  public static final String ACTION = "action";
  public static final String TARGET = "target";
  public static final String ACTUATOR = "actuator";
  public static final String ARGUMENTS = "args";

  /**
   * Subsection keys
   */
  public static final String NAME = "name";
  public static final String PATH = "path";
  public static final String HASHES = "hashes";

  public static final String TYPE = "type";
  public static final String VALUE = "value";
  public static final String RESOLVES_TO_REFS = "resolves_to_refs";
  public static final String BELONGS_TO_REFS = "belongs_to_refs";
  public static final String MIME_TYPE = "mime_type";
  public static final String PAYLOAD_BIN = "payload_bin";
  public static final String URL = "url";
  public static final String DXL_TOPIC = "dxl_topic";
  public static final String ACTUATOR_ID = "actuator_id";
}
