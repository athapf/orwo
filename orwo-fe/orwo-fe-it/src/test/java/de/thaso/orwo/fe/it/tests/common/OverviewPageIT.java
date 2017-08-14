package de.thaso.orwo.fe.it.tests.common;

import de.thaso.orwo.fe.it.base.SeleniumTestBase;
import de.thaso.orwo.fe.it.pages.OverviewPage;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * OverviewPageTest
 *
 * @author thaler
 * @since 26.09.16
 */
public class OverviewPageIT extends SeleniumTestBase {

    @Test
    public void testOverviewPageWith3Notes() {
        updateDatabase();
        final OverviewPage overviewPage = startBrowser("page/overview.xhtml", OverviewPage.class);
        assertThat(overviewPage, is(notNullValue()));
        assertThat(overviewPage.getMaskName().getText(), is("Overview"));
        assertThat(overviewPage.getVersion().getText(), is("V 0.0.1"));
    }
}
