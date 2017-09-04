# PallyCon Gateway JAVA API Sample

**( Version 1.0, 2017.01.23 )**

## 1. 개요

![image alt text](image_0.png)

PallyCon 클라우드 서비스와 연동해 콘텐츠를 패키징하거나 DRM 라이선스를 발급하는데 필요한 게이트웨이 페이지의 Java 버전 샘플입니다.

1. licenseGateway :  어플리케이션에서 콘텐츠 재생 요청 시, 라이선스 발급을 위해 PallyCon 서버에서 licenseGateway 페이지로 라이선스 룰 정보를 요청합니다.

2. packageGateway : 패키져에서 콘텐츠의 DRM 패키징 시, 콘텐츠 ID 정보(CID)를 PallyCon 서버를 통해 packageGateway 페이지로 요청합니다. 패키져에서 직접 CID를 파라미터로 입력하는 경우에는 packageGateway 페이지가 사용되지 않습니다.

## 2. API

## Class GatewayRule
gateway로 요청된 데이터의 파싱과 응답 데이터 생성에 사용할 수 있습니다.

### 1. file path
: com.GatewayRule.java

### 2. Method

#### *public* GatewayRule(string key, String iv )
입력된 key와 iv를 이용해 aes 암복호화 객체를 생성

#### *public string* createPackageInfo ( GatewayDTO gatewayDTO )
패키징 룰 연동 규격에 따른 응답 데이터를 생성

**Parameter**

| type | name | description |
|:---|:---|:-----|
| GatewayDTO | gatewayDTO | 패키징 룰을 설정하는 DTO 객체 |

**Return** *string*

*Example*

```java
String AES256_IV = "0123456789abcdef";
GatewayRule gatewayRule = new GatewayRule("aaaaaaaaaaaaaaaaaaaa", AES256_IV);

gatewayDTO.setNonce(jsonObject.getString("nonce"));
gatewayDTO.setFile_name(jsonObject.getString("file_path"));
gatewayDTO.setFile_path(jsonObject.getString("file_name"));
gatewayDTO.setCid("test-cid");

sResponse = gatewayRule.createPackageInfo(gatewayDTO);

System.out.println(sResponse);

//result : euh3OrEWXGWoJWS4rgy/bvU/6bnIyClZR5tydh0g79AhS1nbh971iJpJuHlrpyaFPPv4/Z7zMLI9UQhsVXvfYRVOdkdIglXFFnt/VNXssqQ=
//before encrypt value : {"error_code":"0000","error_message":"success","cid":"test-cid","nonce":null}
```

#### *public JSONObject* getPackageInfo()
package 연동 response 규격을 반환한다.

**Return** *JSONObject*

#### *public JSONObject* getLicenseRule()
License 연동 response 규격을 반환한다.

**Return** *JSONObject*

#### *public string* parserDecode(String params)
파라미터로 받은 AES256 암호화 된 값을 복호화 하여 반환한다.

**Parameter**

| type | name | description |
|:---|:---|:-----|
| String | params | 복호화할 값 |

**Return** *JSONObject*

*Example*

```java
String AES256_IV = "0123456789abcdef";
GatewayRule gatewayRule = new GatewayRule("aaaaaaaaaaaaaaaaaaaa", AES256_IV);
JSONObject jsonObject = null;
String sData = "euh3OrEWXGWoJWS4rgy/bvU/6bnIyClZR5tydh0g79AhS1nbh971iJpJuHlrpyaFPPv4/Z7zMLI9UQhsVXvfYRVOdkdIglXFFnt/VNXssqQ=";
jsonObject = encrypter.parserDecode(sData);

//result : euh3OrEWXGWoJWS4rgy/bvU/6bnIyClZR5tydh0g79AhS1nbh971iJpJuHlrpyaFPPv4/Z7zMLI9UQhsVXvfYRVOdkdIglXFFnt/VNXssqQ=
//before encrypt value : {"error_code":"0000","error_message":"success","cid":"test-cid","nonce":null}
```

#### *public string* parserEncode(String params)
파라미터로 받은 String 값을 암호화 하여 반환한다.

**Parameter**

| type | name | description |
|:---|:---|:-----|
| String | params | 암호화할 값 |

**Return** *String*

*Example*

```java
String AES256_IV = "0123456789abcdef";
GatewayRule gatewayRule = new GatewayRule("aaaaaaaaaaaaaaaaaaaa", AES256_IV);
jsonObject.put("error_code", "0000");
jsonObject.put("error_message", "success");
jsonObject.put("cid", "test-cid");
jsonObject.put("nonce", null);
System.out.println(gatewayRule.parserEncode(jsonObject.toString()));

//result : euh3OrEWXGWoJWS4rgy/bvU/6bnIyClZR5tydh0g79AhS1nbh971iJpJuHlrpyaFPPv4/Z7zMLI9UQhsVXvfYRVOdkdIglXFFnt/VNXssqQ=
//before encrypt value : {"error_code":"0000","error_message":"success","cid":"test-cid","nonce":null}
```

