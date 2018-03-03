package ed2;
/**
 *
 * @author zkelvinfps
 */
public class Aluno {
    private long matricula;
    private String nome,endereco,telefone,email;
    private short curso;
    
    public Aluno(long matricula, String nome, String endereco,
            String telefone, short curso, String email){
        this.matricula = matricula;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.curso = curso;
        this.email = email;
    }
    
    public void imprimiAluno(){
        System.out.println(matricula+", "+nome+", "+endereco
                +", "+telefone+", "+curso+", "+email);
    }
    
    public long getMatricula() {
        return matricula;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }
    
    public String getTelefone() {
        return telefone;
    }

    public short getCurso() {
        return curso;
    }

    public String getEmail() {
        return email;
    }

    public void setMatricula(long matricula) {
        this.matricula = matricula;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setCurso(short curso) {
        this.curso = curso;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}