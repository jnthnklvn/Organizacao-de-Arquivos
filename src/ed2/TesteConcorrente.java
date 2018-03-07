package ed2;

/**
 *
 * @author zkelvinfps
 */
public class TesteConcorrente {
    static long tempoInicio = System.currentTimeMillis(); //contador
    
    public static void main (String[] args) {
        OrgSeqConcorrente seq = new OrgSeqConcorrente();
        Thread seqT = new Thread(seq);
        seqT.start();

        OrgBrentConcorrente brent = new OrgBrentConcorrente();
        Thread brentT = new Thread(brent);
        brentT.start();
        System.out.println("Tempo total: " + (System.currentTimeMillis() - tempoInicio));
    }
}
