package com.example.exercise2

import androidx.lifecycle.ViewModel
import com.example.exercise2.Data.FormUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.NumberFormat

class FormViewModel: ViewModel() {
    private val _stateUI = MutableStateFlow(FormUIState())
    val stateUI: StateFlow<FormUIState> = _stateUI.asStateFlow()


    fun setform(listProfile: MutableList<String>){
        _stateUI.update { stateSaatIni ->
            stateSaatIni.copy(
                nmhs = listProfile[0],
                nim = listProfile[1],
                kons = listProfile[2],
                jdl = listProfile[3]
            )
        }
    }

    fun setpilihan1(dosenPilihan: String) {
        _stateUI.update { stateSaatIni ->
            stateSaatIni.copy(
                data = dosenPilihan
            )
        }
    }

    fun setpilihan2(dosenPilihan: String) {
        _stateUI.update { stateSaatIni ->
            stateSaatIni.copy(
                data = dosenPilihan
            )
        }
    }

}