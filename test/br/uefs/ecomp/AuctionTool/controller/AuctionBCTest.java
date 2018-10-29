package br.uefs.ecomp.AuctionTool.controller;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.uefs.ecomp.AuctionTool.util.*;
import br.uefs.ecomp.AuctionTool.model.*;
import java.text.ParseException;
import java.util.Date;

public class AuctionBCTest{

	private AuctionBC controller;
	
	@Before
	public void setUp() {
		controller = new AuctionBC();
	}
	
	@Test
	public void testCriarCategoriaComSucesso() {
		Categoria codigoCadastrar = controller.cadastrarCategoria("Automoveis");
                int testa = 1;
                if(codigoCadastrar == null){
                    testa = 0;
                }
		Assert.assertEquals(1 ,testa);
		Categoria categoria = controller.buscarCategoriaPorNome("Automoveis");
		Assert.assertEquals("Automoveis", categoria.getNome());
	}
	
	@Test
	public void testCriarCategoriaDuplicada() {
		Categoria codigoCadastrar = controller.cadastrarCategoria("Automoveis");
		codigoCadastrar = controller.cadastrarCategoria("Automoveis");
                int testa = 1;
                if(codigoCadastrar == null){
                    testa = 0;
                }
		Assert.assertEquals(0, testa);
	}
	
	@Test
	public void testAlterarCategoriaComSucesso() {
		Categoria codigoCadastrar = controller.cadastrarCategoria("Automoveis");
		int codigoAlterar = controller.alterarCategoria("Automoveis","Avioes");
		Assert.assertEquals(2,codigoAlterar);
		Categoria categoria = controller.buscarCategoriaPorNome("Avioes");
		Assert.assertEquals("Avioes", categoria.getNome());
	}
	
	@Test
	public void testAlterarCategoriaParaNomeJaExistente() {
		Categoria codigoCadastrar = controller.cadastrarCategoria("Automoveis");
		codigoCadastrar = controller.cadastrarCategoria("Avioes");
		int codigoAlterar = controller.alterarCategoria("Automoveis","Avioes");
		Assert.assertEquals(1,codigoAlterar);

		Categoria categoria = controller.buscarCategoriaPorNome("Automoveis");
		Assert.assertEquals("Automoveis", categoria.getNome());
		categoria = controller.buscarCategoriaPorNome("Avioes");
		Assert.assertEquals("Avioes", categoria.getNome());
	}
	
	@Test
	public void testAlterarCategoriaInexistente() {
		int codigoAlterar = controller.alterarCategoria("Automoveis","Avioes");
		Assert.assertEquals(0,codigoAlterar);
	}
	
	@Test
	public void testRemoverCategoriaComSucesso() {
		Categoria codigoCadastrar = controller.cadastrarCategoria("Automoveis");
		int codigoRemover = controller.removerCategoria("Automoveis");
		Assert.assertEquals(2,codigoRemover);
	}
	
	@Test
	public void testRemoverCategoriaInexistente() {
		int codigoRemover = controller.removerCategoria("Automoveis");
		Assert.assertEquals(0,codigoRemover);
	}
	
	@Test
	public void testRemoverCategoriaComItem() {
		Categoria codigoCadastrar = controller.cadastrarCategoria("Automoveis");
		int codigoCadastrarPessoa = controller.cadastrarPessoa("33333333333","Fulano","fulano@fulano.com.br","7523421897", "123");
		int codigoCadastrarItem = controller.cadastrarItem("Toyota Corolla","Automoveis",(float)49700.34,"33333333333");
		int codigoRemover = controller.removerCategoria("Automoveis");
		Assert.assertEquals(1,codigoRemover);
	}
	
	@Test
	public void testCadastrarPessoaComSucesso() {
		int codigoCadastrarPessoa = controller.cadastrarPessoa("33333333333","Fulano","fulano@fulano.com.br","7523421897", "123");
		Assert.assertEquals(1,codigoCadastrarPessoa);
		Pessoa pessoa = controller.buscarPessoaPorCpf("33333333333");
		Assert.assertEquals("Fulano", pessoa.getNome());
	}
	
