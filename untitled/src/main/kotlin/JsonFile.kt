import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import kotlin.collections.ArrayList

class JsonFile {
    private val gson = Gson()
    private val FILENAME: String = "data.json"
    private val file = File(FILENAME)


    // Save a list of user in JsonFile
    fun saveList(dataList: ArrayList<User>) {
        val jsonData = gson.toJson(dataList)
        file.writeText(jsonData)
    }

    // Load a list if user from JsonFile
    fun loadList(): ArrayList<User> {
        val jsonData = file.readText()
        val type = object : TypeToken<List<User>>() {}.type
        try {
            return gson.fromJson<ArrayList<User>>(jsonData, type)
        } catch (e: NullPointerException) {
            return ArrayList()
        }

    }

    fun createUser(user: User){
        val listOfUser = loadList()
        listOfUser.add(user)
        saveList(listOfUser)
    }

    fun isUserInFile(id: String): Boolean {
        val listOfUsers: ArrayList<User> = loadList()
        var user: User? = null
        var isFind = false
        for (userList: User in listOfUsers) {
            if (userList.id == id) {
                isFind = true
            }
        }
        return isFind

    }

    fun insertUser(user: User) {
        val listOfUsers: ArrayList<User> = loadList()
        if (!isUserInFile(user.id)) {
            listOfUsers.add(user)
            saveList(listOfUsers)
        }
    }

    fun getAllUser(): ArrayList<User> {
        return loadList()
    }

    fun getUserById(id: String): User? {
        val listOfUsers = loadList()
        var user: User? = null
        for (userList: User in listOfUsers) {
            if (userList.id == id) {
                user = userList
                break
            }
        }

        return user;
    }



    fun updateUser(user: User): Boolean {
        val listOfUsers: ArrayList<User> = loadList()
        var userFind: User? = null
        var isUpdate: Boolean = false
        if (isUserInFile(user.id)) {
            userFind = getUserById(user.id)
            if (userFind != null) {
                listOfUsers.remove(userFind)
                listOfUsers.add(user)
                saveList(listOfUsers)
                isUpdate = true
            } else {
                isUpdate = false
            }
        }
        return isUpdate
    }

    fun removeUser(user: User): Boolean {
        val listOfUsers = loadList()
        var isRemove = false
        if (isUserInFile(user.id)) {
            listOfUsers.remove(user)
            saveList(listOfUsers)
            isRemove = true
        }
        return isRemove
    }

    fun removeUserById(id: String): Boolean {
        val listOfUser: ArrayList<User> = loadList()
        var isRemove = false
        if (isUserInFile(id)) {
            val user: User? = getUserById(id)
            listOfUser.remove(user)
            isRemove = true
            saveList(listOfUser)
        }

        return isRemove
    }

    fun getListOfCarByUserId(id: String): ArrayList<Car> {
        val listOfUsers = loadList()
        var listOfCars: ArrayList<Car> = ArrayList()
        for (userList: User in listOfUsers) {
            if (userList.id == id) {
                listOfCars = userList.listOfCars
                break
            }
        }
        return listOfCars
    }

    fun getCarByLicense(user: User,license: String): Car?{
        val listOfCars = getListOfCarByUserId(user.id)
        var carReturn: Car? = null
        for (car: Car in listOfCars){
            if( car.carLicense == license ) {
                carReturn = car
                break
            }

        }
        return carReturn
    }

    fun updateCarByLicense(user: User, car: Car) {
        val listOfCars = getListOfCarByUserId(user.id)
        val carFinding = getCarByLicense(user, car.carLicense)
        listOfCars.remove(carFinding)
        listOfCars.add(car)
        user.listOfCars = listOfCars
        updateUser(user)
    }

    fun deleteCarById(user: User, car: Car){
        val listOfCars = getListOfCarByUserId(user.id)
        val carFinding = getCarByLicense(user, car.carLicense)
        listOfCars.remove(carFinding)
        user.listOfCars = listOfCars
        updateUser(user)

    }






}