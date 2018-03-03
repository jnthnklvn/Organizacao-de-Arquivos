package ed2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zkelvinfps
 */
public class OrganizadorSimples implements IFileOrganizer {

    private FileChannel canal;

    public OrganizadorSimples(String fileName) {
        File file = new File(fileName);
        try {
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            this.canal = raf.getChannel();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(OrganizadorSimples.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Aluno getAluno(long matricula) {
        try {
            long size = this.canal.size();
            for (long pos = 0; pos < size; pos += TamanhoAluno.TOTAL) {
                ByteBuffer buf = ByteBuffer.allocate(TamanhoAluno.MATRICULA);
                this.canal.read(buf, pos);
                buf.flip();
                if (matricula == buf.getLong()) {
                    ByteBuffer bufAluno = ByteBuffer.allocate(TamanhoAluno.TOTAL);
                    this.canal.read(bufAluno, pos);
                    return ConversorAluno.toAluno(bufAluno);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(OrganizadorSimples.class.getName()).log(Level.SEVERE, null, ex);
        }return null;
    }//canal.truncate() remove ultimo Aluno

    @Override
    public void addAluno(Aluno a) {
        try {
            ByteBuffer buf = ConversorAluno.toByteBuffer(a);
            this.canal.write(buf,this.canal.size());
        } catch (IOException ex) {
            Logger.getLogger(OrganizadorSimples.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Aluno delAluno(long matricula) {
        try {
            long size = this.canal.size();
            long ultimo = size-TamanhoAluno.TOTAL;
            
            for (long pos = 0; pos < size; pos += TamanhoAluno.TOTAL) {
                ByteBuffer buf = ByteBuffer.allocate(TamanhoAluno.MATRICULA);
                this.canal.read(buf, pos);
                buf.flip();
                if (matricula == buf.getLong()) {
                    ByteBuffer buf1 = ByteBuffer.allocate(TamanhoAluno.TOTAL);
                    this.canal.read(buf1, pos);
                    buf1.flip();
                    ByteBuffer bufAluno = ByteBuffer.allocate(TamanhoAluno.TOTAL);
                    this.canal.read(bufAluno, ultimo);
                    bufAluno.flip();
                    this.canal.write(bufAluno, pos);
                    this.canal.truncate(ultimo);
                    return ConversorAluno.toAluno(buf1);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(OrganizadorSimples.class.getName()).log(Level.SEVERE, null, ex);
        }return null;
    }
    @Override
    public void percorrer() {
        try {
            long size = this.canal.size();
            ConversorAluno cA = new ConversorAluno();
            for (long pos = 0; pos < size; pos += TamanhoAluno.TOTAL) {
                ByteBuffer buf = ByteBuffer.allocate(TamanhoAluno.TOTAL);
                this.canal.read(buf, pos);
                buf.flip();
                Aluno a = ConversorAluno.toAluno(buf);
                System.out.println("------- "+pos+" -------");
                a.imprimiAluno();
            }
        } catch (IOException ex) {
            Logger.getLogger(OrganizadorSimples.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void esvaziar(){
        while(!vazio()){
            try {
                this.canal.truncate(0);
            } catch (IOException ex) {
                Logger.getLogger(OrganizadorSimples.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public boolean vazio(){
        try {
            if (this.canal.size()<300){
                return true;
            }
        } catch (IOException ex) {
            Logger.getLogger(OrganizadorSimples.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
