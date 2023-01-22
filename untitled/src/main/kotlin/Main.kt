import com.google.gson.Gson
import java.io.File
import java.lang.reflect.Array
import java.nio.file.Paths
import java.util.Scanner

fun main() {
    Menu().seleccionarInicioMensaje()

   /* val scanner = Scanner(System.`in`)
    val FILENAME: String = "data.json"
    val jsonFile = JsonFile()
    val file = File(FILENAME)


    println(mensajeInicio())
    val opcionMensajeInicio = responseOptionNumber(scanner)

    when (opcionMensajeInicio) {
        "1" -> {
            println("Ingrese el número de cédula de la persona: Str")
            val id = scanner.next()
            println("Ingrese el nombre: Str")
            val name = scanner.next()
            println("Ingrese la edad: Int")
            val age = scanner.nextInt()
            println("Ingrese su salario: Double")
            val salary = scanner.nextDouble()
            println("Es hombre ? (Responsa S para Si o N para no)")
            val isMenOpcion = scanner.next()
            val isMen: Boolean = isMenOpcion == "S"
            println("Está casado ? (Responsa 'S' para Si o 'N' no)")
            val isMaritateOption = scanner.next()
            val maritateStatus: Char = if (isMaritateOption == "S") 'C' else 'S'
            val listCarsOfUser = ArrayList<Car>()
            println("Tiene carros: (Responsa 'S' para Si o 'N' no)")
            val hasCar = scanner.next()
            when (hasCar) {
                "S" -> {
                    println("Desea ingresar información de su vehículo (Responsa 'S' para Si o 'N' no)")
                    val optionToStartWriteInfoOfCar = scanner.next()
                    when (optionToStartWriteInfoOfCar) {
                        "S" -> {

                            do {
                                println("Ingrese el número de puertas de su vehiculo: Int")
                                val doorNumber = scanner.nextInt()
                                println("Ingese el recorrido en Km de su vehículo: Double")
                                val mileage = scanner.nextDouble()
                                println("Ingrese si el vehiculo es nuevo o no ? (Responsa 'S' para Si o 'N' no)")
                                val isNewOption = scanner.next()
                                val isNew: Boolean = isNewOption == "S"
                                println("Ingrese el número de placa: Str")
                                val carLicense = scanner.next()
                                val beginCarLicense: Char = carLicense.get(0)
                                val car = Car(doorNumber, mileage, isNew, carLicense, beginCarLicense, id)
                                listCarsOfUser.add(car)
                                println("Quieres añadir otro carro ? (Responsa 'S' para Si o 'N' no)")
                                val addNewCar = scanner.next()
                            } while (addNewCar == "S")
                        }

                        "N" -> {

                        }
                    }

                }

                "N" -> {

                }
            }

            val listOfUser = jsonFile.loadList()
            val user = User(id, name, age, salary, isMen, maritateStatus, listCarsOfUser)
            if (!jsonFile.isUserInFile(user.id)) {
                listOfUser.add(user)
                jsonFile.saveList(listOfUser)
                println("Gracias por llenar la información")
            } else {
                println("El usuario ya existe!!")
            }


        }

        "2" -> {
            println("Ingrese el id de la persona")
            val id = scanner.next()
            val userFromFile: User? = jsonFile.getUserById(id)
            println(userFromFile)
            if (userFromFile != null) {
                println(mensajeModificacion())
                val modifieldOption = scanner.next()
                when (modifieldOption) {
                    "1" -> {
                        val isRemove = jsonFile.removeUser(userFromFile)
                        if (isRemove) {
                            println("Se eliminó al usuario correctamente")
                        }
                    }

                    "2" -> {
                        println(modificarInfoPersonaOCarro())
                        val modifieldOptionPersonOrCar = scanner.next()
                        when(modifieldOptionPersonOrCar){
                            "1" ->{
                                println("Ingrese el nombre: Str")
                                val nameMod = scanner.next()
                                println("Ingrese la edad: Int")
                                val ageMod = scanner.nextInt()
                                println("Ingrese su salario: Double")
                                val salaryMod = scanner.nextDouble()
                                println("Es hombre ? (Responsa S para Si o N para no)")
                                val isMenOpcionMod = scanner.next()
                                val isMenMod: Boolean = isMenOpcionMod == "S"
                                println("Está casado ? (Responsa 'S' para Si o 'N' no)")
                                val isMaritateOptionMod = scanner.next()
                                val maritateStatusMod: Char = if (isMaritateOptionMod == "S") 'C' else 'S'
                                userFromFile.name = nameMod
                                userFromFile.age = ageMod
                                userFromFile.isMen = isMenMod
                                userFromFile.maritateStatus = maritateStatusMod
                                jsonFile.updateUser(userFromFile)
                            }
                            "2" ->{
                                println("Lista de vehículos")
                                var listOfCar = jsonFile.getListOfCarByUserId(userFromFile.id)
                                for (car in listOfCar){
                                    println(car)
                                }
                                println("Ingrese la placa del vehiculo a modificar: ")
                                val license = scanner.next()
                                val car = jsonFile.getCarByLicense(userFromFile, license)
                                if( car != null){
                                    println("Ingrese el número de puertas de su vehiculo: Int")
                                    val doorNumber = scanner.nextInt()
                                    println("Ingese el recorrido en Km de su vehículo: Double")
                                    val mileage = scanner.nextDouble()
                                    println("Ingrese si el vehiculo es nuevo o no ? (Responsa 'S' para Si o 'N' no)")
                                    val isNewOption = scanner.next()
                                    val isNew: Boolean = isNewOption == "S"
                                    car.doorNumber = doorNumber
                                    car.mileage = mileage
                                    car.isNew = isNew
                                    jsonFile.updateCarByLicense(userFromFile, car)


                                }else{
                                    "El dueno no tiene un carro con esa placa"
                                }

                            }
                        }


                    }
                }
            } else {
                println("El usuario aún no existe !!")
            }

        }


    }*/
}


/*
private fun mensajeInicio(): String {
    val msgMenu = "APLICACIÓN DONDE UNA PERSONA CUENTA CON CERO 0 MÁS CARROS\n" +
            "Seleccione el siguiente menú:\n" +
            "1.- Crear una persona\n" +
            "2.- Modificar la información de una persona\n" +
            "Selecciones una opción: "
    return msgMenu
}

private fun mensajeModificacion(): String {
    val msgInicio =
        "Seleccione el siguiente menú:\n" +
                "1.- Eliminar una persona\n" +
                "2.- Actualizar los datos de una persona\n" +
                "Selecciones una opción: "
    return msgInicio
}

private fun modificarInfoPersonaOCarro(): String {
    val msgMenu =
        "Seleccione el siguiente menú:\n" +
                "1.- Modificar informacion del usuario\n" +
                "2.- Modificar ifnormacion del carro\n" +
                "Selecciones una opción: "
    return msgMenu
}

private fun responseOptionNumber(scanner: Scanner): String {
    return scanner.nextLine()
}

*/
