package com.example.baseprob

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.baseprob.databinding.ActivityMainBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity(), QuizzAdapter.Listener {

    lateinit var binding: ActivityMainBinding
    private val adapter = QuizzAdapter(this)
    private val imagemas = listOf(R.drawable.quizz1,R.drawable.quizz2,R.drawable.quizz3,R.drawable.quizz4,R.drawable.quizz5)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()

    }

    private fun init(){
        binding.apply {
            rsView.layoutManager = LinearLayoutManager(this@MainActivity)
            rsView.adapter = adapter
            for (i in imagemas.indices){
                val quizz = Quizz(imagemas[i], "quizz $i",i+1)
                adapter.addquizz(quizz)
            }
            for (i in imagemas.indices){
                val quizz = Quizz(imagemas[i], "quizz $i",i+1)
                adapter.addquizz(quizz)
            }

        }
    }

    override fun onClick(quizz: Quizz) {
        val myIntent = Intent(this@MainActivity, Quizzes::class.java)
        myIntent.putExtra("key", "Квиз №"+quizz.id.toString()) //Optional parameters
        this@MainActivity.startActivity(myIntent)
    }




    fun replaceFragmentA(view: android.view.View){
        val myIntent = Intent(this@MainActivity, AboutUsActivity::class.java)
        myIntent.putExtra("key", android.R.attr.value) //Optional parameters
        this@MainActivity.startActivity(myIntent)
    }
    fun replaceFragmentP(view: android.view.View){
        val myIntent = Intent(this@MainActivity, ProfileActivity::class.java)
        myIntent.putExtra("key", android.R.attr.value) //Optional parameters
        this@MainActivity.startActivity(myIntent)
    }

    fun show(view: android.view.View){
        if(findViewById<ConstraintLayout>(R.id.lay).visibility==View.VISIBLE){
            findViewById<ConstraintLayout>(R.id.lay).visibility=View.INVISIBLE
        } else {
            findViewById<ConstraintLayout>(R.id.lay).visibility=View.VISIBLE
        }
    }






}