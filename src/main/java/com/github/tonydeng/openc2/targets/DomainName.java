package com.github.tonydeng.openc2.targets;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.github.tonydeng.openc2.utilities.OpenC2Map;

/**
 * Implementation of an DomainName OpenC2 target
 *
 * @author Tony Deng
 * @version V1.0
 * @date 2019-08-18 19:22
 **/
public class DomainName extends OpenC2Map<TargetType> {
    public DomainName() {
        super(TargetType.DOMAIN_NAME);
    }

    /**
     * Constructor to create a domain name target
     *
     * @param domainName domain name value to assign to the target
     */
    public DomainName(String domainName) {
        super(TargetType.DOMAIN_NAME);
        setDomainName(domainName);
    }

    /**
     * Set the value of the domain name object
     *
     * @param domainName the domain name to be assign
     */
    @JsonSetter("domain_name")
    public void setDomainName(String domainName) {
        super.put(TargetType.DOMAIN_NAME.toString(), domainName);
    }

    /**
     * Get the value for the DomainName object
     *
     * @return String value of the domain object
     */
    public String getDomainName() {
        return super.get(TargetType.DOMAIN_NAME.toString()).toString();
    }
}
