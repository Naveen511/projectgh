/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs ExceptionTranslator
 *
 *******************************************************************************/
package com.niche.ng.web.rest.errors;

import com.niche.ng.web.rest.util.HeaderUtil;

import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.http.HttpStatus;
import org.zalando.problem.DefaultProblem;
import org.zalando.problem.Problem;
import org.zalando.problem.ProblemBuilder;
import org.zalando.problem.Status;
import org.zalando.problem.spring.web.advice.ProblemHandling;
import org.zalando.problem.spring.web.advice.validation.ConstraintViolationProblem;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Controller advice to translate the server side exceptions to client-friendly json structures.
 * The error response follows RFC7807 - Problem Details for HTTP APIs (https://tools.ietf.org/html/rfc7807)
 */
@ControllerAdvice
public class ExceptionTranslator implements ProblemHandling {

    /**
     * Post-process Problem payload to add the message key for front-end if needed
     */
    @Override
    public ResponseEntity<Problem> process(@Nullable ResponseEntity<Problem> entity, NativeWebRequest request) {
        if (entity == null || entity.getBody() == null) {
            return entity;
        }
        Problem problem = entity.getBody();
        if (!(problem instanceof ConstraintViolationProblem || problem instanceof DefaultProblem)) {
            return entity;
        }
        ProblemBuilder builder = Problem.builder()
            .withType(Problem.DEFAULT_TYPE.equals(problem.getType()) ? ErrorConstants.DEFAULT_TYPE : problem.getType())
            .withStatus(problem.getStatus())
            .withTitle(problem.getTitle())
            .with("path", request.getNativeRequest(HttpServletRequest.class).getRequestURI());

        if (problem instanceof ConstraintViolationProblem) {
            builder
                .with("violations", ((ConstraintViolationProblem) problem).getViolations())
                .with("message", ErrorConstants.ERR_VALIDATION);
            return new ResponseEntity<>(builder.build(), entity.getHeaders(), entity.getStatusCode());
        } else {
            builder
                .withCause(((DefaultProblem) problem).getCause())
                .withDetail(problem.getDetail())
                .withInstance(problem.getInstance());
            problem.getParameters().forEach(builder::with);
            if (!problem.getParameters().containsKey("message") && problem.getStatus() != null) {
                builder.with("message", "error.http." + problem.getStatus().getStatusCode());
            }
            return new ResponseEntity<>(builder.build(), entity.getHeaders(), entity.getStatusCode());
        }
    }

    @Override
    public ResponseEntity<Problem> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, @Nonnull NativeWebRequest request) {
        BindingResult result = ex.getBindingResult();
        // AnithaRaja Changed Aug 13 18 - from f.getCode() into f.getDefaultMessage() 
        // to display error message in view
        List<FieldErrorVM> fieldErrors = result.getFieldErrors().stream()
            .map(f -> new FieldErrorVM(f.getObjectName(), f.getField(), f.getDefaultMessage()))
            .collect(Collectors.toList());

        Problem problem = Problem.builder()
            .withType(ErrorConstants.CONSTRAINT_VIOLATION_TYPE)
            .withTitle("Method argument not valid")
            .withStatus(defaultConstraintViolationStatus())
            .with("message", ErrorConstants.ERR_VALIDATION)
            .with("fieldErrors", fieldErrors)
            .build();
        return create(ex, problem, request);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Problem> handleNoSuchElementException(NoSuchElementException ex, NativeWebRequest request) {
        Problem problem = Problem.builder()
            .withStatus(Status.NOT_FOUND)
            .with("message", ErrorConstants.ENTITY_NOT_FOUND_TYPE)
            .build();
        return create(ex, problem, request);
    }

    @ExceptionHandler(BadRequestAlertException.class)
    public ResponseEntity<Problem> handleBadRequestAlertException(BadRequestAlertException ex, NativeWebRequest request) {
        return create(ex, request, HeaderUtil.createFailureAlert(ex.getEntityName(), ex.getErrorKey(), ex.getMessage()));
    }

    @ExceptionHandler(ConcurrencyFailureException.class)
    public ResponseEntity<Problem> handleConcurrencyFailure(ConcurrencyFailureException ex, NativeWebRequest request) {
        Problem problem = Problem.builder()
            .withStatus(Status.CONFLICT)
            .with("message", ErrorConstants.ERR_CONCURRENCY_FAILURE)
            .build();
        return create(ex, problem, request);
    }

    // @ExceptionHandler(DataIntegrityViolationException.class)
    // @ResponseBody
    // @ResponseStatus(HttpStatus.CONFLICT)
    // public ErrorVM processDataIntegrityViolationException(DataIntegrityViolationException exception) {
    //     return new ErrorVM(ErrorConstants.ERR_VALIDATION_DUPLICATE, exception.getMessage());
    // }

    // @ExceptionHandler(DataIntegrityViolationException.class)
    // public ResponseEntity<?> dataIntegrityViolationException(DataIntegrityViolationException dve, HttpServletRequest request) {

    //     // final ErrorDetail errorDetail = new ErrorDetail();
    //     // errorDetail.setTimeStamp(new Date().getTime());
    //     // errorDetail.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
    //     // errorDetail.setTitle("Fields arleady exists " + dve.getCause().getCause().getMessage());
    //     // final String fieldValue = dve.getCause().getCause().getMessage().split("'")[1];

    //     // errorDetail.setDetail(fieldValue + " already exists. please use different one");
    //     // errorDetail.setDeveloperMessage(dve.getClass().getName());
    //     // final List<ValidationError> validationErrorList = new ArrayList<ValidationError>();
    //     // validationErrorList.add(new ValidationError(fieldValue, "Must be unique and already taken "));

    //     // errorDetail.getErrors().put("ValidationErrors", validationErrorList);

    //     return new ResponseEntity<>(dve.getCause().getCause().getMessage(), null, HttpStatus.NOT_ACCEPTABLE);

    // }
}
