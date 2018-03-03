package ed2;

import java.nio.ByteBuffer;
/**
 *
 * @author zkelvinfps
 */
public class ConversorAluno {
    public static ByteBuffer toByteBuffer(Aluno a) {
        ByteBuffer buf = ByteBuffer.allocate(TamanhoAluno.TOTAL);
        buf.putLong(a.getMatricula());
        int pos = TamanhoAluno.MATRICULA;
        
        buf.put(a.getNome().getBytes());
        pos += TamanhoAluno.NOME;
        
        buf.position(pos);
        buf.put(a.getEndereco().getBytes());
        pos += TamanhoAluno.ENDERECO;
        
        buf.position(pos);
        buf.put(a.getTelefone().getBytes());
        pos += TamanhoAluno.TELEFONE;
        
        buf.position(pos);
        buf.putShort(a.getCurso());
        pos += TamanhoAluno.CURSO;
        
        buf.put(a.getEmail().getBytes());
        pos += TamanhoAluno.EMAIL;

        buf.position(0);
        return buf;
    }

    public static Aluno toAluno(ByteBuffer buf) {
        long matricula = buf.getLong();
        
        byte[] b_nome = new byte[TamanhoAluno.NOME];
        buf.get(b_nome);
        String nome = new String(b_nome);
        
        byte[] b_endereco = new byte[TamanhoAluno.ENDERECO];
        buf.get(b_endereco);
        String endereco = new String(b_endereco);
        
        byte[] b_telefone = new byte[TamanhoAluno.TELEFONE];
        buf.get(b_telefone);
        String telefone = new String(b_telefone);
        
        short curso = buf.getShort();
        
        byte[] b_email = new byte[TamanhoAluno.EMAIL];
        buf.get(b_email);
        String email = new String(b_email);

        Aluno a = new Aluno(matricula, nome, endereco, telefone, curso, email);
        return a;
    }
}