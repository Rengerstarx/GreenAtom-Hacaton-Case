package com.example.baseprob

import android.R.attr.password
import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso


class Quizzes : AppCompatActivity() {
    var s=0
    var len =10
    var points=0
    var qwn=""
    var kik=""
    var k=""
    var auth: FirebaseAuth?=null
    lateinit var picQ : ImageView
    var imgname=""
    var qnum=""
    var photonum=1
    lateinit var REF_STORAGE_ROOT: StorageReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_quiz_first)
        REF_STORAGE_ROOT = FirebaseStorage.getInstance().reference
        picQ = findViewById(R.id.picQ)
        qwn=intent.getStringExtra("key").toString()
        qnum=intent.getStringExtra("key3").toString()
        kik=intent.getStringExtra("key2").toString()
        auth = FirebaseAuth.getInstance()
        start()
    }



    fun crate(view: View){
        Firebase.database.getReference("Quiz").child(qwn).child("Code"+(s+2).toString()).get().addOnSuccessListener {
            k=it.value.toString()
        }
        if(k=="1" && findViewById<CheckBox>(R.id.Ques1).isChecked==true){
            points+=1
        } else if(k=="2" && findViewById<CheckBox>(R.id.Ques2).isChecked==true){
            points+=1
        }else if(k=="3" && findViewById<CheckBox>(R.id.Ques3).isChecked==true){
            points+=1
        }else if(k=="4" && findViewById<CheckBox>(R.id.Ques4).isChecked==true){
            points+=1
        }
        s+=1
        if(s==len){
            findViewById<TextView>(R.id.result).visibility=View.VISIBLE
            findViewById<ImageButton>(R.id.load).visibility=View.VISIBLE
            findViewById<ImageButton>(R.id.telegaLogo3).visibility=View.VISIBLE
            findViewById<ImageButton>(R.id.vkButton3).visibility=View.VISIBLE
            findViewById<ImageButton>(R.id.adroid_app).visibility=View.VISIBLE
            findViewById<ConstraintLayout>(R.id.lay).visibility=View.INVISIBLE
            findViewById<ImageView>(R.id.imageView3).visibility=View.VISIBLE
            findViewById<TextView>(R.id.textView10).visibility=View.VISIBLE
            findViewById<TextView>(R.id.result).text="Вы набрали "+points.toString()+"/10 за прохождение квиза. Можете оставить свой отзыв в приложении или написать нам в одну из соц сетей"
        }else{
            Firebase.database.getReference("Quiz").child(qwn).child("q"+(s+1).toString()).get().addOnSuccessListener {
                findViewById<TextView>(R.id.texQ).text=it.value.toString()
            }
            Firebase.database.getReference("Quiz").child(qwn).child("a"+(s+1).toString()+"_1").get().addOnSuccessListener {
                findViewById<CheckBox>(R.id.Ques1).text=it.value.toString()
            }
            Firebase.database.getReference("Quiz").child(qwn).child("a"+(s+1).toString()+"_2").get().addOnSuccessListener {
                findViewById<CheckBox>(R.id.Ques2).text=it.value.toString()
            }
            Firebase.database.getReference("Quiz").child(qwn).child("a"+(s+1).toString()+"_3").get().addOnSuccessListener {
                findViewById<CheckBox>(R.id.Ques3).text=it.value.toString()
            }
            Firebase.database.getReference("Quiz").child(qwn).child("a"+(s+1).toString()+"_4").get().addOnSuccessListener {
                findViewById<CheckBox>(R.id.Ques4).text=it.value.toString()
            }
            findViewById<CheckBox>(R.id.Ques1).isChecked=false
            findViewById<CheckBox>(R.id.Ques2).isChecked=false
            findViewById<CheckBox>(R.id.Ques3).isChecked=false
            findViewById<CheckBox>(R.id.Ques4).isChecked=false
            imgname = "quiz"+qnum+"/quiz"+qnum+"_"+(photonum).toString()+".png"
            photonum+=1
            val storageRef = REF_STORAGE_ROOT
            storageRef.child(imgname).downloadUrl
                .addOnSuccessListener { url ->
                    Picasso.get().load(url).into(picQ);
                }
                .addOnFailureListener { exception ->
                    Log.e(TAG, "Exception: ${exception.message}")
                }
        }
    }

    fun Cheker(view: View){
        if(view.id==findViewById<CheckBox>(R.id.Ques1).id){
            findViewById<CheckBox>(R.id.Ques2).isChecked=false
            findViewById<CheckBox>(R.id.Ques3).isChecked=false
            findViewById<CheckBox>(R.id.Ques4).isChecked=false
        } else if(view.id==findViewById<CheckBox>(R.id.Ques2).id){
            findViewById<CheckBox>(R.id.Ques1).isChecked=false
            findViewById<CheckBox>(R.id.Ques3).isChecked=false
            findViewById<CheckBox>(R.id.Ques4).isChecked=false
        } else if(view.id==findViewById<CheckBox>(R.id.Ques3).id){
            findViewById<CheckBox>(R.id.Ques2).isChecked=false
            findViewById<CheckBox>(R.id.Ques1).isChecked=false
            findViewById<CheckBox>(R.id.Ques4).isChecked=false
        } else if(view.id==findViewById<CheckBox>(R.id.Ques4).id){
            findViewById<CheckBox>(R.id.Ques2).isChecked=false
            findViewById<CheckBox>(R.id.Ques3).isChecked=false
            findViewById<CheckBox>(R.id.Ques1).isChecked=false
        }
    }

    fun Load (view: View){
        Firebase.database.getReference("All").child(auth?.currentUser?.uid.toString()).child(qwn).setValue(points.toString())
        val myIntent = Intent(this@Quizzes, MainActivity::class.java)
        this@Quizzes.startActivity(myIntent)
    }

    fun start(){
        points=0
        s=0
        /*
        while(s<10){
            Firebase.database.getReference("Quiz").child("Квиз №4").child("q"+(s+1).toString()).setValue(Quiz1Qus[s])
            Firebase.database.getReference("Quiz").child("Квиз №4").child("a"+(s+1).toString()+"_1").setValue(Quiz1Answ[s*4+0])
            Firebase.database.getReference("Quiz").child("Квиз №4").child("a"+(s+1).toString()+"_2").setValue(Quiz1Answ[s*4+1])
            Firebase.database.getReference("Quiz").child("Квиз №4").child("a"+(s+1).toString()+"_3").setValue(Quiz1Answ[s*4+2])
            Firebase.database.getReference("Quiz").child("Квиз №4").child("a"+(s+1).toString()+"_4").setValue(Quiz1Answ[s*4+3])
            Firebase.database.getReference("Quiz").child("Квиз №4").child("Code"+(s+1).toString()).setValue(Quiz1code[s])
            s+=1
        }*/
        s=0
        imgname = "quiz"+qnum+"/quiz"+qnum+"_"+(photonum).toString()+".png"
        photonum+=1
        val storageRef = REF_STORAGE_ROOT
        storageRef.child(imgname).downloadUrl
            .addOnSuccessListener { url ->
                Picasso.get().load(url).into(picQ);
            }
            .addOnFailureListener { exception ->
                Log.e(TAG, "Exception: ${exception.message}")
            }
        Firebase.database.getReference("Quiz").child(qwn).child("q"+(s+1).toString()).get().addOnSuccessListener {
            findViewById<TextView>(R.id.texQ).text=it.value.toString()
        }
        Firebase.database.getReference("Quiz").child(qwn).child("a"+(s+1).toString()+"_1").get().addOnSuccessListener {
            findViewById<CheckBox>(R.id.Ques1).text=it.value.toString()
        }
        Firebase.database.getReference("Quiz").child(qwn).child("a"+(s+1).toString()+"_2").get().addOnSuccessListener {
            findViewById<CheckBox>(R.id.Ques2).text=it.value.toString()
        }
        Firebase.database.getReference("Quiz").child(qwn).child("a"+(s+1).toString()+"_3").get().addOnSuccessListener {
            findViewById<CheckBox>(R.id.Ques3).text=it.value.toString()
        }
        Firebase.database.getReference("Quiz").child(qwn).child("a"+(s+1).toString()+"_4").get().addOnSuccessListener {
            findViewById<CheckBox>(R.id.Ques4).text=it.value.toString()
        }
        Firebase.database.getReference("Quiz").child(qwn).child("Code"+(s+1).toString()).get().addOnSuccessListener {
            k=it.value.toString()
        }

    }

    fun replaceFragmentQ(view: android.view.View){
        val myIntent = Intent(this@Quizzes, MainActivity::class.java)
        myIntent.putExtra("key", android.R.attr.value) //Optional parameters
        this@Quizzes.startActivity(myIntent)
    }

    fun replaceFragmentA(view: android.view.View){
        val myIntent = Intent(this@Quizzes, AboutUsActivity::class.java)
        myIntent.putExtra("key", android.R.attr.value) //Optional parameters
        this@Quizzes.startActivity(myIntent)
    }
    fun replaceFragmentP(view: android.view.View){
        val myIntent = Intent(this@Quizzes, ProfileActivity::class.java)
        myIntent.putExtra("key", android.R.attr.value) //Optional parameters
        this@Quizzes.startActivity(myIntent)
    }

    fun show(view: android.view.View){
        if(findViewById<ConstraintLayout>(R.id.lay2).visibility==View.VISIBLE){
            findViewById<ConstraintLayout>(R.id.lay2).visibility=View.INVISIBLE
        } else {
            findViewById<ConstraintLayout>(R.id.lay2).visibility=View.VISIBLE
        }
    }

    fun click_vk(view: android.view.View) {
        intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/rosatomru"))
        startActivity(intent)
    }


    fun click_telega(view: android.view.View) {
        intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/rosatomru"))
        startActivity(intent)
    }

    fun click_andr(view: android.view.View) {
        if(findViewById<ImageView>(R.id.imageView4).visibility==View.VISIBLE){
            Firebase.database.getReference("All").child(auth?.currentUser?.uid.toString()).child("Отзывы").child("Отзыв о: "+qwn).setValue(findViewById<EditText>(R.id.textView11).text.toString())
            findViewById<ImageView>(R.id.imageView4).visibility=View.INVISIBLE
            findViewById<EditText>(R.id.textView11).visibility=View.INVISIBLE
            findViewById<EditText>(R.id.textView11).setText("")
        } else {
            findViewById<EditText>(R.id.textView11).visibility=View.VISIBLE
            findViewById<ImageView>(R.id.imageView4).visibility=View.VISIBLE
        }
    }



}