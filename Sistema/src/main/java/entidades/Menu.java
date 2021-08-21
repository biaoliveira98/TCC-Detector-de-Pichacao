package entidades;

import java.util.Scanner;

public class Menu {

    private Integer opcaoFiltro;
    private Scanner scr = new Scanner(System.in);

    public void printMenu() {

        System.out.println("MENU");
        System.out.println();
        System.out.println("1- Entrar com um perfil específico");
        System.out.println("2- Entrar com uma hashtag");
        System.out.println("3- Definir data");
        System.out.println("4- Enviar a requisicao");
        System.out.println("Opcao: ");
    }

    public void menuIncial(Localizacao localizacao, Usuario usuario) {
        menuLocalizacao(localizacao);
        definePerfilEntrada(usuario);
    }

    public void menuLocalizacao(Localizacao localizacao){
        System.out.println("Localização");
        System.out.println("Entre com uma localização: (Exemplo: Campinas, Sao Paulo)");
        localizacao.setLocal(scr.nextLine());
        System.out.println("Localização setada para : "
                + localizacao.getLocal());
    }

    public void menuPerfil(Perfil perfil){

        System.out.println("Perfil");
        System.out.println("Nome perfil: (sem o @)");
        perfil.setNome(scr.nextLine());
    }

    public void menuHashtag(Hashtag hashtag){
        System.out.println("Hashtag");
        System.out.println("Entre com a hashtag: (sem a #)");
        hashtag.setTag("#" + scr.nextLine());
    }

    public void definePerfilEntrada(Usuario perfilEntrada){
        System.out.println("Perfil entrada");
        System.out.println("Entre com o nome do seu perfil: (sem a @)");
        perfilEntrada.setNomePerfil(scr.nextLine());
        System.out.println("Senha: ");
        perfilEntrada.setSenha(scr.nextLine());
    }

    public void menuData(Data data){
        System.out.println("Data");
        System.out.println("Você deseja:");
        System.out.println("(1) Ver posts a partir desta data");
        System.out.println("(2) Ver posts antes desta data");
        System.out.println("(3) Ver posts referentes a esta data");
        System.out.println("Opcao: ");
        data.setOperador(scr.nextInt());
        System.out.println("Entre com a data desejada  XX/XX/XXXX");
        System.out.println("Dia: ");
        data.setDia(scr.nextInt());
        System.out.println("Mes: ");
        data.setMes(scr.nextInt());
        System.out.println("Ano: ");
        data.setAno(scr.nextInt());
        System.out.println("Data: " + data.getDia().toString() +"/"+ data.getMes().toString()+ "/" + data.getAno().toString());
    }
    public Integer definindoFiltros(Perfil perfil, Hashtag hashtag, Data data){
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
                menuData(data);
                break;
            case 4:  //envia requisicao
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


}

