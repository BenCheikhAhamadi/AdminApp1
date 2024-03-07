package sn.ucad.ben.adminapp1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import sn.ucad.ben.adminapp1.dto.RoleDto;
import sn.ucad.ben.adminapp1.entities.RoleEntity;
import sn.ucad.ben.adminapp1.exception.EntityNotFoundException;
import sn.ucad.ben.adminapp1.exception.RequestException;
import sn.ucad.ben.adminapp1.mapper.RoleMapper;
import sn.ucad.ben.adminapp1.repositories.IRolesRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class RoleService {
    public IRolesRepository iRolesRepository;
    public RoleMapper roleMapper;
    MessageSource messageSource;

    public  RoleService(IRolesRepository iRolesRepository,RoleMapper roleMapper,MessageSource messageSource) {
        this.iRolesRepository = iRolesRepository;
        this.roleMapper = roleMapper;
        this.messageSource = messageSource;
    }
    @Transactional(readOnly = true)
 public List<RoleDto>getRoleDto(){

        return StreamSupport.stream(iRolesRepository.findAll().spliterator(),false)
                .map(roleMapper::toRoleDto).collect(Collectors.toList());
 }

 @Transactional(readOnly = true)
public RoleDto getRoleByIdDto(int id){
        return roleMapper.toRoleDto(iRolesRepository.findById(id)
                .orElseThrow(()->
                        new EntityNotFoundException(messageSource.getMessage("role.notfound",new Object[]{id},
                                Locale.getDefault()))));
}

@Transactional
public RoleDto createRoles(RoleDto roleDto){
        iRolesRepository.findById(roleDto.getId())
                .ifPresent(entity -> {
                     throw new  RequestException (
                             messageSource.getMessage("roles.exists",
                                     new Object[]{roleDto.getId()},
                            Locale.getDefault()),HttpStatus.CONFLICT);
                });
        return roleMapper.toRoleDto(iRolesRepository.save(roleMapper.fromRoleDto( roleDto)));
}
@Transactional
public RoleDto updateRoleDto(int id,RoleDto roleDto){
        return iRolesRepository.findById(id)
                .map(entity->{
                    roleDto.setId(id);
                    return  roleMapper.toRoleDto(
                            iRolesRepository.save(roleMapper.fromRoleDto(roleDto)));
                }).orElseThrow(()->new EntityNotFoundException(messageSource.getMessage("role.notfound",new Object[]{id},
                        Locale.getDefault())));
}
@Transactional
public void deleteRoleDto(int id){
        try {
            iRolesRepository.deleteById(id);
        }catch (Exception e){
            throw new RequestException(messageSource.getMessage("role.errordeletion",new Object[]{id},
                    Locale.getDefault()), HttpStatus.CONFLICT);
        }
}


}
