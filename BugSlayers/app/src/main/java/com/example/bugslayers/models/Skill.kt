package com.example.bugslayers.models

data class Skill(
    val skillId: Int,
    val skillName: String,
    val domainId: Int,
    val skillLevel: Int,
    val yearsOfExperience: Int,
    val endorsementList: List<Int>
)
