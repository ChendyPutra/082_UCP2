package com.example.exercise2

import androidx.compose.foundation.layout.padding
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.exercise2.Data.SumberData.data

enum class PengelolaHalaman {
    Home,
    Fomulir,
    Rasa,
    Summary
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormAppBar(
    bisaNavigasiBack: Boolean,
    navigasiUp:() -> Unit,
    modifier: Modifier = Modifier
){
    TopAppBar(
        title = { Text(stringResource(id = R.string.app_name)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        modifier = Modifier,
        navigationIcon = {
            if (bisaNavigasiBack) {
                IconButton(onClick = navigasiUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.btn_back)
                    )
                }
            }
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormApp(
    viewModel: FormViewModel = viewModel(),
    navController: NavHostController =rememberNavController()
){
    Scaffold (
        topBar = {
            FormAppBar(
                bisaNavigasiBack = false,
                navigasiUp = {}
            )
        }
    ){yuhu ->
        val uiState by viewModel.stateUI.collectAsState()

        NavHost(
            navController = navController,
            startDestination = PengelolaHalaman.Home.name,
            modifier = Modifier.padding(yuhu)
        ){

            composable(route = PengelolaHalaman.Home.name){
                HomePage (
                    onNextButtonClicked = {navController.navigate(PengelolaHalaman.Fomulir.name)}
                )
            }


            composable(route = PengelolaHalaman.Rasa.name){
                val context = LocalContext.current
                FormulirPage (
                    pilihanDosen = data.map { id -> context.resources.getString(id)},
                    onSelectionChanged = {viewModel.setpilihan1(it)},
                    onSubmitButtonClicked = {navController.navigate(PengelolaHalaman.Summary.name)},

                )
            }

            composable(route = PengelolaHalaman.Summary.name){
                SummaryPage(
                    FormUIState = uiState,
                    onCancelButtonClicked = {backOrderAndNavigateToRasa(viewModel, navController)}
                )
            }

        }
    }

}

private fun backOrderAndNavigateToHome(
    viewModel : FormViewModel,
    navController: NavHostController
){

}

private fun backOrderAndNavigateToFomulir(
    viewModel : FormViewModel,
    navController: NavHostController
){
    viewModel.resetOrder()
    navController.popBackStack(PengelolaHalaman.Fomulir.name, inclusive = false)
}

private fun backOrderAndNavigateToRasa(
    viewModel : FormViewModel,
    navController: NavHostController
){
    viewModel.resetOrder()
    navController.popBackStack(PengelolaHalaman.Rasa.name, inclusive = false)
}