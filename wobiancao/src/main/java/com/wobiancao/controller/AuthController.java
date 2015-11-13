package com.wobiancao.controller;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.wobiancao.entity.Customer;
import com.wobiancao.protocol.auth.AuthFailResponse;
import com.wobiancao.protocol.auth.TokenResponse;
import com.wobiancao.protocol.auth.TokenSuccessResponse;
import com.wobiancao.protocol.auth.UserinfoResponse;
import com.wobiancao.protocol.auth.UserinfoSuccessResponse;
import com.wobiancao.repository.CustomerRepository;
import com.wobiancao.utils.JsonUtils;

@Controller
@RequestMapping(value = "/auth")
public class AuthController {
	private static final String WECHAT_AUTHORIZE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize";
	private static final String WECHAT_QRCONNECT_URL = "https://open.weixin.qq.com/connect/qrconnect";
	private static final String WECHAT_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";
	private static final String WECHAT_USERINFO_URL = "https://api.weixin.qq.com/sns/userinfo";
	private static final String APP_ID = "wx81ea63d48d76cb00";
	private static final String SECRET = "5dc18a69089ca059c9726427fe0acbce";
	private static final String REDIRECT_URI = "http%3A%2F%2Fwbc.izhuomi.com%2Fauth%2Fcallback";
	private static final String SCOPE_BASE = "snsapi_base";
	private static final String SCOPE_USERINFO = "snsapi_userinfo";
	private static final String RESPONSE_TYPE = "code";
	private static final String GRANT_TYPE = "authorization_code";
	private static final String STATE = "123";
	private static final String LANG = "zh_CN";
	private static final String FRAGMENT = "wechat_redirect";
	private static final String ERROR_CODE_ACCESS_TOKEN_EXPIRES = "42001";
	private static final String CUSTOMER_ATTRIBUTE = "customer";
	
	private RestTemplate restTemplate =  new RestTemplate();
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@RequestMapping(value = "/baseLogin")
	public String baseLogin(HttpSession session) {
//		Customer customer = (Customer) session.getAttribute(CUSTOMER_ATTRIBUTE);
//		if (customer == null) {
//			String wechat = getWechat(SCOPE_BASE);
//			return "redirect:" + wechat;
//		} else {
//			return "redirect:/home";
//		}
		return userinfoLogin(session);
	}
	
	@RequestMapping(value = "/userinfoLogin")
	public String userinfoLogin(HttpSession session) {
		Customer customer = (Customer) session.getAttribute(CUSTOMER_ATTRIBUTE);
		if (customer == null) {
			String wechat = getWechat(SCOPE_USERINFO);
			return "redirect:" + wechat;
		} else {
			return "redirect:/home";
		}
	}
	
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {
		session.removeAttribute(CUSTOMER_ATTRIBUTE);
		return "redirect:/home";
	}
	
	@RequestMapping(value = "/callback")
	public String callback(HttpServletRequest request, @RequestParam(required=false) String code, @RequestParam(required=false) String state, HttpSession session) {
		
		Enumeration<String> parameterNames = request.getParameterNames();
		System.out.println("====parameter====");
		while (parameterNames.hasMoreElements()) {
			String name = parameterNames.nextElement();
			System.out.println(name + "=" + request.getParameter(name));
		}
		
		System.out.println("callback code=" + code);
		TokenResponse tokenResponse = getToken(code);
		System.out.println("====token====");
		System.out.println("accessToken" + tokenResponse.getAccessToken());
		System.out.println("errorMessage" + tokenResponse.getErrorMessage());
		System.out.println("expiresIn" + tokenResponse.getExpiresIn());
		System.out.println("openId" + tokenResponse.getOpenId());
		System.out.println("refreshToken" + tokenResponse.getRefreshToken());
		System.out.println("scope" + tokenResponse.getScope());
		System.out.println("unionId" + tokenResponse.getUnionId());
		System.out.println("errorCode" + tokenResponse.getErrorCode());
		if (tokenResponse.getErrorCode() == null) { // OAuth成功
			String openId = tokenResponse.getOpenId();
			Customer customer = customerRepository.findOneByOpenId(openId);
			String scope = tokenResponse.getScope();
			if (scope.equals(SCOPE_BASE)) {
				// 拉取用户信息需要snsapi_userinfo，从数据库中取得
				if (customer == null) { // 数据库中不存在记录，需要进行授权的登录
					System.out.println("customer not in DB!!");
					System.out.println("redirect:/auth/userinfoLogin");
					return "redirect:/auth/userinfoLogin";
				}
				String accessToken = tokenResponse.getAccessToken();
				UserinfoResponse userinfoResponse = getUserinfo(openId, accessToken);
				System.out.println("====base:userinfo====");
				System.out.println("openId" + userinfoResponse.getOpenId());
				System.out.println("avatar" + userinfoResponse.getAvatar());
				System.out.println("errorMessage" + userinfoResponse.getErrorMessage());
				System.out.println("nickname" + userinfoResponse.getNickname());
				System.out.println("errorCode" + userinfoResponse.getErrorCode());
				if (userinfoResponse.getErrorCode() == null) {
					syncUserinfo(userinfoResponse, customer);
					customerRepository.save(customer);
					session.setAttribute("customer", customer);
				} else {
					return "redirect:/auth/userinfoLogin";
				}
			} else if (scope.equals(SCOPE_USERINFO)) {
				if (customer == null) {
					customer = new Customer();
					customer.setOpenId(openId);
				}
				String accessToken = tokenResponse.getAccessToken();
				UserinfoResponse userinfoResponse = getUserinfo(openId, accessToken);
				if (userinfoResponse.getErrorCode() == null) {
					syncUserinfo(userinfoResponse, customer);
					customerRepository.save(customer);
					session.setAttribute("customer", customer);
				} else {
					return "redirect:/auth/userinfoLogin";
				}
			} else {
				return "error"; //scope invalid
			}
			return "redirect:/home";
		} else {
			return "error";
		}
	}
	
