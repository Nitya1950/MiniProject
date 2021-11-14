package com.MCC.pediatric.centre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity, String> {

    public Optional<AdminEntity> findByEmail(String email);
    public Optional<AdminEntity> findByName(String name);
}

