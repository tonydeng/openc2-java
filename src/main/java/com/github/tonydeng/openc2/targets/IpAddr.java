package com.github.tonydeng.openc2.targets;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.github.tonydeng.openc2.utilities.OpenC2Map;

import static com.github.tonydeng.openc2.targets.TargetType.IP_ADDR;

/**
 * Implementation of an Ipv4Addr OpenC2 target
 *
 * @author Tony Deng
 * @version V1.0
 * @date 2019-08-18 00:25
 **/
public class IpAddr extends OpenC2Map<TargetType> {

    /**
     * Constructor
     */
    public IpAddr() {
        super(IP_ADDR);
    }

    /**
     * Constructor
     *
     * @param ip IP to assign to the ip addr object
     */
    public IpAddr(String ip) {
        super(IP_ADDR);
        setIpAddr(ip);
    }

    @JsonSetter("ip_addr")
    public void setIpAddr(String ip) {
        super.put(IP_ADDR.toString(), ip);
    }

    public String getIpAddr() {
        return super.get(IP_ADDR.toString()).toString();
    }
}
