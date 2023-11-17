package com.example.exercise2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun FormulirPage (
    pilihanDosen: List<String>,
    onSelectionChanged: (String) -> Unit,
    onSubmitButtonClicked: (MutableList<String>) -> Unit,
    modifier: Modifier = Modifier
) {
    var dataygdipilih by rememberSaveable { mutableStateOf("") }
    var nama by remember { mutableStateOf("") }
    var nim by remember { mutableStateOf("") }
    var konsen by remember { mutableStateOf("") }
    var judul by remember { mutableStateOf("") }
    var listData: MutableList<String> = mutableListOf(nama,nim,konsen,judul)

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier =
            Modifier.padding(dimensionResource(R.dimen.padding_medium))
        ) {
            OutlinedTextField(value = nama, onValueChange ={nama=it}, label = { Text(text = "Nama Mahasiswa")} )
            OutlinedTextField(value = nim, onValueChange ={nim=it}, label = { Text(text = "NIM")} )
            OutlinedTextField(value = konsen, onValueChange ={konsen=it}, label = { Text(text = "Konsentrasi")} )
            OutlinedTextField(value = judul, onValueChange ={judul=it}, label = { Text(text = "Judul Skripsi")} )

            pilihanDosen.forEach { item ->
                Row(modifier = Modifier.selectable(
                    selected = dataygdipilih == item,
                    onClick = {
                        dataygdipilih = item
                        onSelectionChanged(item)

                    }

                ),
                    verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(selected = dataygdipilih == item,
                        onClick = {
                            dataygdipilih = item
                            onSelectionChanged(item)
                        }
                    )
                    Text(item)
                }
            }
            Divider(
                thickness = dimensionResource(R.dimen.thickness_divider),
                modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_medium))
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_medium))
                    .weight(1f, false),
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
            ) {

            }
            Divider(
                thickness = dimensionResource(R.dimen.thickness_divider),
                modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_medium))
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_medium))
                    .weight(1f, false),
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
                verticalAlignment = Alignment.Bottom
            ) {
                }
                Button(onClick = { onSubmitButtonClicked(listData)}) {
                    Text(text = stringResource(id = R.string.btn_submit))

                }
            }
        }
    }

