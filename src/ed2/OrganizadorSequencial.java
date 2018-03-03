/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ed2;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author j_kel
 */
public class OrganizadorSequencial implements IFileOrganizer {

    FileChannel canal;

    public OrganizadorSequencial(String fileName) {
        File file = new File(fileName);
        try {
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            this.canal = raf.getChannel();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(OrganizadorSequencial.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void addAluno(Aluno a) {
        try {
            int size = (int) this.canal.size();
            ByteBuffer buf = ConversorAluno.toByteBuffer(a);
            long matricA = a.getMatricula();
            if (size < 300) {
                this.canal.write(buf, 0);
            } else {
                for (int pos = 0; pos < size; pos += TamanhoAluno.TOTAL) {
                    ByteBuffer bufX = ByteBuffer.allocate(TamanhoAluno.MATRICULA);
                    this.canal.read(bufX, pos);
                    bufX.flip();
                    if (matricA < bufX.getLong()) {
                        int tam = size - pos;
                        ByteBuffer buf1 = ByteBuffer.allocate(tam);
                        this.canal.read(buf1, pos);
                        buf1.flip();
                        this.canal.write(buf, pos);
                        this.canal.write(buf1, pos + TamanhoAluno.TOTAL);
                        break;
                    }
                }
                this.canal.write(buf, this.canal.size());
            }
        } catch (IOException ex) {
            Logger.getLogger(OrganizadorSequencial.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Aluno getAluno(long matricA) {
        long l = 0;
        try {
            l = this.canal.size() / TamanhoAluno.TOTAL;
        } catch (IOException ex) {
            Logger.getLogger(OrganizadorSequencial.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getAluno(matricA, 0, l);
    }

    private Aluno getAluno(long matricA, long inicio, long fim) {
        System.out.println("-------------------------------------------------");
        long matricA1;
        long meio = (inicio + fim) / 2;
        if (inicio == fim) {
            return null;
        }
        try {
            ByteBuffer bufX = ByteBuffer.allocate(TamanhoAluno.MATRICULA);
            ByteBuffer buf = ByteBuffer.allocate(TamanhoAluno.TOTAL);
            this.canal.read(bufX, meio * TamanhoAluno.TOTAL);
            bufX.flip();
            matricA1 = bufX.getLong();
            bufX.flip();
            if (matricA == matricA1) {
                this.canal.read(buf, meio * TamanhoAluno.TOTAL);
                buf.flip();
                return ConversorAluno.toAluno(buf);
            } else if (matricA > matricA1) {
                inicio = meio;
            } else {
                fim = meio;
            }
            return getAluno(matricA, inicio, fim);
        } catch (IOException ex) {
            Logger.getLogger(OrganizadorSequencial.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private Aluno getAlunos(long matricA, long inicio, long fim) {
        fim -= 1;
        long meio, matricA1;
        ByteBuffer bufX = ByteBuffer.allocate(TamanhoAluno.MATRICULA);
        ByteBuffer buf = ByteBuffer.allocate(TamanhoAluno.TOTAL);
        while (inicio <= fim) {
            meio = (inicio + fim) / 2;
            try {
                this.canal.read(bufX, meio * TamanhoAluno.TOTAL);
                bufX.flip();
                matricA1 = bufX.getLong();
                bufX.flip();
                if (matricA == matricA1) {
                    this.canal.read(buf, meio * TamanhoAluno.TOTAL);
                    buf.flip();
                    return ConversorAluno.toAluno(buf);
                }else if(matricA < matricA1){
                    fim = meio-1;
                }else{
                    inicio = meio+1;
                }
            } catch (IOException ex) {
                Logger.getLogger(OrganizadorSequencial.class.getName()).log(Level.SEVERE, null, ex);
            }
        }return null;
    }

    @Override
    public Aluno delAluno(long matricA) {
        try {
            int size = (int) this.canal.size();
            for (int pos = 0; pos < size; pos += TamanhoAluno.TOTAL) {
                ByteBuffer bufX = ByteBuffer.allocate(TamanhoAluno.MATRICULA);
                this.canal.read(bufX, pos);
                bufX.flip();
                long matricB = bufX.getLong();
                if (matricA == matricB) {
                    ByteBuffer buf = ByteBuffer.allocate(TamanhoAluno.TOTAL);
                    this.canal.read(buf, pos);
                    buf.flip();
                    int tam = size - pos - TamanhoAluno.TOTAL;
                    ByteBuffer buf1 = ByteBuffer.allocate(tam);
                    this.canal.read(buf1, pos + TamanhoAluno.TOTAL);
                    buf1.flip();
                    this.canal.write(buf1, pos);
                    this.canal.truncate(size - TamanhoAluno.TOTAL);
                    return ConversorAluno.toAluno(buf);
                } else if (matricA < matricB) {
                    return null;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(OrganizadorSequencial.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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
