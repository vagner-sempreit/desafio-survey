package br.com.cielo.desafio.survey.handler;

import br.com.cielo.desafio.survey.exception.PollExistException;
import br.com.cielo.desafio.survey.exception.RequiredFieldsEnum;
import br.com.cielo.desafio.survey.exception.ServeyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.MediaType.APPLICATION_JSON;


@ControllerAdvice(annotations = { RestController.class })
public class SurveyHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    public static final String UNAVAILABLE_SERVICE = "UNAVAILABLE SERVICE";

    @ExceptionHandler({ PollExistException.class })
    @ResponseBody
    public ResponseEntity<ErrorResponse> confictException(PollExistException e) {
        logger.info(e.getError().getDescription());
        return ResponseEntity.status(e.getError().getHttpStatus()).contentType(APPLICATION_JSON).body(new ErrorResponse(e.getError().getDescription(), e.getError().getHttpStatus()));
    }

    @ExceptionHandler({ ServeyException.class })
    @ResponseBody
    public ResponseEntity<ErrorResponse> exceptionSurvey(ServeyException e) {

        logger.info(e.getError().getDescription());
        return ResponseEntity.status(e.getError().getHttpStatus()).contentType(APPLICATION_JSON).body(new ErrorResponse(e.getError().getDescription(), e.getError().getHttpStatus()));
    }


    @ExceptionHandler({ RuntimeException.class})
    @ResponseBody
    public ResponseEntity<String> handleRuntimeException(RuntimeException e) {
        logger.info(e.getMessage());
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).contentType(APPLICATION_JSON)
                .body(new ErrorResponse(UNAVAILABLE_SERVICE, INTERNAL_SERVER_ERROR).toString());

    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        List<ErrorResponse> collect = ex.getBindingResult().getFieldErrors().stream().map(fieldError -> {
            RequiredFieldsEnum requiredFieldsEnum = RequiredFieldsEnum.converter(fieldError.getField());
            String userMessage = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            return new ErrorResponse(userMessage, requiredFieldsEnum.getFieldId(), requiredFieldsEnum.getId());
        }).collect(Collectors.toList());

        return ResponseEntity.status(status).contentType(APPLICATION_JSON).body(collect);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {

        return ResponseEntity.status(status).contentType(APPLICATION_JSON).body(new ErrorResponse(ex.getMessage(), status));

    }



}
