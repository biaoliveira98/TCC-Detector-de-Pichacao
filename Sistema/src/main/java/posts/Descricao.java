package posts;

import entidades.Post;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Descricao {

    public void defineDescricao(List<Post> posts, String path) throws IOException {

        List<File> arquivos = new ArrayList<File>();

        arquivos = pegaArquivosDescricao(path);

        for (Post post : posts) {
            for (File f: arquivos) {
                if(post.getPath() == f.getAbsolutePath()){
                    Path pathArqF = Paths.get(f.getAbsolutePath());
                    List<String> linhasArquivo = Files.readAllLines(pathArqF);
                    for (String linha : linhasArquivo) {
                        post.setDescricao(post.getDescricao() + linha);
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
