package com.sergeevme.cleanandroid.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView
import com.sergeevme.cleanandroid.R
import com.sergeevme.cleanandroid.presentation.utils.IViewTouchListener
import com.sergeevme.cleanandroid.presentation.utils.TouchResize
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val btnResizeListener: IViewTouchListener = TouchResize()

    private val vm by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // findViewById: init
        val textLoad = findViewById<MaterialTextView>(R.id.text_load_main)
        val btnLoad = findViewById<MaterialButton>(R.id.btn_load_main)

        val editTextSave = findViewById<TextInputEditText>(R.id.textInputEdit_toSave_main)
        val btnSave = findViewById<MaterialButton>(R.id.btn_save_main)
        val textLastAction = findViewById<MaterialTextView>(R.id.text_lastAction_main)

        // ViewModel
        vm.loadLive.observe(this) { textLoad.text = it }
        vm.lastActionLive.observe(this, Observer {
            textLastAction.text = it
            editTextSave.setText("")
            editTextSave.clearFocus()
        })

        btnLoad.setOnClickListener {
            // Button action to load
            vm.load()
            textLastAction.text = resources.getString(R.string.loaded)
        }
        btnResizeListener.onTouchListener(btnLoad)

        btnSave.setOnClickListener {
            // Button action to save
            val text = editTextSave.text.toString()
            vm.save(text)
        }
        btnResizeListener.onTouchListener(btnSave)
    }

}