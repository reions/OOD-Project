package com.example.kauplus

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.kauplus.databinding.ActivityMainBinding
import com.example.kauplus.ui.meeting.ScheduleFragment

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)

        binding.mainDrawerLayout.setScrimColor(
            ContextCompat.getColor(
                this,
                android.R.color.transparent
            )
        )
        showInit()
        initDrawerNav()

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        binding.ivBack.setOnClickListener {
            onBackPressed()
        }
        binding.ivMore.setOnClickListener {
            binding.mainDrawerLayout.openDrawer((GravityCompat.START))
        }

        binding.drLogout.setOnClickListener{
            finish()
            startActivity(intent)
        }
        setContentView(binding.root)

    }

    private fun initDrawerNav(){
        binding.drCommunity.setOnClickListener{
            addFragment(StudyCommunityFragment())
            binding.mainDrawerLayout.closeDrawer((GravityCompat.START))
        }

        /* binding.drFacility.setOnClickListener{
            addFragment(StudyCommunityFragment())
            binding.mainDrawerLayout.closeDrawer((GravityCompat.START))
        } */
        binding.drSchedule.setOnClickListener{
            addFragment(ScheduleFragment())
            binding.mainDrawerLayout.closeDrawer((GravityCompat.START))
        }
        binding.drFacility.setOnClickListener {
            addFragment(facilityAppFragment())
            binding.mainDrawerLayout.closeDrawer((GravityCompat.START))

        }
        /*binding.drLogout.setOnClickListener{
            binding.mainDrawerLayout.closeDrawer((GravityCompat.START))
        }*/
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager
                .popBackStack()
        }
        else if (binding.mainDrawerLayout.isDrawerOpen(GravityCompat.START)){
            binding.mainDrawerLayout.closeDrawers()
        }
        else {
            super.onBackPressed()
        }

    }
    fun hideMoreAndShowBack(state: Boolean){
        if(state){
            binding.ivBack.visibility = View.VISIBLE
            binding.ivMore.visibility=View.GONE
        }else{
            binding.ivBack.visibility = View.GONE
            binding.ivMore.visibility=View.VISIBLE
        }
    }
    fun hideLogoAndShowTitle(state: Boolean){
        if(state){
            binding.mainLogo.visibility=View.GONE
            binding.navText.visibility=View.VISIBLE
        }else{
            binding.mainLogo.visibility=View.VISIBLE
            binding.navText.visibility=View.GONE
        }
    }

    fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container_main, fragment).commit()
    }
    fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .commit {
                replace(R.id.container_main, fragment)
                addToBackStack(null)
            }

    }

    private fun showInit() {
        val transaction = supportFragmentManager.beginTransaction()
            .add(R.id.container_main, StudyCommunityFragment())
        transaction.commit()
    }
}