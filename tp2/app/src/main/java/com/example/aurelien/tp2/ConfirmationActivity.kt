package com.example.aurelien.tp2

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_confirmation.*

class ConfirmationActivity : AppCompatActivity() {

    companion object
    {
        const val EXTRA_MESSAGE = "ConfirmationActivity.MESSAGE"
        const val EXTRA_ISCONFIRMED = "ConfirmationActivity.ISCONFIRMED"

        const val VAL_CONFIRM = 0
        const val VAL_MODIFY = 1
        const val VAL_CANCEL = 2


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation)

        //val message = intent.getStringExtra(EXTRA_MESSAGE)//récupére le message reçu de la main activity
        //Toast.makeText(this, "Le message est ${message}", Toast.LENGTH_SHORT).show()
        textViewMessage.text =  intent.getStringExtra(EXTRA_MESSAGE) //rempli le textView avec le message reçu


        //Click sur bouton 'Confirmer'
        btn_confirm.setOnClickListener {
            returnResult(VAL_CONFIRM)
        }

        //Click sur bouton 'Modifier'
        btn_modify.setOnClickListener {
            returnResult(VAL_MODIFY)
        }

        //Click sur bouton 'Annuler'
        btn_cancel.setOnClickListener {
            returnResult(VAL_CANCEL)
        }

    }

    private fun returnResult(res: Int) {
        val retIntent = Intent()
        retIntent.putExtra(EXTRA_ISCONFIRMED, res)
        setResult(Activity.RESULT_OK, retIntent)
        finish()
    }
}