	@Test
	public void testCadastrarPessoaComCpfJaCadastrado() {
		int codigoCadastrarPessoa = controller.cadastrarPessoa("33333333333","Fulano","fulano@fulano.com.br","7523421897", "123");
		codigoCadastrarPessoa = controller.cadastrarPessoa("33333333333","Fulano","fulano@fulano.com.br","7523421897", "123");
		Assert.assertEquals(0, codigoCadastrarPessoa);
	}
	
	@Test
	public void testAlterarPessoaComSucesso() {
		int codigoCadastrarPessoa = controller.cadastrarPessoa("33333333333","Fulano","fulano@fulano.com.br","7523421897", "123");
		int codigoAlterar = controller.alterarPessoa("33333333333","Fulano","fulano@fulano.com","7523421897");
		Assert.assertEquals(1,codigoAlterar);
		Pessoa pessoa = controller.buscarPessoaPorCpf("33333333333");
		Assert.assertEquals("fulano@fulano.com", pessoa.getEmail());
	}
	
	@Test
	public void testAlterarPessoaInexistente() {
		int codigoAlterar = controller.alterarPessoa("33333333333","Fulano","fulano@fulano.com","7523421897");
		Assert.assertEquals(0,codigoAlterar);
	}
	
	@Test
	public void testRemoverPessoaComSucesso() {
		int codigoCadastrarPessoa = controller.cadastrarPessoa("33333333333","Fulano","fulano@fulano.com.br","7523421897", "123");
		int codigoRemover = controller.removerPessoa("33333333333");
		Assert.assertEquals(3,codigoRemover);
	}
	
	@Test
	public void testRemoverPessoaInexistente() {
		int codigoRemover = controller.removerPessoa("33333333333");
		Assert.assertEquals(0,codigoRemover);
	}

	@Test
	public void testRemoverPessoaDonaDeItem() {
		Categoria codigoCadastrar = controller.cadastrarCategoria("Automoveis");
		int codigoCadastrarPessoa = controller.cadastrarPessoa("33333333333","Fulano","fulano@fulano.com.br","7523421897", "123");
		int codigoCadastrarItem = controller.cadastrarItem("Toyota Corolla","Automoveis",(float)49700.34,"33333333333");
		int codigoRemover = controller.removerPessoa("33333333333");
		Assert.assertEquals(1,codigoRemover);
	}
	
	@Test
	public void testRemoverPessoaComLance() {
		Categoria codigoCadastrar = controller.cadastrarCategoria("Automoveis");
		int codigoCadastrarPessoa = controller.cadastrarPessoa("33333333333","Fulano","fulano@fulano.com.br","7523421897", "123");
		codigoCadastrarPessoa = controller.cadastrarPessoa("44444444444","Beltrano","beltrano@beltrano.com.br","7523421797", "123");
		int codigoCadastrarItem = controller.cadastrarItem("Toyota Corolla","Automoveis",(float)49700.34,"33333333333");
		int codigoIniciarLeilao = controller.iniciarLeilaoDoProduto(1, "33333333333");
		int codigoCadastrarLance = controller.efetuarLance("44444444444",1,(float)49700.34);
		int codigoRemover = controller.removerPessoa("44444444444");
		Assert.assertEquals(2,codigoRemover);
	}
	
	@Test
	public void testCadastrarItemComSucesso() {
		Categoria codigoCadastrar = controller.cadastrarCategoria("Automoveis");
		int codigoCadastrarPessoa = controller.cadastrarPessoa("33333333333","Fulano","fulano@fulano.com.br","7523421897", "123");
		int codigoCadastrarItem = controller.cadastrarItem("Toyota Corolla","Automoveis",(float)49700.34,"33333333333");
		Assert.assertEquals(1,codigoCadastrarItem);
	}
	
	@Test
	public void testCadastrarItemComCpfInexistente() {
		Categoria codigoCadastrar = controller.cadastrarCategoria("Automoveis");
		int codigoCadastrarItem = controller.cadastrarItem("Toyota Corolla","Automoveis",(float)49700.34,"33333333333");
		Assert.assertEquals(0,codigoCadastrarItem);
	}
	
	@Test
	public void testCadastrarItemComCategoriaInexistente() {
		int codigoCadastrarPessoa = controller.cadastrarPessoa("33333333333","Fulano","fulano@fulano.com.br","7523421897", "123");
		int codigoCadastrarItem = controller.cadastrarItem("Toyota Corolla","Automoveis",(float)49700.34,"33333333333");
		Assert.assertEquals(1,codigoCadastrarItem);
	}
	
