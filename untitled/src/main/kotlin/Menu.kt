import java.util.*

class Menu {
    private val scanner = Scanner(System.`in`)
    private val jsonFile = JsonFile()


    fun seleccionarInicioMensaje() {
        println(
            "APLICACIÓN DONDE UNA PERSONA CUENTA CON CERO 0 MÁS CARROS\n" +
                    "Seleccione una opcion del siguiente menú:\n"
        )
        val opciones = arrayListOf<String>(
            "Crear persona",
            "Actualizar persona",
            "Eliminar persona por id",
            "Mostrar persona por id",
            "Crear, actualizar, eliminar o mostrar carro",
            "Salir"
        )
        for (i in opciones.indices) {
            println("${i + 1}" + " " + opciones[i])
        }

        when (scanner.next()) {
            "1" -> {
                crearPersona()
                seleccionarInicioMensaje()
            }

            "2" -> {
                actualizarPersona("2")
                seleccionarInicioMensaje()
            }

            "3" -> {
                actualizarPersona("3")
                seleccionarInicioMensaje()
            }

            "4" -> {
                actualizarPersona("4")
                seleccionarInicioMensaje()
            }

            "5" -> {
                actualizarPersona("5")
                seleccionarInicioMensaje()
            }
        }

    }

    private fun eliminarPersona(user: User?) {
        if (jsonFile.removeUser(user!!)) {
            println("Se elimino correctamente al usuario")
        }
    }

    private fun actualizarPersona(opcion: String) {
        println("Ingrese id de la persona")
        val idPersona = scanner.next()
        if (jsonFile.isUserInFile(idPersona)) {
            val user = jsonFile.getUserById(idPersona)
            when (opcion) {
                "2" -> opcionesActualizarPersona(user!!)
                "3" -> eliminarPersona(user!!)
                "4" -> mostraPersona(user!!)
                "5" -> mostrarCrudCarroDadoUsuario(user!!)
            }

        } else {
            println("El usuario no existe aun")
        }

    }

    private fun mostraPersona(user: User?) {
        println(user.toString())
    }

    private fun opcionesActualizarPersona(user: User?) {
        println("Seleccione que desea actualizar")
        val opciones = arrayListOf<String>("Nombre", "Edad", "Salario", "Es hombre", "Estado civil")
        for (i in opciones.indices) {
            println("${i + 1}" + " " + opciones[i])
        }
        when (scanner.next()) {
            "1" -> {
                print("Ingrese el nombre: Str")
                user!!.name = scanner.next()
            }

            "2" -> {
                println("Ingrese la edad: Int")
                user!!.age = scanner.nextInt()
            }

            "3" -> {
                println("Ingrese su salario: Double")
                user!!.salary = scanner.nextDouble()
            }

            "4" -> {
                println("Es hombre ? (Responsa S para Si o N para no)")
                user!!.isMen = scanner.next() == "S"
            }

            "5" -> {
                println("Está casado ? (Responsa 'S' para Si o 'N' no)")
                user!!.maritateStatus = if (scanner.next() == "S") 'C' else 'S'
            }
        }
        jsonFile.updateUser(user!!)
    }

    private fun crearPersona() {
        println("Ingrese el número de cédula de la persona: Str")
        val id = scanner.next()

        if (!jsonFile.isUserInFile(id)) {
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
            println("Tiene carros: (Responsa 'S' para Si o 'N' no)")
            val hasCar = scanner.next()
            val user = User(id, name, age, salary, isMen, maritateStatus)
            jsonFile.createUser(user)
            confirmarAniadirCarro(hasCar, user)
        } else {
            println("El usuario ya existe")
        }

    }

    private fun confirmarAniadirCarro(hasCar: String?, user: User) {
        if (hasCar == "S") {
            println("Desea ingresar información de su vehículo (Responsa 'S' para Si o 'N' no)")
            val mensajeDeConfirmacionAniadirCarro = scanner.next()
            aniadirCarro(mensajeDeConfirmacionAniadirCarro, user)
        }
    }

    private fun aniadirCarro(mensajeDeConfirmacionAniadirCarro: String?, user: User) {
        if (mensajeDeConfirmacionAniadirCarro == "S") {
            do {
                println("Ingrese el número de placa: Str")
                val carLicense = scanner.next()
                if(jsonFile.getCarByLicense(user!!, carLicense) == null){
                    println("Ingrese el número de puertas de su vehiculo: Int")
                    val doorNumber = scanner.nextInt()
                    println("Ingese el recorrido en Km de su vehículo: Double")
                    val mileage = scanner.nextDouble()
                    var isNew = false
                    if(mileage == 0.0){
                        isNew = true
                    }

                    val beginCarLicense: Char = carLicense.get(0)
                    val car = Car(doorNumber, mileage, isNew, carLicense, beginCarLicense, user.id)
                    user.listOfCars.add(car)
                    jsonFile.updateUser(user)
                }else{
                    println("Existe un auto con el mismo número de placa")
                }

                println("Quieres añadir otro carro ? (Responsa 'S' para Si o 'N' no)")
            } while (scanner.next() == "S")
            mostrarCrudCarroDadoUsuario(user)
        }

    }

    private fun mostrarCrudCarroDadoUsuario(user: User) {
        val opciones =
            arrayListOf<String>("Mostrar carro/s", "Actualizar carro", "Eliminar carro", "Crear carro", "Salir")
        for (i in opciones.indices) {
            println("${i + 1}" + " " + opciones[i])
        }
        when (scanner.next()) {
            "1" -> {
                println(user.listOfCars)
                mostrarCrudCarroDadoUsuario(user)

            }

            "2" -> {
                actualizarCarro(user)
                mostrarCrudCarroDadoUsuario(user)

            }

            "3" -> {
                eliminarCarro(user)
                mostrarCrudCarroDadoUsuario(user)

            }

            "4" -> {
                aniadirCarro("S", user)
                mostrarCrudCarroDadoUsuario(user)
            }

        }
    }

    private fun eliminarCarro(user: User) {
        println("Introduzca la placa del carro")
        val placaCarro = scanner.next()
        val car = jsonFile.getCarByLicense(user, placaCarro)
        val listOfCar = jsonFile.getListOfCarByUserId(user.id)
        if (car != null) {
            listOfCar.remove(car)
            user.listOfCars = listOfCar
            jsonFile.updateUser(user)
        }

    }

    private fun actualizarCarro(user: User) {
        println("Introduzca la placa del carro")
        val placaCarro = scanner.next()
        val car = jsonFile.getCarByLicense(user, placaCarro)

        if (car != null) {
            val opciones = arrayListOf<String>("Numero de puertas", "Recorrido")
            for (i in opciones.indices) {
                println("${i + 1}" + " " + opciones[i])
            }
            println("Seleccione una opcion: ")
            when (scanner.next()) {
                "1" -> {
                    println("Actualice el numero de puertas: Int")
                    car.doorNumber = scanner.nextInt()
                    jsonFile.updateCarByLicense(user, car)
                }

                "2" -> {
                    println("Actualice el recorrido en Km: Double")
                    car.mileage = scanner.nextDouble()
                    car.isNew = car.mileage == 0.0
                    jsonFile.updateCarByLicense(user, car)
                }
            }

        } else {
            println("La persona aún no tiene carro o no cuenta con un carro con placa semejante")
        }

    }
}