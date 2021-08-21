package flask;

import entidades.Post;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
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

    public List<Post> predictImagem(String path) throws IOException {

        List<Post> posts = new ArrayList<>();
        PreparaImagens preparaImagens = new PreparaImagens();
        setImagens(pegaImagens(path));

        if(getImagens() != null) {
            HttpPost request = new HttpPost("http://192.168.15.11:5000/predict");
            HttpResponse response;
            HttpHost target = new HttpHost(request.getURI().getHost(), 5000, "https");
            CloseableHttpClient httpClient = null;
            int timeout=30;
            RequestConfig config = RequestConfig.custom()
                    .setConnectTimeout( timeout * 1000)
                    .setConnectionRequestTimeout(timeout * 1000)
                    .setSocketTimeout(timeout * 1000).build();
            String r;
            StringEntity params;
            JSONObject json;
            try {
                for (File f : imagens) {
                    Post post = new Post();
                    String encodstring = preparaImagens.encodeFileToBase64Binary(f);

                    json = new JSONObject();
                    json.put("data", encodstring);
                    json.put("type", "image");

                    httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).disableContentCompression().build();

                    params = new StringEntity(json.toString());
                    request.addHeader("content-type", "application/json");
                    request.setEntity(params);
                    response = httpClient.execute(request);
                    System.out.println("Enviado com sucesso");
                    r = new BasicResponseHandler().handleResponse(response);
                    post.setPath(f.getName());
                    post.setTipo(r);
                    posts.add(post);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                httpClient.close();
            }

        }
        else
        {
            System.out.println("Nenhuma imagem foi encontrada");
        }

        return posts;
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

}