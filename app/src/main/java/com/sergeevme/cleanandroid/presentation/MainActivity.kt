package com.sergeevme.cleanandroid.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView
import com.sergeevme.cleanandroid.R
import com.sergeevme.cleanandroid.presentation.utils.IViewTouchListener
import com.sergeevme.cleanandroid.presentation.utils.TouchResize
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

// @author sergeevme
class MainActivity : AppCompatActivity() {

    private val btnResizeListener: IViewTouchListener = TouchResize()

    // Init ViewModel
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
        val textCounter = findViewById<MaterialTextView>(R.id.text_counter_main)

        // ViewModel
        vm.loadLive.observe(this) { textLoad.text = it }
        vm.lastActionLive.observe(this, Observer {
            textLastAction.text = it
            editTextSave.setText("")
            editTextSave.clearFocus()
        })

        // StateFlow observer
        collectLatestLifecycleFlow(vm.stateFlow) {
            textCounter.text = it.toString()
        }

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

        textCounter.setOnClickListener {
            vm.updateCount()
        }
        btnResizeListener.onTouchListener(textCounter)
    }

    private fun <T> ComponentActivity.collectLatestLifecycleFlow(flow: Flow<T>, collect: suspend (T) -> Unit) {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                flow.collectLatest(collect)
            }
        }
    }

}