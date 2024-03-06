package sn.ucad.ben.adminapp1.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sn.ucad.ben.adminapp1.dto.RoleDto;
import sn.ucad.ben.adminapp1.services.RoleService;

import java.util.List;

@RestController
@RequestMapping("/roles")
@AllArgsConstructor
public class RoleController {
    public RoleService roleService;

    @GetMapping("/role")
    public List<RoleDto> getRoleDto(){

        return roleService.getRoleDto();
    }

    @GetMapping("/roleById/{id}")
    public RoleDto getRoleByIdDto(@PathVariable("id") int id){

        return roleService.getRoleByIdDto(id);
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public RoleDto createRoles(@Valid @RequestBody RoleDto roleDto){

        return roleService.createRoles(roleDto);
    }

    @PutMapping("/updateById/{id}")
    public RoleDto updateRoleDto(@PathVariable("id") int id,@Valid @RequestBody RoleDto roleDto){
        return roleService.updateRoleDto(id, roleDto);
    }

    @DeleteMapping("/deleteById/{id}")
    public void  deleteRoleDto(@PathVariable("id") int id){
        roleService.deleteRoleDto(id);
    }
}
