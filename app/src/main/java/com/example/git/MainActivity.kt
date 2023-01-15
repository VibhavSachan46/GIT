package com.example.git

import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        btnApply.setOnClickListener{
//
//            val name = etName.text.toString()
//            val age = etAge.text.toString().toInt()
//            val country = etCountry.text.toString()
//            val person = Person(name,age,country)
//            Intent(this, SecondActivity::class.java).also{
//                it.putExtra("EXTRA_PERSON",person)
//                startActivity(it)
//            }
//        }

        btnRequest.setOnClickListener {
            requestPermission()
        }

    }

    private fun hasWriteExternalStoragePermission() =
        ActivityCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED

    private fun hasLocationForegroundPermission() =
        ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED

    private fun hasLocationBackgroundPermission() =
        ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_BACKGROUND_LOCATION) == PackageManager.PERMISSION_GRANTED


    private fun requestPermission(){
        var permissionsTorequest = mutableListOf<String>()

        if(!hasWriteExternalStoragePermission())
        {
            permissionsTorequest.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }

        if(!hasLocationForegroundPermission())
        {
            permissionsTorequest.add(android.Manifest.permission.ACCESS_COARSE_LOCATION)
        }

        if(!hasLocationBackgroundPermission())
        {
            permissionsTorequest.add(android.Manifest.permission.ACCESS_BACKGROUND_LOCATION)
        }

        if(permissionsTorequest.isNotEmpty())
        {
            ActivityCompat.requestPermissions(this,permissionsTorequest.toTypedArray(),0)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 0 && grantResults.isNotEmpty())
        {
            for(i in grantResults.indices){
                if(grantResults[i] == PackageManager.PERMISSION_GRANTED)
                {
                    Log.d("PermissionsRequest", "${permissions[i]} granted.")
                }
            }
        }
    }
}
