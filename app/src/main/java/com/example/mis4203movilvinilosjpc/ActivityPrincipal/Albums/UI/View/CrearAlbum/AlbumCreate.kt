package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.UI.View.CrearAlbum

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.Modelo.DataItemRecordlabel
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.Modelo.DataItemsGeneros
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.UI.ViewModel.CreateAlbumsViewModel
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Artistas.UI.ViewModel.ArtistaViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlbumCreate(
    createAlbumsViewModel: CreateAlbumsViewModel,
    navController: NavController,
    artistaViewModel: ArtistaViewModel,
) {

    val nombreAlbum by createAlbumsViewModel.nombreAlbum.collectAsState("")
    val añoLanzamiento by createAlbumsViewModel.añoLanzamiento.collectAsState("")
    val descripcion by createAlbumsViewModel.descriptionAlbum.collectAsState("")
    val artista by createAlbumsViewModel.artistaCreate.collectAsState("")
    val genero by createAlbumsViewModel.generoAlbum.collectAsState("")
    val recordLabel by createAlbumsViewModel.recordLabel.collectAsState("")

    val isButtonEnable by createAlbumsViewModel.enableButtonCreateAlbum.observeAsState(false)


    val enableButtonBackStack by createAlbumsViewModel.enableButtonBackStack.observeAsState(true)

    val listArtistas by artistaViewModel.artistasLoadingState.collectAsState()
    var listadoName by remember { mutableStateOf(listOf<String>()) }

    when (val loadingState = listArtistas) {
        is ArtistaViewModel.LoadingStateArtist.Error -> TODO()
        ArtistaViewModel.LoadingStateArtist.Loading -> TODO()
        is ArtistaViewModel.LoadingStateArtist.Success -> {
            var listado = loadingState.data

            //sacar un listao de name de artistas
            listadoName = listado?.map { it.name } ?: listOf()


        }

    }

    Scaffold(topBar = {
        TopAppBar(colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ), navigationIcon = {
            IconButton(enabled = enableButtonBackStack, onClick = {
                createAlbumsViewModel.enableButton()
                navController.popBackStack()
            }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Imagen del menu del drawer"
                )
            }
        }, title = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = "Crear Album"
            )
        })
    }) {
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                item {
                    CardEditable(
                        datos = "Nombre",
                        habilitadorEditrtext = true,
                        textEdit = nombreAlbum,
                        listadoName = emptyList(),
                        dataPicker = false
                    ) {
                        createAlbumsViewModel.onDatosChangeCreateAlbum(
                            nombreAlbum = it,
                            artista = artista,
                            año = añoLanzamiento,
                            genero = genero,
                            recordLabel = recordLabel,
                            description = descripcion
                        )
                    }
                }
                item {
                    CardEditable(
                        datos = "Artista",
                        habilitadorEditrtext = false,
                        textEdit = "",
                        listadoName = listadoName,
                        dataPicker = false

                    ) {
                        createAlbumsViewModel.onDatosChangeCreateAlbum(
                            nombreAlbum = nombreAlbum,
                            artista = it,
                            año = añoLanzamiento,
                            genero = genero,
                            recordLabel = recordLabel,
                            description = descripcion
                        )
                    }
                }
                // item { cardItems() }
                item {
                    val listadoGenero = DataItemsGeneros(0)
                    CardEditable(
                        datos = "Genero",
                        habilitadorEditrtext = false,
                        textEdit = "",
                        listadoName = listadoGenero.name,
                        dataPicker = false
                    ) {
                        createAlbumsViewModel.onDatosChangeCreateAlbum(
                            nombreAlbum = nombreAlbum,
                            artista = artista,
                            año = añoLanzamiento,
                            genero = it,
                            recordLabel = recordLabel,
                            description = descripcion
                        )
                    }
                }

                item {
                    val listadoRecordLabel = DataItemRecordlabel(0)
                    CardEditable(
                        datos = "Record Label",
                        habilitadorEditrtext = false,
                        textEdit = " ",
                        listadoName = listadoRecordLabel.name,
                        dataPicker = false
                    ) {
                        createAlbumsViewModel.onDatosChangeCreateAlbum(
                            nombreAlbum = nombreAlbum,
                            artista = artista,
                            año = añoLanzamiento,
                            genero = genero,
                            recordLabel = it,
                            description = descripcion
                        )
                    }
                }

                item {
                    EditFechaLanzamiento(
                        datos = "Año de lanzamiento",
                        textEdit = añoLanzamiento
                    ) {
                        createAlbumsViewModel.onDatosChangeCreateAlbum(
                            nombreAlbum = nombreAlbum,
                            artista = artista,
                            año = it,
                            genero = genero,
                            recordLabel = recordLabel,
                            description = descripcion
                        )
                    }

                }
                item {
                    CardEditable(
                        datos = "Descripción",
                        habilitadorEditrtext = true,
                        textEdit = descripcion,
                        listadoName = emptyList(),
                        dataPicker = false
                    ) {
                        createAlbumsViewModel.onDatosChangeCreateAlbum(
                            nombreAlbum = nombreAlbum,
                            artista = artista,
                            año = añoLanzamiento,
                            genero = genero,
                            recordLabel = recordLabel,
                            description = it
                        )
                    }
                }
            }
            btnCrearAlbum(
                modifier = Modifier.align(Alignment.BottomCenter),
                createAlbumsViewModel,
                isButtonEnable, navController
            )
        }
    }
}

