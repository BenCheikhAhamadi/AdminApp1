package sn.ucad.ben.adminapp1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.ucad.ben.adminapp1.entities.RoleEntity;
@Repository
public interface IRolesRepository extends JpaRepository<RoleEntity,Integer> {

}