	@Test
	public void testAlterarItemComSucesso() {
		Categoria codigoCadastrar = controller.cadastrarCategoria("Automoveis");
		int codigoCadastrarPessoa = controller.cadastrarPessoa("33333333333","Fulano","fulano@fulano.com.br","7523421897", "123");
		int codigoCadastrarItem = controller.cadastrarItem("Toyota Corolla","Automoveis",(float)49700.34,"33333333333");
		int codigoAlterarItem = controller.alterarItem(1,"Toyota Corolla","Automoveis",(float)49900.34,"33333333333");
		Assert.assertEquals(1,codigoAlterarItem);
	}
	
	@Test
	public void testAlterarItemInexistente() {
		Categoria codigoCadastrar = controller.cadastrarCategoria("Automoveis");
		int codigoCadastrarPessoa = controller.cadastrarPessoa("33333333333","Fulano","fulano@fulano.com.br","7523421897", "12");
		int codigoCadastrarItem = controller.cadastrarItem("Toyota Corolla","Automoveis",(float)49700.34,"33333333333");
		int codigoAlterarItem = controller.alterarItem(2,"Toyota Corolla","Automoveis",(float)49900.34,"33333333333");
		Assert.assertEquals(2,codigoAlterarItem);
	}
	
	@Test
	public void testAlterarCategoriaDeItemParaCategoriaInexistente() {
		Categoria codigoCadastrar = controller.cadastrarCategoria("Automoveis");
		int codigoCadastrarPessoa = controller.cadastrarPessoa("33333333333","Fulano","fulano@fulano.com.br","7523421897", "123");
		int codigoCadastrarItem = controller.cadastrarItem("Toyota Corolla","Automoveis",(float)49700.34,"33333333333");
		int codigoAlterarItem = controller.alterarItem(1,"Toyota Corolla","Automoveis Novos",(float)49900.34,"33333333333");
		Assert.assertEquals(0,codigoAlterarItem);
	}
	
	@Test
	public void testAlterarDonoDeItemParaPessoaInexistente() {
		Categoria codigoCadastrar = controller.cadastrarCategoria("Automoveis");
		int codigoCadastrarPessoa = controller.cadastrarPessoa("33333333333","Fulano","fulano@fulano.com.br","7523421897", "123");
		int codigoCadastrarItem = controller.cadastrarItem("Toyota Corolla","Automoveis",(float)49700.34,"33333333333");
		int codigoAlterarItem = controller.alterarItem(1,"Toyota Corolla","Automoveis",(float)49900.34,"44444444444");
		Assert.assertEquals(1,codigoAlterarItem);
	}
	
	@Test
	public void testAlterarItemComStatusDiferenteDeCadastrado() {
		Categoria codigoCadastrar = controller.cadastrarCategoria("Automoveis");
		int codigoCadastrarPessoa = controller.cadastrarPessoa("33333333333","Fulano","fulano@fulano.com.br","7523421897", "123");
		int codigoCadastrarItem = controller.cadastrarItem("Toyota Corolla","Automoveis",(float)49700.34,"33333333333");
		controller.iniciarLeilaoDoProduto(1, "33333333333");
		int codigoAlterarItem = controller.alterarItem(1,"Toyota Corolla","Automoveis",(float)49900.34,"33333333333");
		Assert.assertEquals(2,codigoAlterarItem);
	}
	
	@Test
	public void testRemoverItemComSucesso() {
		Categoria codigoCadastrar = controller.cadastrarCategoria("Automoveis");
		int codigoCadastrarPessoa = controller.cadastrarPessoa("33333333333","Fulano","fulano@fulano.com.br","7523421897", "123");
		int codigoCadastrarItem = controller.cadastrarItem("Toyota Corolla","Automoveis",(float)49700.34,"33333333333");
		int codigoRemoverItem = controller.removerItem(1, "33333333333");
		Assert.assertEquals(2,codigoRemoverItem);
	}
	
	@Test
	public void testRemoverItemInexistente() {
		int codigoRemoverItem = controller.removerItem(1,"");
		Assert.assertEquals(1,codigoRemoverItem);
	}
	
