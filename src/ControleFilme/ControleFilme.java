package ControleFilme;

import model.DadosDaRequisicaoListener;
import model.SolicitarDados;
import org.json.JSONObject;
import presenter.Filme;
import presenter.FilmeListener;

import java.awt.*;

public class ControleFilme implements DadosDaRequisicaoListener {
    private FilmeListener listener;
    private SolicitarDados solicitante;


    private static ControleFilme controlador;
    public ControleFilme(){
        solicitante = new SolicitarDados();
        solicitante.setListener(this);

    }

    @Override
    public void converteDados(String convertedora, Image poster) {// Tratamento do Json
        JSONObject responseMovie = new JSONObject(convertedora);
        String movieTitle = responseMovie.get("Title").toString();
        String sinopse = responseMovie.get("Plot").toString();
        String released = responseMovie.get("Released").toString();
        Filme nomedofilme = new Filme(movieTitle, sinopse,released, poster);// passando o novo filme
        listener.infoFilme(nomedofilme);

    }

    public void chamarDados(String dadosoriginais){
        solicitante.recebeTitulo(dadosoriginais);
    }

    public static ControleFilme capturainstancia(){
        if(controlador == null){
            controlador = new ControleFilme();

        }
        return controlador;
    }

    public void setListener(FilmeListener fl){
        listener = fl;
    }
}

