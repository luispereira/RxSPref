package com.lib.rxspreflib.eceptions;

/**
 * @author lpereira on 03/05/2016.
 */
public class SDKNotInitialized extends RuntimeException {
    public SDKNotInitialized() {
        super("SDK not initialized");
    }
}
