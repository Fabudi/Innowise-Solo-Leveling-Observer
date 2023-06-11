package inc.fabudi.innowisesololevelingobserver

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View.INVISIBLE
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), Subscriber {
    private lateinit var tempLabel: TextView
    private lateinit var tempProgressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tempLabel = findViewById(R.id.weatherTemperatureLabel)
        tempProgressBar = findViewById(R.id.progressBar)
        findViewById<Button>(R.id.nextButton).setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
        findViewById<Button>(R.id.prevButton).visibility = INVISIBLE
        WeatherObserver.subscribe(this)
        WeatherObserver.startTimer()
    }

    override fun update(weatherTemperature: Int) {
        tempLabel.text = "$weatherTemperature°"
        tempProgressBar.progress = weatherTemperature
    }

    override fun onStop() {
        super.onStop()
        WeatherObserver.unsubscribe(this)
    }
}