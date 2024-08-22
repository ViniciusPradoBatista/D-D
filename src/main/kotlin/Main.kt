package org.example


fun main() {
    val personagem = Personagem()
    val distribuidor = DistribuicaoPersonalizada()

    distribuidor.distribuirAtributos(personagem)
    personagem.calcularPontosDeVida()

    println("Personagem criado:")
    println("Força: ${personagem.forca}")
    println("Destreza: ${personagem.destreza}")
    println("Constituição: ${personagem.constituicao}")
    println("Inteligência: ${personagem.inteligencia}")
    println("Sabedoria: ${personagem.sabedoria}")
    println("Carisma: ${personagem.carisma}")
    println("Pontos de Vida: ${personagem.pontosDeVida}")
}