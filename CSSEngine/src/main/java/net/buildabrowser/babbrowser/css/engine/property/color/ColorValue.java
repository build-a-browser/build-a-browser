package net.buildabrowser.babbrowser.css.engine.property.color;

import net.buildabrowser.babbrowser.css.engine.property.CSSValue;

public interface ColorValue extends CSSValue {
  
  int asSARGB();
  
  static record SRGBAColor(int r, int g, int b, int a) implements ColorValue {

    @Override
    public int asSARGB() {
      return (a << 24) | (r << 16) | (g << 8) | b;
    }

    public static SRGBAColor create(int r, int g, int b, int a) {
      return new SRGBAColor(r, g, b, a);
    }

  }

}
