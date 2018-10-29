
package br.uefs.ecomp.AuctionTool.model;


public class Pessoa implements Comparable{
    
    private String nome; //Armazena o nome da pessoa
    private String cpf; //Armazena o CPF da pessoa
    private String email; //Armazena o e-mail da pessoa
    private String telefone; //Armazena o telefone da pessoa
    private String senha; //Armazena a senha da pessoa
    private int pontuacao; //Armazena a pontuação da pessoa
    
    /*Construtor*/
    public Pessoa(String nome, String cpf, String email, String telefone){
        
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.telefone = telefone;
        this.pontuacao = 0;
    }
    
    
    public String getNome() {
        return nome; //Retorna o nome da Pessoa
    }

    public void setNome(String nome) {
        this.nome = nome; //Altera o nome da Pessoa
    }

    public String getCpf() {
        return cpf; //Retorna o CPF da Pessoa
    }

    public void setCpf(String cpf) {
        this.cpf = cpf; //Altera o CPF da Pessoa
    }

    public String getEmail() {
        return email; //Retorna o email da Pessoa
    }

    public void setEmail(String email) {
        this.email = email; //Altera o e-mail da Pessoa
    }

    public String getTelefone() {
        return telefone; //Retorna o telefone da Pessoa
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone; //Altera o telefone da Pessoa
    }

    public int getPontuacao() {
        return pontuacao; //Retorna a pontuação da Pessoa
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao; //altera a pontuação da Pessoa
    }

    public String getSenha() {
        return senha; //Retorna a senha da Pessoa
    }

    public void setSenha(String senha) {
        this.senha = senha; //Altera a senha da Pessoa
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof Pessoa){
            Pessoa nova = (Pessoa)o;
            if(pontuacao == nova.getPontuacao()){
                return 0;
            }
            else if(pontuacao < nova.getPontuacao()){
                return -1;
            }
            else{
                return 1;
            }
        }
        return -2;
    }
}
