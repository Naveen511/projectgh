/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs PersistentTokenRepository
 *
 *******************************************************************************/
package com.niche.ng.repository;

import com.niche.ng.domain.PersistentToken;
import com.niche.ng.domain.User;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Spring Data JPA repository for the PersistentToken entity.
 *
 * PersistentTokenRepository Extends JpaRepository to handle the CRUD operation and
 * querying the values using keywords.
 */
public interface PersistentTokenRepository extends JpaRepository<PersistentToken, String> {

    // Query the list of persistentToken using a field user.
    List<PersistentToken> findByUser(User user);

    // Query the list of persistentToken using a field localDate.
    List<PersistentToken> findByTokenDateBefore(LocalDate localDate);

}
