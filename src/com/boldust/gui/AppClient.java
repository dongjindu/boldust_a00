package com.boldust.gui;

import com.boldust.general.BoldustProperties;
import com.boldust.general.LocalDAO;
import com.boldust.general.LocalDAOException;
import com.boldust.general.Res;
import static com.boldust.general.Res.localdao;
import static com.boldust.general.Res.logger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import javax.swing.SwingUtilities;

public class AppClient {

    private Transaction transaction;
    private Session session;

    public static void main() throws Exception {
        Res.setLogLevel();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        }
        );
    }

    private static void createAndShowGUI() {
    }

}
