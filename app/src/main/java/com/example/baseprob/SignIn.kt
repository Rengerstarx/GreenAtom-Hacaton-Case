package com.example.baseprob

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.baseprob.databinding.ActivitySignInBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class SignIn : AppCompatActivity() {

    lateinit var launcher: ActivityResultLauncher<Intent>
    lateinit var auth: FirebaseAuth
    lateinit var binding: ActivitySignInBinding

    lateinit var mAth: FirebaseAuth
    lateinit var mAuthListener: FirebaseAuth.AuthStateListener
    private var ETemail: EditText? = null
    private var ETpassword: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mAth=FirebaseAuth.getInstance()
        mAuthListener =
            AuthStateListener { firebaseAuth ->
                val user = firebaseAuth.currentUser
                if (user != null) {
                    // User is signed in
                } else {
                    // User is signed out
                }
            }

        auth = Firebase.auth
        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try{
                val account = task.getResult(ApiException::class.java)
                if(account != null){
                    firegoogle(account.idToken!!)
                }
            }
            catch (e: ApiException){
                Log.d("MyLog","Google API exception")
            }
        }
        binding.Google.setOnClickListener{
            SignInWithGoogle()
        }
        checkautorization()
    }
    private fun getClient(): GoogleSignInClient{
        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        return GoogleSignIn.getClient(this, gso)
    }


    private fun SignInWithGoogle(){
        val signInClient = getClient()
        launcher.launch(signInClient.signInIntent)
    }
    private fun firegoogle(idToken: String){
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            if(it.isSuccessful){
                Log.d("MyLog","Google signIn done")
                checkautorization()
            }
            else{
                Log.d("MyLog","Google signIn error")
            }
        }
    }

    private fun checkautorization(){
        if(auth.currentUser!=null){
            val myIntent = Intent(this@SignIn, MainActivity::class.java)
            this@SignIn.startActivity(myIntent)
        }
    }

     fun onClickQ(view: android.view.View) {
         ETemail = findViewById<EditText>(R.id.et_email)
         ETpassword = findViewById<EditText>(R.id.et_password)
        if (view.getId() === R.id.btn_sign_in) {
            signin(ETemail!!.text.toString(), ETpassword!!.text.toString())
        } else if (view.getId() === R.id.btn_registration) {
            registration(ETemail!!.text.toString(), ETpassword!!.text.toString())
        }
    }

    fun signin(email: String, password: String) {
        mAth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this,
            OnCompleteListener<AuthResult?> { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        this@SignIn,
                        "Aвторизация успешна",
                        Toast.LENGTH_SHORT
                    ).show()
                    val myIntent = Intent(this@SignIn, MainActivity::class.java)
                    this@SignIn.startActivity(myIntent)
                } else Toast.makeText(
                    this@SignIn,
                    "Aвторизация провалена",
                    Toast.LENGTH_SHORT
                ).show()
            })
    }

    fun registration(email: String, password: String) {
        mAth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this,
            OnCompleteListener<AuthResult?> { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        this@SignIn ,
                        "Регистрация успешна",
                        Toast.LENGTH_SHORT
                    ).show()
                    val myIntent = Intent(this@SignIn, MainActivity::class.java)
                    this@SignIn.startActivity(myIntent)
                } else Toast.makeText(
                    this@SignIn,
                    "Регистрация провалена",
                    Toast.LENGTH_SHORT
                ).show()
            })
    }
}