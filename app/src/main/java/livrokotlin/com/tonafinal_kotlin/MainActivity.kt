package livrokotlin.com.tonafinal_kotlin

import android.app.AlertDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_dados.view.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = DadosAdapter(this)
        lista_media_unidade.adapter = adapter

        fab.setOnClickListener {

            if( txt_notasTexto.visibility == View.GONE ){
                txt_addNota.visibility = View.GONE
                txt_notasTexto.visibility = View.VISIBLE
            }
            if( qtdNotas < 6 ){
                val dados = Dados()
                adapter.add(dados)
                qtdNotas++
            }else{
                Toast.makeText(this,"Número máximo de notas já inserido", Toast.LENGTH_SHORT).show()
            }
        }

        btn_result.setOnClickListener {

            var i = 0
            while( i < qtdNotas ){
                val item = lista_media_unidade.getChildAt(i)

                if( item != null)
                    calc(item.et_nota_lista.text.toString(), item.et_peso_lista.text.toString(), item)
                i++
            }

            if (numTotal > 0 && !error) {

                val soma = mediaGlobal.sumByDouble { it }
                val peso = pesoGlobal.sumByDouble { it.toDouble() }

                mensagens(soma, peso.toInt())

                mediaGlobal.clear()
                pesoGlobal.clear()

            } else {
                Toast.makeText(this, "Insira pelo menos uma nota", Toast.LENGTH_SHORT).show()
            }

            if( error )
                error = false

        }

        btn_reset.setOnClickListener {

            mediaGlobal.clear()
            pesoGlobal.clear()
            numTotal = 0
            qtdNotas = 0
            adapter.clear()
            txt_addNota.visibility = View.VISIBLE
            txt_notasTexto.visibility = View.GONE

        }

        btn_info.setOnClickListener {
            val builder: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)

            builder.setCancelable(false)
            builder.setTitle("To Na Final")
            builder.setMessage("Aplicativo criado por\nPatrick Barnabé M. Santos\nEstudante de Eng. de Computação na Universidade Estadual de Feira de Santana - UEFS")
            builder.setPositiveButton("Voltar") { _, _ ->
            }

            builder.create().show()
        }

        lista_media_unidade.setOnItemLongClickListener { _, _, position, _ ->

            val item = adapter.getItem(position)
            if( mediaGlobal.size != 0 && pesoGlobal.size != 0 ){
                val itemMediaGlobal = mediaGlobal.get(position)
                val itemPesoGlobal = pesoGlobal.get(position)
                pesoGlobal.remove(itemPesoGlobal)
                mediaGlobal.remove(itemMediaGlobal)
            }
            adapter.remove(item)
            numTotal -= 1
            qtdNotas -= 1

            true
        }

        imagem_view.setOnClickListener {

            Toast.makeText(this, "Uuuu – u – uhuhuhuhu!!\nUuuu – u – uhuhuhuhu!!\nPara de me tocaaar...", Toast.LENGTH_SHORT).show()

        }

        imagem_view.setOnLongClickListener {

            val builder: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)

            builder.setCancelable(false)
            builder.setTitle("To Na Final")
            builder.setMessage("Aplicativo criado por\nPatrick Barnabé M. Santos\nEstudante de Eng. de Computação na Universidade Estadual de Feira de Santana - UEFS")
            builder.setPositiveButton("Voltar") { _, _ ->
                Toast.makeText(this@MainActivity, "Por favor não fique me apertando", Toast.LENGTH_SHORT).show()
            }

            builder.create().show()

            true
        }

    }

    private fun mensagens(valorTotal: Double, pesoTotal: Int) {

        val media: Double = valorTotal / pesoTotal
        var txtMedia = ""
        var zoas = ""
        var txt = ""

        if (media >= 7) {
            if (media >= 7 && media < 8) {
                txtMedia = "Por pouco... acho melhor você dar uma manerada no Instagram e na Netflix\nSua média foi $media"
                zoas = "Quase na Final :)"
                txt = "Melhoras"
            } else {
                txtMedia = "Parabéns você passou!!!\nSua média foi $media"
                zoas = "Fugi da Final :)"
                txt = "Não fez mais que sua obrigação!!"
            }
        } else if (media >= 3 && media < 7) {
            var nf = (12.5 - (1.5 * media))
            if (nf < 3) {
                nf = 3.0
            }
            txtMedia = "Que comecem os jogos, você está na Final...\nSua média foi $media e você vai precisar de no mínimo $nf para passar na Prova Final."
            zoas = "Estou na Final :/"
            txt = "Melhor você começar a estudar"
        } else if (media < 3 && media > 0) {
            txtMedia = "Você não alcançou a nota minima para as finais, Tente novamente no próximo semestre...\nSua média foi $media"
            zoas = "Nem na Final :("
            txt = "Never surrender"
        } else if (media < 3 && media > 0) {
            txtMedia = "Rapaz oque você está fazendo da sua vida??\nSua média foi $media"
            zoas = "Nem na Final :("
            txt = "Ta na disney"
        }

        val builder: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)

        builder.setCancelable(false)
        builder.setTitle(zoas)
        builder.setMessage(txtMedia)
        builder.setPositiveButton("Aceito meu Destino") { _, _ ->
            Toast.makeText(this@MainActivity, txt, Toast.LENGTH_SHORT).show()
        }

        builder.create().show()

    }

    private fun calc( n: String, p: String, item: View){

        val nota = n
        val peso = p

        if(nota.toDouble() in 0.0..10.0 && peso.toInt() in 0..10 && nota.isNotEmpty() && peso.isNotEmpty()) {

            mediaGlobal.add(nota.toDouble() * peso.toInt())
            pesoGlobal.add(peso.toInt())
            numTotal += 1

        }else{

            error = true

            if (nota.toDouble() < 0.0 || nota.toDouble() > 10.0 && nota.isNotEmpty() && peso.isNotEmpty()) {
                item.et_nota_lista.error = "Insira um valor de 0 a 10"
                //Toast.makeText(this, "Insira um valor de 0 a 10", Toast.LENGTH_SHORT).show()
            }
            if (nota.isEmpty() && peso.isEmpty()) {
                item.et_nota_lista.error = "Insira um valor de 0 a 10"
                item.et_peso_lista.error = "Insira um valor de 0 a 10"
                //Toast.makeText(this, "Insira um valor de 0 a 10", Toast.LENGTH_SHORT).show()
            }
            if (nota.isEmpty() && peso.isNotEmpty()) {
                item.et_nota_lista.error = "Insira uma nota no valor de 0 a 10"
                //Toast.makeText(this, "Insira um valor de 0 a 10", Toast.LENGTH_SHORT).show()
            }
            if (peso.toInt() < 0 || peso.toInt() > 10 && nota.isNotEmpty() && peso.isNotEmpty()) {
                item.et_peso_lista.error = "Insira um valor de 0 a 10"
                Toast.makeText(this, "Insira um valor de 0 a 10", Toast.LENGTH_SHORT).show()
            }
            if (peso.isEmpty() && nota.isNotEmpty()) {
                item.et_peso_lista.error = "Insira um peso no valor de 0 a 10"
                //Toast.makeText(this, "Insira um peso", Toast.LENGTH_SHORT).show()
            }

        }
    }

}
