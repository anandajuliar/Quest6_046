package com.example.myarsitekturmvvm_046

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myarsitekturmvvm_046.model.DataJK.JenisJK
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myarsitekturmvvm_046.view.FormIsian
import com.example.myarsitekturmvvm_046.view.TampilData
import com.example.myarsitekturmvvm_046.viewmodel.SiswaViewModel

enum class Navigasi {
    Formulirku,
    Detail
}

@Composable
fun DataApp(
    modifier: Modifier = Modifier,
    viewModel: SiswaViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
){
    Scaffold { isiRuang->
        val uiState = viewModel.statusUI.collectAsState()
        NavHost(
            navController = navController,
            startDestination = Navigasi.Formulirku.name,

            modifier = Modifier.padding(paddingValues = isiRuang)) {
            composable(route = Navigasi.Formulirku.name) {
                val konteks = LocalContext.current
                FormIsian(
                    pilihanJK = JenisJK.map {id -> konteks.resources.getString(id)},
                    onSubmitButtonClicked = {
                        viewModel.setSiswa(it)
                        navController.navigate(route = Navigasi.Detail.name)
                    }
                )
            }
            composable(route = Navigasi.Detail.name) {
                TampilData(
                    statusUiSiswa = uiState.value,
                    OnBackBtnClick =  {
                        cancelAndBackToFormulir(navController)
                    },
                )
            }
        }
    }
}

private fun cancelAndBackToFormulir(
    navController: NavHostController
){
    navController.popBackStack(route = Navigasi.Formulirku.name, inclusive = false)
}