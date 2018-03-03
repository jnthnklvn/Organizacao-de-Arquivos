package cadastro;

/**
 * Interface que define as operações de organizadores de arquivos de alunos em
 * disco.
 *
 * @author j_kel
 */
public interface IFileOrganizer {

    /**
     * Dada uma instância da classe Aluno, este método adiciona os dados da
     * instância em um arquivo seguindo o método de organização de arquivos
     * especificado.
     *
     * @param a Instância da classe Aluno
     */
    public void addAluno(Aluno a);

    /**
     * Dado um número de matrícula, este método consulta o arquivo de alunos e
     * devolve uma instância que encapsula aos dados do aluno que contém a
     * matrícula fornecida.
     *
     * @param matricA Número de matrícula para a consulta.
     * @return Instância da classe Aluno correspondente
     */
    public Aluno getAluno(long matricA);

    /**
     * Dado um número de matrícula, localiza e exclui o registro do arquivo de
     * alunos que corresponde à matrícula fornecida.
     *
     * @param matricA Matrícula do aluno a ser excluído.
     * @return Instância da classe Aluno correspondente ao excluído.
     */
    public Aluno delAluno(long matricA);

    /**
     * Percorre todos os registros instanciando e imprimindo as informações dos
     * alunos
     */
    public void percorrer();
}
