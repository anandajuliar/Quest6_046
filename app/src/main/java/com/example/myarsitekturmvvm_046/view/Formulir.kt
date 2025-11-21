package com.example.myarsitekturmvvm_046.view


import com.example.myarsitekturmvvm_046.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Alignment
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormIsian(
    pilihanJK: List<String>,
    onSubmitButtonClicked: (MutableList<String>) -> Unit,
    modifier: Modifier =Modifier
) {
    var txtNama by rememberSaveable{ mutableStateOf(value = "")}
    var txtAlamat by rememberSaveable{ mutableStateOf(value = "")}
    var txtGender by rememberSaveable{ mutableStateOf(value = "")}
    val listData : MutableList<String> = mutableListOf(txtNama, txtGender,txtAlamat)

    Scaffold (modifier = Modifier,
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.home), color = Color.White) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = colorResource(id = R.color.teal_700)
                )
            )
        }
    ){ isiRuang ->
        Column(
            modifier = Modifier.padding(paddingValues = isiRuang),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            OutlinedTextField(
                value = txtNama,
                singleLine = true,
                modifier = Modifier
                    .padding(top = 20.dp)
                    .width(width = 250.dp),
                label = { Text(text = "Nama Lengkap") },
                onValueChange = {
                    txtNama = it
                }
            )
            HorizontalDivider(
                modifier = Modifier
                    .padding(all = 20.dp)
                    .width(width = 250.dp),
                thickness = 1.dp,
                color = Color.Red
            )
            Row {
                pilihanJK.forEach { item ->
                    Row(verticalAlignment = Alignment.CenterVertically){
                        RadioButton(
                        selected = txtGender == item,
                        onClick = {
                            txtGender = item
                        }
                    )
                        Text(text = item)
                    }
                }
            }
            HorizontalDivider(modifier = Modifier
                .padding(all=20.dp)
                .width(width = 250.dp),
                thickness = 1.dp,
                color= Color.Red
            )
            OutlinedTextField(
                value = txtAlamat,
                singleLine = true,
                modifier = Modifier
                    .width(width = 250.dp),
                label = {Text(text = "Alamat") },
                onValueChange = {
                    txtAlamat = it
                }
            )
            Spacer(modifier = Modifier.height(height = 30.dp))
            Button(
                modifier = Modifier.fillMaxWidth(fraction = 1f)
                    .padding(all=25.dp),
                onClick = {onSubmitButtonClicked(listData)}
            ){
                Text(text = stringResource(id = R.string.submit))
            }
        }
    }
}

