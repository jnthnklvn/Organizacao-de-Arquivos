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
public class TesteBrent {

    static long tamA = TamanhoAluno.MATRICULA, matric;

    public static void main(String[] args) {
        // referencia o arquivo com os 1000 elementos aleatorios
        File fOrigem = new File("selected.db");
        try {
            RandomAccessFile fileO = new RandomAccessFile(fOrigem, "rw");
            FileChannel canalO = fileO.getChannel(); //Canal origem

            // referencia o arquivo organizado pelo método implementado
            OrganizadorBrent org = new OrganizadorBrent("enem_brent.db");
            ByteBuffer buf = ByteBuffer.allocate(TamanhoAluno.MATRICULA);
            long tempoInicio = System.currentTimeMillis(); //contador
            for (int i = 0; i < 1000; i++) {
                canalO.read(buf, i * tamA); // Ler da origem
                buf.flip(); //volta ao inicio do buffer
                matric = buf.getLong();
                buf.flip();
                org.getAluno(matric);
            }
            System.out.println("Tempo total: " + (System.currentTimeMillis() - tempoInicio));
        } catch (IOException ex) {
            Logger.getLogger(TesteBrent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
