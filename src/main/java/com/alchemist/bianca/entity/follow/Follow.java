package com.alchemist.bianca.entity.follow;

import com.alchemist.bianca.entity.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Follow {

    @EmbeddedId
    private FollowId followId;

    @MapsId("following")
    @ManyToOne(optional = false)
    @JoinColumn(name = "following", referencedColumnName = "email", updatable = false, insertable = false)
    private User following;

    @MapsId("follower")
    @ManyToOne(optional = false)
    @JoinColumn(name = "follower", referencedColumnName = "email", updatable = false, insertable = false)
    private User follower;

}
