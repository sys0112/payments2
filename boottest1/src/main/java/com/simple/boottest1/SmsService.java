package com.simple.boottest1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

@Service
public class SmsService {
	
	@Value("${coolsms.api_key}")
    private String apiKey;

    @Value("${coolsms.api_secret}")
    private String apiSecret;

    @Value("${coolsms.api_url}")
    private String apiUrl;

    public String sendSms(String phoneNumber, String message) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJtZW1iZXJJZCI6Ik1FTXk0YW94YVRRcU12IiwiYWNjb3VudElkIjoyNDA2MjAyOTA1NzM1OCwiY2xpZW50SWQiOiJDSURQU1NVS0dVUUFGRVVLIiwic2NvcGUiOiJtZXNzYWdlOndyaXRlIiwiaXNTdWJBY2NvdW50IjpmYWxzZSwiaWF0IjoxNzE5NjM5MjcyLCJleHAiOjE3MTk3MjU2NzJ9.COQ_M-OQuV8Q4jcEi2XPg1T-Bqa14Im_9aNwhX4BPxo");

        Map<String, Object> body = new HashMap<>();
        body.put("messages", new Object[]{  // "messages" 필드 추가
            Map.of(
                "to", phoneNumber,
                "from", "01083236611",  // CoolSMS에서 인증된 발신 번호로 변경해야 합니다.
                "text", message,
                "type", "SMS"
            )
        });
        
        
        
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            apiUrl,
            HttpMethod.POST,
            request,
            String.class
        );

        return response.getBody();
    }

}