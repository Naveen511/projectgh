package com.niche.ng.repository;

import com.niche.ng.domain.OperationalHead;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * Spring Data  repository for the OperationalHead entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OperationalHeadRepository extends JpaRepository<OperationalHead, Long>, JpaSpecificationExecutor<OperationalHead> {
    List<OperationalHead> findByStatus(Integer status);
    long countByStatus(Integer status);
}
