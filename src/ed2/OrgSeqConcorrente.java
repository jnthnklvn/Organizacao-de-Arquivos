/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ed2;

import java.io.File;
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
public class OrgSeqConcorrente implements Runnable {

    static long tamA = TamanhoAluno.MATRICULA, matric;

    @Override
    public void run() {
        // referencia o arquivo com os 1000 elementos aleatorios
        File fOrigem = new File("selected.db");
        try {
            RandomAccessFile fileO = new RandomAccessFile(fOrigem, "rw");
            FileChannel canalO = fileO.getChannel(); //Canal origem

            // referencia o arquivo organizado pelo método implementado
            OrganizadorSequencial org = new OrganizadorSequencial("enem_seq.db");
            ByteBuffer buf = ByteBuffer.allocate(TamanhoAluno.MATRICULA);
            for (int i = 0; i < 1000; i++) {
                canalO.read(buf, i * tamA); // Ler da origem
                buf.flip(); //volta ao inicio do buffer
                matric = buf.getLong();
                buf.flip();
                org.getAluno(matric);
                System.out.println(i + ", Sequencial");
            }
        } catch (IOException ex) {
            Logger.getLogger(TesteBrent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
