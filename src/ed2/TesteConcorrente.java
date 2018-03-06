package ed2;

/**
 *
 * @author zkelvinfps
 */
public class TesteConcorrente {
    public static void main (String[] args) {
        OrgSeqConcorrente seq = new OrgSeqConcorrente();
        Thread threadDoPdf = new Thread(seq);
        threadDoPdf.start();

        OrgBrentConcorrente brent = new OrgBrentConcorrente();
        Thread threadDaBarra = new Thread(brent);
        threadDaBarra.start();
    }
}
