package com.example.sendercustombroadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    inner class MInner:BroadcastReceiver()
    {
        val txt=findViewById<TextView>(R.id.textView2)
        override fun onReceive(context: Context?, intent: Intent?) {
            if(intent?.action=="com.example.custombroadcastreceiverapp.ACTION_SEND")
            {
                val mystr=intent.getStringExtra("com.example.custombroadcastreceiverapp")
               Toast.makeText(context,"Sender App+ $mystr",Toast.LENGTH_LONG).show()
                txt.setText(mystr)
            }
        }

    }
   private lateinit var receiver:MInner
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn=findViewById<Button>(R.id.button)

        btn.setOnClickListener{
                val intent=Intent("com.example.custombroadcastreceiverapp.ACTION_SEND")

            intent.putExtra("com.example.custombroadcastreceiverapp","Hello From Sender")
            sendBroadcast(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        receiver=MInner()
        val intentFilter=IntentFilter("com.example.custombroadcastreceiverapp.ACTION_SEND")
        registerReceiver(receiver,intentFilter)
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(receiver)
    }
}