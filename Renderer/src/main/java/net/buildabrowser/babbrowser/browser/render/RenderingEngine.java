package net.buildabrowser.babbrowser.browser.render;

import java.net.URL;
import java.util.function.Consumer;

import net.buildabrowser.babbrowser.browser.render.uistate.Frame;

public interface RenderingEngine {

  Frame createFrame();

  Renderer createBlankRenderer();

  void openRenderer(URL url, Frame frame, Consumer<Renderer> onOpen);
  
}
