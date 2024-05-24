package banco;

import java.util.TreeSet;

public interface ServicioClientes {
    boolean agregarCliente(Cliente cliente);
    boolean eliminarCliente(Integer numero);
    Cliente consultarCliente(Integer numero);
    TreeSet<Cliente> obtenerClientes();
    Cliente buscarClientesPorRFC(String rfc);
}
