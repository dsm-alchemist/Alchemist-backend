package com.alchemist.bianca.entity.mail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@RedisHash
@AllArgsConstructor
public class VerifyCode {

    @Id
    private final String address;

    @Indexed
    private String code;

    @TimeToLive
    private Long ttl;

    public VerifyCode update(String code, Long ttl) {
        this.code = code;
        this.ttl = ttl;
        return this;
    }
}
