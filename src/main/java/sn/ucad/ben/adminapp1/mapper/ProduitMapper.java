package sn.ucad.ben.adminapp1.mapper;

import org.mapstruct.Mapper;
import sn.ucad.ben.adminapp1.dto.ProduitDto;
import sn.ucad.ben.adminapp1.entities.ProduitEntity;

@Mapper
public interface ProduitMapper {
    ProduitDto toProduit(ProduitEntity produitEntity);
    ProduitEntity fromProduit(ProduitDto produitDto);
}
