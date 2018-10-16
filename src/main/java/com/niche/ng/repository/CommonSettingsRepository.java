package com.niche.ng.repository;

import com.niche.ng.domain.CommonSettings;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CommonSettings entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CommonSettingsRepository extends JpaRepository<CommonSettings, Long>, JpaSpecificationExecutor<CommonSettings> {

}
