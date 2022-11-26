/*package com.example.baseprob

import android.content.Intent
import android.media.session.MediaSession.QueueItem
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EdgeEffect
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.baseprob.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class F1Quiz : AppCompatActivity() {

    var ArrayAnswer= arrayOf("0","0","0","0","0","0","0","0","0","0")
    var Quiz1Qus= arrayOf("Какой объем памяти имела автоматическая цифровая вычислительная машина М-1?","Какого числа отмечается День программиста (256 день в году) в високосный год?", "Мотылек замкнул крылышками контакты. Какое жаргонное слово в программировании появилось по этому случаю?","Какому устройству дал имя винтовочный патрон американского происхождения?","Создание какого языка программирования пока не отмечено премией Тьюринга?","Как называют шуточный секрет, заложенный создателями в ПО?","Идеи двоичного кодирования были заложены:","Какова была тактовая частота у первой модели персонального компьютера фирмы IBM?","Что делает Билл Гейтс?","Первая ЭВМ в СССР называлась:")
    var Quiz1Answ= arrayOf("1000000 слов","1024 слов","256 слов","1000 слов","12 сентября","13 сентября","4 декабря","1 сентября","Error","Bug","Breakdown","Mistake","Алгол","Фортран","Модула","Си","Оладушка","Колядка","Пасхалка","Сырок","Джоном фон Нейманом","Готфрид Вильгельм Лейбницом","Адой Лавлейс","Чарльзом Беббиджем","4,77 ГГц","8 МГц","4,77 МГц","8800 Гц","Демонстрирует обьем памяти CD-ROm","Подает самолету сигнал SOS","Пускает солнечных зайчиков","Демонстрирует свое состояние","Стрела","МЭСМ","IBM PC","БЭСМ")
    var Quiz1code= arrayOf("3","1","2","4","4","3","2","3","1","2")
    var RezArrayQus= arrayOf("")
    var RezArrayAns= arrayOf("")
    var RezArraycode= arrayOf("")
    var s=0
    var len =10
    private lateinit var binding: ActivityMainBinding
    var points=0
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

    fun start(){
        findViewById<TextView>(R.id.texQ).text=Quiz1Qus[s]
        findViewById<CheckBox>(R.id.Ques1).text=Quiz1Answ[s*4]
        findViewById<CheckBox>(R.id.Ques2).text=Quiz1Answ[s*4+1]
        findViewById<CheckBox>(R.id.Ques3).text=Quiz1Answ[s*4+2]
        findViewById<CheckBox>(R.id.Ques4).text=Quiz1Answ[s*4+3]
        s+=1
    }

    fun replacer(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.BAZA,fragment)
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

    fun crate(view: View){
        if(RezArraycode[s]=="1" && findViewById<CheckBox>(R.id.Ques1).isChecked==true){
            ArrayAnswer[s]="1"
        } else if(RezArraycode[s]=="2" && findViewById<CheckBox>(R.id.Ques2).isChecked==true){
            Quiz1code[s]="1"
        }else if(RezArraycode[s]=="3" && findViewById<CheckBox>(R.id.Ques3).isChecked==true){
            ArrayAnswer[s]="1"
        }else if(RezArraycode[s]=="4" && findViewById<CheckBox>(R.id.Ques4).isChecked==true){
            ArrayAnswer[s]="1"
        }
        s+=1
        if(s==len){
            s=0
            var k=0
            points=0
            while(k<len){
                if(ArrayAnswer[k]=="1"){
                    points+=1
                }
                k+=1
            }
            findViewById<TextView>(R.id.result).visibility=View.VISIBLE
            findViewById<ImageView>(R.id.imageView228).visibility=View.VISIBLE
            findViewById<Button>(R.id.load).visibility=View.VISIBLE
            findViewById<ConstraintLayout>(R.id.lay).visibility=View.INVISIBLE
            findViewById<TextView>(R.id.result).text=points.toString()+"/10"

        }else{
            findViewById<TextView>(R.id.texQ).text=RezArrayQus[s]
            findViewById<CheckBox>(R.id.Ques1).text=RezArrayAns[s*4]
            findViewById<CheckBox>(R.id.Ques2).text=RezArrayAns[s*4+1]
            findViewById<CheckBox>(R.id.Ques3).text=RezArrayAns[s*4+2]
            findViewById<CheckBox>(R.id.Ques4).text=RezArrayAns[s*4+3]
            findViewById<CheckBox>(R.id.Ques1).isChecked=false
            findViewById<CheckBox>(R.id.Ques2).isChecked=false
            findViewById<CheckBox>(R.id.Ques3).isChecked=false
            findViewById<CheckBox>(R.id.Ques4).isChecked=false
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
        Firebase.database.getReference("All").child(Firebase.auth.currentUser?.uid.toString()).child("Квиз №1").setValue(points)
        var k=0
        while(k<len){
            ArrayAnswer[k]="0"
            k+=1
        }
    }

}*/