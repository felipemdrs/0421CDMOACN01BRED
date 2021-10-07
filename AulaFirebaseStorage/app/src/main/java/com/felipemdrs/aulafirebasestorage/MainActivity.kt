package com.felipemdrs.aulafirebasestorage

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import java.util.*


const val GET_IMAGE_REQUEST = 1

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Adiciona evento de click para abrir o file explorer
        findViewById<Button>(R.id.btnUpload).setOnClickListener {
            val intent = Intent()
            // Filtra todos tipos de imagens
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(intent, GET_IMAGE_REQUEST)
        }

        // Carrega imagem ja armazenada no storage
        loadProfileImageFromStorage()
    }

    private fun loadProfileImageFromStorage() {
        val firebase = FirebaseStorage.getInstance()
        // Pasta onde os arquivos são salvos
        val storage = firebase.getReference("uploads")

        // Obtem o arquivo do Firebase Storage
        storage.child("profile.jpeg").downloadUrl.addOnSuccessListener {
            // Carrega a imagem
            Picasso.get()
                // toString() utilizado para obter URL completa
                .load(it.toString())
                .error(R.drawable.ic_launcher_background)
                .into(findViewById<ImageView>(R.id.imgPreview))
        }
    }

    override fun onActivityResult(request: Int, code: Int, intent: Intent?) {
        super.onActivityResult(request, code, intent)

        // Checa se está tudo OK após selecionar imagem
        if (code == RESULT_OK && request == GET_IMAGE_REQUEST && intent?.data != null) {
            val imageURI = intent.data!!

            imageURI.run {
                // Coloca um preview da imagem selecionada
                findViewById<ImageView>(R.id.imgPreview).setImageURI(this)

                val firebase = FirebaseStorage.getInstance()
                val storage = firebase.getReference("uploads")
                val fileReference = storage.child("profile.jpeg")

                // Envia o arquivo para o Firebase Storage
                fileReference.putFile(this)
                    .addOnSuccessListener {
                        Toast.makeText(this@MainActivity, "Upload com sucesso!", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this@MainActivity, "Upload falhou!", Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }
}