package com.sercapcab.pathfinder.game.entity.role

import com.sercapcab.pathfinder.Since
import com.sercapcab.pathfinder.game.config.json
import com.sercapcab.pathfinder.game.exceptions.EntityAlreadyExistsException
import com.sercapcab.pathfinder.game.exceptions.EntityNotFoundException
import jakarta.validation.Valid
import lombok.AllArgsConstructor
import org.jetbrains.annotations.NotNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder


@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/role-permission")
@Since(version = "1.0")
class RolePermissionRestController(
    @Autowired private val rolePermissionService: RolePermissionService
) {
    @GetMapping(path = ["/"], produces = [json])
    @NotNull
    fun getAllRolePermissions(): ResponseEntity<List<RolePermission>> {
        val rolePermissions = rolePermissionService.findAll().toList()

        return if (rolePermissions.isEmpty())
            ResponseEntity.noContent().build()
        else
            ResponseEntity.ok(rolePermissions)
    }

    @GetMapping(path = ["{rolePermission}"], produces = [json])
    @NotNull
    fun getRolePermission(@PathVariable rolePermission: String): ResponseEntity<RolePermission> {
        val rolePermissionFound = rolePermissionService.findByRolePermission(rolePermission)

        return if (rolePermissionFound.isPresent)
            ResponseEntity.ok(rolePermissionFound.get())
        else
            throw EntityNotFoundException(RolePermission::class.java, rolePermission)
    }

    @PostMapping(path = ["/"], produces = [json])
    @NotNull
    fun createRolePermission(@Valid @RequestBody requestRolePermission: RolePermissionRequest): ResponseEntity<RolePermission> {
        val rolePermissionToSave = requestRolePermission.toRolePermission()

        if (rolePermissionService.findByRolePermission(rolePermissionToSave.rolePermission).isPresent)
            throw EntityAlreadyExistsException(RolePermission::class.java, rolePermissionToSave.rolePermission)

        rolePermissionService.save(rolePermissionToSave)

        return ResponseEntity
            .created(
                ServletUriComponentsBuilder.fromCurrentRequest().path("")
                    .buildAndExpand(requestRolePermission).toUri()
            )
            .body(rolePermissionToSave)
    }
}