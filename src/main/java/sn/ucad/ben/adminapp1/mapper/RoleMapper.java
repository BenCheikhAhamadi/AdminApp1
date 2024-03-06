package sn.ucad.ben.adminapp1.mapper;

import org.mapstruct.Mapper;
import sn.ucad.ben.adminapp1.dto.RoleDto;
import sn.ucad.ben.adminapp1.entities.RoleEntity;
@Mapper
public interface RoleMapper {
    RoleDto toRoleDto(RoleEntity roleEntity);
    RoleEntity fromRoleDto(RoleDto roleDto);
}
