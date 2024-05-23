package banco;

public interface ServicioClientes {
    boolean agregarCliente(Cliente cliente);
    boolean eliminarCliente(Integer numero);
    Cliente consultarCliente(Integer numero);
    Cliente[] obtenerClientes();
    Cliente buscarClientesPorRFC(String rfc);
}
