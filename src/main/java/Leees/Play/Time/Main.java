package Leees.Play.Time;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main
extends JavaPlugin
implements Listener {
    public static ArrayList<String> verified = new ArrayList();
    public static ArrayList<String> blacklisted = new ArrayList();
    public static File completedFile;
    public static File blaclistFile;
    public static File indexhtml;

    public static Main getPlugin() {
        return (Main)Main.getPlugin(Main.class);
    }

    public void onEnable() {
        this.saveDefaultConfig();
        File dir = new File("plugins/LeeesPlayTime");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        if (!(indexhtml = new File(dir, "index.html")).exists()) {
            try {
                indexhtml.createNewFile();
            }
            catch (IOException e) {
                e.printStackTrace();
                this.getServer().getPluginManager().disablePlugin((Plugin)this);
                return;
            }
            if (indexhtml.exists()) {
                try {
                    String fileContent =
                    "<html>\n" +
                    "  <head>\n" +
                    "    <title>LeeesPlayTime</title>\n" +
                    "    <link rel=\"stylesheet\" type=\"text/css\" href=\"https://www.6b6t.org/css/global.css\">\n" +
                    "    <link rel=\"stylesheet\" type=\"text/css\" href=\"https://www.6b6t.org/css/animate.css\">\n" +
                    "    <script src=\"https://cdn.jsdelivr.net/gh/leonardosnt/mc-player-counter/dist/mc-player-counter.min.js\"></script>\n" +
                    "  </head>\n" +
                    "  <body>\n" +
                    "    <header class=\"header\">\n" +
                    "      <div class=\"container\">\n" +
                    "        <ul style=\"overflow: hidden; list-style-type: none; margin: 0; padding: 0;\">\n" +
                    "          <li class=\"logo\"><a href=\"https://www.6b6t.org\"><img src=\"https://www.6b6t.org/img/logo.png\" alt=\"logo\" class=\"logo hvr-grow\" width=\"64px\" height=\"64px\"></a></li>\n" +
                    "          <li class=\"navbutton\"><span class=\"online\">Online: <span data-playercounter-ip=\"6b6t.org\">41</span> / 9000</span></li>\n" +
                    "          <a class=\"nav\" href=\"https://whitelist.6b6t.org\"><li class=\"navbutton\">Whitelist</li></a>\n" +
                    "          <a class=\"nav\" href=\"https://tps.6b6t.org\"><li class=\"navbutton\">TPS</li></a>\n" +
                    "          <a class=\"nav\" href=\"https://www.6b6t.org/upload.php\"><li class=\"navbutton\">Upload</li></a>\n" +
                    "          <a class=\"nav\" href=\"https://www.6b6t.org/gallery.php\"><li class=\"navbutton\">Gallery</li></a>\n" +
                    "          <a class=\"nav\" href=\"https://www.6b6t.org\"><li class=\"navbutton\">Home</li></a>\n" +
                    "        </ul>\n" +
                    "      </div>\n" +
                    "    </header>\n" +
                    "    <div class=\"container clearfix\">\n" +
                    "      <div class=\"content\">\n" +
                    "        <article class=\"article animated slideInUp\" style=\"text-align: center; display: block; margin-left: auto; margin-right: auto; width: 50%;\">\n" +
                    "          <div align=\"center\">\n" +
                    "            <form action=\"/\">\n" +
                    "              <label for=\"username\"><b><h1>LeeesPlayTime</h1></b></label>\n" +
                    "              <label for=\"username\"><b><h2>Ver 2.0.8</h2></b></label>\n" +
                    "              <label for=\"username\"><b><h3>Please enter your username with proper caps and lower case</h3></b></label>\n" +
                    "              <label for=\"username\"><b><h3>Username</h3></b></label>\n" +
                    "              <input type=\"text\" placeholder=\"Enter Username\" name=\"username\" required>\n" +
                    "              <div id=\"html_element\"></div>\n" +
                    "              <input type=\"submit\" value=\"Submit\">\n" +
                    "            </form>\n" +
                    "            playtime_result\n" +
                    "          </div>\n" +
                    "        </article>\n" +
                    "      </div>\n" +
                    "    </div>\n" +
                    "  </body>\n" +
                    "</html>";

                    BufferedWriter writer = new BufferedWriter(new FileWriter("plugins/LeeesPlayTime/index.html"));
                    writer.write(fileContent);
                    writer.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                    this.getServer().getPluginManager().disablePlugin((Plugin)this);
                    return;
                }
            }
        }
        new Thread(() -> {
            try {
                HttpHandler.main(null);
            }
            catch (IOException e) {
                e.printStackTrace();
                this.getServer().getPluginManager().disablePlugin((Plugin)this);
            }
        }).start();
    }

    public void onDisable() {
    }
}

