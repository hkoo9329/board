package com.leejuhyeok.board.config;

import java.util.Map;

import com.leejuhyeok.board.domain.User;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OAuthAttributes {
	private Map<String, Object> attributes;
	private String nameAttributeKey;
	private String nickName;
	private String email;
	private String picture;
	
	
	@Builder
	public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey,
			String nickName, String email, String picturn) {
		this.attributes = attributes;
		this.nameAttributeKey = nameAttributeKey;
		this.nickName = nickName;
		this.picture = picturn;
	}
	
	public static OAuthAttributes of (String registrationId, String userNameAttributeName,
			Map<String, Object> attributes) {
		return ofGoogle(userNameAttributeName, attributes);
	}

	private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
		// TODO Auto-generated method stub
		return OAuthAttributes.builder()
				.nickName((String) attributes.get("nickname"))
				.email((String) attributes.get("email"))
				.picturn((String) attributes.get("picture"))
				.attributes(attributes)
				.nameAttributeKey(userNameAttributeName)
				.build();
	}
	
	public User toEntity() {
		return User.builder()
				.nickName(nickName)
				.email(email)
				.picture(picture)
				.build();
	}
}
