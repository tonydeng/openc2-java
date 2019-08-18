package com.github.tonydeng.openc2.targets;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.github.tonydeng.openc2.utilities.Keys;
import com.github.tonydeng.openc2.utilities.OpenC2Map;

import java.util.Map;

/**
 * Implementation of an File OpenC2 target
 *
 * @author Tony Deng
 * @version V1.0
 * @date 2019-08-18 18:44
 **/
public class File extends OpenC2Map<TargetType> {
    /**
     * Constructor
     */
    public File() {
        super(TargetType.FILE);
    }

    public File setName(String name) {
        super.put(Keys.NAME, name);
        return this;
    }

    public File setPath(String path) {
        super.put(Keys.PATH, path);
        return this;
    }

    @JsonSetter(Keys.HASHES)
    public File setHash(Map<String, Object> hash) {
        super.put(Keys.HASHES, hash);
        return this;
    }

    public String getName() {
        return super.get(Keys.NAME).toString();
    }

    public String getPath() {
        return super.get(Keys.PATH).toString();
    }

    public Map<String, Object> getHashes() {
        return (Map<String, Object>) super.get(Keys.HASHES);
    }
}
