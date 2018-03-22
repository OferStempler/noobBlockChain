package stempler.ofer.model;

import lombok.Data;
import stempler.ofer.utils.Utils;

import java.util.Date;

/**
 * Created by ofer on 27/02/18.
 */
@Data
public class Block {

    private String previousHash;
    private long timestamp;
    private String data;
    private String hash;
    private int nonce;


    //Calculate new hash based on blocks contents
    public String calculateHash(){
        String calculatedHash = Utils.applySha256(previousHash+ Long.toString(timestamp) + data);
        return calculatedHash;
    }

    public Block(String data, String previousHash){
        this.data = data;
        this.previousHash =previousHash;
        this.timestamp = new Date().getTime();
        this.hash = calculateHash();
    }

    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0'); //Create a string with difficulty * "0"
        while(!hash.substring( 0, difficulty).equals(target)) {
            nonce ++;
            hash = calculateHash();
        }
        System.out.println("Block Mined!!! : " + hash);
    }

    //    public Block(String previousHash, long timestamp, String data, String hash) {
//        this.previousHash = previousHash;
//        this.timestamp = timestamp;
//        this.data = data;
//        this.hash = hash;
//    }
}
