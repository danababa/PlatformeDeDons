package com.uca.m2.pdd.Repository;

import com.uca.m2.pdd.Model.entity.Filter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FilterRepository extends JpaRepository<Filter, UUID> {
    List<Filter> findByUserId(UUID userId);
}
