package org.example

class CriadorDePersonagem(
    private val distribuicaoStrategy: DistribuicaoAtributosStrategy
) {
    fun criarPersonagem(raca: Raca): Personagem {
        val personagem = Personagem()
        distribuicaoStrategy.distribuir(personagem, 27)
        raca.aplicarBonus(personagem)  // Chama o método aplicarBonus
        personagem.calcularPontosDeVida()
        return personagem
    }
}