package com.alchemist.bianca.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Follow {

    @EmbeddedId
    private FollowId followId;

    @MapsId("following")
    @ManyToOne(optional = false)
    @JoinColumn(name = "following", referencedColumnName = "user_id", updatable = false, insertable = false)
    private User following;

    @MapsId("follower")
    @ManyToOne(optional = false)
    @JoinColumn(name = "follower", referencedColumnName = "user_id", updatable = false, insertable = false)
    private User follower;

}
