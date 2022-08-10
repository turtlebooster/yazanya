package com.ssafy.B310.jwt;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ssafy.B310.dto.TokenResponse;
import com.ssafy.B310.entity.User;
import com.ssafy.B310.repository.AuthRepository;
import com.ssafy.B310.repository.UserRepository;
import com.ssafy.B310.service.JwtServiceImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {
	
	@Autowired
	AuthRepository authRepository;
	
	@Autowired
	UserRepository userRepository;
	
	
	public static final Logger logger = LoggerFactory.getLogger(JwtServiceImpl.class);

    @Value("${secret.access}")
    private String SECRET_KEY;
    @Value("${secret.refresh}")
    private String REFRESH_KEY;

    private final long ACCESS_TOKEN_VALID_TIME = 1 * 60 * 1000L;   // 1분
    private final long REFRESH_TOKEN_VALID_TIME = 60 * 60 * 24 * 7 * 1000L;   // 1주

    // 객체 초기화, secretKey를 Base64로 인코딩한다.
    @PostConstruct
    protected void init() {
        System.out.println("SECRET_KEY: "+SECRET_KEY);
        System.out.println("REFRESH_KEY = " + REFRESH_KEY);
        SECRET_KEY = Base64.getEncoder().encodeToString(SECRET_KEY.getBytes());
        REFRESH_KEY = Base64.getEncoder().encodeToString(REFRESH_KEY.getBytes());
    }

    // JWT 토큰 생성
    public String createAccessToken(String userId) {
        Claims claims = Jwts.claims();//.setSubject(userPk); // JWT payload 에 저장되는 정보단위
        claims.put("userId", userId);
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + ACCESS_TOKEN_VALID_TIME)) // set Expire Time
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)  // 사용할 암호화 알고리즘과
                .compact();
    }

    public String createRefreshToken(String userId) {
        Claims claims = Jwts.claims();
        claims.put("userId", userId); //
        Date now = new Date();
        Date expiration = new Date(now.getTime() + REFRESH_TOKEN_VALID_TIME);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, REFRESH_KEY)
                .compact();
    }

    // Request의 Header에서 token 값을 가져옵니다. "X-AUTH-TOKEN" : "TOKEN값'
    public String resolveAccessToken(HttpServletRequest request) {
        return request.getHeader("access-token");
    }

    public String resolveRefreshToken(HttpServletRequest request) {
        return request.getHeader("refresh-token");
    }

//    //Refresh 토큰의 DB상의 인덱스 번호를 해시로 받음
//    public Long resolveRefreshIndexToken(HttpServletRequest request) {
//        return Long.parseLong(request.getHeader("REFRESH_TOKEN_INDEX"));
//    }

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
    
	public String getUserID(String jwt) {
		Jws<Claims> claims = Jwts.parser().setSigningKey(this.generateKey()).parseClaimsJws(jwt);
		String userId = (String) claims.getBody().get("userId");
		return userId;
	}
	
	private byte[] generateKey() {
		byte[] key = null;
		try {
			key = SECRET_KEY.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			if (logger.isInfoEnabled()) {
				e.printStackTrace();
			} else {
				logger.error("Making JWT Key Error ::: {}", e.getMessage());
			}
		}
		return key;
	}
	
	
	public TokenResponse issueAccessToken(HttpServletRequest request){
        String accessToken = resolveAccessToken(request);
        String refreshToken = resolveRefreshToken(request);
        
        System.out.println("accessToken = " + accessToken);
        System.out.println("refreshToken = " + refreshToken);
        
        //accessToken이 만료됐고 refreshToken이 맞으면 accessToken을 새로 발급(refreshToken의 내용을 통해서)
        if(isValidRefreshToken(refreshToken)){     //들어온 Refresh 토큰이 유효한지
            System.out.println("Refresh 토큰은 유효함");
            
            Claims claimsToken = getClaimsToken(refreshToken);
            String userId = (String)claimsToken.get("userId");
            
            Optional<User> user = userRepository.findByUserId(userId);
            System.out.println(user.get());
            String tokenFromDB = authRepository.findByuser_userId(user.get().getUserId()).get().getRefreshToken();
            
            System.out.println("tokenFromDB = " + tokenFromDB);
            System.out.println(refreshToken);
            if(refreshToken.equals(tokenFromDB)) {   //DB의 refresh토큰과 지금들어온 토큰이 같은지 확인
                System.out.println("Access 토큰 재발급 완료");
                accessToken = createAccessToken(userId);
                System.out.println("새로운 accessToken ::: " + accessToken);
            }
            else{
                //DB의 Refresh토큰과 들어온 Refresh토큰이 다르면 중간에 변조된 것임
                System.out.println("Refresh Token Tampered");
                //예외발생
                return null;
            }
        }
        else{
            //입력으로 들어온 Refresh 토큰이 유효하지 않음
        	return null;
        }
        
        return TokenResponse.builder()
                .ACCESS_TOKEN(accessToken)
                .REFRESH_TOKEN(refreshToken)
                .build();
    }
    
}