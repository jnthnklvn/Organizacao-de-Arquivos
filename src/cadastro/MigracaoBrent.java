/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastro;

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
            RandomAccessFile fileOrigem = new RandomAccessFile(fOrigem, "rw");
            FileChannel channelOrigem = fileOrigem.getChannel();
            IFileOrganizer org = new OrganizadorBrent("enem_brent.db");// referencia o arquivo organizado pelo método implementado
            // Ler cada aluno do arquivo de origem e inserir no de destino
            
            for (int i = 0; i < 7603290; i++) {
                // Ler da origem
                ByteBuffer buf = ByteBuffer.allocate(TamanhoAluno.TOTAL);
                channelOrigem.read(buf, i*TamanhoAluno.TOTAL);
                buf.flip();
                Aluno a = ConversorAluno.toAluno(buf);
                // Inserir no destino
                org.addAluno(a);
            }
            channelOrigem.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MigracaoBrent.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MigracaoBrent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
