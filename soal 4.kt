package com.example.soalb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.soalb.ui.theme.SoalBTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SoalBTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    JadwalKuliahScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

data class JadwalKuliah(
    val waktu: String,
    val matkul: String,
    val ruang: String,
)

@Composable
fun JadwalKuliahScreen(modifier: Modifier = Modifier) {
    val hariList = listOf("Senin", "Selasa", "Rabu", "Kamis")
    var hariDipilih by remember { mutableStateOf("Senin") }

    val listJadwal = listOf(
        JadwalKuliah("08.00 - 09.40", "Pemrograman Mobile", "Ruang Lab 2"),
        JadwalKuliah("10.00 - 11.40", "Basis Data", "Ruang 304"),
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        
        Text(
            text = "Jadwal Kuliah",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally),
        )

        Spacer(modifier = Modifier.height(12.dp))

        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            hariList.forEach { hari ->
                FilterChip(
                    selected = hari == hariDipilih,
                    onClick = { hariDipilih = hari },
                    label = { Text(hari) },
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize(),
        ) {
            items(
                items = listJadwal,
                key = { it.matkul },
            ) { item ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = item.waktu)
                        Text(
                            text = item.matkul,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.bodyLarge,
                        )
                        Text(text = item.ruang)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun JadwalKuliahScreenPreview() {
    SoalBTheme {
        JadwalKuliahScreen()
    }
}
