import entidades.ImgComparacao;
import entidades.Post;
import flask.EnviaRequisicaoFlask;
import instaloader.RequisicaoInstaloader;
import posts.Descricao;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InstagramGraffitiDetector {

    private RequisicaoInstaloader requisicaoInstaloader = new RequisicaoInstaloader();
    private EnviaRequisicaoFlask enviaRequisicaoFlask = new EnviaRequisicaoFlask();
    private ImgComparacao imgComparacao = new ImgComparacao();

    public void detector() throws IOException {

        Scanner scr = new Scanner(System.in);;
        List<Post> postList;
        Descricao descricao = new Descricao();
        String path;
        Integer op;
        String porcentagens_siamese_model;

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
                if(postList.isEmpty())
                    System.out.println("Não há posts que satisfazem as condicoes");
                else{
                    System.out.println(postList.toString());
                    System.out.println();
                    tratamento_imgs(postList, path);
                    System.out.println("Entre com uma foto de uma pichação ou que contenha parte de uma pichação");
                    imgComparacao.setPath(scr.nextLine());
                    porcentagens_siamese_model=enviaRequisicaoFlask.predictSiamese(imgComparacao.getPath());
                    System.out.println(porcentagens_siamese_model);

//                    //envia para o servidor flask do modelo siames para fazer predict
//                      // envia a imgComparacao e a cada post da postlist
//                      // retorna uma list com as img que tiveram resultados positivos
                }
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

    public void tratamento_imgs(List<Post> postList, String path) {
        BufferedImage image = null;

        for (Post post : postList) {
            try {

                String aux = path+"\\"+post.getPath();
                image = ImageIO.read(new File(aux));

                ImageIO.write(image, "jpg", new File("C:\\img_predict\\images\\out.jpg"));
              //  ImageIO.write(image, "jpeg", new File("C:\\img_predict\\images\\out.jpeg"));
                System.out.println(post.getPath() + " Done");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
