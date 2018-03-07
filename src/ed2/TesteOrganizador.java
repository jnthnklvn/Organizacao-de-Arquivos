/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ed2;

/**
 * Classe para testar metodos dos Organizadores
 * @author j_kel
 */
public class TesteOrganizador {

    //static OrganizadorBrent org = new OrganizadorBrent("C:\\Users\\j_kel\\Desktop\\testeBrent.db");
    static OrganizadorSequencial org = new OrganizadorSequencial("aluno.db");
    
    public static void addAlunos() {
        Aluno a = new Aluno(29, "Bruna", "Salgado", "(79)99862-0665", (short) 171, "brunna.com");
        Aluno b = new Aluno(13, "Geovanne", "São Cristóvão", "(79)99952-7255", (short) 171, "geovanne.com");
        Aluno c = new Aluno(3, "Igor", "São Cristóvão", "(79)99600-3937", (short) 171, "igor.com");
        Aluno d = new Aluno(23, "Kelvin", "Aracaju", "(79)99993-3447", (short) 171, "kelvin.com");
        Aluno e = new Aluno(17, "Luiz", "Boquim", "(79)99907-5426", (short) 170, "luiz.com");
        Aluno f = new Aluno(11, "Mayara", "Aracaju", "(79)99903-5653", (short) 171, "mayara.com");
        Aluno g = new Aluno(5, "Pedro", "Aracaju", "(79)99975-0982", (short) 171, "pedro.com");
        Aluno h = new Aluno(31, "Raul", "São Cristóvão", "(79)99999-9999", (short) 171, "raul.com");
        Aluno i = new Aluno(2, "Roberto", "Aracaju", "(79)99125-9236", (short) 171, "roberto.com");
        Aluno j = new Aluno(19, "Tulio", "Aracaju", "(79)99105-2774", (short) 171, "tulio.com");
        Aluno k = new Aluno(7, "Wilkinson", "Lagarto", "(79)99910-6088", (short) 171, "wilkinson.com");

        org.addAluno(a);
        org.addAluno(b);
        org.addAluno(c);
        org.addAluno(d);
        org.addAluno(e);
        org.addAluno(f);
        org.addAluno(g);
        org.addAluno(h);
        org.addAluno(i);
        org.addAluno(j);
        org.addAluno(k);
    }

    public static void main(String[] args) throws Exception {
        org.esvaziar();
        addAlunos();
    }
}
