package utils;

import com.sun.istack.internal.NotNull;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import sx.blah.discord.handle.obj.IRole;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
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
    private static final String GROUP_XML = "groups";

    /**
     * Role Based Permissions XML Value
     */
    private static final String ROLE_BASED_XML =
            "role_based_permissions";

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

    private boolean roleBased;

    /**
     * Reads document
     */
    public void readDocument() {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;
        Document doc = null;

        try {
             db = dbf.newDocumentBuilder();
             db.parse(new File(FILE_PATH));
        } catch (ParserConfigurationException |
                IOException |
                SAXException e) {
            System.out.println(PARSER_ERROR);
            e.printStackTrace();
            return;
        }

        doc.getDocumentElement().normalize();

        roleBased = Boolean.parseBoolean(doc
                .getElementsByTagName(ROLE_BASED_XML)
                .item(0).getNodeValue());

        NodeList groups = doc.getElementsByTagName(GROUP_XML);

        if (roleBased) {

        }
    }
}
