package ge.akvinnadei.messenger.model

import com.google.firebase.database.IgnoreExtraProperties
import java.io.Serializable

@IgnoreExtraProperties
data class User(
    val id: String? = null,
    val userName: String? = null,
    val passwordHash: Int? = null,
    val profession: String? = null,
    val img: String? = null)