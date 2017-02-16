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

		/*-
		*
		* [업체 청책 반영]
		*
		* 업체의 정책에 맞게 license rule을 생성하는 로직을 이곳에 구현합니다.
		*
		* ** sample 소스는 무제한 라이센스로 세팅하게 되어 있습니다.
		*
		*
		* [Applying Content Usage Rights rule]
		*
		* Your Usage Rule generation logic can be applied here.
		*
		* ** The sample source is setted unlimit license.
		*
		*/


        sResponse = encrypter.createLicenseRule(gatewayDTO);
        response.getWriter().print(sResponse);
    }
    response.getWriter().close();
%>