package com.sercapcab.pathfinder.game.entity.role

import com.sercapcab.pathfinder.Since
import com.sercapcab.pathfinder.game.config.json
import com.sercapcab.pathfinder.game.exceptions.EntityAlreadyExistsException
import com.sercapcab.pathfinder.game.exceptions.EntityNotFoundException
import jakarta.validation.Valid
import lombok.AllArgsConstructor
import org.apache.coyote.Response
import org.jetbrains.annotations.NotNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/role")
@Since(version = "1.0")
class RoleRestController(
    @Autowired private val roleService: RoleService
) {
    @GetMapping(path = [""], produces = [json])
    @NotNull
    fun getAllRoles(): ResponseEntity<List<Role>> {
        val roles = roleService.findAll()

        return if (roles.isEmpty())
            ResponseEntity.noContent().build()
        else
            ResponseEntity.ok(roles)
    }

    @GetMapping(path = ["{roleName}"], produces = [json])
    @NotNull
    fun getRole(@PathVariable roleName: String): ResponseEntity<Role> {
        val role = roleService.findByRoleName(roleName)

        return if (role.isPresent)
            ResponseEntity.ok(role.get())
        else
            throw EntityNotFoundException(Role::class.java, roleName)
    }

    @PostMapping(path = [""], produces = [json])
    @NotNull
    fun createRol(@Valid @RequestBody role: RoleRequest): ResponseEntity<Role> {
        val roleToSave = role.toRole()

        if (roleService.findByRoleName(roleToSave.roleName).isPresent)
            throw EntityAlreadyExistsException(Role::class.java, roleToSave.roleName)

        roleService.save(roleToSave)

        return ResponseEntity
            .created(
                ServletUriComponentsBuilder.fromCurrentRequest().path("")
                    .buildAndExpand(role).toUri()
            )
            .body(roleToSave)
    }

    @PutMapping(path = ["{roleId}"], produces = [json])
    @NotNull
    fun updateRole(@Valid @RequestBody role: RoleRequest, @PathVariable roleId: Int): ResponseEntity<Role> {
        val foundRole = roleService.findById(roleId)

        foundRole.get().roleName = role.roleName
        foundRole.get().rolePermissions = role.rolePermissions

        return ResponseEntity
            .created(
                ServletUriComponentsBuilder.fromCurrentRequest().path("{roleId}")
                    .buildAndExpand(role).toUri()
            )
            .body(role.toRole())
    }
}
