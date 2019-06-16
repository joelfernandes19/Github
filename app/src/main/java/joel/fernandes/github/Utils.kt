package joel.fernandes.github

import android.text.format.DateUtils
import androidx.annotation.Nullable
import java.text.SimpleDateFormat
import java.util.*


fun getTimeAgo(@Nullable toParse: String): String {
    try {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
        val parsedDate = dateFormat.parse(toParse)
        val now = System.currentTimeMillis()
        return DateUtils.getRelativeTimeSpanString(parsedDate.time, now, DateUtils.SECOND_IN_MILLIS).toString()
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return "N/A"
}
