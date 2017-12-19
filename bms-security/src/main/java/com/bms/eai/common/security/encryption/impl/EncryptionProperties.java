package com.bms.eai.common.security.encryption.impl;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author kul_sudhakar
 *
 */
@Component
@ConfigurationProperties(prefix = "vep.security.encryption")
public class EncryptionProperties {
	private int hashLength = 128;
    private int minSaltLength = 16;
    private int maxSaltLength = 24;
    private int minIterations = 1000;
    private int maxIterations = 5000;

    public int getHashLength() {
        return hashLength;
    }

    public void setHashLength(int hashLength) {
        this.hashLength = hashLength;
    }

    public int getMinSaltLength() {
        return minSaltLength;
    }

    public void setMinSaltLength(int minSaltLength) {
        this.minSaltLength = minSaltLength;
    }

    public int getMaxSaltLength() {
        return maxSaltLength;
    }

    public void setMaxSaltLength(int maxSaltLength) {
        this.maxSaltLength = maxSaltLength;
    }

    public int getMinIterations() {
        return minIterations;
    }

    public void setMinIterations(int minIterations) {
        this.minIterations = minIterations;
    }

    public int getMaxIterations() {
        return maxIterations;
    }

    public void setMaxIterations(int maxIterations) {
        this.maxIterations = maxIterations;
    }
}
