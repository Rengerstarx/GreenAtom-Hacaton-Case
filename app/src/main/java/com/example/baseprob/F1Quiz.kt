package com.example.baseprob

import android.content.Intent
import android.media.session.MediaSession.QueueItem
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EdgeEffect
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.baseprob.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class F1Quiz : AppCompatActivity() {

    var ArrayAnswer= arrayOf("0","0","0","0","0","0","0","0","0","0")
    var len =10
    private lateinit var binding: ActivityMainBinding
    var points: Int=0
    //lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replacer(Quiz_first())
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

   override fun onBackPressed() {
        val myIntent = Intent(this@F1Quiz, MainActivity::class.java)
        myIntent.putExtra("key", android.R.attr.value) //Optional parameters
        this@F1Quiz.startActivity(myIntent)
    }

    fun replacer(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.BAZA,fragment).addToBackStack(null)
        fragmentTransaction.commit()
    }

    fun replaceFragmentQ(fragment: Fragment){
        val myIntent = Intent(this@F1Quiz, MainActivity::class.java)
        myIntent.putExtra("key", android.R.attr.value) //Optional parameters
        this@F1Quiz.startActivity(myIntent)
    }
    fun replaceFragmentA(fragment: Fragment){
        val myIntent = Intent(this@F1Quiz, ProfileActivity::class.java)
        myIntent.putExtra("key", android.R.attr.value) //Optional parameters
        this@F1Quiz.startActivity(myIntent)
    }
    fun replaceFragmentP(fragment: Fragment){
        val myIntent = Intent(this@F1Quiz, AboutUsActivity::class.java)
        myIntent.putExtra("key", android.R.attr.value) //Optional parameters
        this@F1Quiz.startActivity(myIntent)
    }

    fun Cheker(view: View){
        if(view.id==findViewById<CheckBox>(R.id.Ques1).id){
            findViewById<CheckBox>(R.id.Ques2).isChecked=false
            ArrayAnswer[0]="1"
        } else if(view.id==findViewById<CheckBox>(R.id.Ques2).id){
            findViewById<CheckBox>(R.id.Ques1).isChecked=false
            ArrayAnswer[0]="0"
        } else if(view.id==findViewById<CheckBox>(R.id.Ques3).id){
            findViewById<CheckBox>(R.id.Ques4).isChecked=false
            ArrayAnswer[1]="0"
        } else if(view.id==findViewById<CheckBox>(R.id.Ques4).id){
            findViewById<CheckBox>(R.id.Ques3).isChecked=false
            ArrayAnswer[1]="1"
        }

    }

    fun Load (view: View){
        var k=0
        points=0
        while(k<len){
            if(ArrayAnswer[k]=="1"){
                points+=1
            }
            k+=1
        }
        Firebase.database.getReference("All").child(Firebase.auth.currentUser?.email.toString()).child("Квиз №1").setValue(points)
        k=0
        while(k<len){
            ArrayAnswer[k]="0"
            k+=1
        }
    }

}