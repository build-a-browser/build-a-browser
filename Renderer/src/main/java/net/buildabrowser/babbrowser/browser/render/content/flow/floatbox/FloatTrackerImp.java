package net.buildabrowser.babbrowser.browser.render.content.flow.floatbox;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import net.buildabrowser.babbrowser.browser.render.content.flow.fragment.ManagedBoxFragment;
import net.buildabrowser.babbrowser.browser.render.layout.LayoutConstraint;

public class FloatTrackerImp implements FloatTracker {

  private static final Comparator<ManagedBoxFragment> fragmentComparator = (r1, r2) -> {
		int result = Float.compare(r1.posY(), r2.posY());

		return result;
	};

  private final Set<ManagedBoxFragment> leftFloats = new TreeSet<ManagedBoxFragment>(fragmentComparator);
	private final Set<ManagedBoxFragment> rightFloats = new TreeSet<ManagedBoxFragment>(fragmentComparator);

  @Override
  public void addLineStartFloat(ManagedBoxFragment box) {
    leftFloats.add(box);
  }

  @Override
  public void addLineEndFloat(ManagedBoxFragment box) {
    rightFloats.add(box);
  }

  @Override
  public float clearedLineStartPosition(float blockStart) {
    return getFreePosition(blockStart, leftFloats);
  }

  @Override
  public float clearedLineEndPosition(float blockStart) {
    return getFreePosition(blockStart, rightFloats);
  }

  @Override
  public float lineStartReservedWidth(float blockStart) {
    float highestOffset = 0;
		for (ManagedBoxFragment box : leftFloats) {
			if (blockStart >= box.posY() && blockStart < box.posY() + box.height()) {
				highestOffset = Math.max(highestOffset, box.posX() + box.width());
			}
		}

		return highestOffset;
  }

  @Override
  public float lineEndReservedWidth(float blockStart) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'lineEndOffset'");
  }

  @Override
  public void reset() {
    leftFloats.clear();
    rightFloats.clear();
  }

  @Override
  public void enterContext(float lineStart, LayoutConstraint constraint) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'enterContext'");
  }

  @Override
  public void exitContext() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'exitContext'");
  }

  private float getFreePosition(float blockStart, Set<ManagedBoxFragment> floats) {
		float nextUncheckedY = -1;
		for (ManagedBoxFragment box : floats) {
      if (nextUncheckedY >= blockStart && box.posY() > nextUncheckedY) {
				return nextUncheckedY;
      } else {
				nextUncheckedY = Math.max(nextUncheckedY, box.posY() + box.height());
			}
		}

		return Math.max(nextUncheckedY, blockStart);
	}

}
