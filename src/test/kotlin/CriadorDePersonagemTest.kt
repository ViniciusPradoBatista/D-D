package org.example

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CriadorDePersonagemTest {

    @Test
    fun `CriarPersonagem`() {
        val raca = Raca("Elfo", "Alto elfo", mapOf("Destreza" to 2, "Inteligencia" to 1))
        val strategy = object : DistribuicaoAtributosStrategy {
            override fun distribuir(personagem: Personagem, pontos: Int) {
                personagem.forca = 8
                personagem.destreza = 12
                personagem.constituicao = 10
                personagem.inteligencia = 13
                personagem.sabedoria = 10
                personagem.carisma = 10
            }
        }
        val criador = CriadorDePersonagem(strategy)
        val personagem = criador.criarPersonagem(raca)

        assertEquals(14, personagem.destreza)
        assertEquals(14, personagem.inteligencia)
    }
}