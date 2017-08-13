package rexcord;

import java.util.ArrayList;
import java.util.List;

/**
 * A list of RexUsers that share the same permissions
 */
public class RexGroup {

    /**
     * Group ID
     */
    private String id;

    /**
     * List of RexUsers
     */
    private List<RexUser> rexUsers = new ArrayList<>();

    /**
     * Default constructor
     * @param id group ID
     * @param rexUsers list of RexUsers
     */
    public RexGroup(String id, List<RexUser> rexUsers) {
        this.id = id;
        this.rexUsers = rexUsers;
    }
}
