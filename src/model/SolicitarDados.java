package model;

import org.json.JSONObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SolicitarDados extends Thread {
    private DadosDaRequisicaoListener listener;
    public static final String URL_REQUEST = "http://www.omdbapi.com/?t=TITULO&apikey=fa4ee9ef";

    public  void recebeTitulo(String nomedofilme) {

            nomedofilme = nomedofilme.replaceAll(" ", "+");

            String Url_tratada = URL_REQUEST.replaceAll("TITULO", nomedofilme);
            nomedofilme = Url_tratada;

            buscaFilme(nomedofilme);
    }

    public  void buscaFilme(String tratada) {
        Thread tr = new Thread() {
            @Override
            public void run() {
                String recebeDados = "";
                Image cartaz;
                try {
                    HttpURLConnection http = conexao(tratada);
                    recebeDados = buscaInfoFilme(http);
                    cartaz = lerCartaz(getURL(recebeDados));
                    listener.converteDados(recebeDados, cartaz);//usando o listener encadeado

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        tr.start();

    }

    public static HttpURLConnection conexao(String urlMontada) throws IOException{
        HttpURLConnection connection = null;
        URL url = new URL(urlMontada);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        return connection;
    }

    public static String buscaInfoFilme(HttpURLConnection conexao) throws IOException{
        InputStream input = conexao.getInputStream();
        BufferedReader leitor = new BufferedReader(new InputStreamReader(input));
        StringBuilder response = new StringBuilder();
        String linha = null;
        while ((linha = leitor.readLine()) != null)
        {
            response.append(linha);
            response.append("\r");
        }
        leitor.close();

        return response.toString();
    }

   public void setListener(DadosDaRequisicaoListener drlistener) {
       listener = drlistener;
    }//encadeamento
    public static Image lerCartaz(String posterUrl) throws IOException
    {
        Image cartaz = null;
        URL url = new URL(posterUrl);
        cartaz = ImageIO.read(url);
        return cartaz;
    }

    public String getURL(String json){ // PEGAR URL DA IMAGEM
        JSONObject jobject = new JSONObject(json);
        return jobject.get("Poster").toString();
    }

   }
