package net.buildabrowser.babbrowser.browser.render.box.content.flow.fragment;

import net.buildabrowser.babbrowser.browser.render.box.ElementBox;

public class UnmanagedBoxFragment extends FlowFragment {

  private final ElementBox box;

  public UnmanagedBoxFragment(ElementBox box) {
    this.box = box;
  }

  public ElementBox box() {
    return this.box;
  }

}
