package Screens;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuscaFilmes extends JFrame implements ActionListener {
    JTextField txtbuscaFilmes = new JTextField("");
    JButton Buscar = new JButton("Buscar");
    JLabel erro = new JLabel("Digite o nome de um filme!!");

    public BuscaFilmes() {
        setTitle("Deseja buscar qual filme ?");
        setVisible(true);
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        iniciaTemplate();
    }

    private void iniciaTemplate()  {
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        txtbuscaFilmes.setMaximumSize(new Dimension(500, 100));
        txtbuscaFilmes.setAlignmentX(Component.LEFT_ALIGNMENT);
        Buscar.setAlignmentX(Component.LEFT_ALIGNMENT);
        erro.setVisible(false);
        add(txtbuscaFilmes);
        add(erro);
        add(Buscar);
        Buscar.setActionCommand("Buscar");
        Buscar.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e){
        try
        {
            if (e.getActionCommand().equalsIgnoreCase("Buscar"))
            {
                if (txtbuscaFilmes.getText().isEmpty())
                {
                    erro.setVisible(true);
                }
                else
                {
                    erro.setVisible(false);

                    new ResultadoPesquisa(txtbuscaFilmes.getText());
                    setVisible(false);
                }

            }
        }
        catch(Exception exe){
            exe.printStackTrace();
        }
    }
}


