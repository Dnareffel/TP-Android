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

    var list: ArrayList<String> = ArrayList()//liste qui contiendra nos messages


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //click sur le boutton envoyer
        btn_envoyer.setOnClickListener {

            val message = editTextMessage.text.toString()//récupére le message tapé dans l'edittext
            //Toast.makeText(this, "Le message est ${editText2.text.toString()}", Toast.LENGTH_SHORT).show()
            startActivityForResult<ConfirmationActivity>(200, ConfirmationActivity.EXTRA_MESSAGE to message)//lance la seconde activity en lui passant le message dans l'intent
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



                    val response = data?.getIntExtra("btn_pressed", 0)//Valeur qui récupére l'id du boutton appuyé dans la ConfirmationActivity

                    //boutton 0 correspont à 'confirmer'
                    if (response == 0) {

                        textViewList.setText(null);//réinitialisation du textView affichant nos messages

                        list.add(data?.getStringExtra("message") + "\n" )//Ajout du nouveau message à la liste de message

                        //supression du premier élément de la liste à 10
                        if(list.size > 10)
                        {
                            list.removeAt(0)
                        }

                        //remplissage du textView avec les élements de notre liste
                        for (elem in list) {

                            textViewList.append(elem)

                        }

                        //textView.append(data?.getStringExtra("message") + "\n")
                        editTextMessage.setText("")

                        Toast.makeText(this, "Le message a bien été envoyé", Toast.LENGTH_SHORT).show()

                    //boutton 1 correspond à 'modifier'
                    } else if (response == 1) {

                        editTextMessage.setText(data?.getStringExtra("message"))
                        Toast.makeText(this, "Vous pouvez modifier votre message", Toast.LENGTH_SHORT).show()

                    //boutton 2 correspond à 'annuler'
                    } else if (response == 2) {

                        editTextMessage.setText("")
                        Toast.makeText(this, "L'envoi du message a bien été annulé", Toast.LENGTH_SHORT).show()
                    }

                //si la requéte s'est mal passée alors
                } else if (resultCode == RESULT_CANCELED) {

                    Toast.makeText(this, "Un problème est survenu", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }



    //fonction de sauvegarde de notre list
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putStringArrayList(STATE_TAB_MESSAGE, list)
        super.onSaveInstanceState(outState)
    }

    //fonction de récupération de la sauvegarde lors de la destruction
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        list = savedInstanceState.getStringArrayList(STATE_TAB_MESSAGE)

        //re-remplissage de la textview supprimée
        for (elem in list) {
            textViewList.append(elem)

        }
    }
}