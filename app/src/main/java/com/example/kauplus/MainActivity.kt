package com.example.kauplus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kauplus.databinding.ActivityMainBinding
import com.example.kauplus.ui.meeting.ScheduleFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding=ActivityMainBinding.inflate(layoutInflater)

        binding.btn.setOnClickListener {
            supportFragmentManager.beginTransaction().run{
                replace(binding.fram.id,ScheduleFragment())
                commit()
            }
        }

        setContentView(binding.root)

    }
}