	private String getWechat(String scope) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(WECHAT_AUTHORIZE_URL);
		builder.queryParam("appid", APP_ID);
		builder.queryParam("redirect_uri", REDIRECT_URI);
		builder.queryParam("response_type", RESPONSE_TYPE);
		builder.queryParam("scope", scope);
		builder.queryParam("state", STATE);
		builder.fragment(FRAGMENT);
		String wechat = builder.build(true).toString();
		return wechat;
	}
	
	private TokenResponse getToken(String code) {
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(WECHAT_TOKEN_URL);
//		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString("http://localhost:8080/auth/commonFail");
//		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString("http://localhost:8080/auth/tokenSuccess");
		uriBuilder.queryParam("appid", APP_ID);
		uriBuilder.queryParam("secret", SECRET);
		uriBuilder.queryParam("code", code);
		uriBuilder.queryParam("grant_type", GRANT_TYPE);
		URI uri = uriBuilder.build().toUri();
//		TokenResponse response = restTemplate.getForObject(uri, TokenResponse.class);
		ResponseEntity<String> entity = restTemplate.getForEntity(uri, String.class);
		String body = entity.getBody();
		TokenResponse response = JsonUtils.jsonToObject(body, TokenResponse.class);
		return response;
	}
	
	private UserinfoResponse getUserinfo(String openId, String accessToken) {
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(WECHAT_USERINFO_URL);
//		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString("http://localhost:8080/auth/commonFail");
//		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString("http://localhost:8080/auth/userinfoSuccess");
		uriBuilder.queryParam("access_token", accessToken);
		uriBuilder.queryParam("openid", openId);
		uriBuilder.queryParam("lang", LANG);
		URI uri = uriBuilder.build().toUri();
		ResponseEntity<String> entity = restTemplate.getForEntity(uri, String.class);
		String body = entity.getBody();
		try {
			body = new String(body.getBytes("ISO-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UserinfoResponse response= JsonUtils.jsonToObject(body, UserinfoResponse.class);
		return response;
	}
	
	private void syncUserinfo(UserinfoResponse response, Customer customer) {
		customer.setNickname(response.getNickname());
		customer.setAvatar(response.getAvatar());
		customer.setRemoteSystem("wechat");
		customer.setCreateDate(new Date());
		customer.setLastLoginDate(new Date());
		customer.setUserinfo("");
	}
	
	@RequestMapping(value = "/commonFail")
	@ResponseBody
	public String commonFail(@RequestParam String appid, @RequestParam String secret, @RequestParam String code, @RequestParam(value="grant_type") String grantType) {
		AuthFailResponse response = new AuthFailResponse();
		response.setErrorCode(40029);
		response.setErrorMessage("invalid code");
		return JsonUtils.objectToJson(response);
	}
	
	@RequestMapping(value = "/tokenSuccess")
	@ResponseBody
	public String tokenSuccess(@RequestParam String appid, @RequestParam String secret, @RequestParam String code, @RequestParam(value="grant_type") String grantType) {
		TokenSuccessResponse response = new TokenSuccessResponse();
		response.setAccessToken("ACCESS_TOKEN");
		response.setExpiresIn(7200L);
		response.setRefreshToken("REFRESH_TOKEN");
		response.setOpenId("OPENID");
		response.setScope(SCOPE_BASE);
		response.setUnionId("UNIONID");
		return JsonUtils.objectToJson(response);
	}
	
	@RequestMapping(value = "/userinfoSuccess")
	@ResponseBody
	public String userinfoSuccess(@RequestParam(value="access_token") String accessToken, @RequestParam String openid, @RequestParam String lang) {
		UserinfoSuccessResponse response = new UserinfoSuccessResponse();
		response.setNickname("NICKNAME");
		response.setAvatar("http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/46");
		response.setOpenId("OPENID");
		response.setSex("1");
		response.setCountry("COUNTRY");
		response.setProvince("PROVINCE");
		response.setCity("CITY");
		response.setPrivilege(Arrays.asList("PRIVILEGE1", "PRIVILEGE2"));
		response.setUnionId("UNIONID");
		return JsonUtils.objectToJson(response);
	}
	
}
