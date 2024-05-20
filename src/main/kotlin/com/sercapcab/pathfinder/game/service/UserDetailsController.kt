package com.sercapcab.pathfinder.game.service

import com.sercapcab.pathfinder.Since
import com.sercapcab.pathfinder.game.config.json
import lombok.AllArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

//@RestController
//@AllArgsConstructor
//@RequestMapping("/api/v1/user")
//@Since(version = "1.0")
//class UserDetailsController(@Autowired private val userDetailsServiceImpl: UserDetailsServiceImpl) {
//    @GetMapping("{user}", produces = [json])
//    fun getUser(@PathVariable user: String): ResponseEntity<UserDetails> {
//        val userd = userDetailsServiceImpl.loadUserByUsername(user)
//
//        return ResponseEntity.ok(userd)
//    }
//}