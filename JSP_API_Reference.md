**Gateway JAVA Module API**

**( Version 1.0, 2017.01.23 )**



[[TOC]]

1. 개요

![image alt text](image_0.png)

PallyCon 서비스 사용 시 pack rule 발급, license rule 발급, application 연동에 필요한 Gateway 페이지를 구성는데 사용 가능한 module 이다.

1. licenseGateway :  Application 에서 컨텐츠 재생 요청 시 라이센스 룰 발급을 위해 PallyCon license server에서는 licenseGateway 페이지로 license Rule 정보를 요청하게 된다.

2. packageGateway : 패키져에서 컨텐츠 DRM 패키징 시 사용될 key 정보를 PallyCon license server에서는 packageGateway 페이지로 키 정보를 요청하게 된다.

2. API 

## Class GatewayRule

	gateway 에 넘어온 data parsing 과 response data를 생성하는데 사용할 수 있다.

1. file path 

: com.GatewayRule.java

2. Method 

#### *public *GatewayRule(string key, String iv )

생성자, 전달받은 key와 iv를  이용해 aes 암복호화 객체를 생성.

#### *public **string* createPackageInfo ( GatewayDTO gatewayDTO )

 	package rule 연동 response 규격에 맞춰서 값을 생성한다.

**Parameter**

<table>
  <tr>
    <td>type</td>
    <td>name</td>
    <td>description</td>
  </tr>
  <tr>
    <td>GatewayDTO</td>
    <td>gatewayDTO</td>
    <td>package rule 발급을 위해 setting 가능한 DTO 객체</td>
  </tr>
</table>


**Return ***string*

*Example.*

<table>
  <tr>
    <td>  String AES256_IV = "0123456789abcdef";
  GatewayRule gatewayRule = new GatewayRule("aaaaaaaaaaaaaaaaaaaa", AES256_IV);

 gatewayDTO.setNonce(jsonObject.getString("nonce"));
 gatewayDTO.setFile_name(jsonObject.getString("file_path"));
 gatewayDTO.setFile_path(jsonObject.getString("file_name"));
 gatewayDTO.setCid("test-cid");

 sResponse = gatewayRule.createPackageInfo(gatewayDTO);

 System.out.println(sResponse);

//result : euh3OrEWXGWoJWS4rgy/bvU/6bnIyClZR5tydh0g79AhS1nbh971iJpJuHlrpyaFPPv4/Z7zMLI9UQhsVXvfYRVOdkdIglXFFnt/VNXssqQ=
//before encrypt value : {"error_code":"0000","error_message":"success","cid":"test-cid","nonce":null}</td>
  </tr>
</table>


#### *public JSONObject* getPackageInfo()

 	package 연동 response 규격을 반환한다.

**Return ***JSONObject*

#### *public JSONObject** *getLicenseRule()

License 연동 response 규격을 반환한다.

**Return ***JSONObject*

#### *public string *parserDecode(String params)

파라미터로 받은 AES256 암호화 된 값을 복호화 하여 반환한다.

**Parameter**

<table>
  <tr>
    <td>type</td>
    <td>name</td>
    <td>description</td>
  </tr>
  <tr>
    <td>String</td>
    <td>params</td>
    <td>복호화할 값</td>
  </tr>
</table>


**Return ***JSONObject*

*Example.*

<table>
  <tr>
    <td>  String AES256_IV = "0123456789abcdef";
  GatewayRule gatewayRule = new GatewayRule("aaaaaaaaaaaaaaaaaaaa", AES256_IV);
  JSONObject jsonObject = null;
  String sData = "euh3OrEWXGWoJWS4rgy/bvU/6bnIyClZR5tydh0g79AhS1nbh971iJpJuHlrpyaFPPv4/Z7zMLI9UQhsVXvfYRVOdkdIglXFFnt/VNXssqQ=";
  jsonObject = encrypter.parserDecode(sData);

//result : euh3OrEWXGWoJWS4rgy/bvU/6bnIyClZR5tydh0g79AhS1nbh971iJpJuHlrpyaFPPv4/Z7zMLI9UQhsVXvfYRVOdkdIglXFFnt/VNXssqQ=
//before encrypt value : {"error_code":"0000","error_message":"success","cid":"test-cid","nonce":null}</td>
  </tr>
</table>


#### *public string *parserEncode(String params)

파라미터로 받은 String 값을  암호화 하여 반환한다.

**Parameter**

<table>
  <tr>
    <td>type</td>
    <td>name</td>
    <td>description</td>
  </tr>
  <tr>
    <td>String</td>
    <td>params</td>
    <td>암호화할 값</td>
  </tr>
</table>


**Return ***String*

*Example.*

