package sn.ucad.ben.adminapp1.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sn.ucad.ben.adminapp1.dto.ProduitDto;
import sn.ucad.ben.adminapp1.services.ProduitService;

import java.util.List;

@RestController
@RequestMapping("/produits")
@AllArgsConstructor
public class ProduitController {
    public ProduitService produitService;

    @GetMapping("/produit")
    public List<ProduitDto>getProduitDto() {
       return produitService.getProduitDto();
    }

    @GetMapping("/produitById/{id}")
    public ProduitDto getProduitById(@PathVariable("id") int id){
        return  produitService.getProuitById(id);
    }
    @PostMapping("/add")
    @ResponseStatus(code= HttpStatus.CREATED)
    public ProduitDto createProduit(@Valid @RequestBody ProduitDto produitDto){
        return produitService.createProduit(produitDto);
    }
    @PutMapping("/update/{id}")
    public ProduitDto updateProduit(@PathVariable("id")int id, @Valid @RequestBody ProduitDto produitDto){
        return produitService.updateProduit(id,produitDto);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteproduit(@PathVariable("id") int id){
        produitService.produitDelete(id);
    }
}
