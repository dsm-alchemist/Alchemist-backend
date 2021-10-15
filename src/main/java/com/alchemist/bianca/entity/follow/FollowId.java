package com.alchemist.bianca.entity.follow;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class FollowId implements Serializable {
    @Column(name = "following")
    private Long following;

    @Column(name = "follower")
    private Long follower;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
