package com.example.aurelien.tp2

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_confirmation.*

class ConfirmationActivity : AppCompatActivity() {

    companion object
    {
        const val EXTRA_MESSAGE = "ConfirmationActivity.MESSAGE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation)

        val message = intent.getStringExtra(EXTRA_MESSAGE)//récupére le message reçu de la main activity
        //Toast.makeText(this, "Le message est ${message}", Toast.LENGTH_SHORT).show()
        textViewMessage.text =  message //rempli le textView avec le message reçu


        //Click sur bouton 'Confirmer'
        btn_Confirm.setOnClickListener {
            val intent = Intent()
            intent.putExtra("btn_pressed",0)//passe la l'id du bouton dans l'intent
            intent.putExtra("message",message)//passe le message
            setResult(RESULT_OK,intent)//Requéte OK
            finish()
        }

        //Click sur bouton 'Modifier'
        btn_Modify.setOnClickListener {
            val intent = Intent()
            intent.putExtra("btn_pressed",1)
            intent.putExtra("message",message)
            setResult(RESULT_OK,intent)
            finish()
        }

        //Click sur bouton 'Annuler'
        btn_Cancel.setOnClickListener {
            val intent = Intent()
            intent.putExtra("btn_pressed",2)
            setResult(RESULT_OK,intent)
            finish()
        }

    }
}
