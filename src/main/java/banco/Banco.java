package banco;

import java.util.ArrayList;
import java.util.Objects;

public class Banco implements ServicioClientes{
    private String nombre;
    private Domicilio domicilio;
    private String rtc;
    private String telefono;
    private ArrayList<Cliente> clientes;

    public Banco(String nombre, Domicilio domicilio, String rtc, String telefono) {
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.rtc = rtc;
        this.telefono = telefono;
        this.clientes = new ArrayList<Cliente>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public String getRtc() {
        return rtc;
    }

    public void setRtc(String rtc) {
        this.rtc = rtc;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    @Override
    public String toString() {
        return "Banco{" +
                "\nnombre='" + nombre + '\'' +
                "domicilio=" + domicilio +
                "rtc='" + rtc + '\'' +
                "telefono='" + telefono + '\'' +
                "clientes=" + clientes +
                '}';
    }

    public String mostrarInfoBanco() {
        return "//------------------------------------- Informacion Banco --------------------------------------//"
                +"\n- nombre: " + nombre
                +"\n- domicilio: " + domicilio
                +"\n- rtc: " + rtc
                +"\n- telefono: " + telefono;
    }

    @Override
    public boolean agregarCliente(Cliente cliente) {
        if(clientes.isEmpty() || !clientes.contains(cliente)){
            clientes.add(cliente);
            return true;
        }
        return false;
    }

    @Override
    public boolean eliminarCliente(Integer numero) {
        for(Cliente cliente: clientes){
            if (Objects.equals(cliente.getNumero(), numero)){
                clientes.remove(cliente);
                return true;
            }
        }
        return false;
    }

    @Override
    public Cliente consultarCliente(Integer numero) {
        for (Cliente cliente : clientes) {
            if (Objects.equals(cliente.getNumero(), numero)){
                return cliente;
            }
        }
        return null;
    }

    @Override
    public Cliente[] obtenerClientes() {
        return clientes.toArray(new Cliente[clientes.size()]);
    }

    @Override
    public Cliente buscarClientesPorRFC(String rfc) {
        for (Cliente cliente : clientes) {
            if (cliente.getRfc().equals(rfc)) {
                return cliente;
            }
        }
        return null;
    }
}
