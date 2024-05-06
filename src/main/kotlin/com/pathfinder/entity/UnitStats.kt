package com.pathfinder.entity

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
data class UnitStats(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private val uuid: UUID,

    @Column(name = "strength")
    private var strength: Int,

    @Column(name = "dexterity")
    private var dexterity: Int,

    @Column(name = "constitution")
    private var constitution: Int,

    @Column(name = "intelligence")
    private var intelligence: Int,

    @Column(name = "wisdom")
    private var wisdom: Int,

    @Column(name = "charisma")
    private var charisma: Int,

    @Column(name = "comment")
    private var comment: String,

    @OneToMany(mappedBy = "stats")
    private var units: Set<Unit>
)
