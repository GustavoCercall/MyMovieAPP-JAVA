package presenter;

import java.awt.*;

public class Filme
{
    public String titulo;
    public String sinopse;
    public String dataDeLancamento;
    public Image poster;

    public Filme(String movieTitle, String sinopse, String released, Image poster)
    {
        this.titulo = movieTitle;
        this.sinopse = sinopse;
        this.dataDeLancamento = released;
        this.poster = poster;
    }
}



