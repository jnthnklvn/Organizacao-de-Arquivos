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
public class TesteBrent {

    public static void main(String[] args) {
        OrganizadorSequencial org = new OrganizadorSequencial("C:\\Users\\j_kel\\Desktop\\testeBrent.db");
        Aluno a = new Aluno(0, "", "", "", (short) 0, "");
        Aluno b = new Aluno(0, "", "", "", (short) 0, "");
        Aluno c = new Aluno(0, "", "", "", (short) 0, "");
        Aluno d = new Aluno(0, "", "", "", (short) 0, "");
        Aluno e = new Aluno(0, "", "", "", (short) 0, "");
        Aluno f = new Aluno(0, "", "", "", (short) 0, "");
        Aluno g = new Aluno(0, "", "", "", (short) 0, "");
        org.esvaziar();
        org.addAluno(a);
        org.addAluno(b);
        org.addAluno(c);
        org.addAluno(d);
        org.addAluno(e);
        org.addAluno(f);
        org.addAluno(g);
        org.percorrer();
    }
}
