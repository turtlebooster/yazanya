package com.ssafy.B310.service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ssafy.B310.entity.User;
import com.ssafy.B310.exception.UnauthorizedException;
import com.ssafy.B310.repository.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtServiceImpl implements JwtService {

	public static final Logger logger = LoggerFactory.getLogger(JwtServiceImpl.class);

	private static final String SALT = "yazanyaAccess";
	private static final int EXPIRE_MINUTES = 60;
	
	@Autowired
	UserRepository userRepository;
	
	// 토큰 생성
	// private static final int EXPIRE_MINUTES = 1;
	
	private String REFRESH_KEY = "yazanyaRefresh";
	private String SECRET_KEY = "yazanyaAccess";

	// Access 토큰 생성
	@Override
	public String createAccessToken(String userId) {
		String jwt = Jwts.builder().setHeaderParam("typ", "ACCESS").setHeaderParam("regDate", System.currentTimeMillis())
				.setExpiration(new Date(System.currentTimeMillis() + 100 * 60 * EXPIRE_MINUTES)).setSubject("access-token")
				.claim("userId", userId).signWith(SignatureAlgorithm.HS256, this.generateKey()).compact();
		
		return jwt;
	}
	
	// Refresh 토큰 생성
	@Override
	public String createRefreshToken(String userId) {
		Claims claims = Jwts.claims();
		claims.put("userId", userId);
		
		Date now = new Date();
		Date expiration = new Date(now.getTime() + 60  *  1000L);
		
		return Jwts.builder().setHeaderParam("typ", "REFRESH").setClaims(claims).setIssuedAt(now).setExpiration(expiration)
				.signWith(SignatureAlgorithm.HS256, this.generateKey()).compact();
	}
	
	
	
	// Request의 Header에서 token 값을 가져옵니다. "X-AUTH-TOKEN" : "TOKEN값'
    public String resolveAccessToken(HttpServletRequest request) {
        return request.getHeader("ACCESS_TOKEN");
    }

    public String resolveRefreshToken(HttpServletRequest request) {
        return request.getHeader("REFRESH_TOKEN");
    }
    
    public Claims getClaimsFormToken(String token) {
        return Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .parseClaimsJws(token)
                .getBody();
    }
    
    public Claims getClaimsToken(String token) {
        return Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(REFRESH_KEY))
                .parseClaimsJws(token)
                .getBody();
    }
    
    
    public boolean isValidAccessToken(String token) {
        System.out.println("isValidToken is : " +token);
        try {
            Claims accessClaims = getClaimsFormToken(token);
            System.out.println("Access expireTime: " + accessClaims.getExpiration());
            System.out.println("Access userId: " + accessClaims.get("userId"));
            return true;
        } catch (ExpiredJwtException exception) {
            System.out.println("Token Expired UserID : " + exception.getClaims().get("userId"));
            return false;
        } catch (JwtException exception) {
            System.out.println("Token Tampered");
            return false;
        } catch (NullPointerException exception) {
            System.out.println("Token is null");
            return false;
        }
    }
    public boolean isValidRefreshToken(String token) {
    	
        try {
            Claims accessClaims = getClaimsToken(token);
            System.out.println("여기에 들어왔어요");
            System.out.println("Access expireTime: " + accessClaims.getExpiration());
            System.out.println("Access userId: " + accessClaims.get("userId"));
            return true;
        } catch (ExpiredJwtException exception) {
            System.out.println("Token Expired UserID : " + exception.getClaims().get("userId"));
            return false;
        } catch (JwtException exception) {
            System.out.println("Token Tampered");
            return false;
        } catch (NullPointerException exception) {
            System.out.println("Token is null");
            return false;
        }
    }
    public boolean isOnlyExpiredToken(String token) {
        System.out.println("isValidToken is : " +token);
        try {
            Claims accessClaims = getClaimsFormToken(token);
            System.out.println("Access expireTime: " + accessClaims.getExpiration());
            System.out.println("Access userId: " + accessClaims.get("userId"));
            return false;
        } catch (ExpiredJwtException exception) {
            System.out.println("Token Expired UserID : " + exception.getClaims().get("userId"));
            return true;
        } catch (JwtException exception) {
            System.out.println("Token Tampered");
            return false;
        } catch (NullPointerException exception) {
            System.out.println("Token is null");
            return false;
        }
    }
	

	private byte[] generateKey() {
		byte[] key = null;
		try {
			key = SALT.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			if (logger.isInfoEnabled()) {
				e.printStackTrace();
			} else {
				logger.error("Making JWT Key Error ::: {}", e.getMessage());
			}
		}
		return key;
	}

	@Override
	public Map<String, Object> get(String key) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		String jwt = request.getHeader("access-token");
		Jws<Claims> claims = null;
		try {
			claims = Jwts.parser().setSigningKey(SALT.getBytes("UTF-8")).parseClaimsJws(jwt);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new UnauthorizedException();
		}
		Map<String, Object> value = claims.getBody();
		logger.info("value : {}", value);
		return value;
	}

	@Override
	public String getUserID(String jwt) {
		Jws<Claims> claims = Jwts.parser().setSigningKey(this.generateKey()).parseClaimsJws(jwt);
		String userId = (String) claims.getBody().get("userId");
		return userId;
	}

	// 전달 받은 토큰 유효성 검사
	@Override
	public boolean isUsable(String jwt) {
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(this.generateKey()).parseClaimsJws(jwt);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}

	// JWT 갱신
	@Override
	public <T> String refresh(String jwt) {
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(this.generateKey()).parseClaimsJws(jwt);
			String userId = (String) claims.getBody().get("userId");
			System.out.println(userId + " 유저가 토큰을 갱신했습니다.");
			return createAccessToken(userId);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	@Override
	public int getUserNum(String jwt) {
		String userId = getUserID(jwt);
		
		Optional<User> oUser = userRepository.findByUserId(userId);
		if (!oUser.isPresent()) {
			return -1;
		}
		
		int userNum = oUser.get().getUserNum();
		return userNum;
	}

}
