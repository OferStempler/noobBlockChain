//package stempler.ofer.service.impl;
//
//import lombok.extern.log4j.Log4j;
//import org.springframework.stereotype.Service;
//import stempler.ofer.model.Block;
//import stempler.ofer.service.BlockService;
//
//import java.nio.charset.StandardCharsets;
//import java.security.MessageDigest;
//import java.util.Date;
//
///**
// * Created by ofer on 27/02/18.
// */
//@Service
//@Log4j
//public class BlockServiceImpl implements BlockService {
//    @Override
//    public String calculateHash(int index, String previousHash, String data, String hash) {
//            String allData = index + previousHash + data + hash;
//            byte[] hashBytes;
//            try {
//                MessageDigest digest = MessageDigest.getInstance("SHA-256");
//                hashBytes = digest.digest(allData.getBytes(StandardCharsets.UTF_8));
//            } catch(Exception e) {
//            log.error("Could not generateHash");
//            return null;
//            }
//            return hashBytes.toString();
//        }
//
//
//    @Override
//    public Block generateNextBlock(Block previousBlock, String blockData) {
////        String previousBlock= getLateshBlock();
//        int nextIndex = previousBlock.getIndex() + 1;
//        String nextTimestamp = new Date().toString();
//        String nextHash = calculateHash(nextIndex, previousBlock.getHash(), nextTimestamp, blockData);
//        return new Block(nextIndex, previousBlock.getHash(), nextTimestamp, blockData, nextHash);
//    }
//}
