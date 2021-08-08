import entidades.Post;
import flask.EnviaRequisicaoFlask;
import instaloader.RequisicaoInstaloader;
import org.json.JSONException;
import posts.Descricao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InstagramGraffitiDetector {

    public static void main(String[] args) throws IOException {

        RequisicaoInstaloader requisicaoInstaloader = new RequisicaoInstaloader();
        EnviaRequisicaoFlask enviaRequisicaoFlask = new EnviaRequisicaoFlask();
        List<Post> postList;
        Descricao descricao = new Descricao();
        String path;

        requisicaoInstaloader.definindoRequisicaoInstaloader();
        path = "C:\\ProjetosJupyterNotebooks\\data\\instaloaderData\\" + requisicaoInstaloader.getNomeDir() + "\\imgs";
        postList = enviaRequisicaoFlask.predictImagem(path);
        if (postList.isEmpty())
            System.out.println("Não há posts");
        else{
            descricao.defineDescricao(postList,path);  //TODO ainda n está salvando certo, ver como relacionar nome do arquivo txt com o da imagem


        System.out.println(postList.get(0).getDescricao());
        System.out.println();
        System.out.println(postList.get(1).getDescricao());}
    }



}
