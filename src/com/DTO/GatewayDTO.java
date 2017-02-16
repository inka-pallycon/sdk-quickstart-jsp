package com.DTO;

/**
 * Created by DEV001 on 2017-01-18.
 */
public class GatewayDTO {

    private boolean limit = false;
    private boolean persistent = false;
    private int duration = 0;
    private String expire_date = "";
    private boolean hardware_drm = false;
    private boolean allow_external_display = false;
    private int control_hdcp = 0;
    private boolean allow_mobile_abnormal_device = false;
    private String key_id = "";
    private String cenc_key = "";
    private String cenc_iv = "";
    private String hls_key = "";
    private String hls_iv = "";
    private String nonce = "";
    private String cek = "";

    private String file_name = "";
    private String file_path = "";
    private String cid = "";

    private String mode = "";
    private String site_id = "";
    private String user_id = "";
    private String device_id = "";
    private String date = "";
    private String category_name = "";
    private String content_name = "";
    private String info_order_id = "";
    private String info_one = "";
    private String info_two = "";
    private String info_three = "";
    private String info_four = "";
    private String last_play_time = "";
    private String lms_percent = "";
    private String lms_sections = "";

    public boolean isLimit() {
        return limit;
    }
    public void setLimit(boolean limit) {
        this.limit = limit;
    }

    public boolean isPersistent() {
        return persistent;
    }

    public void setPersistent(boolean persistent) {
        this.persistent = persistent;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getExpire_date() {
        return expire_date;
    }

    public void setExpire_date(String expire_date) {
        this.expire_date = expire_date;
    }

    public boolean isHardware_drm() {
        return hardware_drm;
    }

    public void setHardware_drm(boolean hardware_drm) {
        this.hardware_drm = hardware_drm;
    }

    public boolean isAllow_external_display() {
        return allow_external_display;
    }

    public void setAllow_external_display(boolean allow_external_display) {
        this.allow_external_display = allow_external_display;
    }

    public int getControl_hdcp() {
        return control_hdcp;
    }

    public void setControl_hdcp(int control_hdcp) {
        this.control_hdcp = control_hdcp;
    }

    public boolean isAllow_mobile_abnormal_device() {
        return allow_mobile_abnormal_device;
    }

    public void setAllow_mobile_abnormal_device(boolean allow_mobile_abnormal_device) {
        this.allow_mobile_abnormal_device = allow_mobile_abnormal_device;
    }

    public String getKey_id() {
        return key_id;
    }

    public void setKey_id(String key_id) {
        this.key_id = key_id;
    }

    public String getCenc_key() {
        return cenc_key;
    }

    public void setCenc_key(String cenc_key) {
        this.cenc_key = cenc_key;
    }

    public String getCenc_iv() {
        return cenc_iv;
    }

    public void setCenc_iv(String cenc_iv) {
        this.cenc_iv = cenc_iv;
    }

    public String getHls_key() {
        return hls_key;
    }

    public void setHls_key(String hls_key) {
        this.hls_key = hls_key;
    }

    public String getHls_iv() {
        return hls_iv;
    }

    public void setHls_iv(String hls_iv) {
        this.hls_iv = hls_iv;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getCek() {
        return cek;
    }

    public void setCek(String cek) {
        this.cek = cek;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getSite_id() {
        return site_id;
    }

    public void setSite_id(String site_id) {
        this.site_id = site_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getContent_name() {
        return content_name;
    }

    public void setContent_name(String content_name) {
        this.content_name = content_name;
    }

    public String getInfo_order_id() {
        return info_order_id;
    }

    public void setInfo_order_id(String info_order_id) {
        this.info_order_id = info_order_id;
    }

    public String getInfo_one() {
        return info_one;
    }

    public void setInfo_one(String info_one) {
        this.info_one = info_one;
    }

    public String getInfo_two() {
        return info_two;
    }

    public void setInfo_two(String info_two) {
        this.info_two = info_two;
    }

    public String getInfo_three() {
        return info_three;
    }

    public void setInfo_three(String info_three) {
        this.info_three = info_three;
    }

    public String getInfo_four() {
        return info_four;
    }

    public void setInfo_four(String info_four) {
        this.info_four = info_four;
    }

    public String getLast_play_time() {
        return last_play_time;
    }

    public void setLast_play_time(String last_play_time) {
        this.last_play_time = last_play_time;
    }

    public String getLms_percent() {
        return lms_percent;
    }

    public void setLms_percent(String lms_percent) {
        this.lms_percent = lms_percent;
    }

    public String getLms_sections() {
        return lms_sections;
    }

    public void setLms_sections(String lms_sections) {
        this.lms_sections = lms_sections;
    }
}
