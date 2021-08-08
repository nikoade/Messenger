package ge.akvinnadei.messenger.model


class User(var _userName : String,var _passwordHash: Integer, var _profession: String){

    private var userName: String
    private var passwordHash: Integer
    private var profession: String

    init {
        this.userName = _userName
        this.passwordHash = _passwordHash
        this.profession = _profession
    }

}