package entidades;

import posts.LocalizacaoPost;

public class Post {

    private String path;
    private String tipo;
    private String descricao;
    private LocalizacaoPost localizacao;

    @Override
    public String toString() {
        return "Post{" +
                ", localizacao=" + localizacao +
                '}';
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descrição) {
        this.descricao = descrição;
    }


    public LocalizacaoPost getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(LocalizacaoPost localizacao) {
        this.localizacao = localizacao;
    }
}
