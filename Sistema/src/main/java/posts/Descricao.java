package posts;

import entidades.Localizacao;
import entidades.Post;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FilenameUtils;

public class Descricao {

    public void defineDescricao(List<Post> posts, String path) throws IOException {

        List<File> arquivos;
        arquivos = pegaArquivosDescricao(path);
        String fSemExt, postSemExt, postLocal, fLocal;


        for (Post post : posts) {
            for (File f: arquivos) {
                fSemExt = FilenameUtils.removeExtension(f.getName());
                System.out.println(fSemExt);
                postSemExt = FilenameUtils.removeExtension(post.getPath());
                System.out.println(postSemExt);
                if((fSemExt.contains("location")) == false) {
                    if (postSemExt.equals(fSemExt)) {
                        Path pathArqF = Paths.get(f.getAbsolutePath());
                        List<String> linhasArquivo = Files.readAllLines(pathArqF);
                        for (String linha : linhasArquivo) {
                            post.setDescricao(post.getDescricao() + linha);
                        }
                    }
                }
                else{
                    postLocal = postSemExt + "_location";
                    if (postLocal.equals(fSemExt)) {
                        Path pathArqF = Paths.get(f.getAbsolutePath());
                        List<String> linhasArquivo = Files.readAllLines(pathArqF);
                        post.setLocalizacao(new LocalizacaoPost());
                        post.getLocalizacao().setLocal(linhasArquivo.get(0));
                        post.getLocalizacao().setUrlMaps(linhasArquivo.get(1));
                    }
                }
            }

        }

    }

    public List<File> pegaArquivosDescricao(String path) {

        File diretorio = new File(path);

        File[] aquivosDoDiretorio = diretorio.listFiles();
        List<File> arquivos = new ArrayList<File>();

        for (int i=0; i<aquivosDoDiretorio.length; i++) {
            if (aquivosDoDiretorio[i].getName().endsWith("txt")) {
                arquivos.add(aquivosDoDiretorio[i]);
            }
        }

        return arquivos;
    }



}
