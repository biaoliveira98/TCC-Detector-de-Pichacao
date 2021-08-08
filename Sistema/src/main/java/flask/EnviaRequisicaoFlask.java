package flask;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class EnviaRequisicaoFlask {

    private List<File> imagens;
    private List<String> predictsImagens;

    public void predictImagem(String path) throws IOException {

        PreparaImagens preparaImagens = new PreparaImagens();
        setImagens(pegaImagens(path));
        if(getImagens() != null) {
            HttpPost request = new HttpPost("http://192.168.15.14:5000/predict");
            HttpResponse response;
            CloseableHttpClient httpClient = null;
            List<String> responseList = new ArrayList<>();
            StringEntity params;
            JSONObject json;
            try {
                for (File f : imagens) {

                    String encodstring = preparaImagens.encodeFileToBase64Binary(f);

                    json = new JSONObject();
                    json.put("data", encodstring);
                    json.put("type", "image");

                    httpClient = HttpClientBuilder.create().build();

                    params = new StringEntity(json.toString());
                    request.addHeader("content-type", "application/json");
                    request.setEntity(params);
                    response = httpClient.execute(request);
                    System.out.println("Enviado com sucesso");
                    responseList.add(new BasicResponseHandler().handleResponse(response));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                httpClient.close();
            }

            setPredictsImagens(responseList);
            System.out.println(predictsImagens.toString());
        }
        else
        {
            System.out.println("Nenhuma imagem foi encontrada");
        }
    }

    public List<File> pegaImagens(String path) {

        File diretorioDeImagens = new File(path);

        // Listo todos os arquivos do diretório
        File[] imagensDoDiretorio = diretorioDeImagens.listFiles();
        List<File> arquivos = new ArrayList<File>();

        // Jogo no ArrayList apenas os que possuem extensão 'jpg'
        for (int i=0; i<imagensDoDiretorio.length; i++) {
            if (imagensDoDiretorio[i].getName().endsWith("jpg")) {
                arquivos.add(imagensDoDiretorio[i]);
            }
        }

        return arquivos;
    }



    public List<File> getImagens() {
        return imagens;
    }

    public void setImagens(List<File> imagens) {
        this.imagens = imagens;
    }


    public List<String> getPredictsImagens() {
        return predictsImagens;
    }

    public void setPredictsImagens(List<String> predictsImagens) {
        this.predictsImagens = predictsImagens;
    }


}