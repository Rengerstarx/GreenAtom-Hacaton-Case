package com.example.baseprob

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.baseprob.databinding.ActivityMainBinding
import android.graphics.Bitmap
import android.provider.MediaStore
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import java.io.IOException

class AboutUsActivity :Activity(){
    private lateinit var binding: ActivityMainBinding
    val GALLERY_REQUEST: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_about_us)
        val button: ImageButton = findViewById<Button>(R.id.buttonGH) as ImageButton
        button.setOnClickListener {
            val photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = "image/*"
            startActivityForResult(photoPickerIntent, GALLERY_REQUEST)
        }

    }

    fun show(view: android.view.View){
        if(findViewById<ConstraintLayout>(R.id.lay).visibility==View.VISIBLE){
            findViewById<ConstraintLayout>(R.id.lay).visibility=View.INVISIBLE
        } else {
            findViewById<ConstraintLayout>(R.id.lay).visibility=View.VISIBLE
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, imageReturnedIntent: Intent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent)
        var bitmap: Bitmap? = null
        val imageView = findViewById<View>(R.id.picture) as ImageView
        when (requestCode) {
            GALLERY_REQUEST -> if (resultCode == RESULT_OK) {
                val selectedImage = imageReturnedIntent.data
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedImage)
                } catch (e: IOException) {
                    e.printStackTrace()
                }

                imageView.setImageBitmap(bitmap)

                imageView.animate().rotation(90F)


            }
        }
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