package sn.ucad.ben.adminapp1.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sn.ucad.ben.adminapp1.dto.UtilisateurDto;
import sn.ucad.ben.adminapp1.services.UtilisateurService;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UtilisateurController {
    public UtilisateurService utilisateurService;
    @GetMapping("/user")
    public List<UtilisateurDto> getUtilisateurDto(){
        return utilisateurService.getUtilisateur();
    }

    @GetMapping("/userById/{id}")
    public UtilisateurDto getUtilisateurById(@PathVariable("id") int id){
        return utilisateurService.getUtilisateurById(id);
    }
    @PostMapping("/add")
    @ResponseStatus(code= HttpStatus.CREATED)
    public UtilisateurDto createUser(@Valid @RequestBody  UtilisateurDto utilisateurDto){
        return utilisateurService.createUtilisateur(utilisateurDto);
    }
 @PutMapping("/update/{id}")
   public UtilisateurDto updateUser(@PathVariable("id") int id,@RequestBody @Valid UtilisateurDto utilisateurDto){
        return utilisateurService.updateUtilisateur(id,utilisateurDto);
   }
   @DeleteMapping("/delete/{id}")
   public void deleteUser(@PathVariable("id") int id){
        utilisateurService.deleteUser(id);
   }

}
