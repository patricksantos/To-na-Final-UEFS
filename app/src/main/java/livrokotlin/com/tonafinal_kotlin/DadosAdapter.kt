package livrokotlin.com.tonafinal_kotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class DadosAdapter(contexto: Context): ArrayAdapter<Dados>(contexto, 0){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val v: View

        if( convertView != null ){

            v = convertView

        }else{

            v = LayoutInflater.from(context).inflate(R.layout.activity_dados, parent,false)

        }

        /*val item = getItem(position)

        val txtNota = v.findViewById<TextView>(R.id.et_nota_lista)
        val txtPeso = v.findViewById<TextView>(R.id.et_peso_lista)

        if( item != null ){
            txtNota.text = item.nota.toString()
            txtPeso.text = item.peso.toString()
        }else{
            txtNota.text = (10).toString()
            txtPeso.text = (10).toString()
        }*/

        return v

    }
}