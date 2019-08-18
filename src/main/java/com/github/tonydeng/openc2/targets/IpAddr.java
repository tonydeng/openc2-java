package com.github.tonydeng.openc2.targets;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.github.tonydeng.openc2.utilities.OpenC2Map;

/**
 * Implementation of an Ipv4Addr OpenC2 target
 *
 * @author Tony Deng
 * @version V1.0
 * @date 2019-08-18 00:25
 **/
public class IpAddr extends OpenC2Map<TargetType> {

    public IpAddr() {
        super(TargetType.IP_ADDR);
    }

    public IpAddr(String ip) {
        super(TargetType.IP_ADDR);

    }

    @JsonSetter("ip_addr")
    public void setIpAddr(String ip) {
        super.put(TargetType.IP_ADDR.toString(), ip);
    }

    public String getIpAddr() {
        return super.get(TargetType.IP_ADDR.toString()).toString();
    }
}
