package instaloader;

import entidades.*;
import entidades.Localizacao;

public class RequisicaoInstaloader {

    private String requisicaoInstaloader;
    private Localizacao localizacao;
    private Perfil perfil;
    private Hashtag hashtag;
    private Menu menu;
    private EnviaInstaloader enviaInstaloader;
    private Data data;
    private String nomeDir;
    private Usuario usuario;

    public void definindoUsuarioSistema() {

        setLocalizacao(new Localizacao());
        setUsuario(new Usuario());
        setMenu(new Menu());

        menu.menuIncial(getLocalizacao(), getUsuario());
    }

    public void definindoRequisicaoInstaloader() {

        setPerfil(new Perfil());
        setHashtag(new Hashtag());
        setMenu(new Menu());
        setEnviaInstaloader(new EnviaInstaloader());
        setData(new Data());

        Integer op;

        do {
            op = menu.definindoFiltros(getPerfil(), getHashtag(), getData());
        } while (op != 4);

        if (localizacao.getLocal() != null && usuario.getNomePerfil() != null && usuario.getSenha() != null) {

            requisicaoInstaloader = "instaloader -V --geotags --login=" + usuario.getNomePerfil();

            if (perfil.getNome() != null) {
                requisicaoInstaloader = requisicaoInstaloader + " "
                        + perfil.getNome();
                nomeDir = perfil.getNome();
            } else if (hashtag.getTag() != null) {
                requisicaoInstaloader = "instaloader -V"
                        + " \"" + hashtag.getTag() + "\"";
                nomeDir = hashtag.getTag();
            }

            if (data.getDia() != null && data.getMes() != null && data.getAno() != null) {
                requisicaoInstaloader = requisicaoInstaloader
                        + " --post-filter=\"date_utc";
                if (data.getOperador() == 1) { //a partir de / depois de
                    requisicaoInstaloader = requisicaoInstaloader + ">=";
                } else if (data.getOperador() == 2) { //antes de
                    requisicaoInstaloader = requisicaoInstaloader + "<=";
                } else if (data.getOperador() == 3) { //referentes a
                    requisicaoInstaloader = requisicaoInstaloader + "==";
                } else {
                    System.out.println("Operação inválida");
                }
                requisicaoInstaloader = requisicaoInstaloader +
                        "datetime(" +
                        +data.getAno() + "," + data.getMes() + "," + data.getDia() + ")\"";
            }

            System.out.println(requisicaoInstaloader);

//            enviaInstaloader.enviaCMD(requisicaoInstaloader, ("\\" + nomeDir + "\\imgs"));
        } else {
            System.out.println("Operação invalida!");
        }
    }


//TODO Validações: data, valores
//TODO talvez tenha que retirar as geotags - verificar

//Getters and setters

    public String getRequisicaoInstaloader() {
        return requisicaoInstaloader;
    }
    public void setRequisicaoInstaloader(String requisicaoInstaloader) {
        this.requisicaoInstaloader = requisicaoInstaloader;
    }


    public Localizacao getLocalizacao() {
        return localizacao;
    }
    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
    }


    public Perfil getPerfil() {
        return perfil;
    }
    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }


    public Hashtag getHashtag() {
        return hashtag;
    }
    public void setHashtag(Hashtag hashtag) {
        this.hashtag = hashtag;
    }


    public Menu getMenu() {
        return menu;
    }
    public void setMenu(Menu menu) {
        this.menu = menu;
    }


    public Data getData() {
        return data;
    }
    public void setData(Data data) {
        this.data = data;
    }

    public String getNomeDir() {
        return nomeDir;
    }

    public void setNomeDir(String nomeDir) {
        this.nomeDir = nomeDir;
    }


    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public EnviaInstaloader getEnviaInstaloader() {
        return enviaInstaloader;
    }
    public void setEnviaInstaloader(EnviaInstaloader enviaInstaloader) {
        this.enviaInstaloader = enviaInstaloader;
    }
}
