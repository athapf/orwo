package de.thaso.orwo.fe.it.components;


import de.thaso.orwo.fe.it.base.BaseCO;

/**
 * ButtonCO
 *
 * @author thaler
 * @since 27.02.17
 */
public class LinkCO extends BaseCO {

    public boolean isVisible() {
        return isPresent(getWebElement())&& getWebElement().isDisplayed();
    }

    public void click() {
        doClick(getWebElement());
    }
}
