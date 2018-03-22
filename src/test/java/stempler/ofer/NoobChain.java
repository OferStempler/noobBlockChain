package stempler.ofer;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import stempler.ofer.model.Block;
import stempler.ofer.model.Transaction;
import stempler.ofer.model.Wallet;
import stempler.ofer.utils.Utils;

import java.security.Security;
import java.util.ArrayList;

/**
 * Created by ofer on 14/03/18.
 */
public class NoobChain {

    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    public static int difficulty = 0;
    public static Wallet walletA;
    public static Wallet walletB;

    public static Boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

        //loop through blockchain to check hashes:
        for(int i=1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i-1);
            //compare registered hash and calculated hash:
            if(!currentBlock.getHash().equals(currentBlock.calculateHash()) ){
                System.out.println("Current Hashes not equal");
                return false;
            }
            //compare previous hash and registered previous hash
            if(!previousBlock.getHash().equals(currentBlock.getPreviousHash()) ) {
                System.out.println("Previous Hashes not equal");
                return false;
            }
            //check if hash is solved
            if(!currentBlock.getHash().substring( 0, difficulty).equals(hashTarget)) {
                System.out.println("This block hasn't been mined");
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {

        Security.addProvider(new BouncyCastleProvider());

        walletA = new Wallet();
        walletB = new Wallet();
        //Test public and private keys
        System.out.println("Private and public keys:");
        System.out.println(Utils.getStringFromKey(walletA.privateKey));
        System.out.println(Utils.getStringFromKey(walletA.publicKey));
        //Create a test transaction from WalletA to walletB 
        Transaction transaction = new Transaction(walletA.publicKey, walletB.publicKey, 5, null);
        transaction.generateSignature(walletA.privateKey);
        //Verify the signature works and verify it from the public key
        System.out.println("Is signature verified");
        System.out.println(transaction.verifiySignature());
//----------------------------------------------------------------------------------Test B
//        blockchain.add(new Block("Hi im the first block", "0"));
//        System.out.println("Trying to Mine block 1... ");
//        blockchain.get(0).mineBlock(difficulty);
//
//        blockchain.add(new Block("Yo im the second block",blockchain.get(blockchain.size()-1).getHash()));
//        System.out.println("Trying to Mine block 2... ");
//        blockchain.get(1).mineBlock(difficulty);
//
//        blockchain.add(new Block("Hey im the third block",blockchain.get(blockchain.size()-1).getHash()));
//        System.out.println("Trying to Mine block 3... ");
//        blockchain.get(2).mineBlock(difficulty);
//
//        System.out.println("\nBlockchain is Valid: " + isChainValid());
//
//        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
//        System.out.println("\nThe block chain: ");
//        System.out.println(blockchainJson);
//-----------------------------------------------------------------------------------Test A
//        Block genesisBlock = new Block("Hi im the first block", "0");
//        System.out.println("Hash for block 1 : " + genesisBlock.getHash());
//
//        Block secondBlock = new Block("Yo im the second block",genesisBlock.getHash());
//        System.out.println("Hash for block 2 : " + secondBlock.getHash());
//
//        Block thirdBlock = new Block("Hey im the third block",secondBlock.getHash());
//        System.out.println("Hash for block 3 : " + thirdBlock.getHash());

    }
}
