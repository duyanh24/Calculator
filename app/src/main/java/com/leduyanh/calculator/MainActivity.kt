package com.leduyanh.calculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.leduyanh.calculator.ultis.addFragment

class MainActivity : AppCompatActivity(), SendMessage {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.addFragment(R.id.frame_container_fragment_keyboard,
            KeyboardFragment(this),false,"keyboard")
        supportFragmentManager.addFragment(R.id.frame_container_fragment_result,
            ResultFragment(),false,"result")
    }

    // receive data from Keyboard fragment and send to Result fragment
    override fun sendData(data: String) {
        val resultFragment: ResultFragment = supportFragmentManager.findFragmentByTag("result") as ResultFragment
        resultFragment.displayReceivedData(data)
    }
}
