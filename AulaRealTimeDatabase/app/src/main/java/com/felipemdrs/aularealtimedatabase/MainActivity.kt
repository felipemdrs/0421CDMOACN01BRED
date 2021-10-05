package com.felipemdrs.aularealtimedatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

data class Endereco (
    val rua: String? = null,
    val numero: Int? = null,
    val bairro: String? = null
) {}


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = Firebase.database
        val userConfig = database.getReference("message").child("config")

        val edtNome = findViewById<EditText>(R.id.edtNome)
        val btn = findViewById<Button>(R.id.button)
        val txtNomeAtual = findViewById<TextView>(R.id.txtNomeAtual)

        btn.setOnClickListener {
            userConfig.setValue(edtNome.text.toString())
            Toast.makeText(this, "Configurações Salvas", Toast.LENGTH_SHORT).show()
        }

        userConfig.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val value = snapshot.getValue<String>()
                txtNomeAtual.text = value
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@MainActivity, "Ocorreu um erro", Toast.LENGTH_SHORT).show()
                Log.w("TAG", "Failed to read value.", error.toException())
            }
        })
    }
}