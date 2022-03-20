/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad Ean (Bogotá - Colombia)
 * Departamento de Tecnologías de la Información y Comunicaciones
 * Licenciado bajo el esquema Academic Free License version 2.1
 * <p>
 * Proyecto Central de Pacientes.
 * Adaptado de CUPI2 (Uniandes)
 * Fecha: Febrero 2021
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package centralPacientes.interfaz;

import centralPacientes.mundo.Paciente;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Panel donde se muestra la lista de pacientes registrados
 */
public class PanelListaPacientes extends JPanel implements ActionListener, ListSelectionListener {
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    private static final String AGREGAR = "Agregar Paciente";

    private static final String BUSCAR = "Buscar";

    private static final String ELIMINAR = "Eliminar";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es una referencia a la ventana principal
     */
    private InterfazCentralPacientes principal;

    // -----------------------------------------------------------------
    // Atributos de interfaz
    // -----------------------------------------------------------------
    /**
     * Componente para desplegar la lista de pacientes
     */
    private JList listaPacientes;

    /**
     * Barra de desplazamiento de la lista
     */
    private JScrollPane barraDesplazamientoLista;

    /**
     * Botón para insertar un paciente
     */
    private JButton botonAgregar;

    /**
     * Botón para eliminar un paciente
     */
    private JButton botonEliminar;

    /**
     * Botón para buscar un paciente
     */
    private JButton botonBuscar;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del panel
     *
     * @param ventana Referencia a la ventana principal
     */
    public PanelListaPacientes(InterfazCentralPacientes ventana) {
        principal = ventana;

        setLayout(new GridBagLayout());
        setBorder(new TitledBorder("Pacientes"));

        // Lista de pacientes
        listaPacientes = new JList<>();
        listaPacientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaPacientes.addListSelectionListener(this);
        barraDesplazamientoLista = new JScrollPane();
        barraDesplazamientoLista.setViewportView(listaPacientes);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 0, 10);
        add(barraDesplazamientoLista, gbc);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(3, 1, 10, 10));

        // Botones
        botonAgregar = new JButton();
        botonAgregar.setActionCommand(AGREGAR);
        botonAgregar.addActionListener(this);
        botonAgregar.setIcon(new ImageIcon("data/nuevo.gif"));
        botonAgregar.setToolTipText("Agregar Paciente");
        panelBotones.add(botonAgregar);

        botonBuscar = new JButton();
        botonBuscar.setActionCommand(BUSCAR);
        botonBuscar.addActionListener(this);
        botonBuscar.setIcon(new ImageIcon("data/buscar.gif"));
        botonBuscar.setToolTipText("Buscar Paciente");
        panelBotones.add(botonBuscar);

        botonEliminar = new JButton();
        botonEliminar.setActionCommand(ELIMINAR);
        botonEliminar.addActionListener(this);
        botonEliminar.setIcon(new ImageIcon("data/eliminar.gif"));
        botonEliminar.setToolTipText("Eliminar Paciente");
        panelBotones.add(botonEliminar);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(panelBotones, gbc);

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Actualiza la lista de pacientes en la interfaz
     */
    public void refrescarLista(ArrayList<Paciente> pacientes) {
        listaPacientes.setListData(pacientes.toArray());
    }

    /**
     * Retorna el paciente que se encuentra seleccionado de la lista. Si no hay un paciente seleccionado se retorna null.
     */
    private Paciente darPacienteSeleccionado() {
        return (Paciente) listaPacientes.getSelectedValue();

    }

    /**
     * Manejo de eventos de los botones
     *
     * @param e Evento que generó la acción - e != null.
     */
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        if (comando.equals(AGREGAR)) {
            principal.mostrarDialogoOpcionesAgregarPaciente();
        }
        else if (comando.equals(BUSCAR)) {
            principal.buscarPaciente();
        }
        else if (comando.equals(ELIMINAR)) {
            principal.eliminarPaciente();
        }
    }

    /**
     * Muestra la información del paciente seleccionado
     *
     * @param e El evento de cambio del ítem seleccionado en la lista
     */
    public void valueChanged(ListSelectionEvent e) {

        if (darPacienteSeleccionado() != null) {
            principal.mostrarInformacionPaciente(darPacienteSeleccionado());
        }
    }
}
