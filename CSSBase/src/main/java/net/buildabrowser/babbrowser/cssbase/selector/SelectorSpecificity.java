package net.buildabrowser.babbrowser.cssbase.selector;

public record SelectorSpecificity(int numIdSelectors, int numClassSelectors, int numTypeSelectors) {
  
  public static int compare(SelectorSpecificity a, SelectorSpecificity b) {
    return
      a.numIdSelectors > b.numIdSelectors ? 1 :
      a.numIdSelectors < b.numIdSelectors ? -1 :
      a.numClassSelectors > b.numClassSelectors ? 1 :
      a.numClassSelectors < b.numClassSelectors ? -1 :
      a.numTypeSelectors > b.numTypeSelectors ? 1 :
      a.numTypeSelectors < b.numTypeSelectors ? -1 :
      0;
  }

}
