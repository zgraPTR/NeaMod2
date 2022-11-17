package jp.neamod2.handlers;

import java.awt.*;
import java.net.URI;

public class BrowserHandle {

    public static void openUrl(String url) {
        Desktop desktop = Desktop.getDesktop();
        try {
            URI uri = new URI(url);
            desktop.browse(uri);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
