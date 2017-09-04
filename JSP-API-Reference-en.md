# PallyCon Gateway JAVA API Sample

**( Version 1.0, 2017.01.23 )**

## 1. 개요

![image alt text](image_0.png)

A Java version of the gateway page that is required to package content or issue DRM licenses with the PallyCon cloud service.

1. licenseGateway : When an application requests to play a content, PallyCon license server requests the license rule information to the licenseGateway page in order to issue a license.

2. packageGateway : Packager requests the content ID information (CID) to be used in DRM packaging of the content to the packageGateway page through PallyCon server. If you enter CID as a parameter of the packager, the packageGateway page is not used.

## 2. API

## Class GatewayRule
Can be used for parsing request data and generating response data.

### 1. file path
: com.GatewayRule.java

### 2. Method

#### *public* GatewayRule(string key, String iv )
Generates aes encryption/decryption object using key and iv values.

#### *public string* createPackageInfo ( GatewayDTO gatewayDTO )
Creates response data according to packaging rule integration specification

**Parameter**
| type | name | description |
|:---|:---|:-----|
| GatewayDTO | gatewayDTO | A DTO object that sets the package rule |

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
Returns response data for packaging integration.

**Return** *JSONObject*

#### *public JSONObject* getLicenseRule()
Returns response data for license integration.

**Return** *JSONObject*

#### *public string* parserDecode(String params)
Decrypts parameter data which is encypted by AES256.

**Parameter**
| type | name | description |
|:---|:---|:-----|
| String | params | data to be decrypted |

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
Encrypts data input by parameter.

**Parameter**
| type | name | description |
|:---|:---|:-----|
| String | params | data to be encrypted |

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
Sets the required value for license issuance and parse it according to JSON spec.

**Parameter**
| type | name | description |
|:---|:---|:-----|
| GatewayDTO | gatewayDTO | object set for issuing licenses |

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
The data object needed to communicate with the gateway. It consists of a getter and a setter.

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
aes256(CBC) encryption module

### 1. file path
: com.AESCrypto256.java

### 2. method

#### 	*public* AESCrypto256(String key, String initialVector )
Set key and initialVector values to be used in common

**Parameter**
| type | name | description |
|:---|:---|:-----|
| String | key | key value for decryption |
| String | initialVector | iv value for decryption |

#### public *string* encrypt(value )
aes256 (CBC) encrypt -> base64 encode -> return result

**Parameter**
| type | name | description |
|:---|:---|:-----|
| String | value | data to be encrypted |

**Return** *string*

#### public *string* decrypt( value )
base64 decode -> aes256 (CBC) decrypt -> return result

**Parameter**
| type | name | description |
|:---|:---|:-----|
| String | value | data to be decrypted |

**Return** *string*
