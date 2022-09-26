package com.demo;

// import static org.junit.Assert.assertTrue;
// import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

public class ContaMagicaTest {
    public static ContaMagica cm;

    @BeforeAll
    public static void setUp() {
       cm = new ContaMagica("123000-6", "Bob Esponja");
    }
    
    // MOVIMENTAÇÔES BÁSICAS DE CONTA DE BANCO
    @Test
    public void retiradaDeValorAleatorioMaiorQueSaldo() {
        cm.deposito(24000.00);
        double maxValorParaRetirada = cm.getSaldo() * 2; // adicionei um teto para o gerador de numeros pseudoaleatorios nao retirar um valor absurdo da conta, no caso eh o dobro 
        Assertions.assertEquals(false, cm.retirada((int)ThreadLocalRandom.current().nextDouble(cm.getSaldo()+1, maxValorParaRetirada)));
    }

    @Test
    public void retiradaDeValorAleatorioMenorIgualQueSald() {
        cm.deposito(24000.00);
        Assertions.assertEquals(true, cm.retirada((int)ThreadLocalRandom.current().nextDouble(1, cm.getSaldo() + 1)));
    }

    // UPGRADE/DOWNGRADE DE CATEGORIA DA CONTA
    @Test
    public void depositaDinheiroMasNaoMudaCategoria(){
        cm.deposito(200000.00);
        Assertions.assertEquals(Categoria.SILVER, cm.getCategoria());
    }
    @Test
    public void depositaMaisDinheiroPassandoParaPlatinum(){
        cm.deposito(cm, 1000.00);
        Assertions.assertEquals(Categoria.GOLD, cm.getCategoria());
    }
    // @Test
    // public void shouldAnswerWithTrue() {
    //     assertTrue(true);
    // }
}
