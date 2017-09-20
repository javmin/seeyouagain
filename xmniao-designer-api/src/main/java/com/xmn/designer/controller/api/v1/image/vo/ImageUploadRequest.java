package com.xmn.designer.controller.api.v1.image.vo;

import com.xmn.designer.base.Request;

/**
 * create 2016/09/29
 *
 * @author yangQiang
 */

public class ImageUploadRequest extends Request {

    private String sessionToken;

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    @Override
    public String toString() {
        return "ImageUploadRequest{" +
                "sessionToken='" + sessionToken + '\'' +
                '}';
    }
}
