package com.example.bugslayers.view.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bugslayers.R
import com.example.bugslayers.models.Skill
import com.example.bugslayers.view.components.SkillsList

@Composable
fun Profile(){
    Column {
        Card(elevation = 16.dp,
            backgroundColor = Color.White,
            modifier = Modifier.padding(top = 2.dp, bottom = (0.5).dp, start = 6.dp, end=6.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp),
            ) {
                Box(modifier = Modifier.weight(0.5f)) {
                    Image(painter = painterResource(id = R.drawable.cartoon_avatar),
                        contentScale = ContentScale.FillBounds,
                        contentDescription = null,
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(150.dp)
                            .border(
                                width = 1.dp, shape = CircleShape,
                                color = Color.Gray.copy(alpha = 0.6f)
                            )
                    )
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Default.Edit,
                            contentDescription = null,
                            modifier = Modifier.offset(x = 100.dp, y=100.dp)
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .weight(0.5f)
                        .height(150.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "Abhijit Puri",
                        fontWeight = FontWeight.Black,
                        fontSize = 24.sp,
                        color = Color(0xff41454f)
                    )
                    Text(text = "puriabhijit000@gmail.com", fontSize = 14.sp)
                }
            }
        }
        //Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.padding(top=4.dp))

        Card(
            elevation = 16.dp,
            backgroundColor = Color.White,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 2.dp, bottom = (0.5).dp, start = 6.dp, end = 6.dp)
        ) {

            Column() {
                Text(text = "Skills",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 30.sp,
                    modifier = Modifier.padding(8.dp),
                    color = Color(0xff41454f)
                )


                var skills = listOf<Skill>(
                    Skill(1, "Java",
                        1, 1,
                        1,
                        listOf(1,2,3)
                    ),
                    Skill(1, "DSA",
                        1, 1,
                        1,
                        listOf(1,2,3)
                    ),
                    Skill(1, "Android Development",
                        1, 1,
                        1,
                        listOf(1,2,3)
                    ),
                    Skill(1, "JSP and Servlets",
                        1, 1,
                        1,
                        listOf(1,2,3)
                    ),
                    Skill(1, "Hibernate",
                        1, 1,
                        1,
                        listOf(1,2,3)
                    ),
                    Skill(1, "OOPS",
                        1, 1,
                        1,
                        listOf(1,2,3)
                    )
                )

                SkillsList(skillsList = skills)
            }

        }

    }
    
}