package com.tpp.threat_perception_platform.exception;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TokenException extends Exception{
    private String msg;
    public TokenException(String message)
    {
        super(message);
        this.msg = message;
    }
}
