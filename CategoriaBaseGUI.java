package co.edu.upb.categorias;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class CategoriaBaseGUI extends JFrame {
    private static final long serialVersionUID = 7089710511457049136L;
    private JComboBox<String> categoriaComboBox;
    private JTextField pinField;
    private JButton establecerPinButton;
    private JButton accederButton;
    private JButton volverButton;
    private CategoriaBase categoriaBase;
    private HashMap<String, Integer> pines = new HashMap<>();

    public CategoriaBaseGUI() {
        setTitle("Menú Principal");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        categoriaComboBox = new JComboBox<>();
        pinField = new JTextField();
        establecerPinButton = new JButton("Establecer PIN");
        accederButton = new JButton("Acceder");
        volverButton = new JButton("Volver al Menú");

        panel.add(categoriaComboBox);
        panel.add(pinField);
        panel.add(establecerPinButton);
        panel.add(accederButton);
        panel.add(volverButton);

        // Agregar las 6 categorías al ComboBox
        categoriaComboBox.addItem("Compras");
        categoriaComboBox.addItem("Eventos");
        categoriaComboBox.addItem("Trabajo");
        categoriaComboBox.addItem("Estudio");
        categoriaComboBox.addItem("Viaje");
        categoriaComboBox.addItem("Deportes");

        establecerPinButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String categoria = (String) categoriaComboBox.getSelectedItem();
                try {
                    int nuevoPin = Integer.parseInt(pinField.getText());
                    pines.put(categoria, nuevoPin);
                    JOptionPane.showMessageDialog(CategoriaBaseGUI.this, "Nuevo PIN establecido para " + categoria + ".");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(CategoriaBaseGUI.this, "Por favor, ingrese un número válido para el PIN.");
                }
            }
        });

        accederButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String categoria = (String) categoriaComboBox.getSelectedItem();
                try {
                    int pinIngresado = Integer.parseInt(pinField.getText());
                    int pinGuardado = pines.getOrDefault(categoria, -1);
                    if (pinIngresado == pinGuardado) {
                        setCategoriaBase(new CategoriaBase(categoria));
                        JOptionPane.showMessageDialog(CategoriaBaseGUI.this, "Acceso concedido a " + categoria + ".");
                        // Puedes agregar funcionalidad específica de la categoría aquí.
                    } else {
                        JOptionPane.showMessageDialog(CategoriaBaseGUI.this, "Acceso denegado. PIN incorrecto.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(CategoriaBaseGUI.this, "Por favor, ingrese un número válido para el PIN.");
                }
            }
        });

        volverButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Regresar al menú principal
                getContentPane().removeAll();
                revalidate();
                repaint();
            }
        });

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CategoriaBaseGUI();
            }
        });
    }

	public CategoriaBase getCategoriaBase() {
		return categoriaBase;
	}

	public void setCategoriaBase(CategoriaBase categoriaBase) {
		this.categoriaBase = categoriaBase;
	}
}

