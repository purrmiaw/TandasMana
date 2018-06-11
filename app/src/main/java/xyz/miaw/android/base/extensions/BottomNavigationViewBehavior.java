package xyz.miaw.android.base.extensions;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.view.View;

import java.util.List;

public class BottomNavigationViewBehavior extends CoordinatorLayout.Behavior<BottomNavigationView> {

    private int _height;

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull BottomNavigationView child, @NonNull View dependency) {

        return dependency instanceof FloatingActionButton || super.layoutDependsOn(parent, child, dependency);
    }

    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, BottomNavigationView child, int layoutDirection) {

        _height = child.getHeight();
        return super.onLayoutChild(parent, child, layoutDirection);

    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull BottomNavigationView child, @NonNull View directTargetChild, @NonNull View target, int nestedScrollAxes, int type) {
        return
                nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL
                || super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes, type);
    }

    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull BottomNavigationView child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {

        // dyconsumed: user initiates movement on the y-axis and it happens on screen
        // dyunconsumed: user initiates movement on the y-axis but it doesn't happen on screen, for example, downwards movement at the bottom end of the list.

        // dy > 0: user swiping up, so screen in scrolling down
        // dy < 0: user swiping down, so screen is scrolling up

        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type);

        if (dyUnconsumed > 0 || dyConsumed > 0){
            this.slideDown(child);
        }
        else if (dyUnconsumed < 0 || dyConsumed < 0) {
            this.slideUp(child);
        }

        List<View> dependencies = coordinatorLayout.getDependencies(child);

        for (View dependency : dependencies) {

            if (dependency instanceof FloatingActionButton) {

                FloatingActionButton floatingActionButton = (FloatingActionButton) dependency;

                if (dyUnconsumed > 0 || dyConsumed > 0)
                {
                    floatingActionButton.hide();
                }
                else if (dyUnconsumed < 0 || dyConsumed < 0)
                {
                    floatingActionButton.show();
                }
            }

        }
    }


    private void slideUp(BottomNavigationView child)
    {
        child.clearAnimation();
        child.animate().translationY(0).setDuration(200);
    }

    private void slideDown(BottomNavigationView child)
    {
        child.clearAnimation();
        child.animate().translationY(_height).setDuration(200);
    }
}
