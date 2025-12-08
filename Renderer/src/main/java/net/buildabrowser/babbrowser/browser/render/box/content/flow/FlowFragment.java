package net.buildabrowser.babbrowser.browser.render.box.content.flow;

import java.util.List;

import net.buildabrowser.babbrowser.browser.render.box.ElementBox;

public interface FlowFragment {
  
  public record TextFragment(int x, int y, String text) implements FlowFragment {

  }

  public record ManagedBoxFragment(
    int x, int y, int width, int height,
    ElementBox box, List<FlowFragment> fragments
  ) implements FlowFragment {

  }

  public record UnmanagedBoxFragment(
    int x, int y, ElementBox box
  ) implements FlowFragment {

  }

}
