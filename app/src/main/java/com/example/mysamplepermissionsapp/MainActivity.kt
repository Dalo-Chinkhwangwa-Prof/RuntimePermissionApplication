package com.example.mysamplepermissionsapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val requestCode = 777
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(arrayOf("android.permission.ACCESS_FINE_LOCATION"), requestCode)
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf("android.permission.ACCESS_FINE_LOCATION"),
                    requestCode
                )
            }
        } else {
            Log.d("TAG_X", "Permission granted")
        }

        open_settings.setOnClickListener {
            val intent = Intent("android.settings.APPLICATION_SETTINGS")
            startActivity(intent)
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == this.requestCode && permissions[0] == "android.permission.ACCESS_FINE_LOCATION" && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            Toast.makeText(this, "Thank you for granting the permission.", Toast.LENGTH_SHORT)
                .show()


        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    "android.permission.ACCESS_FINE_LOCATION"
                )
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf("android.permission.ACCESS_FINE_LOCATION"),
                    requestCode
                )
            } else {
                Toast.makeText(
                    this,
                    "You need to allow permissions to use this application.",
                    Toast.LENGTH_SHORT
                ).show()

                textview_main.text = "You need to allow location permissions to use this application."
                open_settings.visibility = View.VISIBLE
            }

        }
    }
}