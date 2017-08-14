package de.thaso.orwo.fe.it.pages;

import de.thaso.orwo.fe.it.base.BasePO;
import de.thaso.orwo.fe.it.components.LabelCO;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * StandardPageObject
 *
 * @author thaler
 * @since 27.02.17
 */
public abstract class StandardPage extends BasePO {

    @FindBy(how = How.CSS, css = "*[id$='maskName']")
    private LabelCO maskName;

    @FindBy(how = How.CSS, css = "*[id$='version']")
    private LabelCO version;

    public abstract String getPageName();

    @Override
    public boolean isCurrentPage() {
        final WebElement element = getWebDriver().findElement(By.id("pageid"));
        return StringUtils.equals(getPageName(),element.getAttribute("value"));
    }

    public LabelCO getMaskName() {
        return maskName;
    }

    public void setMaskName(final LabelCO maskName) {
        this.maskName = maskName;
    }

    public LabelCO getVersion() {
        return version;
    }

    public void setVersion(final LabelCO version) {
        this.version = version;
    }
}
