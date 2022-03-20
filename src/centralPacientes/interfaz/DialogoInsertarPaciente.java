/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad Ean (Bogot� - Colombia)
 * Departamento de Tecnolog�as de la Informaci�n y Comunicaciones
 * Licenciado bajo el esquema Academic Free License version 2.1
 * <p>
 * Proyecto Central de Pacientes.
 * Adaptado de CUPI2 (Uniandes)
 * Fecha: Febrero 2021
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package centralPacientes.interfaz;

import centralPacientes.mundo.NoExisteException;
import centralPacientes.mundo.YaExisteException;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Di�logo para registrar un paciente
 */
public class DialogoInsertarPaciente extends JDialog implements ActionListener {
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
    private final static String AGREGAR = "Agregar";

    private final static String CANCELAR = "Cancelar";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es una referencia a la ventana principal
     */
    private InterfazCentralPacientes principal;

    /**
     * El tipo de adici�n que se va a realizar (antes o despu�s del paciente dado)
     */
    private int formaAdicion;

    /**
     * El c�digo del paciente con relaci�n al cual se va a realizar la adici�n
     */
    private int codigo;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Panel para ingresar la informaci�n del paciente
     */
    private PanelInformacionPaciente panelInformacion;

    /**
     * Bot�n para agregar un paciente
     */
    private JButton botonAgregar;

    /**
     * Bot�n para cancelar la adici�n del paciente
     */
    private JButton botonCancelar;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del di�logo
     *
     * @param ventana  Referencia a la ventana principal - ventana!=null
     * @param tAdicion Tipo de adici�n que se va a realizar. tAdicion pertenece a {ANTES, DESPUES, COMIENZO, FINAL}
     * @param elCodigo El c�digo del paciente con relaci�n al cual se va a realizar la adici�n.
     */
    public DialogoInsertarPaciente(InterfazCentralPacientes ventana, int tAdicion, int elCodigo) {
        super(ventana, true);
        principal = ventana;
        codigo = elCodigo;
        formaAdicion = tAdicion;
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(298, 307));

        setTitle("Central de Pacientes");
        setResizable(false);

        // Panel para ingresar la informaci�n
        panelInformacion = new PanelInformacionPaciente();
        GridBagConstraints gbc = new GridBagConstraints();
        panelInformacion.cambiarInformacionComboClinicas(principal.darClinicasCentral());
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 4;
        gbc.fill = GridBagConstraints.BOTH;
        add(panelInformacion, gbc);

        // Panel con los botones de agregar - cancelar
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridBagLayout());
        botonAgregar = new JButton();
        botonAgregar.setActionCommand(AGREGAR);
        botonAgregar.addActionListener(this);
        botonAgregar.setIcon(new ImageIcon("data/agregar.gif"));
        botonAgregar.setToolTipText("Agregar Paciente");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 0, 0, 10);
        panelBotones.add(botonAgregar, gbc);

        botonCancelar = new JButton();
        botonCancelar.setActionCommand(CANCELAR);
        botonCancelar.addActionListener(this);
        botonCancelar.setIcon(new ImageIcon("data/cancelar.gif"));
        botonCancelar.setToolTipText("Cancelar");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        panelBotones.add(botonCancelar, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.BOTH;
        add(panelBotones, gbc);

        pack();
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Manejo de eventos de los botones
     */
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        if (comando.equals(AGREGAR)) {
            try {
                int elCodigo = panelInformacion.darCodigoPaciente();
                String elNombre = panelInformacion.darNombrePaciente();
                String laClinica = panelInformacion.darClinicaPaciente();
                String laHistoria = panelInformacion.darInformacionMedicaPaciente();
                int elSexo = panelInformacion.darSexoPaciente();

                if (elCodigo < 0) {
                    JOptionPane.showMessageDialog(this, "El c�digo debe ser un n�mero positivo", "Adici�n de Pacientes", JOptionPane.ERROR_MESSAGE);
                }
                else if (elNombre == null || elNombre.equals("")) {
                    JOptionPane.showMessageDialog(this, "Debe ingresar el nombre del paciente", "Adici�n de Pacientes", JOptionPane.ERROR_MESSAGE);
                }
                else if (laClinica == null || laClinica.equals("")) {
                    JOptionPane.showMessageDialog(this, "Debe ingresar la cl�nica del paciente", "Adici�n de Pacientes", JOptionPane.ERROR_MESSAGE);
                }
                else if (laHistoria == null || laHistoria.equals("")) {
                    JOptionPane.showMessageDialog(this, "Debe ingresar la historia clinica del paciente", "Adici�n de Pacientes", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    principal.agregarPaciente(formaAdicion, codigo, elCodigo, elNombre, laClinica, laHistoria, elSexo);
                    principal.refrescarListaPacientes();
                    dispose();
                }
            }
            catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El c�digo debe ser un valor numerico", "Adici�n de Pacientes", JOptionPane.ERROR_MESSAGE);
            }
            catch (YaExisteException e1) {
                JOptionPane.showMessageDialog(this, e1.getMessage(), "Adici�n de Pacientes", JOptionPane.ERROR_MESSAGE);
            }
            catch (NoExisteException e1) {
                JOptionPane.showMessageDialog(this, "El paciente con c�digo " + codigo + " con relaci�n al cual se va a realizar la adici�n no se encuentra registrado", "Adici�n de Pacientes", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (comando.equals(CANCELAR)) {
            dispose();
        }
    }
}
