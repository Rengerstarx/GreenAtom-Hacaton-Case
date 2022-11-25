package com.example.baseprob

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EdgeEffect
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.baseprob.databinding.ActivityMainBinding
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class AboutUsActivity :AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragmentA(AboutUs())
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.Quiz->replaceFragmentQ(Quiz())
                R.id.Profile->replaceFragmentP(Profile())
                R.id.AboutUs->replaceFragmentA(AboutUs())
                else->{
                }
            }
            true
        }
    }

    fun replaceFragmentA(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.BAZA,fragment)
        fragmentTransaction.commit()
    }
    fun replaceFragmentQ(fragment: Fragment){
        val myIntent = Intent(this@AboutUsActivity, MainActivity::class.java)
        myIntent.putExtra("key", android.R.attr.value) //Optional parameters
        this@AboutUsActivity.startActivity(myIntent)
    }
    fun replaceFragmentP(fragment: Fragment){
        val myIntent = Intent(this@AboutUsActivity, ProfileActivity::class.java)
        myIntent.putExtra("key", android.R.attr.value) //Optional parameters
        this@AboutUsActivity.startActivity(myIntent)
    }
}