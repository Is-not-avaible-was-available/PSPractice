package com.scaler.PSPractice.DTos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter

@AllArgsConstructor
public class ExceptionDTO {
    private HttpStatus httpStatus;
    private String message;
}
