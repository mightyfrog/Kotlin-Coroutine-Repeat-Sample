package org.mightyfrog.android.kotlincoroutinerepeatsample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * @author Shigehiro Soejima
 */
class MainActivity : AppCompatActivity(), CoroutineScope {
    private val job = Job()

    override val coroutineContext: CoroutineContext get() = Dispatchers.Main + job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            launch {
                repeat(10) {
                    withContext(Dispatchers.Default) {
                        Thread.sleep(1000L)
                    }
                    val time = java.util.Date(System.currentTimeMillis()).toString()
                    Log.d("coroutine", time)
                    textView.text = time
                    // return@launch to exit prematurely
                    return@repeat
                }
            }

//             as retry
//            launch {
//                repeat(10) {
//                    try {
//                        // do something
//                        return@launch
//                    } catch (e: Exception) {
//                        return@repeat
//                    }
//                }
//            }
        }
    }

    override fun onDestroy() {
        job.cancel()

        super.onDestroy()
    }
}
