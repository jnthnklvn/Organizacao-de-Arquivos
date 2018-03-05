/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * @author j_kel
 */
public class OrganizadorBrent implements IFileOrganizer {

    FileChannel canal;
    final long LENT = 10000019;
    long posAux;
    
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
        int custo = 0; posAux=pos;
        ByteBuffer buf = ByteBuffer.allocate(TamanhoAluno.MATRICULA);
        try {
            long size = (this.canal.size() / TamanhoAluno.TOTAL);
            this.canal.read(buf, pos * TamanhoAluno.TOTAL);
            buf.flip();
            long matricula = buf.getLong();
            buf.flip();
            while (matricula > 0) {
                pos += inc;
                if (pos >= size) {
                    pos -= size;
                }
                this.canal.read(buf, pos * TamanhoAluno.TOTAL);
                buf.flip();
                matricula = buf.getLong();
                buf.flip();
                custo++;
                if (pos==posAux){
                    return custo;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(OrganizadorBrent.class.getName()).log(Level.SEVERE, null, ex);
        }posAux=pos;
        return custo;
    }

    @Override
    public void addAluno(Aluno a) {
        try {
            long pos = (a.getMatricula() % LENT);
            long posO = pos;
            ByteBuffer bufA = ConversorAluno.toByteBuffer(a);
            ByteBuffer bufA1 = ByteBuffer.allocate(TamanhoAluno.TOTAL);
            ByteBuffer buf = ByteBuffer.allocate(TamanhoAluno.MATRICULA);
            this.canal.read(buf, pos * TamanhoAluno.TOTAL);
            buf.flip();
            long matricula = buf.getLong();
            buf.flip();
            if (matricula <= 0) {
                this.canal.write(bufA, pos * TamanhoAluno.TOTAL);
            } else {
                this.canal.read(bufA1, posO * TamanhoAluno.TOTAL);
                bufA1.flip();
                long incA = ((a.getMatricula()) % (LENT - 2)) + 1;
                long incA1 = (matricula % (LENT - 2)) + 1;
                int custo1 = 0, custo2 = custoA1(matricula, incA1, pos)-1;
                long size = (this.canal.size() / TamanhoAluno.TOTAL);
                long matricAux = buf.getLong();
                buf.flip();
                while (matricAux > 0) {
                    pos += incA;
                    if (pos >= size) {
                        pos -= size;
                    }
                    this.canal.read(buf, pos * TamanhoAluno.TOTAL);
                    buf.flip();
                    matricAux = buf.getLong();
                    buf.flip();
                    if (matricAux == matricula) {
                        break;
                    }
                    custo1++;
                }
                if (custo1 <= custo2) {
                    this.canal.write(bufA, pos * TamanhoAluno.TOTAL);
                } else {
                    this.canal.write(bufA1, posAux * TamanhoAluno.TOTAL);
                    this.canal.write(bufA, posO * TamanhoAluno.TOTAL);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(OrganizadorSequencial.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Aluno getAluno(long matricA) {
        long pos = matricA % LENT, posA = pos;
        long inc = (matricA % (LENT - 2)) + 1;
        ByteBuffer buf = ByteBuffer.allocate(TamanhoAluno.MATRICULA);
        try {
            this.canal.read(buf, pos * TamanhoAluno.TOTAL);
            buf.flip();
            long matric = buf.getLong();
            buf.flip();
            long size = this.canal.size() / TamanhoAluno.TOTAL;
            while (matric != 0 && matric!=matricA) {
                pos += inc;
                if (pos >= size) {
                    pos -= size;
                }
                this.canal.read(buf, pos * TamanhoAluno.TOTAL);
                buf.flip();
                matric = buf.getLong();
                buf.flip();
                if(posA == pos){break;}
            }
            if (matric == matricA) {
                buf = ByteBuffer.allocate(TamanhoAluno.TOTAL);
                this.canal.read(buf, pos * TamanhoAluno.TOTAL);
                buf.flip();
                return ConversorAluno.toAluno(buf);
            }
        } catch (IOException ex) {
            Logger.getLogger(OrganizadorBrent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Aluno delAluno(long matricA) {
        long pos = matricA % LENT, posA = pos;
        long inc = (matricA % (LENT - 2)) + 1;
        ByteBuffer buf = ByteBuffer.allocate(TamanhoAluno.MATRICULA);
        try {
            this.canal.read(buf, pos * TamanhoAluno.TOTAL);
            buf.flip();
            long matric = buf.getLong();
            buf.flip();
            long size = this.canal.size() / TamanhoAluno.TOTAL;
            while (matric != 0 && matric!=matricA) {
                pos += inc;
                if (pos >= size) {
                    pos -= size;
                }
                this.canal.read(buf, pos * TamanhoAluno.TOTAL);
                buf.flip();
                matric = buf.getLong();
                buf.flip();
                if(posA == pos){break;}
            }
            if (matric == matricA) {
                buf = ByteBuffer.allocate(TamanhoAluno.TOTAL);
                this.canal.read(buf, pos * TamanhoAluno.TOTAL);
                buf.flip();
                Aluno a = ConversorAluno.toAluno(buf);
                a.setMatricula(-1);
                buf = ConversorAluno.toByteBuffer(a);
                this.canal.write(buf, pos * TamanhoAluno.TOTAL);
                return a;
            }
        } catch (IOException ex) {
            Logger.getLogger(OrganizadorBrent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void percorrer() {
        try {
            long size = this.canal.size();
            for (long pos = 0; pos < size; pos += TamanhoAluno.TOTAL) {
                ByteBuffer buf = ByteBuffer.allocate(TamanhoAluno.TOTAL);
                this.canal.read(buf, pos);
                buf.flip();
                Aluno a = ConversorAluno.toAluno(buf);
                a.imprimiAluno();
            }
        } catch (IOException ex) {
            Logger.getLogger(OrganizadorSimples.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
