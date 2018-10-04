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

        val message = intent.getStringExtra(EXTRA_MESSAGE)
        //Toast.makeText(this, "Le message est ${message}", Toast.LENGTH_SHORT).show()
        textView2.text =  message

        buttonConfirm.setOnClickListener {
            val intent = Intent()
            intent.putExtra("btn_pressed",0)
            intent.putExtra("message",message)
            setResult(RESULT_OK,intent)
            finish()
        }

        buttonModify.setOnClickListener {
            val intent = Intent()
            intent.putExtra("btn_pressed",1)
            intent.putExtra("message",message)
            setResult(RESULT_OK,intent)
            finish()
        }

        buttonCancel.setOnClickListener {
            val intent = Intent()
            intent.putExtra("btn_pressed",2)
            setResult(RESULT_OK,intent)
            finish()
        }

    }
}
