package com.savvas.jobapp.util;

import com.savvas.jobapp.dto.Token;

import java.time.LocalDateTime;

/**
 * @Author: TCMALTUNKAN - MEHMET ANIL ALTUNKAN
 * @Date: 30.12.2019:09:39, Pzt
 **/
public interface TokenProvider {
    Token generateAccessToken(String subject);

    String getUsernameFromToken(String token);

    LocalDateTime getExpiryDateFromToken(String token);

    boolean validateToken(String token);
}
