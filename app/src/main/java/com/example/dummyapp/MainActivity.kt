package com.example.dummyapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.dummyapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val REQUEST_TEMPLATE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnInvoke.setOnClickListener {
            val intent = Intent()

            intent.action = Intent.ACTION_VIEW
            intent.putExtra(Intent.EXTRA_TEXT, binding.txtCustomMessage.text.toString())

            val shareIntent = Intent.createChooser(intent, "Choose an app")

            startActivityForResult(shareIntent, REQUEST_TEMPLATE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode==RESULT_OK) {
            if (requestCode == REQUEST_TEMPLATE) {
                val msg = data?.getStringExtra(Intent.EXTRA_TEXT)
                binding.txtMessage.text = msg
            }
        }
    }
}
