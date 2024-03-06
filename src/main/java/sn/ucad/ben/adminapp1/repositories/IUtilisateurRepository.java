package sn.ucad.ben.adminapp1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.ucad.ben.adminapp1.entities.UtilisateurEntity;
@Repository
public interface IUtilisateurRepository extends JpaRepository<UtilisateurEntity,Integer> {
    UtilisateurEntity findByEmail(String email);
}
