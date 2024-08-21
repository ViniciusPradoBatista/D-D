package org.example

class Raca(
    val nome: String,
    val bonusForca: Int = 0,
    val bonusDestreza: Int = 0,
    val bonusConstituicao: Int = 0,
    val bonusInteligencia: Int = 0,
    val bonusSabedoria: Int = 0,
    val bonusCarisma: Int = 0
) {
    fun aplicarBonus(personagem: Personagem) {
        personagem.forca += bonusForca
        personagem.destreza += bonusDestreza
        personagem.constituicao += bonusConstituicao
        personagem.inteligencia += bonusInteligencia
        personagem.sabedoria += bonusSabedoria
        personagem.carisma += bonusCarisma
    }
}