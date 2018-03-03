/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastro;

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
 * @author j_kel
 */
public class OrganizadorBrent implements IFileOrganizer {

    FileChannel canal;
    final long LENT = 7;

    public OrganizadorBrent(String fileName) {
        File file = new File(fileName);
        try {
            RandomAccessFile rf = new RandomAccessFile(file, "rw");
            this.canal = rf.getChannel();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(OrganizadorBrent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int custoA1(long m, long inc, long pos) {
        pos += inc;
        int custo = 0;
        ByteBuffer bufA1 = ByteBuffer.allocate(TamanhoAluno.MATRICULA);
        try {
            this.canal.read(bufA1, pos * TamanhoAluno.TOTAL);
            bufA1.flip();
            while (bufA1.getLong() != 0) {
                pos += inc;
                this.canal.read(bufA1, pos * TamanhoAluno.TOTAL);
                bufA1.flip();
                custo++;
            }
        } catch (IOException ex) {
            Logger.getLogger(OrganizadorBrent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return custo;
    }

    @Override
    public void addAluno(Aluno a) {
        try {
            long pos = (a.getMatricula() % LENT);
            ByteBuffer buf = ByteBuffer.allocate(TamanhoAluno.MATRICULA);
            ByteBuffer bufA = ConversorAluno.toByteBuffer(a);
            ByteBuffer bufA1 = ByteBuffer.allocate(TamanhoAluno.TOTAL);
            this.canal.read(bufA1, pos * TamanhoAluno.TOTAL);
            bufA1.flip();
            Aluno a1 = ConversorAluno.toAluno(bufA1);
            if (a1.getMatricula() == 0) {
                this.canal.write(bufA, pos * TamanhoAluno.TOTAL);
            } else {
                long matricA1 = a1.getMatricula();
                long incA = ((a.getMatricula()) % (LENT - 2)) + 1;
                long incA1 = ((a1.getMatricula()) % (LENT - 2)) + 1;
                int custo1 = 0, custo2 = custoA1(matricA1, incA1, pos);
                while (buf.getLong() != 0) {
                    pos += incA;
                    this.canal.read(bufA1, pos * TamanhoAluno.TOTAL);
                    buf.flip();
                    if (buf.getLong()==a1.getMatricula()){
                       break;
                    }
                    custo1++;
                }
                if (custo1 <= custo2) {
                    this.canal.write(bufA, (pos + (incA*custo1)) * TamanhoAluno.TOTAL);
                } else {
                    this.canal.write(bufA1, (pos + (incA1*custo2)) * TamanhoAluno.TOTAL);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(OrganizadorSequencial.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Aluno getAluno(long matricA) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Aluno delAluno(long matricA) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void percorrer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
