package com.epam.newsmanagement.util;


import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;

public class Coder {

    public static String getHashedPassword(String pw) {
        PasswordEncoder encoder = new Md5PasswordEncoder();
        String hashedPass = encoder.encodePassword(pw, null);
        return hashedPass;
    }

}