	@Test
	public void testRemoverItemComStatusDiferenteDeCadastrado() {
		Categoria codigoCadastrar = controller.cadastrarCategoria("Automoveis");
		int codigoCadastrarPessoa = controller.cadastrarPessoa("33333333333","Fulano","fulano@fulano.com.br","7523421897", "123");
		int codigoCadastrarItem = controller.cadastrarItem("Toyota Corolla","Automoveis",(float)49700.34,"33333333333");
		controller.iniciarLeilaoDoProduto(1, "33333333333");
		int codigoRemoverItem = controller.removerItem(1, "33333333333");
		Assert.assertEquals(3,codigoRemoverItem);
	}
	
	@Test
	public void testListarLances() {
		Categoria codigoCadastrar = controller.cadastrarCategoria("Automoveis");
		int codigoCadastrarPessoa = controller.cadastrarPessoa("33333333333","Fulano","fulano@fulano.com.br","7523421897", "123");
		codigoCadastrarPessoa = controller.cadastrarPessoa("44444444444","Beltrano","beltrano@beltrano.com.br","7523421797", "123");
		codigoCadastrarPessoa = controller.cadastrarPessoa("77777777777","Cicrano","cicrano@cicrano.com.br","7523491797", "123");
		codigoCadastrarPessoa = controller.cadastrarPessoa("99999999999","Outra Pessoa","pessoa@pessoa.com.br","7523471797", "123");
		int codigoCadastrarItem = controller.cadastrarItem("Toyota Corolla","Automoveis",(float)49700.34,"33333333333");
		int codigoIniciarLeilao = controller.iniciarLeilaoDoProduto(1, "33333333333");
		int codigoCadastrarLance = controller.efetuarLance("44444444444",1,(float)49700.34);
		codigoCadastrarLance = controller.efetuarLance("77777777777",1,(float)49800.34);
		codigoCadastrarLance = controller.efetuarLance("99999999999",1,(float)49900.34);
                Iterator itrLances = controller.listarLancesProduto(1);
                Lance mostra = (Lance)itrLances.next();
                Assert.assertEquals("99999999999", mostra.getPessoa().getCpf());
                mostra = (Lance)itrLances.next();
                Assert.assertEquals("77777777777", mostra.getPessoa().getCpf());
                mostra = (Lance)itrLances.next();
                Assert.assertEquals("44444444444", mostra.getPessoa().getCpf());   
	}
        
        @Test
        public void testDesistirItem(){
            Categoria codigoCadastrar = controller.cadastrarCategoria("Automoveis");
            int codigoCadastrarPessoa = controller.cadastrarPessoa("33333333333","Fulano","fulano@fulano.com.br","7523421897", "123");
            codigoCadastrarPessoa = controller.cadastrarPessoa("44444444444","Beltrano","beltrano@beltrano.com.br","7523421797", "123");
            int codigoCadastrarItem = controller.cadastrarItem("Toyota Corolla","Automoveis",(float)49700.34,"33333333333");
	    int codigoIniciarLeilao = controller.iniciarLeilaoDoProduto(1, "33333333333");
            int codigoCadastrarLance = controller.efetuarLance("44444444444",1,(float)49700.34);
            
            int desistir = controller.desistirItem(1, "44444444444");
            Assert.assertEquals(2, desistir);
        }
        
        @Test
        public void testar() throws ParseException{
            Categoria codigoCadastrar = controller.cadastrarCategoria("Automoveis");
            int codigoCadastrarPessoa = controller.cadastrarPessoa("33333333333","Fulano","fulano@fulano.com.br","7523421897", "123");
            codigoCadastrarPessoa = controller.cadastrarPessoa("44444444444","Beltrano","beltrano@beltrano.com.br","7523421797", "123");
            int codigoCadastrarItem = controller.cadastrarItem("Toyota Corolla","Automoveis",(float)49700.34,"33333333333");
	    int codigoIniciarLeilao = controller.iniciarLeilaoDoProduto(1, "33333333333");
            int codigoCadastrarLance = controller.efetuarLance("44444444444",1,(float)49700.34);
            
            Iterator lista = controller.listarLancesProduto(1);
            Lance mostra = (Lance) lista.next();
            Date data = mostra.getDt_hr_lance();
            //System.out.println("> Data: "+data.getDate()+"/"+(data.getMonth()+1)+"/"+(data.getYear()+1900)+" "+data.getHours()+":"+data.getMinutes());
            
        }
        
