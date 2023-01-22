data class User(
    var id: String,
    var name: String = "No  name",
    var age: Int = 0,
    var salary: Double = 0.0,
    var isMen: Boolean,
    var maritateStatus: Char = 'S',
    var listOfCars: ArrayList<Car> = arrayListOf<Car>()
) {

    public fun addCar(car: Car) {
        this.listOfCars.add(car)
    }

    override fun toString(): String {
        var salida = "Id: '$id'\n" +
                "Nombre: '$name'\n" +
                "Edad: $age\n" +
                "Salario: $salary\n" +
                "Es hombre: $isMen\n" +
                "Estado civil: $maritateStatus\n" +
                "Lista de carros:\n"
        for (car in listOfCars) {
            salida += car
        }
        return salida
    }


}