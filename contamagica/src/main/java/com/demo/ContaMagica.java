package com.demo;

public class ContaMagica {
    private String numero;
    private String nomeCorrentista;
    private double saldo;
    private Categoria categoria;

    public ContaMagica(String numero, String nomeCorrentista) throws IllegalNumberException, IllegalNameException{
        this.numero = numero;
        this.nomeCorrentista = nomeCorrentista;
        this.saldo = 0.0; 
        this.categoria = Categoria.SILVER;
        verificaNome(nomeCorrentista);
        verificaNroConta(numero);
     }

    public String getNumeroConta(){
        return numero;
    }

    public String getNomeCorrentista(){
        return nomeCorrentista;
    }

    public double getSaldo(){
        return saldo;
    }

    public Categoria getCategoria(){
        return categoria;
    }

    public boolean deposito(double valor){
        if (valor <= 0.0){
            return false;
        }
        if (categoria == Categoria.SILVER && saldo < 50000){
            saldo += valor;
            return true;
        }
        if (categoria == Categoria.SILVER && saldo >= 50000){
            //saldo += valor;
            if (saldo > 200000){
                saldo += valor; 
                categoria = Categoria.PLATINUM;
                return true;
            }
            else{ 
                saldo += valor;
                categoria = Categoria.GOLD;
                return true;
            }    
            //return true;
        }
        if (categoria == Categoria.GOLD && saldo <= 200000){
            saldo += valor+(valor*0.01); 
            return true;
        }
        if (categoria == Categoria.GOLD && saldo > 200000){
            saldo += valor+(valor*1.01); 
            categoria = Categoria.PLATINUM;
            return true;
        }
        if (categoria == Categoria.PLATINUM){
            saldo += valor+(valor*1.02); 
            return true;
        }
        return false;
    }

    public boolean retirada(double valor){
        if (valor <= 0.0){
            return false;
        }
        if (categoria == Categoria.PLATINUM && saldo >= 100000){
            saldo -= valor;
            return true;
        }
        if (categoria == Categoria.PLATINUM && saldo < 100000){ 
            saldo -= valor;
            if(saldo < 25000){
                categoria = Categoria.SILVER;
                return true;
            }
            else{
                categoria = Categoria.GOLD;
                return true;
            }
        }
        if (categoria == Categoria.GOLD && saldo >= 25000){
            saldo -= valor;
            return true;
        }
        if (categoria == Categoria.GOLD && saldo < 25000){ 
            saldo -= valor;
            categoria = Categoria.SILVER;
            return true;
        }
        if (categoria == Categoria.SILVER && saldo-valor > 0){
            saldo -= valor;
            return true;
        }
        return false;
    }

    private void verificaNroConta(String numero) {
        int posTraco = numero.indexOf('-');
        String nroStr = numero.substring(0,posTraco);
        int nroConta = Integer.parseInt(nroStr);
        int verificador = Integer.parseInt(numero.substring(posTraco+1));
        
        if (nroConta < 99999 || nroConta > 999999){
            throw new IllegalNumberException();
        }
        int soma = 0;
        for(int i=0;i<nroStr.length();i++){
            soma += (Integer.parseInt(nroStr.charAt(i)+""));
        }
        if (soma != verificador){
            throw new IllegalNumberException();
        }
    }

    private void verificaNome(String nome){ 
        if (nome.length() < 3){
            throw new IllegalNameException();
        }
    }
    
    @Override
    public String toString() {
        return "ContaMagica [categoria=" + categoria + ", nomeCorrentista=" + nomeCorrentista + ", numero=" + numero
                + ", saldo=" + saldo + "]";
    }

}
