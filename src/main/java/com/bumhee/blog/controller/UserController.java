package com.bumhee.blog.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.bumhee.blog.config.auth.PrincipalDetail;
import com.bumhee.blog.model.KakaoProfile;
import com.bumhee.blog.model.OAuthToken;
import com.bumhee.blog.model.User;
import com.bumhee.blog.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class UserController {

	@Value("${cos.key}")
	private String cosKey;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@GetMapping("/auth/joinForm")
	public String joinForm()
	{
		return "user/joinForm";
	}
	
	@GetMapping("/auth/loginForm")
	public String loginForm()
	{
		return "user/loginForm";
	}
	
	@GetMapping("/user/updateForm")
	public String updateForm(@AuthenticationPrincipal PrincipalDetail principal)
	{
		return "user/updateForm";
	}
	
	@GetMapping("/auth/kakao/callback")
	public String kakaoCallback(String code)
	{
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new  HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "79ba785d98f8e559e33d06a3daac8ebf");
		params.add("redirect_uri", "http://localhost:8000/auth/kakao/callback");
		params.add("code", code);
		
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);
		
		ResponseEntity<String> reponse = rt.exchange(
				"https://kauth.kakao.com/oauth/token",
				HttpMethod.POST,
				kakaoTokenRequest,
				String.class
				);
		ObjectMapper objectMapper = new ObjectMapper();
		OAuthToken oauthToken = null;
		
		try {
			oauthToken = objectMapper.readValue(reponse.getBody(), OAuthToken.class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(oauthToken.getAccess_token());
		
		
		RestTemplate rt2 = new RestTemplate();
		HttpHeaders headers2 = new  HttpHeaders();
		headers2.add("Authorization", "Bearer " + oauthToken.getAccess_token());
		headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 = new HttpEntity<>(headers2);
		
		ResponseEntity<String> reponse2 = rt2.exchange(
				"https://kapi.kakao.com/v2/user/me",
				HttpMethod.POST,
				kakaoProfileRequest2,
				String.class
				);
		
		
		ObjectMapper objectMapper2 = new ObjectMapper();
		KakaoProfile kakaoProfile = null;
		
		try {
			kakaoProfile = objectMapper2.readValue(reponse2.getBody(), KakaoProfile.class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		User kakaoUser = User.builder().username(kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId())
				.password(cosKey)
				.email(kakaoProfile.getKakao_account().getEmail())
				.oauth("kakao")
				.build();
		User originUser = userService.회원찾기(kakaoUser.getUsername());

		if(originUser.getUsername() == null)
		{
			System.out.println("기존회원 아니라서 회원가입한다");
			userService.회원가입(kakaoUser);
		}				
		
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), cosKey));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return "redirect:/";
	}
}
