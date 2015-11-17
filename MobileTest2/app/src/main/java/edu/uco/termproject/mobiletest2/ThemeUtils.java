package edu.uco.termproject.mobiletest2;

import android.app.Activity;
import android.content.Intent;

public class ThemeUtils {
    private static int cTheme;

    public final static int ORIGIN = 0;
    public final static int BLUE = 1;
    public final static int YELLOW = 2;

    public static void changeToTheme(Activity activity, int theme) {
        cTheme = theme;
        activity.finish();

        activity.startActivity(new Intent(activity, activity.getClass()));
    }

    public static void onActivityCreateSetTheme(Activity activity) {
        switch (cTheme) {
            default:
            case ORIGIN:
                activity.setTheme(R.style.BlackTheme);
                break;
            case BLUE:
                activity.setTheme(R.style.BlueTheme);
                break;
            case YELLOW:
                activity.setTheme(R.style.YellowTheme);
                break;

        }

    }
}
