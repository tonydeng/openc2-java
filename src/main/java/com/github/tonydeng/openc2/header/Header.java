package com.github.tonydeng.openc2.header;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * @author dengtao
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Header {

  /**
   * Message Protocol version
   */
  private String version;
  /**
   * The Message Command ID
   */
  private String commandId;
  /**
   * Creation date/time of the content
   */
  private String created;
  /**
   * The Message Sender
   */
  private String sender;
  /**
   * The type and version of the message body
   */
  private String contentType;

  @JSONField(serialize = false)
  public final boolean isEmpty() {
    return StringUtils.isEmpty(version)
        && StringUtils.isEmpty(commandId) && StringUtils.isEmpty(created)
        && StringUtils.isEmpty(sender) && StringUtils.isEmpty(contentType);
  }
}
