package com.example.aurelien.tp2

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.mediaSessionManager
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.startActivityForResult
import org.jetbrains.anko.textClassificationManager


class MainActivity : AppCompatActivity() {

    companion object
    {
        const val STATE_TAB_MESSAGE = "MainActivity.tab_message"

    }

    var messages_list: ArrayList<String> = ArrayList()//liste qui contiendra nos messages


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //click sur le boutton envoyer
        btn_envoyer.setOnClickListener {
            //Toast.makeText(this, "Le message est ${editText2.text.toString()}", Toast.LENGTH_SHORT).show()
            startActivityForResult<ConfirmationActivity>(200, ConfirmationActivity.EXTRA_MESSAGE to editTextMessage.text.toString())//lance la seconde activity en lui passant le message dans l'intent
        }
    }


    //fonction qui gére le retour de ConfirmationActibity
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
        //Si le code de la requéte correspond alors
            200 -> {
                //Si la requéte s'est déroulée correctement alors
                if (resultCode == RESULT_OK) {

                    //Toast.makeText(this, "Le message est ${data?.getIntExtra("btn_pressed",0)}", Toast.LENGTH_SHORT).show()


                    val r = data?.getIntExtra(ConfirmationActivity.EXTRA_ISCONFIRMED, ConfirmationActivity.VAL_CANCEL) ?: ConfirmationActivity.VAL_CANCEL


                    //boutton 0 correspont à 'confirmer'
                    when(r) {

                        ConfirmationActivity.VAL_CONFIRM -> {

                            textViewList.setText(null);//réinitialisation du textView affichant nos messages

                            messages_list.add(editTextMessage.text.toString() + "\n" )//Ajout du nouveau message à la liste de message

                            if(messages_list.size > 10)
                            {
                                messages_list.removeAt(0)
                            }

                            updateMessageView()

                            editTextMessage.text.clear()
                        }
                        ConfirmationActivity.VAL_MODIFY -> {
                        } // rien de spécial à faire
                        ConfirmationActivity.VAL_CANCEL -> {
                            // On efface le texte saisi
                            editTextMessage.text.clear()
                        }
                    }
                //si la requéte s'est mal passée alors
                } else if (resultCode == RESULT_CANCELED) {

                    Toast.makeText(this, "Un problème est survenu", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    private fun updateMessageView() {


        //remplissage du textView avec les élements de notre liste
        for (elem in messages_list) {

            textViewList.append(elem)

        }
    }

    //fonction de sauvegarde de notre list
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putStringArrayList(STATE_TAB_MESSAGE, messages_list)
        super.onSaveInstanceState(outState)
    }

    //fonction de récupération de la sauvegarde lors de la destruction
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        messages_list = savedInstanceState.getStringArrayList(STATE_TAB_MESSAGE)

        //re-remplissage de la textview supprimée
        updateMessageView()
    }
}