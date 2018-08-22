package com.kucaroom.mypicture.util;import com.qiniu.util.Auth;import com.qiniu.util.StringMap;public class QiNiuUtil {    /**     * 获取七牛token     *     * @return     */    public static String getToken(String accessKey,String secretKey,String bucket){        Auth auth = Auth.create(accessKey, secretKey);        StringMap putPolicy = new StringMap();        putPolicy.put("returnBody", "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"fsize\":$(fsize),\"width\":\"$(imageInfo.width)\",\"height\":\"$(imageInfo.height)\"}");        long expireSeconds = 3600 * 24;        String upToken = auth.uploadToken(bucket,null,expireSeconds,putPolicy);        return upToken;    }}