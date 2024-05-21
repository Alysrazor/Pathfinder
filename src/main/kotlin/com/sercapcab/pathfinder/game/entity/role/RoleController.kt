package com.sercapcab.pathfinder.game.entity.role

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping("/api/v1/role")
class RoleController @Autowired constructor(
    private val roleService: RoleService
) {

    @GetMapping
    fun getAllRoles(): ResponseEntity<Iterable<Role>> {
        return ResponseEntity.ok(roleService.findAll())
    }

    @GetMapping("/{id}")
    fun getRoleById(@PathVariable id: Long): ResponseEntity<Role> {
        val role = roleService.findById(id)
        return if (role != null) {
            ResponseEntity.ok(role)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/name/{roleName}")
    fun getRoleByName(@PathVariable roleName: RoleEnum): ResponseEntity<Role> {
        val role = roleService.findByRoleName(roleName)
        return if (role != null) {
            ResponseEntity.ok(role)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping(path = ["/"])
    fun createRole(@RequestBody role: Role): ResponseEntity<Role> {
        roleService.save(role)

        return ResponseEntity
            .created(
                ServletUriComponentsBuilder.fromCurrentRequest().path("/")
                    .buildAndExpand(role).toUri()
            )
            .body(role)
    }

    @PutMapping("/{id}")
    fun updateRole(@PathVariable id: Long, @RequestBody role: Role): ResponseEntity<Role> {
        if (role.roleId == null || role.roleId != id) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }

        roleService.save(role)

        return ResponseEntity.ok(role)
    }

    @DeleteMapping("/{id}")
    fun deleteRole(@PathVariable id: Long): ResponseEntity<Void> {
        roleService.deleteById(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}
