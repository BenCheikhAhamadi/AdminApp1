package sn.ucad.ben.adminapp1.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestException extends RuntimeException {
    String message;
    HttpStatus status;
}
