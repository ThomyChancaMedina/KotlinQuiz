package com.kotlinquiz.app.data.server.provider

import com.kotlinquiz.app.data.server.model.TestQuestion


fun listQuestion(): List<String> {
    return listOf(

        "1. El Principio de segregación es lo mismo que “Interface Segregation Principle”",
        "2. ¿si en una clase estoy mostrando los datos y a la vez esta misma clase trae los datos para mostrarlos en la View, estoy violando Single Responsibility Principle?",
        "3. ¿El Single Responsibility principle me ayuda a que mi interfaz sea bien visto por el usuario?",
        "4. ¿Es obligatorio que mi clase tenga muchos métodos públicos ?",
        "5. ¿Si una clase tiene muchos import, es que estoy haciendo un buen trabajo en cada una de mis otras clases y no estoy haciendo trabajo de mas?",
        "6. ¿si no soy capaz de escribir mi test unitarios tengo que mirar mis interfaces como guiá?",
        "7. ¿Mi clase tiene muchas lineas? Es un indicio de que no estoy organizando mi código y sera menos manejable",
        "8. ¿Por reglas de Open Close el extender el comportamiento de mi App sin modificar mi código anterior?, estamos violación de esta regla",
        "9. ¿Crear clases polimórficas es importante, ya que esta nos ayuda acoplar nuevas funcionalidades sin necesidad de modificar otras clases o métodos?",
        "10. ¿El Polimorfismo quiere decir que una clase padre es única y solo éste puede ser insustituible en el y que sus hijas comparten o puede adoptar la forma del padre?",
        "11. ¿La clase padre es algo que se puede cambiar?",
        "12. La frase usar herencia es hipotecar su software, Quiere decir que todo funciona bien al principio, pero con el tiempo te vas dando cuenta que ya no funciona bien la clase como al principio cuando la creaste por falta de más funcionalidades que necesitas crear",
        "13. Las herencias son lo mismo que las composiciones",
        "14. La composición es aquella que tiene  instancia de una clase que a su ves contiene instancia de otras clases implementando  funciones necesarias",
        "15. En la composición, delegamos responsabilidades en colaboradores designados para ello",
        "16. Las herencias se suelen adaptarse a cambias, evitando así el llamado “Spaguetti code”",
        "17. Se dice que al usar composiciones podemos elegir si vamos a tener un 0, 1 o N elementos con los que interactuar al mismo tipo",
    )
}



    fun getItemsForProvider(): List<TestQuestion> {
        Thread.sleep(2000)
        return listQuestion().mapIndexed { idx, value ->
            TestQuestion(
                idx,
                value,
                0,
                ""
            )
        }
    }

