/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs GodownRepository
 *
 *******************************************************************************/
package com.niche.ng.repository;

import com.niche.ng.domain.Godown;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Godown entity.
 *
 * GodownRepository Extends JpaRepository to handle the CRUD operation and
 * querying the values using keywords.
 */
@SuppressWarnings("unused")
@Repository
public interface GodownRepository extends JpaRepository<Godown, Long>, JpaSpecificationExecutor<Godown> {

}
