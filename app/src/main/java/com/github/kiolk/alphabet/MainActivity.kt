package com.github.kiolk.alphabet;

import android.os.Bundle;
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import javax.inject.Inject;

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var test : TestClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);

        val test : TestClass = App.component.plusTaestClass().testClass
        Toast.makeText(this, "Context hash is ${test.context}", Toast.LENGTH_LONG).show()
        Log.e("TAG", "Context hash is ${test.context}")
    }
}
