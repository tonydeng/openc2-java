package com.github.tonydeng.openc2.targets;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.github.tonydeng.openc2.utilities.Keys;
import com.github.tonydeng.openc2.utilities.OpenC2Map;

import java.util.Map;

/**
 * Implementation of an Artifact OpenC2 target
 *
 * @author Tony Deng
 * @version V1.0
 * @date 2019-08-18 20:36
 **/
public class Artifact extends OpenC2Map<TargetType> {

    /**
     * Constructor
     */
    public Artifact() {
        super(TargetType.ARTIFACT);
    }

    public String getMime() {
        return (String) super.get(Keys.MIME_TYPE);
    }

    public Byte[] getPayloadBin() {
        return (Byte[]) super.get(Keys.PAYLOAD_BIN);
    }


    public String getUrl() {
        return (String) super.get(Keys.URL);
    }

    public Map<String, Object> getHashes() {
        return (Map<String, Object>) super.get(Keys.HASHES);
    }

    /**
     * Set the mime value
     *
     * @param mime to be assigned to the object
     * @return Artifact object used for method chaining
     */
    @JsonSetter(Keys.MIME_TYPE)
    public Artifact setMime(String mine) {
        super.put(Keys.MIME_TYPE, mine);
        return this;
    }

    /**
     * Set the payload_bin value
     *
     * @param value payload_bin data in binary format
     * @return Artifact object used for method chaining
     */
    @JsonSetter(Keys.PAYLOAD_BIN)
    public Artifact setPayloadBin(Byte[] value) {
        super.put(Keys.PAYLOAD_BIN, value);
        return this;
    }

    /**
     * Set the URL value
     *
     * @param value URL string to assign
     * @return Artifact object used for method chaining
     */
    public Artifact setUrl(String value) {
        super.put(Keys.URL, value);
        return this;
    }

    /**
     * Set the hashes value
     *
     * @param value URL string to assign
     * @return Artifact object used for method chaining
     */
    public Artifact setHashes(Map<String, Object> value) {
        super.put(Keys.HASHES, value);
        return this;
    }
}
