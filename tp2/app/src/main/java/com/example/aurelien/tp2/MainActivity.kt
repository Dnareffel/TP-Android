package com.example.aurelien.tp2

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.startActivityForResult




class MainActivity : AppCompatActivity() {

    companion object
    {
        const val STATE_TAB_MESSAGE = "MainActivity.tab_message"

    }

    var list: ArrayList<String> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {

            val message = editText2.text.toString()
            //Toast.makeText(this, "Le message est ${editText2.text.toString()}", Toast.LENGTH_SHORT).show()
            startActivityForResult<ConfirmationActivity>(200, ConfirmationActivity.EXTRA_MESSAGE to message)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {

            200 -> {
                if (resultCode == RESULT_OK) {

                    //Toast.makeText(this, "Le message est ${data?.getIntExtra("btn_pressed",0)}", Toast.LENGTH_SHORT).show()

                    val response = data?.getIntExtra("btn_pressed", 0)

                    if (response == 0) {

                        textView.setText(null);

                        list.add(data?.getStringExtra("message") + "\n" )

                        if(list.size > 10)
                        {
                            list.removeAt(0)
                        }

                        for (elem in list) {

                            textView.append(elem)

                        }

                        //textView.append(data?.getStringExtra("message") + "\n")
                        editText2.setText("")

                        Toast.makeText(this, "Le message a bien été envoyé", Toast.LENGTH_SHORT).show()

                    } else if (response == 1) {

                        editText2.setText(data?.getStringExtra("message"))
                        Toast.makeText(this, "Vous pouvez modifier votre message", Toast.LENGTH_SHORT).show()

                    } else if (response == 2) {

                        editText2.setText("")
                        Toast.makeText(this, "L'envoi du message a bien été annulé", Toast.LENGTH_SHORT).show()
                    }
                } else if (resultCode == RESULT_CANCELED) {

                    Toast.makeText(this, "Un problème est survenu", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    override fun onSaveInstanceState(outState: Bundle) {
        outState.putStringArrayList(STATE_TAB_MESSAGE, list)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        list = savedInstanceState.getStringArrayList(STATE_TAB_MESSAGE)

        for (elem in list) {
            textView.append(elem)

        }
    }
}