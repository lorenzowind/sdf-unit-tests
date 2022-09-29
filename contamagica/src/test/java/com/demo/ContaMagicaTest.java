package com.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

public class ContaMagicaTest {
    public ContaMagica cm;

    @BeforeEach
    public void setUp() {
       cm = new ContaMagica("123000-6", "Bob Esponja");
    }
    @Test
    public void criacaoDeContaComValorLimiteMinimo(){
        boolean falhou = false;
        try {
            ContaMagica cm2 = new ContaMagica("99999-45", "Lula Molusco");
            System.out.println(cm2.getNumeroConta());
        } catch (IllegalNumberException e) {
            falhou = true;
            System.out.println(e.getMessage());

        }
        Assertions.assertFalse(falhou);
    }
    @Test
    public void criacaoDeContaComValorMenorQueLimiteMinimo(){
        boolean falhou = false;
        try {
            ContaMagica cm3 = new ContaMagica("99998-44", "Patrick Estrela");
            System.out.println(cm3.getNumeroConta());
        } catch (IllegalNumberException e) {
            falhou = true;
            System.out.println(e.getMessage());
        }
        Assertions.assertTrue(falhou);
    }
    @Test
    public void criacaoDeContaComValorMenorQueLimiteMinimo2(){
        boolean falhou = false;
        try {
            ContaMagica cm3 = new ContaMagica("99990-37", "Patrick Estrela");
            System.out.println(cm3.getNumeroConta());
        } catch (IllegalNumberException e) {
            falhou = true;
            System.out.println(e.getMessage());
        }
        Assertions.assertTrue(falhou);
    }
    @Test
    public void criacaoDeContaComValorMenorQueLimiteMinimo3(){
        boolean falhou = false;
        try {
            ContaMagica cm3 = new ContaMagica("9999-37", "Patrick Estrela");
            System.out.println(cm3.getNumeroConta());
        } catch (IllegalNumberException e) {
            falhou = true;
            System.out.println(e.getMessage());
        }
        Assertions.assertTrue(falhou);
    }
    @Test
    public void criacaoDeContaComValorMenorQueLimiteMinimo4(){
        boolean falhou = false;
        try {
            ContaMagica cm3 = new ContaMagica("99999-99", "Patrick Estrela");
            System.out.println(cm3.getNumeroConta());
        } catch (IllegalNumberException e) {
            falhou = true;
            System.out.println(e.getMessage());
        }
        Assertions.assertTrue(falhou);
    }
    // MOVIMENTAÇÔES BÁSICAS DE CONTA DE BANCO
    @Test
    public void retiradaDeValorAleatorioMaiorQueSaldo() {
        cm.deposito(240000.00);
        double maxValorParaRetirada = cm.getSaldo() * 2; // limite para o gerador de numeros pseudoaleatorios nao retirar um valor absurdo da conta, no caso eh o dobro 
        Assertions.assertEquals(false, cm.retirada((int)ThreadLocalRandom.current().nextDouble(cm.getSaldo()+1, maxValorParaRetirada)));
    }

    @Test
    public void retiradaDeValorAleatorioMenorOuIgualAoSaldo() {
        cm.deposito(240000.00);
        Assertions.assertEquals(true, cm.retirada((int)ThreadLocalRandom.current().nextDouble(1, cm.getSaldo())));
    }

    // UPGRADE/DOWNGRADE DE CATEGORIA DA CONTA
    @Test
    public void depositaDinheiroMasNaoMudaCategoria(){
        cm.deposito(2000000.00);
        System.out.println("Saldo: " + cm.getSaldo());
        System.out.println("Categoria: " + cm.getCategoria());
        Assertions.assertEquals(Categoria.SILVER, cm.getCategoria());
    }
    @Test
    public void depositaDinheiroMasNaoMudaCategoria2(){
        cm.deposito(200000.00);
        System.out.println("Saldo: " + cm.getSaldo());
        System.out.println("Categoria: " + cm.getCategoria());
        Assertions.assertEquals(Categoria.SILVER, cm.getCategoria());
    }
    @Test
    public void depositaMaisDinheiroPassandoParaGold(){
        cm.deposito(200000.00);
        cm.deposito(1000.00);
        System.out.println("Saldo: " + cm.getSaldo());
        System.out.println("Categoria: " + cm.getCategoria());
        Assertions.assertEquals(Categoria.GOLD, cm.getCategoria());
    }
    @Test
    public void depositaMaisDinheiroPassandoParaPlatinum(){
        cm.deposito(201000.00);
        cm.deposito(1000.00);
        System.out.println("Saldo: " + cm.getSaldo());
        System.out.println("Categoria: " + cm.getCategoria());
        Assertions.assertEquals(Categoria.PLATINUM, cm.getCategoria());
    }
    @Test
    public void depositaDinheiroPassaParaPlatinumMasPerdeMetade(){
        cm.deposito(201000.00);
        cm.deposito(1000.00);
        cm.retirada(100000.00);
        cm.retirada(2000.00);
        System.out.println("Saldo: " + cm.getSaldo());
        System.out.println("Categoria: " + cm.getCategoria());
        Assertions.assertEquals(Categoria.PLATINUM, cm.getCategoria());
    }
    @Test
    public void depositaDinheiroPassaParaPlatinumMasRetiraPassandoParaGold(){
        cm.deposito(201000.00);
        cm.deposito(1000.00);
        cm.retirada(103000.00);
        cm.retirada(2000.00);
        System.out.println("Saldo: " + cm.getSaldo());
        System.out.println("Categoria: " + cm.getCategoria());
        Assertions.assertEquals(Categoria.GOLD, cm.getCategoria());
    }
    @Test
    public void depositaDinheiroPassaParaPlatinumMasPerdeTudo(){
        cm.deposito(201000.00);
        cm.deposito(1000.00);
        cm.retirada(178000.00);
        cm.retirada(1000.00);
        System.out.println("Saldo: " + cm.getSaldo());
        System.out.println("Categoria: " + cm.getCategoria());
        Assertions.assertEquals(Categoria.SILVER, cm.getCategoria());
    }
    @Test
    public void depositaDinheiroMasPerdeMaisQuePossui(){
        cm.deposito(2000.00);
        //cm.retirada(2200.00);
        Assertions.assertEquals(false, cm.retirada(2200.00));
        System.out.println("Saldo: " + cm.getSaldo());
        System.out.println("Categoria: " + cm.getCategoria());
    }
    @Test
    public void depositaDinheiroSobeDeCategoriaMasPerdeTudo(){
        // esperado: nao há valorização de depósito ainda, mas a conta será reconhecida como PLATINUM
        cm.deposito(201000.00);
        cm.deposito(1000.00);
        //cm.retirada(202000.00);
        Assertions.assertEquals(true, cm.retirada(202000.00));
        System.out.println("Saldo: " + cm.getSaldo());
        System.out.println("Categoria: " + cm.getCategoria());
    }
    @Test
    public void depositaDinheiroSobeDeCategoriaPerdeTudoMasFicaComValorValorizadoPlatinum(){
        cm.deposito(201000.00);
        cm.deposito(1000.00);
        cm.deposito(20000.00);
        //cm.retirada(202000.00);
        Assertions.assertEquals(true, cm.retirada(202000.00));
        System.out.println("Saldo: " + cm.getSaldo());
        System.out.println("Categoria: " + cm.getCategoria());
    }
}
