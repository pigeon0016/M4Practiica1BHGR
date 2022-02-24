package com.example.bhgrpractica1v2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bhgrpractica1v2.databinding.ActivityMain2Binding



class MainActivity2 : AppCompatActivity() {
    private lateinit var mBinding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        mBinding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(mBinding.root)
        //setContentView(R.layout.activity_main2)

        // con esto traes los datos usando el bundle
     val bundle = intent.extras
        val datos = bundle?.getString("paquete")
        mBinding.resultadoazul.text = datos
    }
}