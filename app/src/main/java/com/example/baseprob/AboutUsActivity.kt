package com.example.baseprob

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import android.graphics.Bitmap
import android.provider.MediaStore
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.io.IOException

class AboutUsActivity :Activity(){
    val GALLERY_REQUEST: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_about_us)

    }

    fun show(view: android.view.View){
        if(findViewById<ConstraintLayout>(R.id.lay).visibility==View.VISIBLE){
            findViewById<ConstraintLayout>(R.id.lay).visibility=View.INVISIBLE
        } else {
            findViewById<ConstraintLayout>(R.id.lay).visibility=View.VISIBLE
        }
    }

    fun createnewUser(view: View){
        val fio=findViewById<EditText>(R.id.textView15).text
        val birthday=findViewById<EditText>(R.id.textView16).text
        val how=findViewById<EditText>(R.id.textView17).text
        val city=findViewById<EditText>(R.id.textView18).text
        Firebase.database.getReference("Users").child(fio.toString()).child("Birthday").setValue(birthday.toString())
        Firebase.database.getReference("Users").child(fio.toString()).child("Reasons").setValue(how.toString())
        Firebase.database.getReference("Users").child(fio.toString()).child("City").setValue(city.toString())
    }


    fun replaceFragmentQ(view: android.view.View){
        val myIntent = Intent(this@AboutUsActivity, MainActivity::class.java)
        myIntent.putExtra("key", android.R.attr.value) //Optional parameters
        this@AboutUsActivity.startActivity(myIntent)
    }
    fun replaceFragmentP(view: android.view.View){
        val myIntent = Intent(this@AboutUsActivity, ProfileActivity::class.java)
        myIntent.putExtra("key", android.R.attr.value) //Optional parameters
        this@AboutUsActivity.startActivity(myIntent)
    }
}