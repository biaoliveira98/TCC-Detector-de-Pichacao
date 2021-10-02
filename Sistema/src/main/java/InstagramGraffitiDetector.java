import entidades.ImgComparacao;
import entidades.Post;
import flask.EnviaRequisicaoFlask;
import instaloader.RequisicaoInstaloader;
import posts.Descricao;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

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
                    scr.nextLine();
                    //resizeImage(imagem usuario);
                    imgComparacao.setPath("C:\\img_predict\\img_usuario\\img_usuario.jpeg"); //scr.nextLine() TODO
                    porcentagens_siamese_model=enviaRequisicaoFlask.predictSiamese(imgComparacao.getPath());
                    System.out.println("Antes");
                    System.out.println(postList.toString());
                    postList = submetendoPorcentagemSimilaridade(postList, porcentagens_siamese_model);
                    System.out.println("Depois");
                    System.out.println(postList.toString());
                    //System.out.println(porcentagens_siamese_model);

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
        Integer cont = 1;

        for (Post post : postList) {
            try {

                String aux = path+"\\"+post.getPath();
                image = ImageIO.read(new File(aux));

                image = resizeImage(image, 128,128);
                ImageIO.write(image, "jpg", new File("C:\\img_predict\\images\\out" + " " + cont + ".jpg"));
                //ImageIO.write(image, "jpeg", new File("C:\\img_predict\\images\\out.jpeg"));
                cont++;

                System.out.println(post.getPath() + " Done");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
        Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
        BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
        return outputImage;
    }

    public List<Double> tratamentoPorcentagem(String porcentagens){  //transforma response em list

        List<Double> porcentagemList = new ArrayList<>();

        String s1= porcentagens;
        String replace = s1.replace("[","");
        //System.out.println(replace);
        String replace1 = replace.replace("]","");
        //System.out.println(replace1);
        String replace2 = replace1.replace("\n",",");
        replace2 = replace2.replace(" ","");
        //System.out.println(replace2);
        List<String> myList = new ArrayList<String>(Arrays.asList(replace2.split(",")));
        //System.out.println(myList.toString());
        porcentagemList.addAll(myList.stream().map(Double::valueOf).collect(Collectors.toList()));
        //System.out.println(porcentagemList.toString());

        return porcentagemList;
    }

    public List<Post> submetendoPorcentagemSimilaridade(List<Post> postList, String porcentagem){

        List<Double> porcentagens = tratamentoPorcentagem(porcentagem);
        Integer cont = 0;

        for (Post post : postList) {

            post.setPorcentagemSimilaridade(porcentagens.get(cont));
            cont++;
        }

        Collections.sort(postList,  Collections.reverseOrder(new Comparator<Post>(){
            public int compare(Post o1, Post o2) {
                if (o1.getPorcentagemSimilaridade() == null || o2.getPorcentagemSimilaridade() == null)
                    return 0;
                return o1.getPorcentagemSimilaridade().compareTo(o2.getPorcentagemSimilaridade());
            }
        }));


        return postList;
    }

}
