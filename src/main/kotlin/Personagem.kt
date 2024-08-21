package org.example

data class Personagem(
    var forca: Int = 8,
    var destreza: Int = 8,
    var constituicao: Int = 8,
    var inteligencia: Int = 8,
    var sabedoria: Int = 8,
    var carisma: Int = 8,
    var pontosDeVida: Int = 10
) {
    fun calcularPontosDeVida() {
        pontosDeVida = 10 + ((constituicao - 10) / 2)
    }
    
}