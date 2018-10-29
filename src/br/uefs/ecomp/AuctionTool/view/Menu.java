/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.AuctionTool.view;
import br.uefs.ecomp.AuctionTool.controller.*;
import br.uefs.ecomp.AuctionTool.util.*;
import br.uefs.ecomp.AuctionTool.model.*;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Menu {
    private static AuctionBC controla = new AuctionBC();
    
    private static String cpf; //Variável usada para armazenar o CPF do atual usuário.
    /*Verifica se o usuário é um 'usuário' ou administrador*/
    private static void verificarUsuario() throws IOException, ParseException{
        clear();
        System.out.printf("\t> Você é:\n\n");
        System.out.println("(01) Administrador.");
        System.out.println("(02) Usuário.");
        System.out.printf("> Selecione uma opção: ");
        int escolha = Console.readInt();
        
        switch(escolha){
            case 1:{
                loginAdmin();
                break;
            }
            case 2:{
                reconhecimentoUsuario();
                break;
            }
            default:{
                clear();
                System.out.println("\t> Opção Inválida.");
                pause();
                clear();
                verificarUsuario();
                break;
            }
        }
    
    }
    /*Verifica se o usuário já é cadastrado no sistema.*/
    private static void reconhecimentoUsuario() throws IOException, ParseException{
        clear();
        System.out.println("\t> Bem-Vindo ao LOX.");
        System.out.println("> Você já é cadastrado no nosso sistema?");
        System.out.println("(01)________Sim");
        System.out.println("(02)________Não");
        
        System.out.printf("> Selecione uma opção acima:");
        int escolha = Console.readInt();
        
        switch(escolha){
            case 1:{
                loginUsuario();
                break;
            }
            
            case 2:{
                cadastroUsuario();
                break;
            }
            
            default:{
                System.out.println("\t> Opção Inválida!");
                pause();
                clear();
                reconhecimentoUsuario();
                break;
            }
        } 
    }
    /*Cadastra um novo usuário no Sistema.*/
    private static void cadastroUsuario() throws IOException, ParseException{
        clear();
        System.out.printf("\t> Cadastro de Pessoa no Sistema:\n\n");
        
        System.out.printf("> Digite seu nome:");
        String nome = Console.readString();
        
        System.out.printf("> Digite seu CPF:");
        String cpf = Console.readString();
        
        System.out.printf("> Digite seu telefone:");
        String telefone = Console.readString();
        
        System.out.printf("> Digite seu e-mail:");
        String email = Console.readString();
        
        System.out.printf("> Digite uma senha:");
        String senha = Console.readString();
        int escolha;
        do{
            clear();
            System.out.println("\t> Os Dados inseridos estão corretos?");
            System.out.println("> Não será possível alterá-los posteriormente.\n");
            System.out.println("\t Sim - (01) || Não (02)");
            escolha = Console.readInt();
            if(escolha == 2){
                cadastroUsuario();
            }
            if(escolha != 1 && escolha != 2){
                clear();
                System.out.println("\t> Você Selecionou uma Opção inválida.");
                pause();
            }
        }while(escolha != 1 && escolha != 2);
        
        int verifica = controla.cadastrarPessoa(cpf, nome, email, telefone, senha);
        clear();
        /*Caso já exista uma pessoa com o CPF digitado, encerra a execução do metodo.*/
        if(verifica == 0){
            System.out.println("\t> A pessoa com o CPF informado já existe no Sistema.");
            pause();
            verificarUsuario();
        }
        /*Senão cria um novo usuário*/
        else{
            System.out.println("\t> Pessoa cadastrada com sucesso!\n");
            System.out.println("> Nome: "+nome.toUpperCase());
            System.out.println("> Login: "+ cpf.toUpperCase());
            System.out.println("> Senha: "+senha);
            pause();
            verificarUsuario();
        }
    }
    /*Permite o login de um usuário já cadastrado no sistema.*/
    private static void loginUsuario() throws IOException, ParseException{
        clear();
        System.out.println("\t> Sessão Usuário:\n\n");
        
        System.out.printf("> Insira seu CPF:");
        String user = Console.readString();
        
        System.out.printf("> Insira sua senha:");
        String senha = Console.readString();
        
        boolean verifica = controla.autenticaUsuario(user, senha);
        cpf = user;
        clear();
        /*Caso o usuário exista no Sistema, exibe o menu ao usuário.*/
        if(verifica == true){
            menuUsuario();
        }
        /*Casa o usuário não exista, retorna ao menu de usuário.*/
        else{
            System.out.println("\n\t> Usuário ou senha Incorretos.");
            pause();
            clear();
            verificarUsuario();
        }
    }
    /*Exibe o menu de um usuário do sistema.*/
    private static void menuUsuario() throws IOException, ParseException{
        clear();
        System.out.println("\t> Leilão Eletrônico LOX <\n\t> Sessão Usuário:<\n");
        System.out.println("(0)____________Voltar ao Menu Principal.");
        System.out.println("(01)___________Alterar Dados Cadastrais.");
        System.out.println("(02)___________Inserir Item.");
        System.out.println("(03)___________Alterar Item.");
        System.out.println("(04)___________Remover Item.");
        System.out.println("(05)___________Dar Lance.");
        System.out.println("(06)___________Colocar Item em Leilão.");
        System.out.println("(07)___________Desistir de um Item.");
        System.out.println("(08)___________Listar Itens por Categoria.");
        System.out.println("(09)___________Encerrar Leilão.");
        System.out.println("(10)___________Sair.");
        System.out.printf("> Selecione uma opção: ");
        int escolha = Console.readInt();
        
        switch(escolha){
            
            case 0:{
                verificarUsuario(); //Retorna ao menu principal.
                break;
            }
            case 1:{
                alterarPessoa(); //Altera os dados cadastrais de uma pessoa.
                break;
            }
            
            case 2:{
                inserirItem(); //Insere um item no sistema.
                break;
            }
            
            case 3:{
                alterarItem(); //Altera os dados de um item.
                break;
            }
            
            case 4:{
                removerItem(); //Remove um item.
                break;
            }
            
            case 5:{
                darLance(); //Dá um lance em um determinado item.
                break;
            }
            
            case 6:{
                colocarItemEmLeilao(); //Coloca determinado item em leilão.
                break;
            }
            
            case 7:{
                desistirItem(); //Desiste de um Item, caso tenha ganhado o leilão.
                break;
            }
            
            case 8:{
                listarItensPorCategoria(); //Lista os Itens cadastrados no sistema por categoria.
                break;
            }
            case 9:{
                encerrarLeilao(); //Encerra o leilão de um item.
                break;
            }
            case 10:{
                clear();
                System.out.println("\t> Obrigado por Utilizar Nosso Sistema. :)");
                System.exit(0);
                break;
            }
            
            default:{ //Caso a opção selecionada não exista reexibe o menu.
                clear();
                System.out.println("\t> Você selecionou uma opção inválida.");
                pause();
                clear();
                menuUsuario();
            }
        }
    
    }
    /*Altera os dados de um usuário que esta utilizando o sistema.*/
    private static void alterarPessoa() throws IOException, ParseException{
        clear();
        System.out.println("\t> Alteração de Dados Cadastrais:\n");
        
        System.out.printf("> Digite seu novo telefone: ");
        String telefone = Console.readString();
        
        System.out.printf("> Digite seu novo e-mail: ");
        String email = Console.readString();
        
        System.out.printf("> Digite seu novo nome: ");
        String nome = Console.readString();
        
        int escolha;
        do{
            clear();
            System.out.println("\t> Os Dados inseridos estão corretos?");
            System.out.println("\t Sim - (01) || Não (02)");
            escolha = Console.readInt();
            if(escolha == 2){
                cadastroUsuario();
            }
            if(escolha != 1 && escolha != 2){
                clear();
                System.out.println("\t> Você Selecionou uma Opção inválida.");
                pause();
            }
        }while(escolha != 1 && escolha != 2);
        
        clear();
        int alterar = controla.alterarPessoa(cpf, nome, email, telefone);
        
        if(alterar == 1){
            System.out.println("\t> Alteração Realizada com Sucesso.");
            pause();
        }
        /*Essa verificação está aqui, para fins de descargo de consciência, pois o sistema
        altera os dados do usuário que está utilizando-o, portanto, é impossivel
        que o usuário não exista.*/
        else if(alterar == 0){
            System.out.println("\t > Você não existe.");
            pause();
        }
        clear();
        menuUsuario();
    }
    /*Insere um item no sistema.*/
    private static void inserirItem() throws IOException, ParseException{
        int verifica = 0;
        clear();
        System.out.println("\t> Inserção de Item:\n");
        
        System.out.printf("> Digite o nome do Item: ");
        String nomeItem = Console.readString();
        
        System.out.printf("> Digite o preço inicial para o Item: ");
        float preco_inicial = Console.readFloat();
        
        System.out.println("> Qual a Categoria do Item: ");
        System.out.println("(01)________Imóvel.");
        System.out.println("(02)________Remédio.");
        System.out.println("(03)________Alimento.");
        System.out.println("(04)________Outros.");
        System.out.printf("> ");
        int tipo_item = Console.readInt();
        clear();
       
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String data = null;
        String categoria = null;
        Date validade = null;
        
        switch (tipo_item) {
            case 1: //Dados adicionais caso o item inserido seja um imóvel.
                int ocupacao_imovel;
                System.out.println("> O Imóvel está ocupado?");
                System.out.println("(01)______Sim.");
                System.out.println("(02)______Não.");
                System.out.printf("> ");
                ocupacao_imovel = Console.readInt();
                if(ocupacao_imovel != 1 && ocupacao_imovel != 2){
                    System.out.println("\t> Opção Inválida.");
                    pause();
                    menuUsuario();
                }
                verifica = controla.cadastrarItem(nomeItem, ocupacao_imovel, preco_inicial, cpf);
                break;
            case 2: //Dados adicionais caso o item adicionado seja um remédio.
                int receita_remedio;
                System.out.println("> Digite a validade do Remédio: ");
                System.out.println("> Digite a Data nesse formato: dd/mm/aaaa.");
                System.out.printf("> ");
                data = Console.readString();
                clear();
                System.out.println("> O remédio precisa de Receita?");
                System.out.println("(01)___________Sim.");
                System.out.println("(02)___________Não.");
                System.out.printf("> ");
                receita_remedio = Console.readInt();
                if(receita_remedio != 1 && receita_remedio != 2){
                    System.out.println("\t> Opção Inválida.");
                    pause();
                    menuUsuario();
                }
                validade = formato.parse(data);
                verifica = controla.cadastrarItem(nomeItem, receita_remedio, validade, preco_inicial, cpf);
                break;
            case 3: //Dados adicionais caso o item seja um Alimento.
                System.out.println("Digite a validade do Alimento:");
                System.out.println("> Digite a Data nesse formato: dd/mm/aaaa.");
                System.out.printf("> ");
                data = Console.readString();
                validade = formato.parse(data);
                verifica = controla.cadastrarItem(nomeItem, validade, preco_inicial, cpf);
                break;
            case 4: //Nome da categoria "outros".
                System.out.println("> Digite o nome da categoria do Item:");
                System.out.printf("> ");
                categoria = Console.readString();
                verifica = controla.cadastrarItem(nomeItem, categoria, preco_inicial, cpf);
                break;
            default: //Caso o usuário não selecione uma opção válida.
                System.out.println("> Você selecionou uma opção inválida.");
                pause();
                menuUsuario();
                break;
        }
        
        clear();
        switch (verifica) {
            case 0: //Verificação falsa, pois o usuário deve existir para poder logar no sistema.
                System.out.println("\t> A Pessoa com esse CPF não existe.");
                pause();
                break;
            case 1:
                System.out.println("\t> Item cadastrado sucesso.");
                pause();
                break;
        }
        menuUsuario();
        
    }
    /*Altera os dados de um item no sistema.*/
    private static void alterarItem() throws IOException, ParseException{
        clear();
        System.out.println("\t> Alteração de Item:\n");
        
        System.out.printf("> Digite o id do Item que deseja alterar:");
        int id = Console.readInt();
        
        System.out.printf("> Digite o novo nome para o item:");
        String novoNome = Console.readString();
        
        System.out.printf("> Digite um novo preço inicial para o item:");
        float novo_preco = Console.readFloat();
        
        System.out.printf("> Digite uma nova categoria para o item:");
        String categoria = Console.readString();
        
        int escolha;
        do{
            clear();
            System.out.println("\t> Os Dados inseridos estão corretos?");
            System.out.println("\t Sim - (01) || Não (02)");
            escolha = Console.readInt();
            if(escolha == 2){
                cadastroUsuario();
            }
            if(escolha != 1 && escolha != 2){
                clear();
                System.out.println("\t> Você Selecionou uma Opção inválida.");
                pause();
            }
        }while(escolha != 1 && escolha != 2);
        /*Altera os dados do item, caso exista.*/
        int verifica = controla.alterarItem(id, novoNome, categoria, novo_preco, cpf);
        clear();
        switch (verifica) {
            case 0: //Exibe o erro caso a categoria não exista.
                System.out.println("\t> A Categoria selecionada não existe.");
                pause();
                break;
            case 1: //Alterou com sucesso.
                System.out.println("\t> Alteração realizada com sucesso.");
                pause();
                break;
            case 2: //Exibe o erro caso o item não exista ou possua lances.
                System.out.println("\t> O Item não existe no Sistema ou possui lances.");
                pause();
                break;
            default:
                break;
        }
        
        menuUsuario();
    }
    /*Remove um item do sistema.*/
    private static void removerItem() throws IOException, ParseException{
        clear();
        System.out.println("\t> Remoção de Item:\n");
        
        System.out.println("> Digite o Código(ID) do item que deja remover: ");
        System.out.println("PS.: Digite '0' para ver a lista dos seus itens.");
        int codigo = Console.readInt();
        clear();
        
        if(codigo != 0){
            int verifica = controla.removerItem(codigo, cpf);
            
            switch (verifica) {
                case 0:
                    System.out.println("\t> Não existem itens cadastrados no Sistema.");
                    pause();
                    break;
                case 1:
                    System.out.println("\t> Não existe item com o código informado.");
                    pause();
                    break;
                case 2:
                    System.out.println("\t> Item removido com Sucesso.");
                    pause();
                    break;
                case 3:
                    System.out.println("\t> Não foi possivel remover o Item.");
                    System.out.println("> Possíveis Acontecimentos:");
                    System.out.println("> O Item não pertence a você.");
                    System.out.println("> O Item possui lances relacionados a ele.");
                    pause();
                    break;
                default:
                    break;
            }
            menuUsuario();
        }
 
        else{
            Iterator listar_itens = controla.listarItensPorPessoa(cpf);
            if(listar_itens == null){
                System.out.println("\t> Você não possui itens cadastrados no Sistema.");
                pause();
                menuUsuario();
            }
            while(listar_itens.hasNext()){
                Item imprime = (Item) listar_itens.next();
                if(imprime.getDono().getCpf().equals(cpf)){
                    System.out.println("> Nome: "+imprime.getNome().toUpperCase());
                    System.out.println("> Código: "+imprime.getCodigo());
                    System.out.println("> Categoria: "+imprime.getCategoria().getNome().toUpperCase());
                    System.out.println("-----------------------------------------------------------------");
                }
            }
            pause();
            System.out.printf("> Digite o Código(ID) do item que deja remover: ");
            codigo = Console.readInt();
            
            int verifica = controla.removerItem(codigo, cpf);
            switch (verifica) {
                case 0:
                    System.out.println("\t> Não existem itens cadastrados no Sistema.");
                    pause();
                    break;
                case 1:
                    System.out.println("\t> Não existe item com o código informado.");
                    pause();
                    break;
                case 2:
                    System.out.println("\t> Item removido com Sucesso.");
                    pause();
                    break;
                case 3:
                    System.out.println("\t> Não foi possivel remover o Item.");
                    System.out.println("> Possíveis Acontecimentos:");
                    System.out.println("> O Item não pertence a você.");
                    System.out.println("> O Item possui lances relacionados a ele.");
                    pause();
                    break;
                default:
                    break;
            }
        }
        menuUsuario();
    }
    /*Coloca um certo item em leilão.*/
    private static void colocarItemEmLeilao() throws IOException, ParseException{
        clear();
        System.out.println("\t> Colocar Item em Leilão\n");
        
        System.out.println("> Digite o Id do Item que deseja colocar em Leilão: ");
        System.out.printf("> Digite '0' para ver os seus Itens cadastrados no Sistema: ");
        int id = Console.readInt();
        clear();
        if(id == 0){
            Iterator listar = controla.listarItensPorPessoa(cpf);
            if(listar == null){
                System.out.println("\t> Não Existem itens cadastrados no sistema que lhe pertencem.");
                pause();
                menuUsuario();
            }
            else{
                System.out.println("\t> Listando Itens que lhe Pertencem e Não Estam em Leilão:\n");
                while(listar.hasNext()){
                    Item mostrar = (Item) listar.next();
                    if(mostrar.getDono().getCpf().equals(cpf) && mostrar.isStatus() == Status.CADASTRADO){
                        System.out.println("> ID Item: "+mostrar.getCodigo());
                        System.out.println("> Nome: "+mostrar.getNome().toUpperCase());
                        System.out.println("> Categoria: "+mostrar.getCategoria().getNome().toUpperCase());
                        System.out.println("------------------------------------------------------------");
                    }
                }
                pause();
            }
            System.out.printf("> Digite o Id do Item que deseja colocar em Leilão: ");
            id = Console.readInt();
        }
        
        int verifica = controla.iniciarLeilaoDoProduto(id, cpf);
        clear();
        switch (verifica) {
            case 0: //Item não existe no sistema.
                System.out.println("\t> O Item com o referido ID não existe.");
                pause();
                break;
            case 1: //O item não pertence ao atual usuário.
                System.out.println("\t> O referido Item é de outro usuário.");
                pause();
                break;
            case 2: //Está em leilão.
                System.out.println("\t> O Item entrou em Leilão com Sucesso.");
                pause();
                break;
        }
        
        menuUsuario();
        
    }
    /*Registra um lance em um detemrinado item.*/
    private static void darLance() throws IOException, ParseException{
        clear();
        System.out.println("\t> Dar Lance em um Item:\n");
        
        System.out.println("> Digite o ID do Item que deseja dar um lance: ");
        System.out.println("> Digite '0' para ver os Itens cadastrados no Sistemas.");
        System.out.printf("> ");
        int id_item = Console.readInt();
        clear();
        if(id_item == 0){
            Iterator listar_item = controla.listarItens();
            if(listar_item == null){ //Exibe esse erro caso não existam itens no sistema.
                System.out.println("\t> Não existem Itens no Sistema.");
                pause();
                menuUsuario();
            }
            System.out.println("\t> Listando Itens em Leilão:\n");
            boolean existe = false;
            /*Caso o usuário não selecione um item válido, o sistema exibirá todos os itens
            no sistema, que podem receber lances.*/
            while(listar_item.hasNext()){
                Item listar = (Item) listar_item.next();
                DecimalFormat df = new DecimalFormat("0.00");
                if(listar.isStatus() == Status.EM_LEILAO){
                    System.out.println("> Nome: "+listar.getNome().toUpperCase());
                    System.out.println("> ID: "+listar.getCodigo());
                    System.out.println("> Valor Mínimo: "+df.format(listar.getPreco_inicial()));
                    System.out.println("--------------------------------------------------------------");
                    existe = true;
                }
            } 
            /*Exibe esse erro caso não existam itens em leilão.*/
            if(existe == false){
                System.out.println("\t> Não existem itens em Leilão.");
                pause();
                menuUsuario();
            }
            System.out.printf("> Digite o ID do Item que deseja dar um lance: ");
            id_item = Console.readInt();
        }
        System.out.println("> Digite o valor do lance: ");
        float valor = Console.readFloat();
        
        clear();
        int verifica = controla.efetuarLance(cpf, id_item, valor);
        switch (verifica) {
            case 0: //Item não existe.
                System.out.println("\t> O Item com o referido ID não existe.");
                pause();
                break;
            case 1: //A pessoa não existe.(Impossível, pois o sistema usa o CPF do atual usuário.)
                System.out.println("\t> A pessoa com o referido CPF não existe.");
                pause();
                break;
            case 2: //Retorna esse erro caso o usuário dê um lance inferior ao Valor Minimo.
                System.out.println("\t> O Valor do Lance é inferior ao Preço Inicial do Item.");
                pause();
                break;
            case 3: //Lance cadastrado com sucesso.
                System.out.println("\t> Lance registrado com sucesso.");
                pause();
                break;
            case 4: //Item não está em leilão.
                System.out.println("\t> O Item não está em Leilão.");
                pause();
                break;
            case 5:
                System.out.println("\t> O Item está fora de Validade.");
                pause();
                break;
                
            case 6:
                System.out.println("\t> Você não pode dar um lance no seu próprio Iten.");
                pause();
                break;
        }
        menuUsuario();
    }
    /*Lista itens por Categoria*/
    private static void listarItensPorCategoria() throws IOException, ParseException{
        clear();
        
        System.out.println("\t> Listagem de Itens por Categoria:\n");
        
        System.out.printf("> Digite a Categoria que deseja listar os Itens:");
        String categoria = Console.readString();
        clear();
        Iterator listar = controla.listarItens();
        if(listar == null){ //Verifica se o Iterador é nulo.
            System.out.println("\t> Não existem itens cadastrados no Sistema.");
            pause();
            menuUsuario();
        }
        boolean existe = false;
        while(listar.hasNext()){ //Printa os Itens de uma determinada categoria
            Item listando = (Item) listar.next();
            if(listando.getCategoria().getNome().equalsIgnoreCase(categoria)){
                System.out.println("> Nome: "+listando.getNome().toUpperCase());
                System.out.println(">ID "+ listando.getCodigo());
                System.out.println("> Categoria: "+listando.getCategoria().getNome().toUpperCase());
                System.out.println("----------------------------------------------------------------------");
                existe = true;
            }
        }
        if(existe == false){ /*Caso essa variável continue falso, significa que não
            existem itens nesta categoria.*/
            System.out.println("\t> Não existem Itens com essa Categoria.");
            pause();
        }
        menuUsuario();
    
    }
    /*Permite ao usuário desistir de um item ao qual ele tenha efetuado um lance.*/
    private static void desistirItem() throws IOException, ParseException{
        clear();
        
        System.out.println("\t> Desistência de Item:\n");
        
        System.out.printf("> Digite o ID do item que deseja desistir: ");
        int item = Console.readInt();
        
        int desistencia = controla.desistirItem(item, cpf);
        clear();
        
        if(desistencia == 0){ //Inexistência de um Item.
            System.out.println("\t> O Item não existe.");
            pause();
        }
        else if(desistencia == 2){ //Desistência efetuada.
            System.out.println("\t> Você Desistiu do Item com sucesso.");
            pause();
        }
        else if(desistencia == 3){
            System.out.println("\t> O Item ainda não foi vendido.");
            pause();
        }
        else if(desistencia == 4){
            System.out.println("\t> Você não possui o maior Lance desse Item");
            pause();
        }
        menuUsuario();
    }
        /*Permite ao usuário encerrar um leilão de seu item.*/
    private static void encerrarLeilao() throws IOException, ParseException{
        clear();
        
        System.out.println("\t> Encerramento de Leilão:\n");
        System.out.printf("> Digite o ID do Item que deseja Encerrar o Leilão: ");
        int item = Console.readInt();
        
        int verifica = controla.encerrarLeilao(item, cpf);
        clear();
        
        if(verifica == 0){
            System.out.println("\t> O Item não existe.");
            pause();
        }
        /*O item não está em leilão.*/
        else if(verifica == 1){
            System.out.println("\t> O Item encontra-se na condição de Cadastrado.");
            pause();
        }
        /*Caso o item esteja com o Status: vendido ou desistência, não permite o fim do leilão.*/
        else if(verifica == 2 || verifica == 3){
            System.out.println("\t> O Item já foi vendido.");
            pause();
        }
        /*Não permite o fim do leilão caso não possua lances.*/
        else if(verifica == 4){
            System.out.println("\t> O Item não possui lances.");
            pause();
        }
        else if(verifica == 5){
            System.out.println("\t> O Leilão foi encerrado com Sucesso.");
            pause();
        }
        else if(verifica == 6){
            System.out.println("\t> O Item não lhe pertence.");
            pause();
        }
        menuUsuario();
    }
    
    
    /*****************************************************************************/
    /*****************************************************************************/
    /**A partir daqui, todos os métodos abaixo são relacionados ao Administrador**/
    /*****************************************************************************/
    /*****************************************************************************/
    private static void loginAdmin() throws IOException, ParseException{
        clear();
        System.out.println("\t> Sessão Administrador:\n");
        System.out.printf("> Insira seu Usuário:");
        String admin = Console.readString();
        
        System.out.printf("> Insira sua Senha: ");
        String senha = Console.readString();
        
        boolean verificado = controla.autenticaAdmin(admin, senha);
        clear();
        
        if(verificado == true){
            menuAdmin();
        }
        else{
            System.out.println("\n\n\t> Usuário ou Senha incorretos.\n");
            pause();
            clear();
            verificarUsuario();
        }
    }
    /*Mostra o menu ao administrador.*/
    private static void menuAdmin() throws IOException, ParseException{
        clear();
        System.out.println("\t> Leilão Eletrônico LOX <\nSessão Administrador:\n");
        System.out.println("(0)_________________Voltar ao Menu Principal.");
        System.out.println("(01)________________Inserir Categoria.");
        System.out.println("(02)________________Alterar Categoria.");
        System.out.println("(03)________________Remover Categoria.");
        System.out.println("(04)________________Remover Pessoa.");
        System.out.println("(05)________________Exibir Vencedor.");
        System.out.println("(06)________________Listar Pessoas.");
        System.out.println("(07)________________Listar Lances.");
        System.out.println("(08)________________Sair.");
        System.out.printf("> Selecione uma das opções acima: ");
        int escolha = Console.readInt();
      
        switch(escolha){
            case 0:{
                verificarUsuario();
                break;
            }
            case 1:{
                inserirCategoria();
                break;
            }
            case 2:{
                alterarCategoria();
                break;
            }
            case 3:{
                removerCategoria();
                break;
            }

            case 4:{
                removerPessoa();
                break;
            }
            
            case 5:{
                exibirVencedor();
                break;
            }
            case 6:{
                listarPessoas();
                break;
            }
            
            case 7:{
                listarLances();
                break;
            } 
            case 8:{
                clear();
                System.out.println("\t> Obrigado por Utilizar Nosso Sistema. :)");
                System.exit(0);
                break;
            }
            default:{
                clear();
                System.out.println("\t> A opção selecionada é inválida.");
                pause();
                clear();
                menuAdmin();
            }
        }
    }
    /*Insere uma categoria no Sistema.*/
    private static void inserirCategoria() throws IOException, ParseException {
        clear();
        System.out.println("\t>Inserção de Categoria.<");
        System.out.printf("> Digite o nome da Categoria que deseja inserir no sistema:");
        String categoria = Console.readString();

        Categoria verifica = controla.cadastrarCategoria(categoria);
        clear();
        if(verifica != null){ //Cadastra a categoria.
            System.out.println("\t> Categoria Cadastrada com Sucesso.");
            pause();
            menuAdmin();
        }
        else{
            System.out.println("\t> A Categoria Digitada já existe no Sistema.");
            pause();
            menuAdmin();
        } 
    }
    /*Altera uma categoria do Sistema.*/
    private static void alterarCategoria() throws IOException, ParseException{
        clear();
        System.out.printf("\t> Alteração de Categoria:\n\n");

        System.out.println("> Digite o nome da Categoria que deseja alterar:");
        System.out.println("> Digite 'lista' para ver a lista de Categorias cadastradas.");
        String nome_antigo = Console.readString();
        clear();
        if(nome_antigo.equalsIgnoreCase("lista")){
            /*Lista as categorias caso o usuário deseje fazê-lo.*/
            Iterator listar = controla.listarCategorias();
            if(!listar.hasNext()){
                System.out.println("\t> Não existem Categorias cadastradas.");
                pause();
                menuAdmin();
            }
            else{
                
                System.out.println("\t> Listando Categorias:\n");
                while(listar.hasNext()){
                    Categoria listando = (Categoria) listar.next();
                    System.out.println("Nome: "+listando.getNome().toUpperCase());
                    System.out.println("Código: "+listando.getCodigo());
                    System.out.println("------------------------------------------------------");
                }
                pause();
                clear();
                System.out.println("> Digite o nome da Categoria que deseja alterar:");
                nome_antigo = Console.readString();
            }
            
        }
        System.out.printf("> Digite o novo nome para a Categoria selecionada:");
        String nome = Console.readString();
        
        int verifica = controla.alterarCategoria(nome_antigo, nome);
        clear();
        
        if(verifica == 0){ //Caso o usuário digite uma categoria que não exista, um erro será exibido.
            System.out.println("\t> A Categoria selecionada não existe no Sistema.");
            pause();
        }
        else if(verifica == 1){
            System.out.println("\t> Uma Categoria com o Novo Nome já existe.");
            pause();
        }
        else if(verifica == 2){
            System.out.println("\t> Categoria Alterada com sucesso!");
            pause();
        }
        menuAdmin();
    }
    /*Remove uma categoria do Sistema.*/
    private static void removerCategoria() throws IOException, ParseException{
        clear();
        System.out.printf("\t> Remoção de Categoria:\n\n");
        System.out.printf("> Digite o nome da categoria que deseja remover:");
        String categoria = Console.readString();
        clear();
        int verifica = controla.removerCategoria(categoria);
        switch (verifica) {
            case 0:
                System.out.println("\t> A categoria digitada não existe no sistema.");
                pause();
                break;
            case 1:
                System.out.println("\t> Categoria removida com sucesso.");
                pause();
                break;
            case 2: //Exibe um erro caso existam itens com tal categoria.
                System.out.println("\t> A Categoria selecionada possui itens cadastrados.");         
                pause();
                break;
        }
        menuAdmin();  
    }
    /*Remove uma pessoa do Sistema.*/
    private static void removerPessoa() throws IOException, ParseException{
        clear();
        System.out.println("\t> Remoção de Pessoa:");
        
        System.out.printf("> Digite o CPF da pessoa que deseja remover:");
        String cpf = Console.readString();
        
        int remove = controla.removerPessoa(cpf);
        clear();
        switch (remove) {
            case 0:
                System.out.println("\t> A Pessoa com esse CPF não existe.");
                pause();
                break;
            case 1: //Não permite que a pessoa seja removida por possuir um item.
                System.out.println("\t> A Pessoa informada possui um item cadastrado no Sistema.");
                pause();
                break;
            case 2: //Não permite que a pessoa seja removida por possuir um lance.
                System.out.println("\t> A Pessoa informada possui um lance em um Item.");
                pause();
                break;
            case 3: //A pessoa foi removida com sucesso.
                System.out.println("\t> Pessoa removida com sucesso.");
                pause();
                break;
                
            case 4: //Esse valor nunca será retornado pela controller.
                System.out.println("\t> Algo deu muito errado.");
                pause();
                break;
            default:
                break;
        }
        menuAdmin();
    }
    /*Mostra o vencedor de um leilão de determinado item.*/
    private static void exibirVencedor() throws IOException, ParseException{
        clear();
        
        System.out.println("\t> Exibindo Vencedor de Leilão:\n");
        
        System.out.printf("\t> Digite o ID do Item que deseja ver o Vencedor: ");
        int item = Console.readInt();
        clear();
        Pessoa vencedor = controla.exibeVencedor(item);
        if(vencedor == null){
            System.out.println("\t> Não foi possível exibir o vencedor: ");
            System.out.println("> 1 - O Item não existe.");
            System.out.println("> 2 - O Item não possui lances.");
            System.out.println("> 3 - O Item ainda está leilão.");
            System.out.println("> 4 - O Item não entrou em Leilão.");
            pause();
        }
        /*Exibe os dados do vencedor.*/
        else{
            System.out.println("\t> Vencedor:\n");
            System.out.println("> Nome: "+vencedor.getNome().toUpperCase());
            System.out.println("> CPF: "+vencedor.getCpf());
            System.out.println("> Telefone: "+vencedor.getTelefone());
            pause();
        }
        menuAdmin();
    }
    /*Lista as pessoas por ordem de pontuação decrescentemente.*/
    private static void listarPessoas() throws IOException, ParseException{
        clear();
        
        System.out.println("\t> Listagem de Pessoas por Pontuação:\n");
        Comparable[] listar = controla.listarPessoasPorPontuacao();
        clear();
        if(listar == null){
            System.out.println("\t> Não Existem pessoas no Sistema.");
            pause();
        }
        else{
            /*Imprime os elementos do vetor ordenado.*/
            for(int i = 0; i < listar.length; i++){
                Pessoa imprime = (Pessoa)listar[i];
                System.out.println("> Nome: "+imprime.getNome().toUpperCase());
                System.out.println("> CPF: "+imprime.getCpf());
                System.out.println("> Pontuação: "+imprime.getPontuacao());
                System.out.println("--------------------------------------------------------------------");
            }
        }
        menuAdmin();
    }
    /*Lista os lances de determinado item.*/
    private static void listarLances() throws IOException, ParseException{
        clear();
        
        System.out.println("\t> Listagem de Lances por Item:\n");
        
        System.out.printf("> Digite o ID do Item que deseja visualizar os Lances: ");
        int idItem = Console.readInt();
        clear();
        Iterator lista = controla.listarLancesProduto(idItem); 
        if(lista == null){
            System.out.println("\t> Esse Item não possui lances ou Não existe.");
            pause();
        }
        else{
            while(lista.hasNext()){
                /*Lista os Lances de um determinado Item.*/
                Lance imprimir = (Lance) lista.next();
                Date data = imprimir.getDt_hr_lance();
                DecimalFormat df = new DecimalFormat("0.00");
                System.out.println("> Nome: "+imprimir.getPessoa().getNome().toUpperCase());
                System.out.println("> Valor: R$ "+df.format(imprimir.getValor()));
                System.out.println("> Data: "+data.getDate()+"/"+(data.getMonth()+1)+"/"+(data.getYear()+1900)+" "+data.getHours()+":"+data.getMinutes());
                System.out.println("-------------------------------------------------------------------");
            }
            pause();
        }
        menuAdmin();
    }
    /*Aguarda o ENTER do usuário.*/
    private static void pause() throws IOException{
        System.out.println("");
        System.out.println("\tPressione ENTER para continuar...\n");
        System.in.read();
    }
    /*"Limpa" a tela, para melhorar a visibilidade.*/
    private static void clear(){
        System.out.println("");
        System.out.println("-------------------------------------------------");
        System.out.println("");
    }
    
    public static void main(String[] args) throws IOException, ParseException{

        verificarUsuario();
    }
}