        @Test
        public void darLanceComValorMenor(){
            Categoria codigoCadastrar = controller.cadastrarCategoria("Automoveis");
            int codigoCadastrarPessoa = controller.cadastrarPessoa("33333333333","Fulano","fulano@fulano.com.br","7523421897", "123");
            codigoCadastrarPessoa = controller.cadastrarPessoa("44444444444","Beltrano","beltrano@beltrano.com.br","7523421797", "123");
            int codigoCadastrarItem = controller.cadastrarItem("Toyota Corolla","Automoveis",(float)49700.34,"33333333333");
	    int codigoIniciarLeilao = controller.iniciarLeilaoDoProduto(1, "33333333333");
            int codigoCadastrarLance = controller.efetuarLance("44444444444",1,(float)45000.00);
            
            Assert.assertEquals(2, codigoCadastrarLance);
        }
        
        @Test
        public void darLanceEmAlimentoVencido(){
            Categoria codigoCadastrar = controller.cadastrarCategoria("Alimento");
            int codigoCadastrarPessoa = controller.cadastrarPessoa("33333333333","Fulano","fulano@fulano.com.br","7523421897", "123");
            Date agora = new Date();
            agora.setDate(10);
            agora.setMonth(10);
            agora.setYear(-500);
            codigoCadastrarPessoa = controller.cadastrarPessoa("44444444444","Beltrano","beltrano@beltrano.com.br","7523421797", "123");
            int codigoCadastrarItem = controller.cadastrarItem("Pizza", agora, (float)100.00,"33333333333");
	    int codigoIniciarLeilao = controller.iniciarLeilaoDoProduto(1, "33333333333");
            int codigoCadastrarLance = controller.efetuarLance("44444444444",1,(float)45000.00);
            
            Assert.assertEquals(5, codigoCadastrarLance);
            
        }
        
        @Test
        public void darLanceEmRemedioVencido(){
            Categoria codigoCadastrar = controller.cadastrarCategoria("Remedio");
            int codigoCadastrarPessoa = controller.cadastrarPessoa("33333333333","Fulano","fulano@fulano.com.br","7523421897", "123");
            Date agora = new Date();
            agora.setDate(10);
            agora.setMonth(10);
            agora.setYear(-500);
            codigoCadastrarPessoa = controller.cadastrarPessoa("44444444444","Beltrano","beltrano@beltrano.com.br","7523421797", "123");
            int codigoCadastrarItem = controller.cadastrarItem("Anador", 1, agora, (float)150.00, "33333333333");
	    int codigoIniciarLeilao = controller.iniciarLeilaoDoProduto(1, "33333333333");
            int codigoCadastrarLance = controller.efetuarLance("44444444444",1,(float)45000.00);
            
            Assert.assertEquals(5, codigoCadastrarLance);
        }
        
        @Test
        public void testExibirVencedorLeilao(){
            int codigoCadastrarPessoa = controller.cadastrarPessoa("33333333333","Fulano","fulano@fulano.com.br","7523421897", "123");
            codigoCadastrarPessoa = controller.cadastrarPessoa("44444444444","Fulano","fulano@fulano.com.br","7523421897", "123");
            codigoCadastrarPessoa = controller.cadastrarPessoa("55555555555","Fulano","fulano@fulano.com.br","7523421897", "123");
            codigoCadastrarPessoa = controller.cadastrarPessoa("6666666","Fulano","fulano@fulano.com.br","7523421897", "123");
            Categoria codigoCadastrar = controller.cadastrarCategoria("Automoveis");
            int codigoCadastrarItem = controller.cadastrarItem("Toyota Corolla","Automoveis",(float)49700.34,"33333333333");
            int codigoIniciarLeilao = controller.iniciarLeilaoDoProduto(1, "33333333333");
            
            int cadastrarLance = controller.efetuarLance("44444444444", 1, (float)65500.10);
            cadastrarLance = controller.efetuarLance("55555555555", 1, (float)95014.85);
            cadastrarLance = controller.efetuarLance("6666666", 1, (float)74554.040);
            int encerra_leilao = controller.encerrarLeilao(1, "33333333333");
            Pessoa vencedor = controller.exibeVencedor(1);
            
            Assert.assertEquals("55555555555", vencedor.getCpf());
        }
        
