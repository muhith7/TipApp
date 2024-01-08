package com.example.tipapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tipapp.components.InputField
import com.example.tipapp.ui.theme.TipAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                //TopHeader()
                MainConten()
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit){
    TipAppTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colorScheme.background) {
            content()
            //ScreenDemo(model = CounterViewModel())
        }
    }
}

@Composable
fun TopHeader(totalPerPerson :Double = 0.0) {
    Surface (modifier = Modifier
        .fillMaxWidth()
        .height(150.dp)
        .clip(shape = CircleShape.copy(all = CornerSize(12.dp))),
        color = Color(0xFFE9D7F7)
        //.clip(shape = RoundedCornerShape(corner = CornerSize(12.dp)))
    ) {
        val total = "%.2f".format(totalPerPerson)
        Column (
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Text(text = "Total Per Person",
                style = MaterialTheme.typography.labelLarge
            )
            Text(text = "Rp$total",
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.ExtraBold)

        }
    }
}


@OptIn(ExperimentalComposeUiApi::class)
@Preview
@Composable
fun MainConten() {
    BillForm(){billAmt ->
        Log.d("AMT", "MainConten: $billAmt")
    }

}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BillForm (modifier : Modifier = Modifier,
              onValChange: (String) -> Unit = {}
){
    val totalBillState = remember {
        mutableStateOf("")
    }
    val validState = remember(totalBillState.value) {
        totalBillState.value.trim().isNotEmpty()
    }

    val keyboardController = LocalSoftwareKeyboardController.current

    Surface ( modifier = Modifier
        .padding(2.dp)
        .fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
        border = BorderStroke(width = 1.dp, color = Color.LightGray)
    ){
        Column {
            InputField(valueState = totalBillState,
                labelId = "Enter Bill",
                enabled = true,
                isSingleLine = true,
                onAction = KeyboardActions{
                    if (!validState) return@KeyboardActions
                    onValChange(totalBillState.value.trim())


                    keyboardController?.hide()
                })
        }
    }

}




















//@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TipAppTheme {
        MyApp (){
        }
    }
}