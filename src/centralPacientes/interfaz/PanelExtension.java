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

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * Panel de manejo de extensiones
 */
public class PanelExtension extends JPanel implements ActionListener {

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando Opción 1
     */
    private static final String OPCION_1 = "OPCION_1";

    /**
     * Comando Opción 2
     */
    private static final String OPCION_2 = "OPCION_2";

    /**
     * Comando Opción 3
     */
    private static final String OPCION_3 = "OPCION_3";

    /**
     * Comando Opción 4
     */
    private static final String OPCION_4 = "OPCION_4";

    /**
     * Comando Opción 5
     */
    private static final String OPCION_5 = "OPCION_5";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación
     */
    private InterfazCentralPacientes principal;

    // -----------------------------------------------------------------
    // Atributos de interfaz
    // -----------------------------------------------------------------

    /**
     * Botón Opción 1
     */
    private JButton btnOpcion1;

    /**
     * Botón Opción 2
     */
    private JButton btnOpcion2;

    /**
     * Botón Opción 3
     */
    private JButton btnOpcion3;

    /**
     * Botón Opción 4
     */
    private JButton btnOpcion4;

    /**
     * Botón Opción 5
     */
    private JButton btnOpcion5;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del panel
     *
     * @param ventana Ventana principal
     */
    public PanelExtension(InterfazCentralPacientes ventana) {
        principal = ventana;

        setBorder(new TitledBorder("Opciones"));
        setLayout(new GridBagLayout());

        // Botón opción 1
        btnOpcion1 = new JButton("Cant. Hombres");
        btnOpcion1.setActionCommand(OPCION_1);
        btnOpcion1.addActionListener(this);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 0, 5);
        add(btnOpcion1, gbc);

        // Botón opción 2
        btnOpcion2 = new JButton("Cant. Mujeres");
        btnOpcion2.setActionCommand(OPCION_2);
        btnOpcion2.addActionListener(this);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 0, 5);
        add(btnOpcion2, gbc);

        // Botón opción 3
        btnOpcion3 = new JButton("Salir");
        btnOpcion3.setActionCommand(OPCION_3);
        btnOpcion3.addActionListener(this);
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 0, 5);
        add(btnOpcion3, gbc);

        /*// Botón opción 4
        btnOpcion4 = new JButton("Opción 4");
        btnOpcion4.setActionCommand(OPCION_4);
        btnOpcion4.addActionListener(this);
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 0, 5);
        add(btnOpcion4, gbc);

        // Botón opción 5
        btnOpcion5 = new JButton("Opción 5");
        btnOpcion5.setActionCommand(OPCION_5);
        btnOpcion5.addActionListener(this);
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 0, 5);
        add(btnOpcion5, gbc);*/
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Manejo de los eventos de los botones
     *
     * @param e Acción que generó el evento.
     */
    public void actionPerformed(ActionEvent e) {
        if (OPCION_1.equals(e.getActionCommand())) {
            principal.reqFuncOpcion1();
        }
        else if (OPCION_2.equals(e.getActionCommand())) {
            principal.reqFuncOpcion2();
        }
        else if (OPCION_3.equals(e.getActionCommand())) {
            principal.reqFuncOpcion3();
        }
        else if (OPCION_4.equals(e.getActionCommand())) {
            principal.reqFuncOpcion4();
        }
        else if (OPCION_5.equals(e.getActionCommand())) {
            principal.reqFuncOpcion5();
        }

    }

}
