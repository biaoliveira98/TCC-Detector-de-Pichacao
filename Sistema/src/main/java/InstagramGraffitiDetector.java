import entidades.Post;
import flask.EnviaRequisicaoFlask;
import instaloader.RequisicaoInstaloader;
import posts.Descricao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InstagramGraffitiDetector {

    private RequisicaoInstaloader requisicaoInstaloader = new RequisicaoInstaloader();
    private EnviaRequisicaoFlask enviaRequisicaoFlask = new EnviaRequisicaoFlask();

    public void detector() throws IOException {

        Scanner scr = new Scanner(System.in);;
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

                postList = filtragemPosts(postList);
                System.out.println(postList.toString());


            }

            System.out.println("Deseja fazer outra requisição: 1-Sim ou 2-Nao");
            op = scr.nextInt();

        }while(op != 2);

        System.out.println("Finalizando...");
    }

    public List<Post> filtragemPosts(List<Post> postList){

        List<Post> postsPichacao= new ArrayList<>();

        for (Post post : postList) {

            if(requisicaoInstaloader.getHashtag().getTag() == null) {
                if (post.getLocalizacao() != null) {
                    if (post.getLocalizacao().getLocal().equals(requisicaoInstaloader.getLocalizacao().getLocal())) {
                        if (post.getTipo().equals("pichacao")) {
                            postsPichacao.add(post);
                        }
                    }
                }
            }
            else
            {
                if (post.getTipo().equals("pichacao")) {
                    postsPichacao.add(post);
                }
            }
        }

        return postsPichacao;
    }

}
