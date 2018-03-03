/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ed2;

/**
 *
 * @author j_kel
 */
public class TesteOrganizador {
    public static void main(String[] args) throws Exception {
        OrganizadorBrent org = new OrganizadorBrent("C:\\Users\\j_kel\\Desktop\\testeBrent.db");
        
        Aluno a = new Aluno(29, "Bruna", "Salgado", "(79)99862-0665", (short) 171, "brunna.com");
        Aluno j = new Aluno(13, "Geovanne", "São Cristóvão", "(79)99952-7255", (short) 171, "geovanne.com");
        Aluno b = new Aluno(3, "Igor", "São Cristóvão", "(79)99600-3937", (short) 171, "igor.com");
        Aluno c = new Aluno(23, "Kelvin", "Aracaju", "(79)99993-3447", (short) 171, "kelvin.com");
        Aluno d = new Aluno(17, "Luiz", "Boquim", "(79)99907-5426", (short) 170, "luiz.com");
        Aluno e = new Aluno(11, "Mayara", "Aracaju", "(79)99903-5653", (short) 171, "mayara.com");
        Aluno f = new Aluno(5, "Pedro", "Aracaju", "(79)99975-0982", (short) 171, "pedro.com");
        Aluno g = new Aluno(31, "Raul", "São Cristóvão", "(79)99999-9999", (short) 171, "raul.com");
        //Aluno h = new Aluno(2, "Roberto", "Aracaju", "(79)99125-9236", (short) 171, "roberto.com");
        //Aluno i = new Aluno(19, "Tulio", "Aracaju", "(79)99105-2774", (short) 171, "tulio.com");
        //Aluno k = new Aluno(7, "Wilkinson", "Lagarto", "(79)99910-6088", (short) 171, "wilkinson.com");
        //Aluno l = new Aluno(37, "Wilkinson2", "Lagarto2", "(79)99910-60882", (short) 171, "wilkinson2.com");
        org.addAluno(a);
        org.addAluno(b);
        System.out.println("------------------ PERCORRER ------------------");
        org.percorrer();
        org.addAluno(c);
        System.out.println("------------------ PERCORRER ------------------");
        org.percorrer();
        org.addAluno(d);
        System.out.println("------------------ PERCORRER ------------------");
        org.percorrer();
        org.addAluno(e);
        System.out.println("------------------ PERCORRER ------------------");
        org.percorrer();
        org.addAluno(f);
        System.out.println("------------------ PERCORRER ------------------");
        org.percorrer();
        org.addAluno(g);
        /**System.out.println("------------------ PERCORRER ------------------");
        org.percorrer();
        org.addAluno(h);
        System.out.println("------------------ PERCORRER ------------------");
        org.percorrer();
        org.addAluno(i);
        System.out.println("------------------ PERCORRER ------------------");
        org.percorrer();
        org.addAluno(j);
        System.out.println("------------------ PERCORRER ------------------");
        org.percorrer();
        org.addAluno(k);
        org.addAluno(l);
        */System.out.println("------------------ PERCORRER ------------------");
        org.percorrer();

        //org.delAluno(23);
        //org.delAluno(7);
        //org.delAluno(2);
        /**
        System.out.println("------------------ PERCORRER ------------------");
        org.percorrer();
        long tempoInicio = System.currentTimeMillis();
        Aluno a1 = org.getAluno(5);
        a1.imprimiAluno();
        a1 = org.getAluno(7);
        a1.imprimiAluno();
        a1 = org.getAluno(11);
        a1.imprimiAluno();
        a1 = org.getAluno(13);
        a1.imprimiAluno();
        a1 = org.getAluno(2);
        a1.imprimiAluno();
        a1 = org.getAluno(17);
        a1.imprimiAluno();
        a1 = org.getAluno(19);
        a1.imprimiAluno();
        a1 = org.getAluno(23);
        a1.imprimiAluno();
        a1 = org.getAluno(29);
        a1.imprimiAluno();
        a1 = org.getAluno(31);
        a1.imprimiAluno();
        a1 = org.getAluno(37);
        a1.imprimiAluno();
        a1.imprimiAluno();
        a1 = org.getAluno(7);
        a1.imprimiAluno();
        a1 = org.getAluno(11);
        a1.imprimiAluno();
        a1 = org.getAluno(13);
        a1.imprimiAluno();
        a1 = org.getAluno(2);
        a1.imprimiAluno();
        a1 = org.getAluno(17);
        a1.imprimiAluno();
        a1 = org.getAluno(19);
        a1.imprimiAluno();
        a1 = org.getAluno(23);
        a1.imprimiAluno();
        a1 = org.getAluno(29);
        a1.imprimiAluno();
        a1 = org.getAluno(31);
        a1.imprimiAluno();
        a1 = org.getAluno(37);
        a1.imprimiAluno();
        a1.imprimiAluno();
        a1 = org.getAluno(7);
        a1.imprimiAluno();
        a1 = org.getAluno(11);
        a1.imprimiAluno();
        a1 = org.getAluno(13);
        a1.imprimiAluno();
        a1 = org.getAluno(2);
        a1.imprimiAluno();
        a1 = org.getAluno(17);
        a1.imprimiAluno();
        a1 = org.getAluno(19);
        a1.imprimiAluno();
        a1 = org.getAluno(23);
        a1.imprimiAluno();
        a1 = org.getAluno(29);
        a1.imprimiAluno();
        a1 = org.getAluno(31);
        a1.imprimiAluno();
        a1 = org.getAluno(37);
        a1.imprimiAluno();
        a1.imprimiAluno();
        a1 = org.getAluno(7);
        a1.imprimiAluno();
        a1 = org.getAluno(11);
        a1.imprimiAluno();
        a1 = org.getAluno(13);
        a1.imprimiAluno();
        a1 = org.getAluno(2);
        a1.imprimiAluno();
        a1 = org.getAluno(17);
        a1.imprimiAluno();
        a1 = org.getAluno(19);
        a1.imprimiAluno();
        a1 = org.getAluno(23);
        a1.imprimiAluno();
        a1 = org.getAluno(29);
        a1.imprimiAluno();
        a1 = org.getAluno(31);
        a1.imprimiAluno();
        a1 = org.getAluno(37);
        a1.imprimiAluno();
        System.out.println("Tempo Total: "+(System.currentTimeMillis()-tempoInicio));**/
    }
}