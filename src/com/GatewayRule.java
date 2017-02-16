package com;

import com.DTO.GatewayDTO;
import com.Exception.CustomException;
import org.json.JSONException;
import org.json.JSONObject;
import utils.StringUtil;

/**
 * Created by DEV001 on 2017-01-18.
 */
public class GatewayRule {

    private String key;
    private String iv;
    private JSONObject playback_policy = null;
    private JSONObject security_policy = null;
    private JSONObject external_key = null;

    private JSONObject license_ruls = null;
    private JSONObject package_ruls = null;

    /**
     *
     *
     * @param key
     * @param iv
     */
    public GatewayRule(String key, String iv){
        this.key = key;
        this.iv = iv;
    }

    public GatewayDTO parserRequestServiceManager(String params){
        try {
            JSONObject jsonObject = new JSONObject(params);
            GatewayDTO gatewayDTO = new GatewayDTO();
            if (!jsonObject.isNull("mode")) {
                gatewayDTO.setMode(StringUtil.nvl(jsonObject.getString("mode"), ""));
            }
            if (!jsonObject.isNull("site_id")) {
                gatewayDTO.setSite_id(StringUtil.nvl(jsonObject.getString("site_id"), ""));
            }
            if (!jsonObject.isNull("user_id")) {
                gatewayDTO.setUser_id(StringUtil.nvl(jsonObject.getString("user_id"), ""));
            }
            if (!jsonObject.isNull("device_id")) {
                gatewayDTO.setDevice_id(StringUtil.nvl(jsonObject.getString("device_id"), ""));
            }
            if (!jsonObject.isNull("date")) {
                gatewayDTO.setDate(StringUtil.nvl(jsonObject.getString("date"), ""));
            }
            if (!jsonObject.isNull("category_name")) {
                gatewayDTO.setCategory_name(StringUtil.nvl(jsonObject.getString("category_name"), ""));
            }
            if (!jsonObject.isNull("content_name")) {
                gatewayDTO.setContent_name(StringUtil.nvl(jsonObject.getString("content_name"), ""));
            }
            if (!jsonObject.isNull("info_order_id")) {
                gatewayDTO.setInfo_order_id(StringUtil.nvl(jsonObject.getString("info_order_id"), ""));
            }
            if (!jsonObject.isNull("info_one")) {
                gatewayDTO.setInfo_one(StringUtil.nvl(jsonObject.getString("info_one"), ""));
            }
            if (!jsonObject.isNull("info_two")) {
                gatewayDTO.setInfo_two(StringUtil.nvl(jsonObject.getString("info_two"), ""));
            }
            if (!jsonObject.isNull("info_three")) {
                gatewayDTO.setInfo_three(StringUtil.nvl(jsonObject.getString("info_three"), ""));
            }
            if (!jsonObject.isNull("info_four")) {
                gatewayDTO.setInfo_four(StringUtil.nvl(jsonObject.getString("info_four"), ""));
            }
            if (!jsonObject.isNull("last_play_time")) {
                gatewayDTO.setLast_play_time(StringUtil.nvl(jsonObject.getString("last_play_time"), ""));
            }
            if (!jsonObject.isNull("lms_percent")) {
                gatewayDTO.setLms_percent(StringUtil.nvl(jsonObject.getString("lms_percent"), ""));
            }
            if (!jsonObject.isNull("lms_sections")) {
                gatewayDTO.setLms_sections(StringUtil.nvl(jsonObject.getString("lms_sections"), ""));
            }

            return gatewayDTO;
        }catch (JSONException e){
            return null;
        }
    }

    public String createPackageInfo(GatewayDTO gatewayDTO)throws  Exception{
        String returnStr = "";
        this.package_ruls = new JSONObject();
        String error_code = "0000";
        String error_message = "Success";
        if(!"".equals(StringUtil.nvl(gatewayDTO.getNonce(), ""))){
            this.package_ruls.put("nonce", gatewayDTO.getNonce());
        }else{
            error_code = "0001";
            error_message = "nonce is not invalid!";
        }

        if(!"".equals(StringUtil.nvl(gatewayDTO.getCid(), ""))){
            this.package_ruls.put("cid", gatewayDTO.getCid());
        }else{
            error_code = "0002";
            error_message = "cid is not invalid!";
        }

        this.package_ruls.put("error_code", error_code);
        this.package_ruls.put("error_message", error_message);

        returnStr = parserEncode(this.package_ruls.toString());
        return returnStr;
    }

    public JSONObject getPackageInfo(){
        if(this.package_ruls != null) {
            return this.package_ruls;
        }else{
            throw new NullPointerException();
        }
    }

    public JSONObject getLicenseRule(){
        if(this.license_ruls != null) {
            return this.license_ruls;
        }else{
            throw new NullPointerException();
        }
    }

    public JSONObject parserDecode(String params) throws  Exception{
        String decodeOrgData = "";
        JSONObject json = null;
        try{
            AESCrypto256 aes = new AESCrypto256(this.key, this.iv);

            decodeOrgData = aes.decrypt(params);
            json = new JSONObject(decodeOrgData);
        }catch (CustomException e){
            e.setErrorCode("0001");
            e.setErrorMsg("aes key error");
            e.getMessage();
        }
        return json;
     }

