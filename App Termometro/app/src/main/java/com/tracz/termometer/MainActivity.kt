package com.tracz.termometer

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import helpers.MqttHelper
import kotlinx.android.synthetic.main.activity_main.*
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended
import org.eclipse.paho.client.mqttv3.MqttMessage
import org.json.JSONException
import org.json.JSONObject
import java.util.*


class MainActivity : AppCompatActivity() {

    private val JSON_URL3 = "http://44.228.65.254/site/query_select_by_page_db_desc_luminosidade1.php?page=1"
    private var request2: JsonArrayRequest? = null
    private var requestQueue2: RequestQueue? = null

    private val JSON_URL2 = "http://44.228.65.254/site/query_select_by_page_db_desc_umidade1.php?page=1"
    private var request3: JsonArrayRequest? = null
    private var requestQueue3: RequestQueue? = null

    var mqttHelper: MqttHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        data = findViewById<View>(R.id.textValor) as TextView
        data2 = findViewById<View>(R.id.textValor2) as TextView
        data3 = findViewById<View>(R.id.textValor3) as TextView
        jsonrequest2()
        jsonrequest3()
        startMqtt()

        button.setOnClickListener {
            val intent = Intent(this, AmbienteActivity::class.java)
            startActivity(intent)
        }
        button2.setOnClickListener {
            val intent = Intent(this, BlocoActivity::class.java)
            startActivity(intent)
        }

    }

    private fun startMqtt() {

        mqttHelper = MqttHelper(getApplicationContext())
        mqttHelper!!.mqttAndroidClient.setCallback(object : MqttCallbackExtended {

            override fun connectComplete(b: Boolean, s: String) {
                Log.w("Debug", "Connected")

            }

            override fun connectionLost(throwable: Throwable) {}

            @Throws(Exception::class)
            override fun messageArrived(topic: String, mqttMessage: MqttMessage) {
                Log.w("Debug", mqttMessage.toString())

                if (topic == "umidade1") {

                    val estado = String(mqttMessage.payload)

                    data?.text = "UMIDADE: $estado %"

                }
                if (topic == "luminosidade1") {

                    val estado2 = String(mqttMessage.payload)

                    data2?.text = "LUMINOSIDADE: $estado2 lux"

                }


            }

            override fun deliveryComplete(iMqttDeliveryToken: IMqttDeliveryToken) {}
        })
    }

    private fun jsonrequest2() {
        request2 = JsonArrayRequest(JSON_URL2, Response.Listener { response ->
            var jsonObject2: JSONObject
            for (i in 0 until response.length()) {
                try {
                    jsonObject2 = response.getJSONObject(i)
                    data?.text = ("UMIDADE:" + jsonObject2.getString("valor") + " %")
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
        }, Response.ErrorListener { })
        requestQueue2 = Volley.newRequestQueue(this@MainActivity)
        requestQueue2?.add(request2)
    }

    private fun jsonrequest3() {
        request3 = JsonArrayRequest(JSON_URL3, Response.Listener { response ->
            var jsonObject: JSONObject
            for (i in 0 until response.length()) {
                try {
                    jsonObject = response.getJSONObject(i)
                    data2?.text = "LUMINOSIDADE:" + jsonObject.getString("valor") + " lux"
                    data3?.text = "Última medição realizada em: " + jsonObject.getString("datahora")
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
        }, Response.ErrorListener { })
        requestQueue3 = Volley.newRequestQueue(this@MainActivity)
        requestQueue3?.add(request3)
    }

    fun ClosedRange<Int>.random() =
        Random().nextInt(endInclusive - start) + start

    companion object {
        var data: TextView? = null
        var data2: TextView? = null
        var data3: TextView? = null
    }
}