<table>
  <tr>
    <td>   String AES256_IV = "0123456789abcdef";
   GatewayRule gatewayRule = new GatewayRule("aaaaaaaaaaaaaaaaaaaa", AES256_IV);
    jsonObject.put("error_code", "0000");
    jsonObject.put("error_message", "success”);
    jsonObject.put(“cid", “test-cid”);
    jsonObject.put(“nonce", null);
   System.out.println(gatewayRule.parserEncode(jsonObject.toString()));
 
//result : euh3OrEWXGWoJWS4rgy/bvU/6bnIyClZR5tydh0g79AhS1nbh971iJpJuHlrpyaFPPv4/Z7zMLI9UQhsVXvfYRVOdkdIglXFFnt/VNXssqQ=
//before encrypt value : {"error_code":"0000","error_message":"success","cid":"test-cid","nonce":null}</td>
  </tr>
</table>


#### *public **string *createLicenseRule (GatewayDTO gatewayDTO)

라이센스 발급에 필요한 값을 set 하여 JSON 규격에 맞게 파싱한다.

**Parameter**

<table>
  <tr>
    <td>type</td>
    <td>name</td>
    <td>description</td>
  </tr>
  <tr>
    <td>GatewayDTO</td>
    <td>gatewayDTO</td>
    <td>라이센스 발급에 필요한 값이 셋팅 되어 있는 객체</td>
  </tr>
</table>


**Return ***String*

*Example.*

<table>
  <tr>
    <td>String AES256_IV = "0123456789abcdef";
GatewayRule gatewayRule = new GatewayRule("aaaaaaaaaaaaaaaaaaaa", AES256_IV);
JSONObject jsonObject = null;
GatewayDTO gatewayDTO = new GatewayDTO();
gatewayDTO.setNonce("aaa”);
sResponse = encrypter.createLicenseRule(gatewayDTO);
//result : euh3OrEWXGWoJWS4rgy/bvU/6bnIyClZR5tydh0g79AhS1nbh971iJpJuHlrpyaF0FQcnSGk9BFYMXbHGll3hcfLGN6xn/uESB6qnv2ET8EEBkl3XDoAER780or3TIL5aZJQw0AgtNCp7VBNb6y3cJawWTdswjFb5VlJy8dv0AOZnpSYIpPzPbkID+kcF7TnLL6gmClgCQ79/KxcD+PJm2En2tQ9bJz8+RMGCFZ+Yeubyogg50ek5A9wyxmbknY6dThov6iDJIbMTcE3Flie1j654O1YfSoFE/i+oL8ZA8w5hD9DpSBLtlYyK3TsjGKvEtY3nGML+HQd+eudn/G4yg==</td>
  </tr>
</table>


## Class GatewayDTO

	gateway 와의 통신에 필요한 data 객체. getter와 setter로 구성되어 있다.

1. file path 

: com.DTO.gatewayDTO.java

2. Method 

**_Getter_**

*public String ***getCek***()*

*public String ***getCenc_iv***()*

*public String ***getCenc_key***()*

*public String ***getCid***()*

*public int ***getControl_hdcp***()*

*public String ***getDevice_id***()*

*public int ***getDuration***()*

*public String ***getExpire_date***()*

*public String ***getFile_name***()*

*public String ***getFile_path***()*

*public String ***getHls_iv***()*

*public String ***getHls_key***()*

*public String ***getKey_id***()*

*public String ***getNonce***()*

*public String ***getSite_id***()*

*public String ***getUser_id***()*

*public boolean ***isAllow_external_display***()*

*public boolean ***isAllow_mobile_abnormal_device***()*

*public boolean ***isHardware_drm***()*

*public boolean ***isLimit***()*

*public boolean ***isPersistent***()*

#### *Setter*

*public void ***setAllow_external_display(boolean allow_external_display)**

*public void ***setAllow_mobile_abnormal_device(boolean allow_mobile_abnormal_device)**

*public void ***setCek(String cek)**

*public void ***setCenc_iv(String cnec_iv)**

*public void ***setCenc_key(String cenc_key)**

*public void ***setCid(String cid)**

*public void ***setControl_hdcp(String control_hdcp)**

*public void ***setDevice_id(String device_id)**

*public void ***setDuration(int duration)**

*public void ***setExpire_date(String expire_data)**

*public void ***setFile_name(String filr_name)**

*public void ***setFile_path(String file_path)**

*public void ***setHardware_drm(boolean hardware_drm)**

*public void ***setHls_iv(String hls_iv)**

*public void ***setHls_key(String hls_key)**

*public void ***setKey_id(String key_id)**

*public void ***setLimit(boolean limit)**

*public void ***setNonce(String nonce)**

*public void ***setPersistent(boolean persistent)**

*public void ***setSite_id(String site_id)**

*public void ***setUser_id(String user_id)**

## Class AESCrypto256

aes256(CBC) 암호화 모듈.

1. file path

: com.AESCrypto256.java

2. method

#### 	*public  *AESCrypto256(String key, String initialVector )

	생성자, 공용으로 사용될 key 와 initialVector 값을 셋팅한다. 

**Parameter**

<table>
  <tr>
    <td>type</td>
    <td>name</td>
    <td>description</td>
  </tr>
  <tr>
    <td>String</td>
    <td>key</td>
    <td>암호화를 풀기위한 key 값</td>
  </tr>
  <tr>
    <td>String</td>
    <td>initialVector</td>
    <td>암호화를 풀기위한 iv 값</td>
  </tr>
</table>


#### public *string *encrypt(value )

	aes256 (CBC) encrypt -> base64 encode 된 값을  리턴한다.

**Parameter**

<table>
  <tr>
    <td>type</td>
    <td>name</td>
    <td>description</td>
  </tr>
  <tr>
    <td>string</td>
    <td>value</td>
    <td>암호화할 값</td>
  </tr>
</table>


**Return ***string*

#### public *string *decrypt( value )

	base64 decode -> aes256 (CBC) decrypt -> 된 값을  리턴한다.

**Parameter**

<table>
  <tr>
    <td>type</td>
    <td>name</td>
    <td>description</td>
  </tr>
  <tr>
    <td>string</td>
    <td>value</td>
    <td>암호화를 풀기 위한 값</td>
  </tr>
</table>


**Return ***string*

