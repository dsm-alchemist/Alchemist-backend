package com.alchemist.bianca.entity.user;

import com.alchemist.bianca.dto.user.response.LankResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserRepositoryCustom {
    void startTimer(Long time);
    void stopTimer(Long second);
    Page<LankResponse> rank(Pageable pageable);
    void bulkUpdate();
    Long getSecond();
}
