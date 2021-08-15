package ge.akvinnadei.messenger.model

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Duration
import java.time.LocalDateTime
import java.time.Month
import java.util.*
import java.util.concurrent.TimeUnit

@RequiresApi(Build.VERSION_CODES.O)
data class Message(val value: String? = null, val sender: String? = null,
                   val receiver: String? = null, val date: Long? = null)