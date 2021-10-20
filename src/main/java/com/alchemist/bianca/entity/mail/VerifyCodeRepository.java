package com.alchemist.bianca.entity.mail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerifyCodeRepository extends CrudRepository<VerifyCode, String> {
}
