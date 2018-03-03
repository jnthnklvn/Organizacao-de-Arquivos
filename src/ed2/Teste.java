package ed2;

import java.nio.ByteBuffer;
/**
 *
 * @author zkelvinfps
 */
public class Teste {
    public static void main(String[] args) {
        Aluno a = new Aluno(11, "Marta", "Rua A, 01", "(79)3211-2397",
                (short)171, "marta@ufs.br");
        ByteBuffer buf = ConversorAluno.toByteBuffer(a);
        Aluno b = ConversorAluno.toAluno(buf);
        
        System.out.println(b.getMatricula());
        System.out.println(b.getNome());
        System.out.println(b.getEndereco());
        System.out.println(b.getTelefone());
        System.out.println(b.getCurso());
        System.out.println(b.getEmail());
    }
}
