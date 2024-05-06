package com.sercapcab.pathfinder.mvc.model.entity

import com.sercapcab.pathfinder.Since
import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import java.util.UUID

@Entity
@Table(name = "unit_stats_template")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Since(version = "1.0")
data class UnitStat(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val uuid: UUID,

    @Column(name = "strength")
    var strength: Int,

    @Column(name = "dexterity")
    var dexterity: Int,

    @Column(name = "constitution")
    var constitution: Int,

    @Column(name = "intelligence")
    var intelligence: Int,

    @Column(name = "wisdom")
    var wisdom: Int,

    @Column(name = "charisma")
    var charisma: Int,

    @Column(name = "comment")
    var comment: String,

    @OneToMany(mappedBy = "unitStat",
        fetch = FetchType.LAZY)
    var units: Set<Unit> = setOf()
)
