package livrokotlin.com.tonafinal_kotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_unidade.*

class UnidadeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_unidade)

        btn_calcular_media.setOnClickListener {

            val nota = txt_nota.text.toString()
            val peso = txt_peso.text.toString()

            if(nota.toDouble() in 0.0..10.0 && peso.toInt() in 0..10 && nota.isNotEmpty() && peso.isNotEmpty()) {

                val dadosInseridos = Dados(nota.toDouble(), peso.toInt())

                adapterGlobal.add(dadosInseridos)

                mediaGlobal.add(nota.toDouble() * peso.toInt())
                pesoGlobal.add(peso.toInt())
                numTotal += 1

                finishFromChild(this)

            }else{

                if (nota.toDouble() < 0.0 || nota.toDouble() > 10.0 && nota.isNotEmpty() && peso.isNotEmpty()) {
                    Toast.makeText(this, "Insira um valor de 0 a 10", Toast.LENGTH_SHORT).show()
                }
                else if (nota.isEmpty() && peso.isEmpty()) {
                    Toast.makeText(this, "Insira um valor de 0 a 10", Toast.LENGTH_SHORT).show()
                }
                else if (nota.isEmpty() && peso.isNotEmpty()) {
                    Toast.makeText(this, "Insira um valor de 0 a 10", Toast.LENGTH_SHORT).show()
                }
                else if (peso.toInt() < 0 || peso.toInt() > 10 && nota.isNotEmpty() && peso.isNotEmpty()) {
                    Toast.makeText(this, "Insira um valor de 0 a 10", Toast.LENGTH_SHORT).show()
                }
                else if (peso.isEmpty() && nota.isNotEmpty()) {
                    Toast.makeText(this, "Insira um peso", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }
}
