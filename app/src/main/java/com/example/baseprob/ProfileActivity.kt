package com.example.baseprob

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import android.net.Uri
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout


class ProfileActivity : AppCompatActivity(){

    var Tx0: TextView? = null
    var men: ImageButton?=null
    var Im2: ImageView? = null
    var Tx1: TextView? = null
    var Tx2: TextView? = null
    var ImB1: ImageButton? = null
    var ImB2: ImageButton? = null
    var ImB3: ImageButton? = null
    var ImB4: ImageButton? = null
    var ImB5: ImageButton? = null
    var Scrol: ConstraintLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_profile)


    }

    fun click_vk(view: android.view.View) {
        intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/rosatomru"))
        startActivity(intent)
    }


    fun click_telega(view: android.view.View) {
        intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/rosatomru"))
        startActivity(intent)
    }

    fun click_gmail(view: android.view.View) {
        Tx2=findViewById(R.id.gmail_addres)
        Tx2?.visibility=if(Tx2?.visibility==View.INVISIBLE)
            View.VISIBLE
        else
            View.INVISIBLE
    }

    fun click_gallery(view: android.view.View) {
        Tx0=findViewById(R.id.text_compnay2)
        Im2=findViewById(R.id.rosatom_logo)
        Tx1=findViewById(R.id.text_compnay2)
        Tx2=findViewById(R.id.gmail_addres)
        ImB1=findViewById(R.id.vkButton)
        ImB2=findViewById(R.id.telegaLogo)
        ImB3=findViewById(R.id.gmailButton)
        ImB4=findViewById(R.id.back_Button)
        ImB5=findViewById(R.id.gallery_button)
        men=findViewById(R.id.perehod2)
        Scrol=findViewById<ConstraintLayout>(R.id.scroll_Gallery)
        findViewById<ConstraintLayout>(R.id.lay).visibility=View.INVISIBLE

        Scrol?.visibility = View.VISIBLE
        ImB4?.visibility = View.VISIBLE
        ImB3?.visibility = View.INVISIBLE
        ImB2?.visibility = View.INVISIBLE
        ImB1?.visibility = View.INVISIBLE
        Tx0?.visibility = View.INVISIBLE
        Im2?.visibility = View.INVISIBLE
        ImB5?.visibility = View.INVISIBLE
        Tx1?.visibility = View.INVISIBLE
        men?.visibility = View.INVISIBLE
    }

    fun click_back(view: android.view.View) {
        Tx0=findViewById(R.id.text_compnay2)
        Im2=findViewById(R.id.rosatom_logo)
        Tx1=findViewById(R.id.text_compnay2)
        Tx2=findViewById(R.id.gmail_addres)
        ImB1=findViewById(R.id.vkButton)
        ImB2=findViewById(R.id.telegaLogo)
        ImB3=findViewById(R.id.gmailButton)
        ImB4=findViewById(R.id.back_Button)
        ImB5=findViewById(R.id.gallery_button)
        Scrol=findViewById<ConstraintLayout>(R.id.scroll_Gallery)
        men=findViewById(R.id.perehod2)

        Scrol?.visibility = View.INVISIBLE
        ImB4?.visibility = View.INVISIBLE
        ImB3?.visibility = View.VISIBLE
        ImB2?.visibility = View.VISIBLE
        ImB1?.visibility = View.VISIBLE
        Tx0?.visibility = View.VISIBLE
        Im2?.visibility = View.VISIBLE
        ImB5?.visibility = View.VISIBLE
        Tx1?.visibility = View.VISIBLE
        men?.visibility = View.VISIBLE
    }


    fun replaceFragmentQ(view: android.view.View){
        val myIntent = Intent(this@ProfileActivity, MainActivity::class.java)
        myIntent.putExtra("key", android.R.attr.value) //Optional parameters
        this@ProfileActivity.startActivity(myIntent)
    }
    fun replaceFragmentA(view: android.view.View){
        val myIntent = Intent(this@ProfileActivity, AboutUsActivity::class.java)
        myIntent.putExtra("key", android.R.attr.value) //Optional parameters
        this@ProfileActivity.startActivity(myIntent)
    }

    fun show(view: android.view.View){
        if(findViewById<ConstraintLayout>(R.id.lay).visibility==View.VISIBLE){
            findViewById<ConstraintLayout>(R.id.lay).visibility=View.INVISIBLE
        } else {
            findViewById<ConstraintLayout>(R.id.lay).visibility=View.VISIBLE
        }
    }

}