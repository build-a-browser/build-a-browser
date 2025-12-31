package net.buildabrowser.babbrowser.browser.render.content.flow.floatbox;

import net.buildabrowser.babbrowser.browser.render.content.flow.fragment.ManagedBoxFragment;
import net.buildabrowser.babbrowser.browser.render.layout.LayoutConstraint;

public interface FloatTracker {
	
	void addLineStartFloat(ManagedBoxFragment box);

	void addLineEndFloat(ManagedBoxFragment box);

	float clearedLineStartPosition(float blockStart);

	float clearedLineEndPosition(float blockStart);

	float lineStartReservedWidth(float blockStart);

	float lineEndReservedWidth(float blockStart);

	void enterContext(float lineStart, LayoutConstraint constraint);

	void exitContext();

	void reset();

	static FloatTracker create() {
		return new FloatTrackerImp();
	}

}