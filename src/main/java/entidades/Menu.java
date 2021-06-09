package entidades;

import java.util.Scanner;

public class Menu {

    private Integer opcaoFiltro;
    private String perfilEntrada;
    private Scanner scr = new Scanner(System.in);;

    public void printMenu() {

        System.out.println("MENU");
        System.out.println();
        System.out.println("1- Entrar com um perfil específico");
        System.out.println("2- Entrar com uma hashtag");
        System.out.println("3- Enviar a requisicao");
        System.out.println("Opcao: ");
    }

    public void menuIncial(Localizacao localizacao) {
        menuLocalizacao(localizacao);
        definePerfilEntrada(perfilEntrada);
    }

    public void menuLocalizacao(Localizacao localizacao){
        System.out.println("Localização");
        System.out.println("Escolha um estado: (ex: SP, MG, RJ ...)");
        localizacao.setEstado(scr.nextLine());
        System.out.println("Escolha uma cidade: (ex: São Paulo, Campinas ...)");
        localizacao.setCidade(scr.nextLine());
        System.out.println("Localização setada para : "
                + localizacao.getCidade() + ","
                + localizacao.getEstado());
    }

    public void menuPerfil(Perfil perfil){

        System.out.println("entidades.Perfil");
        System.out.println("Nome perfil: (sem o @)");
        perfil.setNome(scr.nextLine());
        System.out.println("Esse perfil é privado? 1-Sim ou 2-Não");
        if(scr.nextInt() == 1)
            perfil.setPrivado(Boolean.TRUE);
        else
            perfil.setPrivado(Boolean.FALSE);
    }

    public void menuHashtag(Hashtag hashtag){
        System.out.println("entidades.Hashtag");
        System.out.println("Entre com a hashtag: (sem a #)");
        hashtag.setTag("#" + scr.nextLine());
    }

    public void definePerfilEntrada(String perfilEntrada){
        System.out.println("entidades.Perfil entrada");
        System.out.println("Entre com o nome do seu perfil: (sem a @)");
        setPerfilEntrada("@" + scr.nextLine());
    }

    public Integer definindoFiltros(Perfil perfil, Hashtag hashtag){
        printMenu();
        setOpcaoFiltro(scr.nextInt());

        scr.nextLine();
        switch (opcaoFiltro){

            case 1:
                menuPerfil(perfil);
                break;
            case 2:
                menuHashtag(hashtag);
                break;
            case 3:
                break;
            default:
                System.out.println("Essa opção não existe, tente novamente...");
                break;
        }
        scr.nextLine();
        return opcaoFiltro;
    }

    public Integer getOpcaoFiltro() {
        return opcaoFiltro;
    }
    public void setOpcaoFiltro(Integer opcaoFiltro) {
        this.opcaoFiltro = opcaoFiltro;
    }

    public String getPerfilEntrada() {
        return perfilEntrada;
    }

    public void setPerfilEntrada(String perfilEntrada) {
        this.perfilEntrada = perfilEntrada;
    }


}

