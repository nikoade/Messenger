package ge.akvinnadei.messenger.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class TestUser(val userName: String ? = null, val passwordHash: Int, val profession: String ? = null)