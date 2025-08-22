package net.buildabrowser.babbrowser.browser;

import java.awt.Component;
import java.awt.Dimension;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import net.buildabrowser.babbrowser.browser.net.ProtocolRegistry;
import net.buildabrowser.babbrowser.browser.render.core.box.Box;
import net.buildabrowser.babbrowser.browser.render.core.box.BoxGenerator;
import net.buildabrowser.babbrowser.htmlparser.HTMLParser;
import net.buildabrowser.babbrowser.spec.dom.Document;

public class Main {
  
  public static void main(String[] args) throws IOException, URISyntaxException {
    ProtocolRegistry protocolRegistry = ProtocolRegistry.create();
    URL url = new URI(args[0]).toURL();
    try (InputStream inputStream = protocolRegistry.request(url)) {
      Document document = HTMLParser.create().parse(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
      System.out.println(document);
      showWindow(document);
    }
  }

  private static void showWindow(Document document) {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
      throw new RuntimeException(e);
    }

    BoxGenerator boxGenerator = BoxGenerator.create();
    Box documentBox = boxGenerator.box(document).get(0);
    Component content = documentBox.render();

    JFrame.setDefaultLookAndFeelDecorated(true);
    JFrame frame = new JFrame("BuildABrowser Test Program");
    frame.setSize(new Dimension(800, 500));
    frame.setMaximumSize(new Dimension(800, 500));
    frame.add(content);
    frame.setVisible(true);
  }

}
