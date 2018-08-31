package org.mightyfrog.android.kotlincoroutinerepeatsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.coroutines.experimental.DefaultDispatcher
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.withContext

/**
 * @author Shigehiro Soejima
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { _ ->
            launch(UI) {
                repeat(10) {
                    withContext(DefaultDispatcher) {
                        Thread.sleep(1000L)
                    }
                    textView.text = java.util.Date(System.currentTimeMillis()).toString()
                    // return@launch to exit prematurely
                    return@repeat
                }
            }

            // as retry
//            launch(UI) {
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
}
