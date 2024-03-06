package sn.ucad.ben.adminapp1.mapper;

import org.mapstruct.Mapper;
import sn.ucad.ben.adminapp1.dto.UtilisateurDto;
import sn.ucad.ben.adminapp1.entities.UtilisateurEntity;

@Mapper
public interface UtilisateurMapper  {
    UtilisateurDto toUtilisateur(UtilisateurEntity utilisateurEntity);
    UtilisateurEntity fromUtilisateur(UtilisateurDto utilisateurDto);
}
