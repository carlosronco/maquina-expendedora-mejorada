public class MaquinaExpendedoraMejorada {
    
    // El precio del billete
    private int precioBillete;
    // La cantidad de dinero que lleva metida el cliente actual
    private int balanceClienteActual;
    // El total de dinero almacenado en la maquina desde su ultimo vaciado
    private int totalDineroAcumulado;
    // El origen del billete
    private String estacionOrigen;
    // El destino del billete
    private String estacionDestino;
    // El número de billetes vendidos
    private int numeroBilletesVendidos;
    // Tipo de máquina (normal o con premios)
    private boolean tipoPremio;
    // Número maximo de billetes que puede vender
    private int maximoBilletes;
    
    /**
     * Crea una maquina expendedora de billetes de tren con el 
     * precio del billete y el origen y destino dados. Se asume que el precio
     * del billete que se recibe es mayor que 0.
     * (normal = false, con premios = true)
     */
    public MaquinaExpendedoraMejorada(int precioDelBillete, String origen, String destino, boolean tipo, int maximoDeBilletes) {
        precioBillete = precioDelBillete;
        balanceClienteActual = 0;
        totalDineroAcumulado = 0;
        estacionOrigen = origen;
        estacionDestino = destino;
        numeroBilletesVendidos = 0;
        tipoPremio = tipo;
        maximoBilletes = maximoDeBilletes;
    }
    
    public MaquinaExpendedoraMejorada(boolean tipo, int maximoDeBilletes) {
        precioBillete = 20;
        balanceClienteActual = 0;
        totalDineroAcumulado = 0;
        estacionOrigen = "León";
        estacionDestino = "Madrid";
        tipoPremio = tipo;
        maximoBilletes = maximoDeBilletes;
    }

    /**
     * Devuelve el precio del billete
     */
    public int getPrecioBillete() {
        return precioBillete;
    }

    /**
     * Devuelve la cantidad de dinero que el cliente actual lleva introducida
     */
    public int getBalanceClienteActual() {
        return balanceClienteActual;
    }

    /**
     * Simula la introduccion de dinero por parte del cliente actual
     */
    public void introducirDinero(int cantidadIntroducida) {
        if (numeroBilletesVendidos < maximoBilletes) {
            if (cantidadIntroducida > 0) {
                balanceClienteActual = balanceClienteActual + cantidadIntroducida;
            }
            else {
                System.out.println(cantidadIntroducida + " no es una cantidad de dinero valida.");
            } 
        } else {
            System.out.println("Ya se ha vendido el máximo de billetes, no puedes meter más dinero");
        }      
    }

    /**
     * Imprime un billete para el cliente actual
     */
    public void imprimirBillete() {
        int cantidadDeDineroQueFalta = precioBillete - balanceClienteActual;
        if (numeroBilletesVendidos < maximoBilletes) {
            if (cantidadDeDineroQueFalta <= 0) {        
                // Simula la impresion de un billete
                System.out.println("##################");
                System.out.println("# Billete de tren:");
                System.out.println("# De " + estacionOrigen + " a " + estacionDestino);
                System.out.println("# " + precioBillete + " euros.");
                System.out.println("##################");
                System.out.println();         
            
                // Premio por la compra de un billete
                if (tipoPremio) {
                    System.out.println("Has ganado un 25% de descuento ("+precioBillete*0.25+"€) para compras en BurgerKing");
                }
                // Actualiza el total de dinero acumulado en la maquina
                totalDineroAcumulado = totalDineroAcumulado + precioBillete;
                // Reduce el balance del cliente actual dejandole seguir utilizando la maquina
                balanceClienteActual = balanceClienteActual - precioBillete;
                // Actualiza el total de billetes vendidos
                numeroBilletesVendidos++;
            }
            else {
                System.out.println("Necesitas introducir " + cantidadDeDineroQueFalta + " euros mas!");       
            }
        } else {
            System.out.println("Ya se ha vendido el máximo de billetes, no puedes comprar otro.");
        }       
    }
    
    /**
     * Cancela la operacion de compra del cliente actual y le
     * devuelve al cliente el dinero que ha introducido hasta el momento
     */
    public int cancelarOperacionYDevolverDinero() {
        int cantidadDeDineroADevolver;
        cantidadDeDineroADevolver = balanceClienteActual;
        balanceClienteActual = 0;
        return cantidadDeDineroADevolver;
    }
    
    /**
     * Se vacía todo el dinero que hay en la máquina
     */
    public int vaciarDineroDeLaMaquina() {
        int dineroExtraido = 0;
        if (balanceClienteActual == 0) {
            dineroExtraido = totalDineroAcumulado;
            balanceClienteActual = 0;
            totalDineroAcumulado = 0;
        } else {
            System.out.println("No se puede vaciar la máquina, hay una operación en curso");
            dineroExtraido = -1;
        }
        return dineroExtraido;
    }
    
    /**
     * Se cuentan los billetes vendidos
     */
    public int getNumeroBilletesVendidos() {
        return numeroBilletesVendidos;
    }
}
