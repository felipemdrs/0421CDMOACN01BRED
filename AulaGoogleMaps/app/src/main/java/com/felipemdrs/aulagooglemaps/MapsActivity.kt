package com.felipemdrs.aulagooglemaps

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.felipemdrs.aulagooglemaps.databinding.ActivityMapsBinding
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.Marker
import kotlin.random.Random

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        for (i in 1..300) {
            val meuRoteador = LatLng((-80..80).random().toDouble(), (-80..80).random().toDouble())
            val marker = mMap.addMarker(MarkerOptions().position(meuRoteador)
                .title("Meu roteador"))

            marker.isDraggable = true

            if (i%2 == 0) {
                marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN))
            }
        }

        mMap.setOnMarkerDragListener(object: GoogleMap.OnMarkerDragListener {
            override fun onMarkerDragStart(p0: Marker) {
            }

            override fun onMarkerDrag(p0: Marker) {
            }

            override fun onMarkerDragEnd(marker: Marker) {
                Toast.makeText(this@MapsActivity, marker.position.toString(), Toast.LENGTH_LONG).show()
            }
        })

        // Add a marker in Sydney and move the camera
        val meuRoteador = LatLng(-23.6283, -46.6409)
        mMap.addMarker(MarkerOptions().position(meuRoteador)
            .title("Meu roteador"))

        mMap.moveCamera(CameraUpdateFactory.newLatLng(meuRoteador))

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            mMap.isMyLocationEnabled = true
        } else {
            requestFineLocationAccess()
        }
    }

    fun IntRange.random() =
        Random.nextInt((endInclusive + 1) - start) + start

    val FINE_LOCATION_REQUEST_CODE = 100

    private fun requestFineLocationAccess() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            val permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
            ActivityCompat.requestPermissions(this, permissions, FINE_LOCATION_REQUEST_CODE)
        }
    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == FINE_LOCATION_REQUEST_CODE) {
            if (grantResults.all { result -> result == PackageManager.PERMISSION_GRANTED}) {
                mMap.isMyLocationEnabled = true
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}