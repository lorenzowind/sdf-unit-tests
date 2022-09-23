package com.demo;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

public class ContaMagicaTest {
    private ContaMagica cm = null;

    @BeforeAll
    void setUp() {
        cm = new ContaMagica("1234-100123", "Bob Esponja");
    }
    
    @AfterEach
    void cleanAcc(){
        cm.retirada(cm.getSaldo());
    }

    // MOVIMENTAÇÔES BÁSICAS DE CONTA DE BANCO
    @Test
    public void retiradaDeValorAleatorioMaiorQueSaldoRetornaFalse() {
        cm.deposito(29000.00);
        double doubleDeposito = cm.getSaldo() * 2;
        Assertions.assertEquals(false, ThreadLocalRandom.current().nextDouble(cm.getSaldo(), doubleDeposito + 1));
    }
    @Test
    public void retiradaDeValorAleatorioMenorIgualQueSaldoRetornaTrue() {
        cm.deposito(29000.00);
        Assertions.assertEquals(true, cm.retirada(ThreadLocalRandom.current().nextDouble(1, cm.getSaldo() + 1)));
    }

    // CRIAÇÃO INAPROPRIADA DE CONTAS
    @Test
    public void criaContaComNumeroErrado() {
        try {
            ContaMagica CONTA_TESTE = new ContaMagica("0000000", "Nicolas0192");    
            Assertions.assertEquals(true, false);
        } catch (IllegalNumberException e) {
        }
    }

    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }
}
