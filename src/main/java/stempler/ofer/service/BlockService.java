package stempler.ofer.service;

import stempler.ofer.model.Block;

/**
 * Created by ofer on 27/02/18.
 */
public interface BlockService {

    public String calculateHash (int index, String previousHash,  String data, String hash);
    public Block generateNextBlock(Block block, String blockData);
}
