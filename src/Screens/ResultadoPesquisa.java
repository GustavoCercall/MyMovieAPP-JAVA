package Screens;

import ControleFilme.ControleFilme;
import model.SolicitarDados;
import presenter.Filme;
import presenter.FilmeListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResultadoPesquisa extends JFrame implements ActionListener, FilmeListener {
    JLabel Titulodofilme = new JLabel("");
    JLabel Sinopse = new JLabel("");
    JLabel Datadelancamento = new JLabel("");
    JLabel poster = new JLabel();
    JButton voltar = new JButton("VOLTAR");
    private String Nomedofilme;

    public ResultadoPesquisa(String text) {
        this.Nomedofilme = text;
        ControleFilme.capturainstancia().setListener(this);//instanciando a classe controlefilme,
        setTitle("Filme Encontrado:");
        setVisible(true);
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        iniciaTemplate();

    }

    private void iniciaTemplate() {
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        Titulodofilme.setAlignmentX(Component.LEFT_ALIGNMENT);
        Sinopse.setAlignmentX(Component.LEFT_ALIGNMENT);
        Datadelancamento.setAlignmentX(Component.LEFT_ALIGNMENT);

        add(poster);
        add(Titulodofilme);
        add(Sinopse);
        add(Datadelancamento);
        add(voltar);

        voltar.setActionCommand("voltar");
        voltar.addActionListener(this);
        ControleFilme.capturainstancia().chamarDados(this.Nomedofilme);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            if(e.getActionCommand().equalsIgnoreCase("Voltar")){
                setVisible(false);
                new BuscaFilmes();
            }

        }
        catch(Exception exe){
            exe.printStackTrace();
        }

    }

    @Override
    public void infoFilme(Filme filme) { // receber os dados prontos, implementa filmelistner
        Titulodofilme.setText(filme.titulo);
        Sinopse.setText(filme.sinopse);
        Datadelancamento.setText(filme.dataDeLancamento);
        poster.setIcon(new ImageIcon(filme.poster));
    }
}
