package com.example.nenraya;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    GameActivity gameActivity;

    @Before
    public void setUp() {
        gameActivity = new GameActivity();
    }

    @Test (timeout = 100)
    public void checkMethods() {
        GameActivity gameActivity = new GameActivity();

        // Check getTableRow;

        assertThat(gameActivity.getTableRow(0), is(0));
        assertThat(gameActivity.getTableRow(1), is(0));
        assertThat(gameActivity.getTableRow(2), is(0));

        assertThat(gameActivity.getTableRow(3), is(1));
        assertThat(gameActivity.getTableRow(4), is(1));
        assertThat(gameActivity.getTableRow(5), is(1));

        assertThat(gameActivity.getTableRow(6), is(2));
        assertThat(gameActivity.getTableRow(7), is(2));
        assertThat(gameActivity.getTableRow(8), is(2));

        // Check getTableCol:

        assertThat(gameActivity.getTableCol(0), is(0));
        assertThat(gameActivity.getTableCol(1), is(1));
        assertThat(gameActivity.getTableCol(2), is(2));

        assertThat(gameActivity.getTableCol(3), is(0));
        assertThat(gameActivity.getTableCol(4), is(1));
        assertThat(gameActivity.getTableCol(5), is(2));

        assertThat(gameActivity.getTableCol(6), is(0));
        assertThat(gameActivity.getTableCol(7), is(1));
        assertThat(gameActivity.getTableCol(8), is(2));
    }
}