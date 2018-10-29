package br.uefs.ecomp.AuctionTool.controller;

import br.uefs.ecomp.AuctionTool.util.*;
import br.uefs.ecomp.AuctionTool.model.*;
import java.util.Date;
public class AuctionBC {
    
    /*Listas encadeadas para armazenamento dos referidos Objetos*/
    private Lista categorias;
    private Lista pessoas;
    private Lista itens;
    /*Construtor da Controller que cria as listas utilizadas.*/
        public AuctionBC(){
            this.categorias = new Lista();
            this.pessoas = new Lista();
            this.itens = new Lista();
        }
        /*Método que retorna um iterador para mostrar as categorias cadastradas no sistema.*/
        public Iterator listarCategorias(){
            Iterator lista_cat = categorias.iterador();
            
            return lista_cat;
	}
        /*Método que retorna um iterador para listar os itens do sistema.*/
	public Iterator listarItens(){
		Iterator listar = itens.iterador();
                return listar;
	}
        /*Método que retorna um iterador para listar os itens de uma determinada pessoa*/
        public Iterator listarItensPorPessoa(String cpf){
            Iterator listar_itens = itens.iterador();
            while(listar_itens.hasNext()){
                Item verifica = (Item) listar_itens.next();
                if(verifica.getDono().getCpf().equals(cpf)){
                    Iterator lista = itens.iterador();
                    return lista;
                }
            }
            return null;
        }
	/*Método que cria uma nova categoria e à coloca no fim da lista.*/
	public Categoria cadastrarCategoria(String nome) {
            Categoria verifica = buscarCategoriaPorNome(nome);
            if(verifica == null){
                Date data = new Date();
                Categoria novo = new Categoria(nome, data);
                categorias.addEnd(novo);
                return novo;
            }
            else{
                return null;
            }
	}
        /*Método que altera o nome da categoria.*/
        public int alterarCategoria(String nomeAtual, String novoNome) {
            //Verifica se a categoria que seria alterada existe.
            //Em caso negativo, retorna 0.
            if(!verificaCategoriaPorNome(nomeAtual)){
                return 0;
            }
            if(verificaCategoriaPorNome(novoNome)){
                return 1;
            }
            
            //Senão, altera o nome da categoria.
            else{
                Categoria altera = buscarCategoriaPorNome(nomeAtual);
                altera.setNome(novoNome);
                return 2;
            }
	}
	/*Método que verifica a existência de uma categoria a partir de seu nome.*/
	public boolean verificaCategoriaPorNome(String nome){
            
            Iterator lista = categorias.iterador();
            while(lista.hasNext()){
                    Categoria altera = (Categoria) lista.next();
                    if(altera.getNome().equalsIgnoreCase(nome)){
                        return true;
                    }
                }
            //Retorna falso caso a categoria não exista, no sistema.
            return false;
	}
	/*Método que buscar e retorna uma categoria a partir do seu nome.*/
	public Categoria buscarCategoriaPorNome(String nome) {
		Iterator percorre = categorias.iterador();
                while(percorre.hasNext()){
                    Categoria altera = (Categoria) percorre.next();
                    if(altera.getNome().equalsIgnoreCase(nome)){
                        return altera;
                    }
                }
                //Retorna null em caso negativo.
                return null;
                        
	}
        /*Método que remove uma categoria do sistema a partir do seu nome.*/
	public int removerCategoria(String nomeCategoria) {
            
                boolean existe = false;
                //Verifica a existência da categoria a partir do seu nome.
		if(!verificaCategoriaPorNome(nomeCategoria)){
                    return 0;
                }
                //Verifica se existem itens relacionados a essa categoria.
                else{
                    Iterator verificar_itens = itens.iterador();
                    while(verificar_itens.hasNext()){
                        Item verifica = (Item) verificar_itens.next();
                        if(verifica.getCategoria().getNome().equalsIgnoreCase(nomeCategoria)){
                            existe = true;
                            break;
                        }
                    }
                }
                /*Caso existam itens relacionados a categoria citada, o método encerrará.*/
                if(existe == true){
                    return 1;
                }
                /*Busca a categoria e a remove.*/
                else{
                    Iterator busca_categoria = categorias.iterador();
                    int i = 0;
                    while(busca_categoria.hasNext()){
                        Categoria removida = (Categoria) busca_categoria.next();
                        if(removida.getNome().equalsIgnoreCase(nomeCategoria)){
                            categorias.remove(i);
                            return 2;
                        }
                        i++;
                    }
                }
                return 3;
	}
        /*Método que busca uma pessoa a partir do seu CPF*/
        public Pessoa buscarPessoaPorCpf(String cpf) {
            Iterator verifica = pessoas.iterador();
            Pessoa olha = null;
            while(verifica.hasNext()){
                olha = (Pessoa) verifica.next();
                if(olha.getCpf().equals(cpf)){
                    return olha;
                }
            }
            //Retorna null em caso negativo.
            return null;
	}
        /*Método que cadastra uma pessoa ao sistema e cria um login e senha para ela.*/
        public int cadastrarPessoa(String cpf, String nome, String email, String telefone, String senha) {
            Pessoa nova = buscarPessoaPorCpf(cpf);
            if(nova == null){
                nova = new Pessoa(nome, cpf, email, telefone);
                nova.setSenha(senha);
                pessoas.addEnd(nova);
                return 1;
            }
            else{
                return 0;
            }
	}
        /*Método que altera os dados de uma pessoa cadastrada no sistema.*/
        public int alterarPessoa(String cpf, String nome, String email, String telefone) {
            Pessoa altera = buscarPessoaPorCpf(cpf);
            /*Retorna null caso a pessoa citada não exista no sistema.
            PS.: Nunca acontecerá.*/
            if(altera == null){
                return 0;
            }
            else{
                altera.setEmail(email);
                altera.setTelefone(telefone);
                altera.setNome(nome);
                return 1;
            }
	}
        /*Método que remove uma pessoa do sistema, caso a referida pessoa não possua um item
        ou um lance associado a ela.*/
        public int removerPessoa(String cpf) {
            Pessoa removida = buscarPessoaPorCpf(cpf);
            /*Caso a pessoa com o referido CPF não exista, retornará 0*/
            if(removida == null){
                return 0;
            }
            
            else{
                Iterator verifica_itens = itens.iterador();
                /*Verifica se a referida pessoa possui um item no sistema*/
                while(verifica_itens.hasNext()){
                    Item verifica = (Item) verifica_itens.next();
                    if(verifica.getDono().getCpf().equals(cpf)){
                        return 1;
                    }
                }
                Iterator remover_pessoa = itens.iterador();
                /*Verifica se a referida pessoa possui um item no sistema.*/
                while(remover_pessoa.hasNext()){
                    Item remover = (Item) remover_pessoa.next();
                    FilaPrioridade verifica_fila = remover.getLances();
                    Iterator verifica_lances = verifica_fila.iterador();
                    
                    while(verifica_lances.hasNext()){
                        Lance verificar_lance = (Lance) verifica_lances.next();
                        if(verificar_lance.getPessoa().getCpf().equals(cpf)){
                            return 2;
                        }
                    }
                }
            }
            Iterator remover_pessoa = pessoas.iterador();
            int i = 0;
            while(remover_pessoa.hasNext()){
                Pessoa remover = (Pessoa) remover_pessoa.next();
                if(remover.getCpf().equals(cpf)){
                    pessoas.remove(i);
                    return 3;
                }
                i++;
            }
            /*Essa instrução de retorno está aqui, porque o NetBeans mandou.
            Porém, essa instrução nunca será retornada.(Ou não deveria).*/
            return 4;
	}
        /*Cadastra um Item qualquer.*/
	public int cadastrarItem(String nomeItem, String nomeCategoria, float precoInicial, String cpfDono) {
		
            Pessoa dono = buscarPessoaPorCpf(cpfDono);
            if(dono == null){
                return 0;
            }
            Date agora = new Date();
            Categoria categoria = buscarCategoriaPorNome(nomeCategoria);
            if(categoria == null){
                categoria = new Categoria(nomeCategoria, agora);
                categorias.addEnd(categoria);
            }
            Item novo = new Item(nomeItem, precoInicial, agora, dono, categoria);
            itens.addEnd(novo);
            
            return 1;
        }
        /*Cadastra um item que seja um remédio.*/
        public int cadastrarItem(String item, int receita_remedio, Date validade, float preco_inicial, String cpfDono){
            
            Date agora = new Date();
            Categoria verifica = buscarCategoriaPorNome("remedio");
            Pessoa verifica_pessoa = null;
            if(verifica == null){
                verifica = cadastrarCategoria("remedio");
            }
            else{
                verifica_pessoa = buscarPessoaPorCpf(cpfDono);
                if(verifica_pessoa == null){
                    return 0;
                }
            }
            boolean receita = false;
            /*Seta o atributo de acordo com oque foi selecionado pelo usuário.
            Faz referência a possibilidade do remédio não possuir receita.*/
            if(receita_remedio == 1){
                receita = true;
            }
            /*Cria um novo objeto e o joga na lista de itens.*/
            Remedio novo = new Remedio(item, verifica, preco_inicial, agora, verifica_pessoa, validade, receita);
            itens.addEnd(novo);
            return 1;
        }
        /*Cadastra um item que seja um alimento*/
        public int cadastrarItem(String item, Date validade, float preco_inicial, String cpfDono){
            Date agora = new Date();
            Pessoa dono = buscarPessoaPorCpf(cpfDono);
            
            if(dono == null){
                return 0;
            }
            
            Categoria categoria = buscarCategoriaPorNome("alimento");
            if(categoria == null){
                categoria = cadastrarCategoria("alimento");
            }
            
            Alimento novo = new Alimento(item, categoria, preco_inicial, agora, dono, validade);
            itens.addEnd(novo);
            return 1;
        }
        /*Cadastra um item que seja um imovel.*/
        public int cadastrarItem(String item, int ocupacao_imovel, float preco_inicial, String cpfDono){
            Date agora = new Date();
            /*Verifica a existência da pessoa.*/
            Pessoa dono = buscarPessoaPorCpf(cpfDono);
            if(dono == null){
                return 0;
            }
            /*Verifica a existência da categoria.*/
            Categoria categoria = buscarCategoriaPorNome("imovel");
            if(categoria == null){
                categoria = cadastrarCategoria("imovel");
            }
            boolean ocupacao = false;
            /*Seta o status do imóvel baseando-se no que o usuário passou como parâmetro.*/
            if(ocupacao_imovel == 1){
                ocupacao = true;
            }
                /*Cria um novo imóvel e o joga na lista.*/
                Imovel novo = new Imovel(item, categoria, preco_inicial, agora, dono, ocupacao);
                itens.addEnd(novo);
            return 1;
            
        }
        /*Método que busca um item a partir do seu ID*/
        public Item buscarItemPorID(int idItem){
            Iterator item = itens.iterador();
            while(item.hasNext()){
                Item busca = (Item) item.next();
                if(busca.getCodigo() == idItem){
                    return busca;
                }
            }
            return null;
        }
        /*Método que altera os dados de um item caso esse não esteja em leilão.*/
        public int alterarItem(int idItem, String nomeItem, String nomeCategoria, float precoInicial, String cpfDono) {
            
            Categoria verifica = buscarCategoriaPorNome(nomeCategoria);
            if(verifica == null){
                return 0;
            }
            
            else{
                Item alterado = buscarItemPorID(idItem);
                if(alterado != null && alterado.isStatus() == Status.CADASTRADO){
                    alterado.setCategoria(verifica);
                    alterado.setNome(nomeItem);
                    alterado.setPreco_inicial(precoInicial);
                    return 1;
                }
            }
            return 2;
	}
        /*Método que registra um Lance de uma pessoa em um Item.*/
	public int efetuarLance(String cpfPessoa, int idItem, float valorLance) {
            
            Item verifica_item = buscarItemPorID(idItem);
            Pessoa pessoa = buscarPessoaPorCpf(cpfPessoa);
            Date agora = new Date();
            
            if(verifica_item == null){
                return 0;
            }
            
            if(pessoa == null){
                return 1;
            }
            
            if(verifica_item.getDono().getCpf().equals(cpfPessoa)){
                return 6;
            }
            
            if(verifica_item.getCategoria().getNome().equalsIgnoreCase("REMEDIO")){
                
                if(((Remedio)verifica_item).getDt_validade().compareTo(agora) < 0){
                    return 5;
                }
                else{
                    if(valorLance < verifica_item.getPreco_inicial()){
                        return 2;
                    }
                    Lance lance = new Lance(agora, pessoa, valorLance);
                    verifica_item.getLances().add(lance);
                    return 3;
                }
            }
            
            else if(verifica_item.getCategoria().getNome().equalsIgnoreCase("ALIMENTO")){
                
                if(((Alimento)verifica_item).getValidade().compareTo(agora) < 0){
                    return 5;
                }
                else{
                    if(valorLance < verifica_item.getPreco_inicial()){
                        return 2;
                    }
                    Lance lance = new Lance(agora, pessoa, valorLance);
                    verifica_item.getLances().add(lance);
                    return 3;
                }
            }
            if(valorLance < verifica_item.getPreco_inicial()){
                return 2;
            }
            if(verifica_item.isStatus() == Status.EM_LEILAO){
                Lance lance = new Lance(agora, pessoa, valorLance);
                verifica_item.getLances().add(lance);
                return 3;
            }
            return 4;
	}
        /*Método que permite que o dono de um determinado produto o coloque em leilão.*/
	public int iniciarLeilaoDoProduto(int idItem, String cpf) {
            Item leiloar = buscarItemPorID(idItem);
            
            if(leiloar == null){
                return 0;
            }
            else if(!leiloar.getDono().getCpf().equals(cpf)){
                return 1;
            }
            else{
                leiloar.setStatus(Status.EM_LEILAO);
                return 2;
            }
	}
        /*Método que remove um Item caso esse não possua lances relacionados à ele e não esteja em leilão.*/
	public int removerItem(int idItem, String cpf) {
            if(itens == null){
                return 0;
            }
            Item removido = buscarItemPorID(idItem);
            if(removido == null){
                return 1;
            }
            
            else{
                if(removido.getLances().isEmpty()){
                    Iterator remover = itens.iterador();
                    int i = 0;
                    while(remover.hasNext()){
                        Item remove = (Item) remover.next();
                        
                        if(remove.getCodigo() == idItem && remove.isStatus() == Status.CADASTRADO && remove.getDono().getCpf().equals(cpf)){
                            itens.remove(i);
                            return 2;
                        }
                        i++;
                    }
                }
            }
            return 3;
	}
        /*Método que permite que um usuário desista de seu lance*/
        public int desistirItem(int idItem, String cpf){
            
            Item desistencia = buscarItemPorID(idItem);
            if(desistencia == null){
                return 0;
            }
            Pessoa desistiu = buscarPessoaPorCpf(cpf);
            if(desistiu == null){
                return 1;
            }
            if(desistencia.isStatus() == Status.VENDIDO || desistencia.isStatus() == Status.DESISTENCIA){
                Lance remover = (Lance)desistencia.getLances().peek();
                if(remover.getPessoa().getCpf().equals(cpf)){
                    desistencia.setStatus(Status.DESISTENCIA);
                    desistencia.getLances().remove();
                    desistiu.setPontuacao(desistiu.getPontuacao()-1);
                    if(desistencia.getLances().isEmpty()){
                        Pessoa dono = desistencia.getDono();
                        dono.setPontuacao(dono.getPontuacao()-1);
                        return 2;
                    }
                    return 2;
                }
                return 4;
            }
            else{
                return 3;
            }  
        }
        /*Método que retorna um iterador, para lsitar os lances de um produto.*/
	public Iterator listarLancesProduto(int idProduto) {
            Item listar = buscarItemPorID(idProduto);
            if(listar == null){
                return null;
            }
            Iterator listar_lances = listar.getLances().iterador();
            if(!listar_lances.hasNext()){
                return null;
            }
            return listar_lances;
	}
        /*Método que retorna o Vencedor de um leilão.*/
        public Pessoa exibeVencedor(int idItem){
            Item mostrar = buscarItemPorID(idItem);
            if(mostrar == null){
                return null;
            }
            else if(mostrar.getLances().isEmpty()){
                return null;
            }
            else if((mostrar.isStatus() == Status.VENDIDO || mostrar.isStatus() == Status.DESISTENCIA)){
                Lance vencedor = (Lance) mostrar.getLances().peek();
                return vencedor.getPessoa();
            }
            return null;
            
        }
        /*Método que retorna um Array para listar as pessoas por pontuação*/
        public Comparable[] listarPessoasPorPontuacao(){
            Iterator copiar_pessoas = pessoas.iterador();
            if(!copiar_pessoas.hasNext()){
                return null;
            }
            Comparable[] pessoas_ordenadas = new Comparable[pessoas.size()];
            int x = 0;
            while(copiar_pessoas.hasNext()){
                pessoas_ordenadas[x] = (Comparable) copiar_pessoas.next();
                x++;
            }
            
            if(pessoas_ordenadas[0] == null){
                return null;
            }
            
            
            HeapSort.heapSort(pessoas_ordenadas, pessoas_ordenadas.length);
            
            return pessoas_ordenadas;
        }
        /*Método que encerra um leilão de um item a pedido de seu dono*/
        public int encerrarLeilao(int idItem, String cpfDono){
            Item encerrado = buscarItemPorID(idItem);
            if(encerrado == null){
                return 0;
            }
            
            if(encerrado.isStatus() == Status.CADASTRADO){
                return 1;
            }
            else if(encerrado.isStatus() == Status.DESISTENCIA){
                return 2;
            }
            else if(encerrado.isStatus() == Status.VENDIDO){
                return 3;
            }
            else{
                if(encerrado.getLances().isEmpty()){
                    return 4;
                }
                else{
                    if(encerrado.getDono().getCpf().equals(cpfDono)){
                        encerrado.getDono().setPontuacao(encerrado.getDono().getPontuacao()+1);
                        encerrado.setStatus(Status.VENDIDO);
                        return 5;
                    }
                    else{
                        return 6;
                    }
                }
            }
        }
        /*Método que permite o login do Administrador.*/
        public boolean autenticaAdmin(String user, String senha){
            if(user.equals("jamyllesf") && senha.equals("pbl2")){
                return true;
            }
            else{
                return false;
            }
        }
        /*Método que verifica se o usuário está cadastrado ou não no sistema.*/
        public boolean autenticaUsuario(String cpf, String senha){
        Iterator verifica = pessoas.iterador();
        while(verifica.hasNext()){
            Pessoa confere = (Pessoa) verifica.next();
            if(confere.getCpf().equals(cpf) && confere.getSenha().equals(senha)){
                return true;
            }
        }
        return false;
        }
}
