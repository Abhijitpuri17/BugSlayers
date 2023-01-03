package com.example.bugslayers.view.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.bugslayers.models.Skill

@Composable
fun SkillsList(skillsList: List<Skill>){
    LazyColumn(){
        items(skillsList){
            Text(text = it.skillName)
        }
    }
}