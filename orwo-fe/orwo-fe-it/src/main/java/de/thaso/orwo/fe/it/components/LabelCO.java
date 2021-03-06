package de.thaso.orwo.fe.it.components;

import de.thaso.orwo.fe.it.base.BaseCO;

/**
 * LabelCO
 *
 * @author thaler
 * @since 03.03.17
 */
public class LabelCO extends BaseCO {

    public boolean isVisible() {
        return isPresent(getWebElement())&& getWebElement().isDisplayed();
    }

    public String getText() {
        waitForElement();
        return getWebElement().getText();
    }
}
