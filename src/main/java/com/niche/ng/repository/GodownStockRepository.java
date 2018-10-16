/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs GodownStockRepository
 *
 *******************************************************************************/
package com.niche.ng.repository;

import com.niche.ng.domain.GodownStock;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;


/**
 * Spring Data  repository for the GodownStock entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GodownStockRepository extends JpaRepository<GodownStock, Long>, JpaSpecificationExecutor<GodownStock> {
    public List<GodownStock> findByGodownIdAndPickListCategoryId(@Param("godownId") Long godownId, @Param("pickListCategoryId") Long pickListCategoryId);

}
