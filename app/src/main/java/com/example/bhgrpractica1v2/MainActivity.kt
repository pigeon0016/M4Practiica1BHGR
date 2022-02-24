package com.example.bhgrpractica1v2

import android.content.Intent
import android.icu.text.Transliterator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Toast
import com.example.bhgrpractica1v2.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    // VARIABLES GLOBALES
    // crea una variable global para el uso del mBinding
    private lateinit var mBinding: ActivityMainBinding
    private lateinit var aformulas: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {

        //SPLASH ejecucion y letargo
        Thread.sleep(2000)
        setTheme(R.style.AppTheme)
        //Splashh finaliza



        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        //setContentView(R.layout.activity_main)


        // se crea un array adapter
        aformulas = ArrayAdapter<String>(this,
            android.R.layout.simple_spinner_dropdown_item)
        //decirle a la vista q este es un adaptado
        mBinding.formulalist.adapter = aformulas

        //llenamos el spinner
        aformulas.addAll(Arrays.asList("SELECCIONA UNA FORMULA","VELOCIDAD","FUERZA", "IMC"))

        // para manipular la seleccion del spinner
        mBinding.formulalist.onItemSelectedListener = this
        mBinding.formulalist.adapter = aformulas

    }


    //metodos de onitemselectedlistener
    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {

        var forselected = aformulas.getItem(position)
        //var tipoformula = aformulas.getItem(position)
        mBinding.eleccionf.text = forselected
        //mBinding.eleccionf.text = position.toString()----------Aqui salia el valor del array 0 1 2 3


        if (mBinding.eleccionf.text.toString() == "SELECCIONA UNA FORMULA"){
            //mBinding.eleccionf.text = "SELECCIONA UNA FORMULA--->"
            mBinding.variable0.text = "BIENVENIDO"
            mBinding.var1.text = ""
            mBinding.var2.text = ""
            // binding.VARIABLE.visibility = View.INSISIBLE  Variable es el ID del campo a eliminar
            mBinding.variable1.visibility = View.INVISIBLE
            mBinding.variable2.visibility = View.INVISIBLE
            mBinding.eleccionf.visibility = View.INVISIBLE
            mBinding.forresultado.text = ""

        }


        if (mBinding.eleccionf.text.toString() == "VELOCIDAD"){
            //mBinding.eleccionf.text = "FORMULA DE LA VELOCIDAD"
            mBinding.variable0.text = "Velocidad = Distancia(m) / Tiempo(s)"
            mBinding.var1.text = "Distancia(m)"
            mBinding.var2.text = "Tiempo(s)"

            //mBinding.hojita.setImageResource("ivelocidad")
            mBinding.hojita.setImageResource(R.drawable.ivelocidad)
            mBinding.variable1.visibility = View.VISIBLE
            mBinding.variable2.visibility = View.VISIBLE
            mBinding.eleccionf.visibility = View.INVISIBLE
            mBinding.forresultado.text = ""
        }

        if (mBinding.eleccionf.text.toString() == "FUERZA"){
            //mBinding.eleccionf.text = "FORMULA DE LA FUERZA"
            mBinding.variable0.text = "Fuerza = Masa(Kg) * Aceleracion(m/S)"
            mBinding.hojita.setImageResource(R.drawable.ifuerza)
            mBinding.var1.text = "Masa(Kg)"
            mBinding.var2.text = "Aceleracion(m/s)"
            mBinding.variable1.visibility = View.VISIBLE
            mBinding.variable2.visibility = View.VISIBLE
            mBinding.eleccionf.visibility = View.INVISIBLE
            mBinding.forresultado.text = ""
        }

        if (mBinding.eleccionf.text.toString() == "IMC"){
            //mBinding.eleccionf.text = "FORMULA DEL INDICE MASA CORPORAL"
            mBinding.variable0.text = "IMC = Peso(Kg) / Altura(m)"
            mBinding.hojita.setImageResource(R.drawable.imc)
            mBinding.var1.text = "Peso(Kg)"
            mBinding.var2.text = "Altura(m)"
            mBinding.variable1.visibility = View.VISIBLE
            mBinding.variable2.visibility = View.VISIBLE
            mBinding.eleccionf.visibility = View.INVISIBLE
            mBinding.forresultado.text = ""
        }

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }
    ///////////////////////////////////////////////////////////////////

   /////////////////////////////////////BOTON
    // codigo cuando aprieta el boton
    fun click(view: View) {
        if (validaCampos()) {


            if(mBinding.eleccionf.text.toString()== "VELOCIDAD"){
                val dato1 = Integer.parseInt(mBinding.variable1.text.toString())
                val dato2 = Integer.parseInt(mBinding.variable2.text.toString())
                calculo1(dato1,dato2)


            }

            if(mBinding.eleccionf.text.toString()== "FUERZA"){
                val dato1 = Integer.parseInt(mBinding.variable1.text.toString())
                val dato2 = Integer.parseInt(mBinding.variable2.text.toString())
                calculo2(dato1,dato2)
            }

            if(mBinding.eleccionf.text.toString()== "IMC"){
                val dato1 = Integer.parseInt(mBinding.variable1.text.toString())
                val dato2 = Integer.parseInt(mBinding.variable2.text.toString())
                calculo3(dato1,dato2)
            }




        }else{
            //mensaje de error si esta vacio alguna de las 2 variables
            Toast.makeText(this,"Porfavor ingresa un numero", Toast.LENGTH_LONG).show()}

            // ejemplo de como aparecer o desaparecr un cuadro
            // binding.VARIABLE.visibility = View.INSISIBLE  Variable es el ID del campo a eliminar
    }

    //////////////////////////////////////////////////////////////////////////

    // las firmulas matematicas/////////////////////////////////////////////////////////////////////////
    private fun calculo1(numero1: Int,numero2: Int){
        var numero3 = numero1.toFloat() / numero2.toFloat()
        //mBinding.forresultado.text = "El resultado es $numero3 "


        // esto logra pasar los datos de un activyty a otrp
        val intent = Intent(this,MainActivity2::class.java)
        intent.putExtra("paquete","El resultado es $numero3")
        startActivity(intent)

    }

    private fun calculo2(numero1: Int,numero2: Int){
        var numero3 = numero1.toFloat() * numero2.toFloat()
        //mBinding.forresultado.text = "El resultado es $numero3 "

        // esto logra pasar los datos de un activyty a otrp
        val intent = Intent(this,MainActivity2::class.java)
        intent.putExtra("paquete","El resultado es $numero3")
        startActivity(intent)

    }

    private fun calculo3(numero1: Int,numero2: Int){
        var numero3 = numero1.toFloat() / numero2.toFloat()
        //mBinding.forresultado.text = "El resultado es $numero3 "

        // esto logra pasar los datos de un activyty a otrp
        val intent = Intent(this,MainActivity2::class.java)
        intent.putExtra("paquete","El resultado es $numero3")
        startActivity(intent)

    }
    /////////////////////////////////////////////////////////////////////////////// cierre de funciones con la formula

    // valida campos
    private fun validaCampos():Boolean{
        if(mBinding.variable1.text.toString() == ""){
            //Marca el error en el campo
            mBinding.variable1.error = "Se necesita un dato"
            mBinding.variable1.requestFocus()
            return false
        } else if (mBinding.variable2.text.toString() ==""){
            //Marca el error en el campo
            mBinding.variable2.error = "Se necesita un dato"
            mBinding.variable2.requestFocus()
            return false
        } else return true
    }
  //Valida campos fin



}