    public String parserEncode(String params) throws Exception{
        String encodeOrgData = "";
        try{
            AESCrypto256 aes = new AESCrypto256(this.key, this.iv);
            encodeOrgData = aes.encrypt(params);
        }catch (CustomException e){
            e.setErrorCode("0001");
            e.setErrorMsg("aes key error");
            e.getMessage();
        }
        return encodeOrgData;
     }

    public String createLicenseRule(GatewayDTO gatewayDTO)throws Exception{
        this.license_ruls = new JSONObject();
        String returnStr = "";
        String error_code = "0000";
        String error_message = "Success";
        if(!"".equals(StringUtil.nvl(gatewayDTO.getNonce(), ""))){
            createPlaybackPolicy(gatewayDTO.isLimit(), gatewayDTO.isPersistent(), gatewayDTO.getDuration(), gatewayDTO.getExpire_date());
            createSecurityPolicy(gatewayDTO.isHardware_drm(), gatewayDTO.isAllow_external_display(), gatewayDTO.getControl_hdcp(), gatewayDTO.isAllow_mobile_abnormal_device());
            createExternalKey(gatewayDTO.getKey_id(), gatewayDTO.getCenc_key(), gatewayDTO.getCenc_iv(), gatewayDTO.getHls_key(), gatewayDTO.getHls_iv(), gatewayDTO.getCek());
            if(!this.external_key.isNull("mpeg_cenc")){
                JSONObject jsonObject = (JSONObject)external_key.get("mpeg_cenc");
                if(jsonObject.isNull("key_id") ^ jsonObject.isNull("key")){
                    error_code = "1002";
                    error_message = "mpeg cenc paramter Type is not invalid";
                }
            }
            if(!this.external_key.isNull("hls_aes")){
                JSONObject jsonObject = (JSONObject)external_key.get("hls_aes");
                if(jsonObject.isNull("key") ^ jsonObject.isNull("iv")){
                    error_code = "1003";
                    error_message = "hls aes paramter Type is not invalid";
                }
            }

            if(this.playback_policy.length() != 0 ){
                this.license_ruls.put("playback_policy", this.playback_policy);
            }
            if(this.security_policy.length() != 0 ){
                this.license_ruls.put("security_policy", this.security_policy);
            }
            if(this.external_key.length() != 0 ){
                this.license_ruls.put("external_key", this.external_key);
            }

            this.license_ruls.put("nonce", gatewayDTO.getNonce());
        }else{
            error_code = "1001";
            error_message = "nonce is not invalid!!";
        }
        this.license_ruls.put("error_code", error_code);
        this.license_ruls.put("error_message", error_message);

        returnStr = parserEncode(this.license_ruls.toString());
        return returnStr;
    }

    private void createPlaybackPolicy(boolean limit, boolean persistent, int duration, String expire_date){
        this.playback_policy = new JSONObject();
        this.playback_policy.put("persistent", persistent);

        if(limit){
            this.playback_policy.put("limit", limit);
            if("".equals(StringUtil.nvl(expire_date, ""))){
                this.playback_policy.put("duration", duration);
            }else{
                this.playback_policy.put("expire_date", expire_date);
            }
        }else{
            this.playback_policy.put("limit", limit);
        }
    }

    private void createSecurityPolicy(boolean hardware_drm, boolean allow_external_display, int control_hdcp, boolean allow_mobile_abnormal_device){
        this.security_policy = new JSONObject();
        JSONObject output_protect = new JSONObject();

        this.security_policy.put("hardware_drm",hardware_drm);
        this.security_policy.put("allow_external_display",allow_external_display);

        output_protect.put("control_hdcp", control_hdcp);
        output_protect.put("allow_mobile_abnormal_device", allow_mobile_abnormal_device);

        this.security_policy.put("output_protect", output_protect);

    }

    private void createExternalKey(String key_id, String cenc_key, String cenc_iv, String hls_key, String hls_iv, String cek){
        this.external_key = new JSONObject();
        JSONObject mpeg_cenc = new JSONObject();
        JSONObject hls_aes = new JSONObject();
        JSONObject ncg = new JSONObject();

        if(!"".equals(StringUtil.nvl(key_id, ""))){
            mpeg_cenc.put("key_id", key_id);
        }
        if(!"".equals(StringUtil.nvl(cenc_key, ""))) {
            mpeg_cenc.put("key", cenc_key);
        }
        if(!"".equals(StringUtil.nvl(cenc_iv, ""))) {
            mpeg_cenc.put("iv", cenc_iv);
        }
        if(!"".equals(StringUtil.nvl(hls_key, ""))) {
            hls_aes.put("key", hls_key);
        }
        if(!"".equals(StringUtil.nvl(hls_iv, ""))) {
            hls_aes.put("iv", hls_iv);
        }
        if(!"".equals(StringUtil.nvl(cek, ""))) {
            ncg.put("cek", cek);
        }

        if(mpeg_cenc.length() != 0 ){
            this.external_key.put("mpeg_cenc", mpeg_cenc);
        }
        if(hls_aes.length() != 0 ){
            this.external_key.put("hls_aes", hls_aes);
        }
        if(ncg.length() != 0 ){
            this.external_key.put("ncg", ncg);
        }
    }
}
