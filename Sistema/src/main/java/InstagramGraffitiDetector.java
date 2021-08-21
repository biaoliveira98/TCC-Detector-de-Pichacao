import entidades.Post;
import flask.EnviaRequisicaoFlask;
import instaloader.RequisicaoInstaloader;
import posts.Descricao;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class InstagramGraffitiDetector {

    public void detector() throws IOException {

        Scanner scr = new Scanner(System.in);;
        RequisicaoInstaloader requisicaoInstaloader = new RequisicaoInstaloader();
        EnviaRequisicaoFlask enviaRequisicaoFlask = new EnviaRequisicaoFlask();
        List<Post> postList;
        Descricao descricao = new Descricao();
        String path;
        Integer op;

        requisicaoInstaloader.definindoUsuarioSistema();

        do{
            requisicaoInstaloader.definindoRequisicaoInstaloader();
            path = "C:\\ProjetosJupyterNotebooks\\data\\instaloaderData\\" + requisicaoInstaloader.getNomeDir() + "\\imgs";
            postList = enviaRequisicaoFlask.predictImagem(path);
            if (postList.isEmpty())
                System.out.println("Não há posts");
            else{
                descricao.defineDescricao(postList,path);

                System.out.println(postList.toString());
            }

            System.out.println("Deseja fazer outra requisição: 1-Sim ou 2-Nao");
            op = scr.nextInt();

        }while(op != 2);

        System.out.println("Finalizando...");
    }



}
