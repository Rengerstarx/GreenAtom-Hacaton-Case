package com.example.baseprob

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.baseprob.databinding.ActivitySignInBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignIn : AppCompatActivity() {
    lateinit var launcher: ActivityResultLauncher<Intent>
    lateinit var auth: FirebaseAuth
    lateinit var binding: ActivitySignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
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

    private  fun checkautorization(){
        if(auth.currentUser!=null){
            val myIntent = Intent(this@SignIn, MainActivity::class.java)
            this@SignIn.startActivity(myIntent)
        }
    }
}