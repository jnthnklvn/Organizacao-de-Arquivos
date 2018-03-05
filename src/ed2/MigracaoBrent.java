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
public class MigracaoBrent {

    public static void main(String[] args) {
        File fOrigem = new File("enem_aleat.db");
        try {
            RandomAccessFile fileO = new RandomAccessFile(fOrigem, "rw");
            try (FileChannel canalO = fileO.getChannel()) {
                // referencia o arquivo organizado pelo método implementado
                IFileOrganizer org = new OrganizadorBrent("enem_brent.db");
                // Ler cada aluno do arquivo de origem e inserir no de destino
                long tempoInicio = System.currentTimeMillis();
                int tamA = TamanhoAluno.TOTAL;
                for (long i = 0; i < 7603290; i++) {
                    // Ler da origem
                    ByteBuffer buf = ByteBuffer.allocate(tamA);
                    canalO.read(buf, i*tamA);
                    buf.flip();
                    Aluno a = ConversorAluno.toAluno(buf);
                    // Inserir no destino
                    org.addAluno(a);
                }
                System.out.println("Tempo Total: " + (System.currentTimeMillis() - tempoInicio)/60000);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MigracaoBrent.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MigracaoBrent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
