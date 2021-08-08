package ge.akvinnadei.messenger.model


class User(_userName: String, _passwordHash: Int, _profession: String){

    private var userName: String
    private var passwordHash: Int
    private var profession: String
    private var contacts: ArrayList<User>

    init {
        this.userName = _userName
        this.passwordHash = _passwordHash
        this.profession = _profession
        this.contacts = ArrayList<User>()
    }

    fun getUserName(): String {
        return userName;
    }

    fun setUserName(_userName: String) {
        userName = _userName;
    }

    fun getProfession(): String {
        return profession;
    }

    fun setProfession(_profession: String) {
        profession = _profession;
    }

    fun setPasswordHash(_passwordHash: Int){
        passwordHash = _passwordHash;
    }

    fun getContacts(): ArrayList<User>{
        return contacts;
    }

    fun setContacts(_contacts: ArrayList<User>){
        contacts = _contacts
    }

    fun addContact(_user: User){
        contacts.add(_user)
    }

    override fun toString(): String {
        var toPrint = "user name: " + userName + "\n"
        toPrint += "profession: " + profession + "\n"
        return toPrint
    }

    fun printContacts(){
        println(userName + "'s contacts:")
        for (contact in contacts){
            println(contact.toString())
        }
    }

}