#### *public string* createLicenseRule (GatewayDTO gatewayDTO)
라이센스 발급에 필요한 값을 set 하여 JSON 규격에 맞게 파싱한다.

**Parameter**

| type | name | description |
|:---|:---|:-----|
| GatewayDTO | gatewayDTO | 라이선스 발급에 필요한 값이 세팅되어 있는 객체 |

**Return** *String*

*Example*

```java
String AES256_IV = "0123456789abcdef";
GatewayRule gatewayRule = new GatewayRule("aaaaaaaaaaaaaaaaaaaa", AES256_IV);
JSONObject jsonObject = null;
GatewayDTO gatewayDTO = new GatewayDTO();
gatewayDTO.setNonce("aaa");
sResponse = encrypter.createLicenseRule(gatewayDTO);
//result : euh3OrEWXGWoJWS4rgy/bvU/6bnIyClZR5tydh0g79AhS1nbh971iJpJuHlrpyaF0FQcnSGk9BFYMXbHGll3hcfLGN6xn/uESB6qnv2ET8EEBkl3XDoAER780or3TIL5aZJQw0AgtNCp7VBNb6y3cJawWTdswjFb5VlJy8dv0AOZnpSYIpPzPbkID+kcF7TnLL6gmClgCQ79/KxcD+PJm2En2tQ9bJz8+RMGCFZ+Yeubyogg50ek5A9wyxmbknY6dThov6iDJIbMTcE3Flie1j654O1YfSoFE/i+oL8ZA8w5hD9DpSBLtlYyK3TsjGKvEtY3nGML+HQd+eudn/G4yg==
```

## Class GatewayDTO
gateway 와의 통신에 필요한 data 객체. getter와 setter로 구성되어 있다.

### 1. file path 
: com.DTO.gatewayDTO.java

### 2. Method 

#### **_Getter_**

*public String* **getCek**()

*public String* **getCenc_iv**()

*public String* **getCenc_key**()

*public String* **getCid**()

*public int* **getControl_hdcp**()

*public String* **getDevice_id**()

*public int* **getDuration**()

*public String* **getExpire_date**()

*public String* **getFile_name**()

*public String* **getFile_path**()

*public String* **getHls_iv**()

*public String* **getHls_key**()

*public String* **getKey_id**()

*public String* **getNonce**()

*public String* **getSite_id**()

*public String* **getUser_id**()

*public boolean* **isAllow_external_display**()

*public boolean* **isAllow_mobile_abnormal_device**()

*public boolean* **isHardware_drm**()

*public boolean* **isLimit**()

*public boolean* **isPersistent**()

#### *Setter*

*public void* **setAllow_external_display(boolean allow_external_display)**

*public void* **setAllow_mobile_abnormal_device(boolean allow_mobile_abnormal_device)**

*public void* **setCek(String cek)**

*public void* **setCenc_iv(String cnec_iv)**

*public void* **setCenc_key(String cenc_key)**

*public void* **setCid(String cid)**

*public void* **setControl_hdcp(String control_hdcp)**

*public void* **setDevice_id(String device_id)**

*public void* **setDuration(int duration)**

*public void* **setExpire_date(String expire_data)**

*public void* **setFile_name(String filr_name)**

*public void* **setFile_path(String file_path)**

*public void* **setHardware_drm(boolean hardware_drm)**

*public void* **setHls_iv(String hls_iv)**

*public void* **setHls_key(String hls_key)**

*public void* **setKey_id(String key_id)**

*public void* **setLimit(boolean limit)**

*public void* **setNonce(String nonce)**

*public void* **setPersistent(boolean persistent)**

*public void* **setSite_id(String site_id)**

*public void* **setUser_id(String user_id)**

## Class AESCrypto256
aes256(CBC) 암호화 모듈.

### 1. file path
: com.AESCrypto256.java

### 2. method

#### 	*public* AESCrypto256(String key, String initialVector )
생성자, 공용으로 사용될 key 와 initialVector 값을 셋팅

**Parameter**

| type | name | description |
|:---|:---|:-----|
| String | key | 암호화를 풀기 위한 key 값 |
| String | initialVector | 암호화를 풀기 위한 iv 값 |

#### public *string* encrypt(value )
aes256 (CBC) encrypt -> base64 encode 된 값을  리턴한다.

**Parameter**

| type | name | description |
|:---|:---|:-----|
| String | value | 암호화할 데이터 |

**Return** *string*

#### public *string* decrypt( value )
base64 decode -> aes256 (CBC) decrypt된 값을 리턴한다.

**Parameter**

| type | name | description |
|:---|:---|:-----|
| String | value | 복호화할 데이터 |

**Return** *string*
