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

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var Quiz1Qus= arrayOf("Какой объем памяти имела автоматическая цифровая вычислительная машина М-1?","Какого числа отмечается День программиста (256 день в году) в високосный год?", "Мотылек замкнул крылышками контакты. Какое жаргонное слово в программировании появилось по этому случаю?","Какому устройству дал имя винтовочный патрон американского происхождения?","Создание какого языка программирования пока не отмечено премией Тьюринга?","Как называют шуточный секрет, заложенный создателями в ПО?","Идеи двоичного кодирования были заложены:","Какова была тактовая частота у первой модели персонального компьютера фирмы IBM?","Что делает Билл Гейтс?","Первая ЭВМ в СССР называлась:")
    var Quiz1Answ= arrayOf("1000000 слов","1024 слов","256 слов","1000 слов","12 сентября","13 сентября","4 декабря","1 сентября","Error","Bug","Breakdown","Mistake","ROM","CPU","RAM","HDD","Алгол","Фортран","Модула","Си","Оладушка","Колядка","Пасхалка","Сырок","Джоном фон Нейманом","Готфрид Вильгельм Лейбницом","Адой Лавлейс","Чарльзом Беббиджем","4,77 ГГц","8 МГц","4,77 МГц","8800 Гц","Демонстрирует обьем памяти CD-ROm","Подает самолету сигнал SOS","Пускает солнечных зайчиков","Демонстрирует свое состояние","Стрела","МЭСМ","IBM PC","БЭСМ")
    var Quiz1code= arrayOf("3","1","2","4","4","3","2","3","1","2")
    var Quiz2Answ= arrayOf("BIT-real","DARQ","ЁКЛМН!","нрзбрч","С востока на запад","С запада на восток","Радиально и синхронно из нескольких центров разработки","Зюйд-зюйд-вест какой-нибудь","Искусственный интеллект","5G","Технологии расширенной реальности","Квантовые вычисления","Около 700 Кб — как прочитать с десяток текстовых страниц в сети","Около 50 Мб — как прослушать музыкальный альбом","Около 2 Гб — как посмотреть парочку фильмов","Нисколько не генерирует, потому что виснет","Чеховский персонаж, доработанный нейросетью","Приложение, обеспечивающее независимость от центрального сервера","Компьютер или программу, переносящие задачи по обработке информации на сервер","Гаджет толщиной меньше 1 см","3 копии данных, 2 из них на разных носителях информации, 1 на удаленной площадке","3 площадки, 2 разных носителя, 1 копия данных","3 носителя, 2 копии, 1 площадка","Всегда осуществлять обратный отсчет перед запуском новой программы","Около 1%","Примерно каждый десятый","Более 50%","Я буду первым","25 Тб: 196 серверов по 128 Гб в каждом","Всего лишь один сервер на 1 Тб","Это был квантовый компьютер, не оперирующий битами","52 Тб — по количеству карт в покерной колоде","Машинное обучение","Глубинное обучение","Обучение с подкреплением"," А когда уже будет розыгрыш серверной станции?","В среднем 42 часа в год","Неделя точно набегает","Мне больно об этом думать","10 дней в год, которые называются «черной декадой»")
    var Quiz2code= arrayOf("2","1","4","3","3","1","3","1","2","1")
    var Quiz2Qus= arrayOf("Какой аббревиатурой обозначают четыре ключевых ИТ-тренда: блокчейн, искусственный интеллект, дополненную реальность и квантовые вычисления?","Суперприложения (мобильные приложения, которые могут решать практически любые задачи современного человека) становятся все более популярны. В качестве примера можно привести WeChat с более чем миллиардом пользователей. Если представить себе карту, как происходила географическая экспансия этих приложений в мире?", "Новые технологии способствуют росту граничных вычислений (те, что происходят непосредственно на предприятии — быстро и без передачи в облако). Но одна технология решила воздержаться от участия. Назовите ее.", "Работая удаленно, мы всегда остаемся на связи благодаря новейшим технологиям. Какова при этом нагрузка на ИТ-инфраструктуру, не каждый задумывается. Какое количество данных генерирует групповой видеозвонок в Skype на 5 участников?","Что называют тонким клиентом в ИТ-индустрии?", "Специалисты по работе с данными придумали правило 3-2-1, обеспечивающее соблюдение техник безопасности. Что означают эти цифры?", "Сегодня многие работают с облачными сервисами. Как вы думаете, каков процент пользователей, желающих вернуть хотя бы часть своей инфраструктуры из облачной в локальную?", "Одним из знаковых событий в истории развития ИИ стала победа суперкомпьютера Libratus в игре в покер против человека в 2017 году. Это означало, что машина научилась блефовать. Какой объем оперативной памяти потребовался ей для этой победы?", "Продвинутый искусственный интеллект в ИТ-системах развивался постепенно. Что из перечисленного появилось позже всего?","У водителей и администраторов дата-центров есть один общий показатель: они тратят примерно одинаковое время на стояние в пробках и попытки выяснить, что именно не работает в системе. Сколько же времени они на это тратят?")
    var ArrayAnswer= arrayOf("0","0","0","0","0","0","0","0","0","0")
    var RezArrayQus= arrayOf("","","","","","","","","","")
    var RezArrayAns= arrayOf("","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","")
    var RezArraycode= arrayOf("","","","","","","","","","")
    var s=0
    var len =10
    var points=0
    var qwn=""

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

    fun mainka(view: android.view.View){
        s=0
        supportFragmentManager.beginTransaction().replace(R.id.BAZA, Quiz_first()).addToBackStack(null).commit()
    }

    fun quizPage(view: android.view.View){
        findViewById<ImageView>(R.id.fonn).visibility=View.INVISIBLE
        findViewById<Button>(R.id.gobut1).visibility=View.INVISIBLE
        findViewById<Button>(R.id.gobut2).visibility=View.INVISIBLE
        findViewById<ConstraintLayout>(R.id.lay).visibility=View.VISIBLE
        if(view.id==findViewById<Button>(R.id.gobut1).id){
            qwn="Квиз №1"
        } else if(view.id==findViewById<Button>(R.id.gobut2).id){
            qwn="Квиз №2"
        }
        start()
    }


    fun crate(view: View){
        if(RezArraycode[s]=="1" && findViewById<CheckBox>(R.id.Ques1).isChecked==true){
            ArrayAnswer[s]="1"
        } else if(RezArraycode[s]=="2" && findViewById<CheckBox>(R.id.Ques2).isChecked==true){
            ArrayAnswer[s]="1"
        }else if(RezArraycode[s]=="3" && findViewById<CheckBox>(R.id.Ques3).isChecked==true){
            ArrayAnswer[s]="1"
        }else if(RezArraycode[s]=="4" && findViewById<CheckBox>(R.id.Ques4).isChecked==true){
            ArrayAnswer[s]="1"
        }
        s+=1
        if(s==len){
            s=0
            points=0
            while(s<len){
                if(ArrayAnswer[s]=="1"){
                    points+=1
                }
                s+=1
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
        Firebase.database.getReference("All").child(Firebase.auth.currentUser?.uid.toString()).child(qwn).setValue(points)
        var k=0
        while(k<len){
            ArrayAnswer[k]="0"
            k+=1
        }
    }
    fun start(){
        /*RezArrayAns=Quiz1Answ
        RezArrayQus=Quiz1Qus
        RezArraycode=Quiz1code
        s=0
        while(s<10){
            j=0
            while (j<4){
                Firebase.database.getReference("Quiz").child("Квиз №"+(2).toString()).child(Quiz1Qus[s]).child(Quiz1Answ[s*4+j]).setValue("0")
                j+=1
            }
            var k=Quiz1code[s].toInt()
            Firebase.database.getReference("Quiz").child("Квиз №"+(2).toString()).child(Quiz1Qus[s]).child(Quiz1Answ[s*4+k-1]).setValue("1")
            s+=1
        }*/
        if(qwn=="Квиз №1"){
            var k=0
            while(k!=40){
                if(k<10){
                    RezArrayQus[k]=Quiz1Qus[k]
                    RezArraycode[k]=Quiz1code[k]
                }
                RezArrayAns[k]=Quiz1Answ[k]
                k+=1
            }
        } else if(qwn=="Квиз №2"){
            var k=0
            while(k!=40){
                if(k<10){
                    RezArrayQus[k]=Quiz2Qus[k]
                    RezArraycode[k]=Quiz2code[k]
                }
                RezArrayAns[k]=Quiz2Answ[k]
                k+=1
            }
        }
        s=0
        findViewById<TextView>(R.id.texQ).text=RezArrayQus[s]
        findViewById<CheckBox>(R.id.Ques1).text=RezArrayAns[s*4]
        findViewById<CheckBox>(R.id.Ques2).text=RezArrayAns[s*4+1]
        findViewById<CheckBox>(R.id.Ques3).text=RezArrayAns[s*4+2]
        findViewById<CheckBox>(R.id.Ques4).text=RezArrayAns[s*4+3]
        s+=1

    }



}