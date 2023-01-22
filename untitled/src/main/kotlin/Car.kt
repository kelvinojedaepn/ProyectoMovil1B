data class Car(
    var doorNumber: Int,
    var mileage: Double,
    var isNew: Boolean = (mileage == 0.0),
    var carLicense: String,
    var beginCarLicense: Char = carLicense.get(0),
    var ownerId: String = ""
) {


    override fun toString(): String {
        return "Numero puertas: $doorNumber " +
                " -> Kilometros recorridos: $mileage " +
                " -> Es nuevo: $isNew " +
                " -> Licencia de carro: '$carLicense '" +
                " -> Inicial licencia de carro: ${carLicense.get(0)}"
    }


}