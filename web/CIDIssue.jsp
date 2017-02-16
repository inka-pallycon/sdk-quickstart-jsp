<%@ page import="com.AESCrypto256" %>
<%@ page import="com.GatewayRule" %>
<%@ page import="org.json.JSONObject" %>
<%@ page import="com.DTO.GatewayDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="config.jsp"%>
<%
    request.setCharacterEncoding("UTF-8");
    String AES256_IV  = "0123456789abcdef";

    GatewayRule encrypter = new GatewayRule(siteKey, AES256_IV);

    JSONObject jsonObject = null;
    GatewayDTO gatewayDTO = new GatewayDTO();
    String sData = "";
    String sResponse = "";

    if(request.getParameter("data") == null){
        sData ="";
        response.getWriter().print("fail");
    }else{
        sData = request.getParameter("data");
        jsonObject = encrypter.parserDecode(sData);

        gatewayDTO.setNonce(jsonObject.getString("nonce"));
        gatewayDTO.setFile_name(jsonObject.getString("file_path"));
        gatewayDTO.setFile_path(jsonObject.getString("file_name"));

		/*-
		*
		* [업체 청책 반영]
		*
		* 업체의 정책에 맞게 Content ID를 생성하는 로직을 이곳에 구현합니다.
		* Content ID를 생성하는데 활용할 값은 다음과 같습니다.
		*
		* - gatewayDTO.getFile_name();
		* - gatewayDTO.getFile_path();
		*
		* ** sample 소스는 무조건 CID를 test-sample 으로 설정됩니다.
		*
		*
		* [Applying CID rule]
		*
		* Your CID generation logic can be applied here.
		* The below parameters can be used for the logic.
		*
		* - gatewayDTO.getFile_name();
		* - gatewayDTO.getFile_path();
		*
		* ** The default $ContentID value for test is test-sample.
		*
		*/
        gatewayDTO.setCid("test-sample");

        sResponse = encrypter.createPackageInfo(gatewayDTO);

        response.getWriter().print(sResponse);
    }
    response.getWriter().close();
%>