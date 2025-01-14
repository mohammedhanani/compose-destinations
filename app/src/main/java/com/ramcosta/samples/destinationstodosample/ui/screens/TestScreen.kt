package com.ramcosta.samples.destinationstodosample.ui.screens

import android.os.Parcelable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.samples.destinationstodosample.ui.screens.profile.SerializableExample
import com.ramcosta.samples.destinationstodosample.ui.screens.profile.Stuff
import kotlinx.parcelize.Parcelize

@Destination
@Composable
fun TestScreen(
    id: String,
    stuff1: Long = 1L,
    stuff2: Stuff?,
    stuff3: Things? = Things(),
    stuff4: SerializableExample? = SerializableExample()
) {
    Text(
        modifier = Modifier.fillMaxSize(),
        textAlign = TextAlign.Center,
        text = "id = $id \n stuff1 = $stuff1 \n stuff2 = $stuff2 \n stuff3 = $stuff3 \n stuff4 = $stuff4"
    )
}

@Parcelize
data class Things(
    val thingyOne: String = "thingy1",
    val thingyTwo: String = "thingy2",
) : Parcelable
