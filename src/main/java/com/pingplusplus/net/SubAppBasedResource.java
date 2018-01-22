package com.pingplusplus.net;

import com.pingplusplus.AppContextHolder;
import com.pingplusplus.PingppAccount;
import com.pingplusplus.exception.InvalidRequestException;

import java.io.UnsupportedEncodingException;

public abstract class SubAppBasedResource extends AppBasedResource {

    /**
     * @param clazz
     * @param subAppId
     * @return singleClassURL
     * @throws InvalidRequestException
     */
    protected static String singleClassURL(Class<?> clazz, String subAppId) throws InvalidRequestException {
        if (AppContextHolder.getAppId() == null) {
            throw new InvalidRequestException("Please set app_id using AppContextHolder.setAppId(<APP_ID>)", "app_id", null);
        }
        return String.format("%s/v1/apps/%s/sub_apps/%s/%s", PingppAccount.getApiBase(), AppContextHolder.getAppId(), subAppId, className(clazz));
    }

    /**
     * @param clazz
     * @param subAppId
     * @return classURL
     * @throws InvalidRequestException
     */
    protected static String classURL(Class<?> clazz, String subAppId) throws InvalidRequestException {
        return String.format("%ss", singleClassURL(clazz, subAppId));
    }

    /**
     * @param clazz
     * @param id
     * @param subAppId
     * @return instanceURL
     * @throws InvalidRequestException
     */
    protected static String instanceURL(Class<?> clazz, String subAppId, String id) throws InvalidRequestException {
        try {
            return String.format("%s/%s", classURL(clazz, subAppId), urlEncode(id));
        } catch (UnsupportedEncodingException e) {
            throw new InvalidRequestException("Unable to encode parameters to " + CHARSET, null, e);
        }
    }
}