@Composable
fun CardEditable(
    datos: String, // El nombre del editor de texto
    habilitadorEditrtext: Boolean, // Decide si es editor de texto libre o con dropdown
    textEdit: String,
    listadoName: List<String>,
    dataPicker: Boolean, // Indica si es un campo de fecha
    onCommentChange: (String) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) } // Dropdown menu
    var selectText by remember { mutableStateOf("") } // Selección de la lista de dropdown menu
    var showDatePicker by remember { mutableStateOf(false) } // Controla la visibilidad del DatePicker
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        modifier = Modifier.fillMaxWidth()
    ) {

        if (habilitadorEditrtext) {
            OutlinedTextField(
                value = textEdit,
                onValueChange = { onCommentChange(it) },
                label = { Text(datos) },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hide()
                    }
                ),
                readOnly = dataPicker, // Si es un campo de fecha, hazlo de solo lectura
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .clickable {
                        if (dataPicker) {
                            showDatePicker = true
                        }
                    }
            )
        } else { // EditText con dropdown
            Column(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = selectText,
                    onValueChange = { selectText = it },
                    label = { Text(datos) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    enabled = false,
                    readOnly = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .clickable { expanded = !expanded }
                )
                // Dropdown menu
                DropdownMenu(
                    expanded = expanded, // True o false
                    onDismissRequest = { expanded = false }, // Cierra el menú
                    modifier = Modifier.fillMaxWidth()
                ) {
                    listadoName.forEach { name ->
                        DropdownMenuItem(
                            text = { Text(text = name) },
                            onClick = {
                                expanded = false
                                selectText = name
                                onCommentChange(name)
                            }
                        )
                    }
                }
            }
        }
    }

    Spacer(modifier = Modifier.padding(vertical = 10.dp))
}

@Composable
fun EditFechaLanzamiento(datos: String, textEdit: String, onCommentChange: (String) -> Unit) {

    var showDatePicker by remember { mutableStateOf(false) } // Controla la visibilidad del DatePicker
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
            showDatePicker = true
        }
    ) {
        OutlinedTextField(
            value = textEdit,
            onValueChange = { onCommentChange(it) },
            label = { Text(datos) },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                }
            ),
            enabled = false,
            readOnly = true, // Si es un campo de fecha, hazlo de solo lectura
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)

        )
    }
    if (showDatePicker) {
        val calendar = Calendar.getInstance()
        DatePickerDialog(
            context,
            { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                val selectedDate = "$dayOfMonth/${month + 1}/$year"
                onCommentChange(selectedDate)
                showDatePicker = false
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }
}



@Composable
fun btnCrearAlbum(
    modifier: Modifier,
    createAlbumsViewModel: CreateAlbumsViewModel,
    isButtonEnable: Boolean,
    navController: NavController,
) {
    Button(
        onClick = { createAlbumsViewModel.onCreateAlbum(navController) },
        modifier = modifier.fillMaxWidth(),
        enabled = isButtonEnable
    ) {
        Text(text = "Crear Album")
    }
}