        @Test
        public void testPontuacaoDoDonoDoItem(){
            int codigoCadastrarPessoa = controller.cadastrarPessoa("33333333333","Fulano","fulano@fulano.com.br","7523421897", "123");
            codigoCadastrarPessoa = controller.cadastrarPessoa("44444444444","Fulano","fulano@fulano.com.br","7523421897", "123");
            codigoCadastrarPessoa = controller.cadastrarPessoa("55555555555","Fulano","fulano@fulano.com.br","7523421897", "123");
            codigoCadastrarPessoa = controller.cadastrarPessoa("6666666","Fulano","fulano@fulano.com.br","7523421897", "123");
            Categoria codigoCadastrar = controller.cadastrarCategoria("Automoveis");
            int codigoCadastrarItem = controller.cadastrarItem("Toyota Corolla","Automoveis",(float)49700.34,"33333333333");
            int codigoIniciarLeilao = controller.iniciarLeilaoDoProduto(1, "33333333333");
            
            int cadastrarLance = controller.efetuarLance("44444444444", 1, (float)65500.10);
            cadastrarLance = controller.efetuarLance("55555555555", 1, (float)95014.85);
            cadastrarLance = controller.efetuarLance("6666666", 1, (float)74554.040);
            int encerra_leilao = controller.encerrarLeilao(1, "33333333333");
            
            Item verifica = controller.buscarItemPorID(1);
            Pessoa dono = verifica.getDono();
            
            Assert.assertEquals(1, dono.getPontuacao());
        }
        
        @Test
        public void testPontuacaoZeradaDoDonoAposDesistencia(){
            int codigoCadastrarPessoa = controller.cadastrarPessoa("33333333333","Fulano","fulano@fulano.com.br","7523421897", "123");
            codigoCadastrarPessoa = controller.cadastrarPessoa("44444444444","Fulano","fulano@fulano.com.br","7523421897", "123");
            codigoCadastrarPessoa = controller.cadastrarPessoa("55555555555","Fulano","fulano@fulano.com.br","7523421897", "123");
            codigoCadastrarPessoa = controller.cadastrarPessoa("6666666","Fulano","fulano@fulano.com.br","7523421897", "123");
            Categoria codigoCadastrar = controller.cadastrarCategoria("Automoveis");
            int codigoCadastrarItem = controller.cadastrarItem("Toyota Corolla","Automoveis",(float)49700.34,"33333333333");
            int codigoIniciarLeilao = controller.iniciarLeilaoDoProduto(1, "33333333333");
            
            int cadastrarLance = controller.efetuarLance("44444444444", 1, (float)65500.10);
            cadastrarLance = controller.efetuarLance("55555555555", 1, (float)95014.85);
            cadastrarLance = controller.efetuarLance("6666666", 1, (float)74554.040);
            
            int encerra_leilao = controller.encerrarLeilao(1, "33333333333");
            
            int desiste = controller.desistirItem(1, "55555555555");
            desiste = controller.desistirItem(1, "6666666");
            desiste = controller.desistirItem(1, "44444444444");
            Item desistencia = controller.buscarItemPorID(1);
            
            Pessoa dono = desistencia.getDono();
            
            Assert.assertEquals(0, dono.getPontuacao());
        }
        @Test
        public void testDonoDarLanceEmSeuItem(){
            Categoria codigoCadastrar = controller.cadastrarCategoria("Remedio");
            int codigoCadastrarPessoa = controller.cadastrarPessoa("33333333333","Fulano","fulano@fulano.com.br","7523421897", "123");
            Date agora = new Date();
            agora.setDate(10);
            agora.setMonth(10);
            agora.setYear(-500);
            int codigoCadastrarItem = controller.cadastrarItem("Anador", 1, agora, (float)150.00, "33333333333");
	    int codigoIniciarLeilao = controller.iniciarLeilaoDoProduto(1, "33333333333");
            int codigoCadastrarLance = controller.efetuarLance("33333333333",1,(float)45000.00);
            
            Assert.assertEquals(6, codigoCadastrarLance);
        }
       
}