package utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import rexcord.RexCord;
import rexcord.RexGroup;
import rexcord.RexUser;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads permissions.xml
 */
public class PermissionReader {

    /**
     * Group xml value
     */
    private static final String GROUP_XML = "group";

    /**
     * Permissions file path
     */
    private static final String FILE_PATH =
            System.getProperty("user.dir")
                    + "/config/permissions.xml";

    /**
     * Parser error
     */
    private static final String PARSER_ERROR =
            "RexCord: Error parsing XML File";

    /**
     * Permission file not found error
     */
    private static final String PERMISSION_NOT_FOUND_ERROR
            = "RexCord: Permissions file not found. "
            + "Make sure it is created and located in "
            + "the correct directory.";

    /**
     * Reads permission file
     * @throws FileNotFoundException In case file doesn't exist
     */
    public final void readFile() throws FileNotFoundException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;
        Document doc = null;

        try {
            db = dbf.newDocumentBuilder();
            doc = db.parse(new File(FILE_PATH));
        } catch (FileNotFoundException e1) {
            throw new FileNotFoundException(PERMISSION_NOT_FOUND_ERROR);
        } catch (ParserConfigurationException
                | IOException
                | SAXException e2) {
            System.out.println(PARSER_ERROR);
            e2.printStackTrace();
            return;
        }

        doc.getDocumentElement().normalize();

        NodeList groups = doc.getElementsByTagName(GROUP_XML);

        List<RexGroup> createdGroups = readGroups(groups);

        // After reading xml file, adds all groups to RexCord
        RexCord.setRexGroups(createdGroups);
    }

    /**
     * Fetches groups and adds them to a list
     * @param groups NodeList of groups contained in the XML permission file
     * @return a list of all RexGroups
     */
    private List<RexGroup> readGroups(NodeList groups) {
        List<RexGroup> createdGroups = new ArrayList<>();

        for (int i = 0; i < groups.getLength(); i++) {
            if (groups.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) groups.item(i);

                // Gets group id
                String groupID = element.getAttribute("id");

                // Fetches users and adds them to list
                NodeList users = element.getElementsByTagName("userID");
                List<RexUser> groupUsers = readUsers(users);

                // Creates new RexGroup
                RexGroup createdGroup =
                        new RexGroup(groupID, groupUsers);
                createdGroups.add(createdGroup);
            }
        }

        return createdGroups;
    }

    /**
     * Fetches users and adds them to a list
     * @param users NodeList users contained in a given group's xml node
     * @return a list of RexUsers that share the same RexGroup
     */
    private List<RexUser> readUsers(NodeList users) {
        List<RexUser> groupUsers = new ArrayList<>();
        for (int i = 0; i < users.getLength(); i++) {
            RexUser createdUser =
                    new RexUser(users.item(i).getTextContent());
            groupUsers.add(createdUser);
        }

        return groupUsers;
    }
}
