package com.kucaroom.mypicture.service.impl;import com.kucaroom.mypicture.domain.WeChatApp;import com.kucaroom.mypicture.enums.ResponseEnum;import com.kucaroom.mypicture.exception.ApiException;import com.kucaroom.mypicture.repository.WeChatAppRepository;import com.kucaroom.mypicture.service.WeChatAppService;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Service;import java.util.Optional;@Servicepublic class WeChatAppServiceImpl implements WeChatAppService {    @Autowired    private WeChatAppRepository weChatAppRepository;    /**     * 根据app_id获取小程序信息     *     * @author yezi     * @param id     * @return     */    @Override    public WeChatApp findByAppId(Integer id){        Optional<WeChatApp> weChatApp = weChatAppRepository.findById(id);        if(!weChatApp.isPresent()){            throw new ApiException(ResponseEnum.APP_NOT_EXIST);        }        WeChatApp app = weChatApp.get();        return app;    }    /**     * 根据allianceKey查找小程序     * @param allianceKey     * @return     */    @Override    public WeChatApp findByAppAllianceKey(String allianceKey) {        WeChatApp weChatApp = weChatAppRepository.findByAllianceKey(allianceKey);        return weChatApp;    }    /**     * 新建小程序     *     * @author yezi     * @param weChatApp     * @return     */    @Override    public WeChatApp create(WeChatApp weChatApp) {        WeChatApp app = weChatAppRepository.save(weChatApp);        return app;    }    /**     * 更新小程序的名字     *     * @author yezi     * @param appId     * @param appName     * @return     */    @Override    public WeChatApp updateAppNameById(Integer appId, String appName) {        WeChatApp app = this.findByAppId(appId);        app.setName(appName);        WeChatApp result = weChatAppRepository.save(app);        if(result == null){            throw new ApiException(ResponseEnum.APP_UPDATE_FAIL);        }        return result;    }    /**     * 更新小程序的key     *     * @author yezi     * @param appId     * @param appKey     * @return     */    @Override    public WeChatApp updateAppKeyById(Integer appId, String appKey) {        WeChatApp app = this.findByAppId(appId);        app.setAppKey(appKey);        WeChatApp result = weChatAppRepository.save(app);        if(result == null){            throw new ApiException(ResponseEnum.APP_UPDATE_FAIL);        }        return result;    }    /**     * 根据小程序ID更新密钥     *     * @author yezi     * @param appId     * @param appSecret     * @return     */    @Override    public WeChatApp updateAppSecretById(Integer appId, String appSecret) {        WeChatApp app = this.findByAppId(appId);        app.setAppSecret(appSecret);        WeChatApp result = weChatAppRepository.save(app);        if(result == null){            throw new ApiException(ResponseEnum.APP_UPDATE_FAIL);        }        return result;    }    /**     * 通过微信获取用户的openid     *     * @author yezi     * @param aliasKey     * @param code     * @return     */    @Override    public String getOpenIdByWeChatAuth(String aliasKey,String code) {        WeChatApp weChatApp = this.findByAppAllianceKey(aliasKey);        if(weChatApp == null){            throw new ApiException(ResponseEnum.APP_NOT_EXIST);        }        return null;    }}