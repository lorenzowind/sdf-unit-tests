package com.demo;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

public class ContaMagicaTest {
    private ContaMagica cm;

    @BeforeEach
    public void setUp() {
       cm = new ContaMagica("12010-12010", "Bob Esponja");
    }
    

    // MOVIMENTAÇÔES BÁSICAS DE CONTA DE BANCO
    @Test
    public void retiradaDeValorAleatorioMaiorQueSaldoRetornaFalse() {
        cm.deposito(24000.00);
        double maxValorParaRetirada = cm.getSaldo() * 2;
        Assertions.assertEquals(false, ThreadLocalRandom.current().nextDouble(cm.getSaldo(), maxValorParaRetirada + 1));
    }

    @Test
    public void retiradaDeValorAleatorioMenorIgualQueSaldoRetornaTrue() {
        cm.deposito(24000.00);
        Assertions.assertEquals(true, cm.retirada(ThreadLocalRandom.current().nextDouble(1, cm.getSaldo() + 1)));
    }

    // CRIAÇÃO INAPROPRIADA DE CONTAS
    // @Test
    // public void criaContaComNumeroErrado() {
    //     try {
    //         ContaMagica CONTA_TESTE = new ContaMagica("0000000", "Nicolas0192");    
    //         Assertions.assertEquals(true, false);
    //     } catch (IllegalNumberException e) {
    //     }
    // }

    // UPGRADE/DOWNGRADE DE CATEGORIA DA CONTA
    @Test
    public void depositaDinheiroMasFazUpgradeParaMaisDeUmaCategoria(){
        cm.deposito(200000.00);
        Assertions.assertEquals(Categoria.GOLD, cm.getCategoria());
    }

    // @Test
    // public void shouldAnswerWithTrue() {
    //     assertTrue(true);
    // }
}
