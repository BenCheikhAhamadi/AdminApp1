package sn.ucad.ben.adminapp1.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntityNotFoundException extends RuntimeException{
    String message;
}
