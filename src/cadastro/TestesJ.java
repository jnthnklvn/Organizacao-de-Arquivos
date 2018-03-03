/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastro;

/**
 *
 * @author j_kel
 */
public class TestesJ {

    public static void main(String[] args) {
        int i = 5;
        i =(int) ((i*1.5) + (i%0.25));
        System.out.println(i);
    }
}
/**
 * long size = this.canal.size()/TamanhoAluno.TOTAL/2;
            long pos = size;
            ByteBuffer bufX = ByteBuffer.allocate(TamanhoAluno.MATRICULA);
            ByteBuffer buf = ByteBuffer.allocate(TamanhoAluno.TOTAL);
            this.canal.read(bufX, pos*TamanhoAluno.TOTAL);
            bufX.flip();
            matricA1 = bufX.getLong();
            bufX.flip();
            while (matricA!=matricA1){
                if (pos==0){
                    return null;
                }
                else if (matricA1 > matricA){
                    pos/=2;
                }else{
                    pos=pos+(long)(pos+1)/2;
                }
                this.canal.read(bufX, pos*TamanhoAluno.TOTAL);
                bufX.flip();
                matricA1 = bufX.getLong();
                bufX.flip();
                System.out.println(pos+" "+matricA1);
            }this.canal.read(buf, pos*TamanhoAluno.TOTAL);
            buf.flip();
            return ConversorAluno.toAluno(buf);/**
            System.out.println(pos*TamanhoAluno.TOTAL+" 0");
            float inc = 0.5f;
            while (matricA1 > matricA) {
                size *= inc; pos=size;
                System.out.println(pos*TamanhoAluno.TOTAL+" 1");
                this.canal.read(bufX, pos*TamanhoAluno.TOTAL);
                bufX.flip();
                matricA1 = bufX.getLong();
                bufX.flip();

                while (matricA1 < matricA) {
                    pos += size;
                    System.out.println(size+" "+pos*TamanhoAluno.TOTAL+" 2");
                    this.canal.read(bufX, pos*TamanhoAluno.TOTAL);
                    bufX.flip();
                    matricA1 = bufX.getLong();
                    bufX.flip();
                }
            }
            while (matricA1 < matricA) {
                size *=  inc; size+=1;
                pos += size;
                System.out.println(matricA1+" "+pos*TamanhoAluno.TOTAL+" -1");
                this.canal.read(bufX, pos*TamanhoAluno.TOTAL);
                bufX.flip();
                matricA1 = bufX.getLong();
                bufX.flip();

                while (matricA1 > matricA) {
                    pos -= size/2;
                    this.canal.read(bufX, pos*TamanhoAluno.TOTAL);
                    bufX.flip();
                    matricA1 = bufX.getLong();
                    System.out.println(size+" "+matricA1+" "+pos*TamanhoAluno.TOTAL+" -2");
                    bufX.flip();
                }
            }
            if (matricA1 == matricA) {
                this.canal.read(buf, pos*TamanhoAluno.TOTAL);
                buf.flip();
                return ConversorAluno.toAluno(buf);
            }**/
