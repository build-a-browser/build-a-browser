package net.buildabrowser.babbrowser.browser.render.box.content.flow.fragment;

import java.util.List;

import net.buildabrowser.babbrowser.browser.render.box.ElementBox;

public class ManagedBoxFragment extends FlowFragment {

  private final int width;
  private final int height;
  private final ElementBox box;
  private final List<FlowFragment> fragments;

  public ManagedBoxFragment(
    int width, int height,
    ElementBox box, List<FlowFragment> fragments
  ) {
    this.width = width;
    this.height = height;
    this.box = box;
    this.fragments = fragments;
  }

  public int width() {
    return this.width;
  }

  public int height() {
    return this.height;
  }

  public ElementBox box() {
    return this.box;
  }

  public List<FlowFragment> fragments() {
    return this.fragments;
  }

}
