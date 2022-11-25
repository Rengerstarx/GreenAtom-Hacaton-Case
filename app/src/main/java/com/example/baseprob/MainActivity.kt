package com.example.baseprob

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EdgeEffect
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.baseprob.databinding.ActivityMainBinding
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragmentQ(Quiz())
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

    fun replaceFragmentQ(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.BAZA,fragment)
        fragmentTransaction.commit()
    }
    fun replaceFragmentA(fragment: Fragment){
        val myIntent = Intent(this@MainActivity, AboutUsActivity::class.java)
        myIntent.putExtra("key", android.R.attr.value) //Optional parameters
        this@MainActivity.startActivity(myIntent)
    }
    fun replaceFragmentP(fragment: Fragment){
        val myIntent = Intent(this@MainActivity, ProfileActivity::class.java)
        myIntent.putExtra("key", android.R.attr.value) //Optional parameters
        this@MainActivity.startActivity(myIntent)
    }

    fun mainka(view: View){
        supportFragmentManager.beginTransaction().replace(R.id.BAZA, QuizMainPage()).addToBackStack(null).commit()
        
    }

    fun onClick (view: android.view.View){
        var database = Firebase.database
    }

}