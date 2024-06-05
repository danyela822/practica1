package main;

import banco.Banco;
import banco.Cuenta;
import banco.CuentaDeAhorro;
import banco.CuentaDeCheque;
import banco.Cliente;
import banco.Domicilio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    public static Banco banco;
    public static Cliente cliente;
    public static void main(String[] args) throws IOException {
        practica1();
        practica8();
    }

    public static void practica8() throws IOException {

        System.out.println("\n// ------------------------------------------ PRACTCA 8 --------------------------------------------  //");

        Domicilio domicilio5 = new Domicilio("Calle 5",5, "Colonia 5", "Estado 5",121);
        Domicilio domicilio6 = new Domicilio("Calle 6",6, "Colonia 6", "Estado 6",141);
        Domicilio domicilio7 = new Domicilio("Calle 7",7, "Colonia 7", "Estado 7",151);

        Cliente cliente1 = new Cliente(1,"Cliente 1", domicilio5, "rfc 1", "Telefono 1", "fecha 1");
        Cliente cliente2 = new Cliente(2,"Cliente 2", domicilio6, "rfc 2", "Telefono 2", "fecha 2");
        Cliente cliente3 = new Cliente(3,"Cliente 3", domicilio7, "rfc 3", "Telefono 3", "fecha 3");

        List<Cuenta> cuentas = crearCuentas(obtenerCuentas());

        cliente1.getCuentas().addAll(cuentas.stream().filter(cuenta -> cuenta.getNumero() == 1).toList());
        cliente2.getCuentas().addAll(cuentas.stream().filter(cuenta -> cuenta.getNumero() == 2).toList());
        cliente3.getCuentas().addAll(cuentas.stream().filter(cuenta -> cuenta.getNumero() == 3).toList());

        List<Cliente> clientes = List.of(cliente1,cliente2,cliente3);
        clientes.forEach(cliente -> {
            System.out.println("NUMERO CLIENTE: "+cliente.getNumero());
            cliente.getCuentas().forEach(cuenta -> System.out.println(cuenta.toString()));
        });
    }

    public static List<Cuenta> crearCuentas(List<String> listaCuentas){

        List<Cuenta> cuentasAhorro = listaCuentas.stream().filter(cuentaAhorro -> cuentaAhorro.startsWith("CA"))
                .map(cuentaString -> cuentaString.substring(3,cuentaString.length()-1))
                .map(cuentaAH -> {
                    String datos[] = cuentaAH.split(",");
                    Cuenta ca = new CuentaDeAhorro(Double.parseDouble(datos[2].trim()),Integer.parseInt(datos[4].trim()),Double.parseDouble(datos[3].trim()));
                    ca.setFechaApertura(datos[1].trim());
                    return ca;
                }).toList();

        List<Cuenta> cuentasCheque = listaCuentas.stream().filter(cuentaCheque -> cuentaCheque.startsWith("CC"))
                .map(cuentaString -> cuentaString.substring(3,cuentaString.length()-1))
                .map(cuentaCC -> {
                    String datos[] = cuentaCC.split(",");
                    Cuenta cc = new CuentaDeCheque(Double.parseDouble(datos[2].trim()),Integer.parseInt(datos[4].trim()),Double.parseDouble(datos[3].trim()));
                    cc.setFechaApertura(datos[1].trim());
                    return cc;
                }).toList();

        List<Cuenta> cuentas = new ArrayList<>();
        cuentas.addAll(cuentasAhorro);
        cuentas.addAll(cuentasCheque);

        return cuentas;
    }

    public static List<String> obtenerCuentas() throws IOException {

        String ruta = "practica1/src/main/resources/cuentas.txt";
        String lineaActual;
        List<String> cuentas = new ArrayList<String>();

        try (FileReader lectorArchivo = new FileReader(ruta);
             BufferedReader lectorLinea = new BufferedReader(lectorArchivo)) {

            while ((lineaActual = lectorLinea.readLine()) != null) {
                cuentas.add(lineaActual);
            }
            System.out.println("------------- TEXTO CUENTAS ------------");
            cuentas.forEach(cuenta -> System.out.println(cuenta));
        }
        return cuentas;
    }

    public static void practica1(){

        System.out.println("\n// ------------------------------------------ PRACTCA 1 -------------------------------------------- //");

        // ------------------------------------------------- Domicilios ---------------------------------------------- //

        Domicilio domicilio1 = new Domicilio("Calle 1",1, "Colonia 1", "Estado 1", 123);
        Domicilio domicilio2 = new Domicilio("Calle 2",2, "Colonia 2", "Estado 2",456);
        Domicilio domicilio3 = new Domicilio("Calle 3",3, "Colonia 3", "Estado 3",789);
        Domicilio domicilio4 = new Domicilio("Calle 4", 4, "Colonia 4", "Estado 4", 101);

        // ------------------------------------------------- Cliente ---------------------------------------------- //
        Cliente cliente1 = new Cliente(4,"Juan Perez", domicilio1, "rfc 4", "Telefono 4", "fecha 4");
        Cliente cliente2 = new Cliente(5,"Luis Lopez", domicilio2, "rfc 5", "Telefono 5", "fecha 5");
        cliente = new Cliente(6, "Camilo Cortez", domicilio4, "rfc 6", "Telefono 6", "fecha 6");

        // ------------------------------------------------- Cuentas ---------------------------------------------- //

        Cuenta cuentaAhorro1 = new CuentaDeAhorro(1000000.0,1,0.1);
        Cuenta cuentaAhorro2 = new CuentaDeAhorro(1500000.0,2,0.2);

        CuentaDeCheque cuentaDeCheque1 = new CuentaDeCheque(2000000.0,3,2000.0);
        CuentaDeCheque cuentaDeCheque2 = new CuentaDeCheque(2500000.0,4,2500.0);

        // ------------------------------------ Se agregan cuentas a los clientes ----------------------------------- //

        cliente1.getCuentas().add(cuentaAhorro1);
        cliente1.getCuentas().add(cuentaDeCheque1);

        cliente2.getCuentas().add(cuentaAhorro2);
        cliente2.getCuentas().add(cuentaDeCheque2);

        // ------------------------------------- Se crea Banco y se agregan clientes -------------------------------- //

        banco = new Banco("Banca Electronica",domicilio3,"rtc3","Telefono 3");
        banco.getClientes().add(cliente1);
        banco.getClientes().add(cliente2);

        // ------------------------------------------- Mostrar informacion Banco -------------------------------------- //

        System.out.println(banco.mostrarInfoBanco());

        // ----------------------------------------- Mostrar informacion Clientes ------------------------------------ //

        System.out.println(cliente1.mostrarInfoCliente());
        System.out.println(cliente1.mostrarInfoCuentas());
        System.out.println(cliente2.mostrarInfoCliente());
        System.out.println(cliente2.mostrarInfoCuentas());

        // -------------------------------------------- Metodos Clientes --------------------------------------------- //
        llamarMetodosClientes();
        llamarMetodosCuentas();
    }

    public static void llamarMetodosClientes(){

        System.out.println("\n// ------------------------------------ Interfaz ServicioClientes -------------------------------------- //");

        //Agregar
        banco.agregarCliente(cliente);
        System.out.println("Cliente agregado:\n" + cliente.mostrarInfoCliente());

        //Eliminar
        boolean eliminado = banco.eliminarCliente(1);
        System.out.println("Eliminado: " + eliminado);

        //Consultar
        Cliente clienteBuscar = banco.consultarCliente(6);
        System.out.println("Consultado: \n" + clienteBuscar.mostrarInfoCliente());

        //Obtener
        TreeSet<Cliente> clientes = banco.obtenerClientes();
        System.out.println("\nTodos los clientes:\n");
        for (Cliente cliente : clientes) {
            System.out.println(cliente.mostrarInfoCliente());
        }

        //Buscar por RFC
        Cliente clienteRFC = banco.buscarClientesPorRFC("rfc 4");
        System.out.println("Cliente RFC: \n" + clienteRFC.mostrarInfoCliente());


        //Ordenar (TreeSet ordena automaicamente en forma ascendente)
        System.out.println("\nClientes ordendos por ID (Mayor a menor)");
        for (Cliente cliente : banco.getClientes()) {
            System.out.println(cliente.toString());
        }
    }

    public static void llamarMetodosCuentas(){

        System.out.println("\n // ------------------------------------ Interfaz ServicioCuentas -------------------------------------- //");

        //Agregar Cuenta
        Cuenta cuentaAhorro3 = new CuentaDeAhorro(500000.0, 5, 0.21);
        Cuenta cuentaCheque3 = new CuentaDeCheque(600000.0, 6, 6000.0);
        Cuenta cuentaCheque4 = new CuentaDeCheque(700000.0, 7, 7000.0);

        boolean cuentaAhorroAgregada = cliente.agregarCuenta(cuentaAhorro3);
        boolean cuentaChequeAgregada1 = cliente.agregarCuenta(cuentaCheque3);
        boolean cuentaChequeAgregada2 = cliente.agregarCuenta(cuentaCheque4);

        System.out.println("Cuenta ahorro: " + cuentaAhorroAgregada);
        System.out.println("Cuenta cheque: " + cuentaChequeAgregada1);
        System.out.println("Cuenta cheque: " + cuentaChequeAgregada2);

        //Cancelar
        boolean cancelada = cliente.cancelarCuenta(6);
        System.out.println("Cancelada: " + cancelada);

        //Abonar
        cliente.abonarCuenta(5, 50.0);
        System.out.println("Abonado 50.0 \nSaldo Actual: "+ cuentaAhorro3.getSaldo());

        //Retirar
        cliente.retirar(5, 100000.0);
        System.out.println("Se retiran 100000.0. Nuevo saldo es: " + cuentaAhorro3.getSaldo());

        //Obtener
        ArrayList<Cuenta> cuentas = cliente.obtenerCuentas();
        System.out.println("Todas las Cuentas de Cliente con Id: "+cliente.getNumero());
        for (Cuenta cuenta : cuentas) {
            System.out.println(cuenta.toString());
        }

        //Ordenar
        System.out.println("\nCuentas de un cliente por orden de saldo (Mayor a menor)");
        Collections.sort(cliente.getCuentas());
        for (Cuenta cuenta : cliente.getCuentas()) {
            System.out.println(cuenta.toString());
        }
    }
}