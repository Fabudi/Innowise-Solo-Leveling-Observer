package inc.fabudi.innowisesololevelingobserver

import android.os.Handler
import android.os.Looper
import android.util.Log

object WeatherObserver {
    private val subscribers = ArrayList<Subscriber>()

    lateinit var mainHandler: Handler

    private val updateTextTask = object : Runnable {
        override fun run() {
            notify(whatIsTheWeather())
            mainHandler.postDelayed(this, 5000)
        }
    }

    fun startTimer(){
        mainHandler = Handler(Looper.getMainLooper())
        mainHandler.post(updateTextTask)
    }
    fun stopTimer(){
        mainHandler.removeCallbacks(updateTextTask)
    }

    fun subscribe(s: Subscriber) {
        Log.d("Observer", "${s.javaClass.name} subscribed")
        subscribers.add(s)
    }

    fun unsubscribe(s: Subscriber) {
        Log.d("Observer", "${s.javaClass.name} unsubscribed")
        subscribers.remove(s)
    }

    fun notify(weatherTemperature: Int) {
        for (s in subscribers) s.update(weatherTemperature)
    }

    fun whatIsTheWeather(): Int {
        return (System.currentTimeMillis() % 35).toInt()
    }

}