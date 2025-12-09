package net.buildabrowser.babbrowser.browser.render.box.content.flow.fragment;

public class TextFragment extends FlowFragment {

  private final String text;

  public TextFragment(String text) {
    this.text = text;
  }

  public String text() {
    return this.text;
  };

}