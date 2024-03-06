package sn.ucad.ben.adminapp1.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class RoleDto {
    private int id;
    @NotNull(message = "Le nom du role ne doit pas etre null")
    private String nom;

}
