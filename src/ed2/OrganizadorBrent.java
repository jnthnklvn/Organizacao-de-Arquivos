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
    final long LENT = 7;
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
        int custo = 0;
        ByteBuffer bufA1 = ByteBuffer.allocate(TamanhoAluno.MATRICULA);
        try {
            this.canal.read(bufA1, pos * TamanhoAluno.TOTAL);
            long size = (this.canal.size() / TamanhoAluno.TOTAL);
            bufA1.flip();
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            long matric = bufA1.getLong();
            bufA1.flip();
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println(matric+", "+pos+", "+inc);
            while (matric > 0) {
                pos += inc;
                if (pos >= size) {
                    pos -= size;
                }
                this.canal.read(bufA1, pos * TamanhoAluno.TOTAL);
                bufA1.flip();
                matric = bufA1.getLong();
                bufA1.flip();
                System.out.println(pos+", "+matric);
                custo++;
            }
        } catch (IOException ex) {
            Logger.getLogger(OrganizadorBrent.class.getName()).log(Level.SEVERE, null, ex);
        }posAux=pos;
        return custo;
    }

    @Override
    public void addAluno(Aluno a) {
        try {
            long pos = (a.getMatricula() % LENT);long pos1 = pos;
            ByteBuffer buf = ByteBuffer.allocate(TamanhoAluno.TOTAL);
            ByteBuffer bufA = ConversorAluno.toByteBuffer(a);
            ByteBuffer bufA1 = ByteBuffer.allocate(TamanhoAluno.MATRICULA);
            this.canal.read(bufA1, pos * TamanhoAluno.TOTAL);
            bufA1.flip();
            long matric = bufA1.getLong();
            bufA1.flip();
            if (matric <= 0) {
                this.canal.write(bufA, pos * TamanhoAluno.TOTAL);
            } else {
                this.canal.read(buf, pos1*TamanhoAluno.TOTAL);
                buf.flip();
                long incA = ((a.getMatricula()) % (LENT - 2)) + 1;
                long incA1 = (matric % (LENT - 2)) + 1;
                int custo1 = 0, custo2 = custoA1(matric, incA1, pos)-1;
                long size = (this.canal.size() / TamanhoAluno.TOTAL);
                long matricAux = bufA1.getLong();
                bufA1.flip();
                while (matricAux != 0) {
                    pos += incA;
                    if (pos >= size) {
                        pos -= size;
                    }
                    this.canal.read(bufA1, pos * TamanhoAluno.TOTAL);
                    bufA1.flip();
                    matricAux = bufA1.getLong();
                    bufA1.flip();
                    if (matricAux == matric) {
                        break;
                    }
                    custo1++;
                }
                if (custo1 <= custo2) {
                    System.out.println("heeeerrrreee");
                    this.canal.write(bufA, pos * TamanhoAluno.TOTAL);
                } else {
                    System.out.println("rrrririiiiii");
                    this.canal.write(buf, posAux * TamanhoAluno.TOTAL);
                    this.canal.write(bufA, pos1 * TamanhoAluno.TOTAL);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(OrganizadorSequencial.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Aluno getAluno(long matricA) {
        long pos = matricA % LENT;
        long inc = (matricA % (LENT - 2)) + 1;
        ByteBuffer buf = ByteBuffer.allocate(TamanhoAluno.MATRICULA);
        try {
            this.canal.read(buf, pos * TamanhoAluno.TOTAL);
            buf.flip();
            long matric = buf.getLong();
            long size = (this.canal.size() / TamanhoAluno.TOTAL);
            while (matric != matricA && matric > 0) {
                pos += inc;
                if (pos >= size) {
                    pos -= size;
                }
                this.canal.read(buf, pos * TamanhoAluno.TOTAL);
                buf.flip();
                matric = buf.getLong();
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
        long pos = matricA % LENT;
        long inc = (matricA % (LENT - 2)) + 1;
        ByteBuffer buf = ByteBuffer.allocate(TamanhoAluno.MATRICULA);
        try {
            this.canal.read(buf, pos * TamanhoAluno.TOTAL);
            buf.flip();
            long matric = buf.getLong();
            long size = (this.canal.size() / TamanhoAluno.TOTAL);
            while (matric != matricA && matric > 0) {
                pos += inc;
                if (pos >= size) {
                    pos -= size;
                }
                this.canal.read(buf, pos * TamanhoAluno.TOTAL);
                buf.flip();
                matric = buf.getLong();
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
                System.out.println("-------------- " + pos + " --------------");
                a.imprimiAluno();
            }
        } catch (IOException ex) {
            Logger.getLogger(OrganizadorSimples.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void esvaziar() {
        while (!vazio()) {
            try {
                this.canal.truncate(0);
            } catch (IOException ex) {
                Logger.getLogger(OrganizadorSimples.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean vazio() {
        try {
            if (this.canal.size() < 300) {
                return true;
            }
        } catch (IOException ex) {
            Logger.getLogger(OrganizadorSimples.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
