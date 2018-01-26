package com.example.jorgenskevik.e_cardholders.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by jorgenskevik on 26.01.2018.
 */

public class Token {

    @SerializedName("Authorization")
    @Expose
    private String Authorization;

    @SerializedName("Accept-Version")
    @Expose
    private String AcceptVersion;

    @SerializedName("client-key")
    @Expose
    private String clientKey;

    @SerializedName("deviceId")
    @Expose
    private String deviceId;

    public Token(String deviceId){
        this.deviceId = deviceId;

    }

    public String getAuthorization() {
        return Authorization;
    }

    public void setAuthorization(String authorization) {
        Authorization = authorization;
    }

    public String getAcceptVersion() {
        return AcceptVersion;
    }

    public void setAcceptVersion(String acceptVersion) {
        AcceptVersion = acceptVersion;
    }

    public String getClientKey() {
        return clientKey;
    }

    public void setClientKey(String clientKey) {
        this.clientKey = clientKey;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
