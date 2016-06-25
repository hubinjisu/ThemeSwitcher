package com.android.hubin.themeswitcher;

import android.app.Application;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ApplicationTestCase;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
@RunWith(AndroidJUnit4.class)
public class ApplicationTest
{
    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(
            ThemeActivity.class);
    private ThemeActivity mActivity;
    private TextView mContentView;

    @Before
    public void setUp() throws Exception {
        // @Before注解表示在执行所有的testCase之前要做的事情

        // getActivity()方法会在开始所有的testCase之前启动相应的Activity
//            mActivity = getActivity();
        // findViewById()很熟悉吧？找到我要测试的控件
//            mContentView = (TextView) mActivity.findViewById(R.id.hello);
    }

    @Test
    public void testPreconditions() {
        // @Test注解表示一个测试用例方法
        onView(withText("Switch Theme")).check(matches(isDisplayed()));
    }
    @Test
    public void testTheme() {
        // @Test注解表示一个测试用例方法
        onView(withId(R.id.btn_switch)).perform(click()).check(matches(isDisplayed()));
    }
}