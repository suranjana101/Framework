package com.excercise.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.excercise.qa.base.TestBase;

public class FlashPage extends TestBase {
	// Repository
	@FindBy(xpath = "//exercise-label[3]//div[1]")
	private WebElement AnswerText;

	@FindBy(xpath = "//exercise-text-box/input")
	private WebElement TextField;

	@FindBy(xpath = "//exercise-renderer/exercise-image[2]")
	private WebElement PinkButton;

	@FindBy(xpath = "//exercise-renderer/exercise-image[1]")
	private WebElement PurpleButton;

	@FindBy(xpath = "//button[@id='check']")
	private WebElement CheckButton;

	@FindBy(xpath = "//i[contains(text(),'replay')]")
	private WebElement ResetButton;

	@FindBy(xpath = "//span[@id='exercise_status']")
	private WebElement ResultTextArea;

	public java.util.List<WebElement> Checkbox(String name) {
		return driver.findElements(By.xpath("//exercise-multiple-choice[2]//label[contains(text(),'" + name + "')]"));
	}

	public WebElement RadioButton(String name) {
		return driver.findElement(By.xpath("//exercise-multiple-choice[1]//label[contains(text(),'" + name + "')]"));
	}

	public FlashPage() {
		PageFactory.initElements(driver, this);
	}

	// Actions
	public String getAnswerText() {
		return AnswerText.getText();
	}

	public String getCorrectnessText() {
		return ResultTextArea.getText();
	}

	/**
	 * This method will extract the text from flash excercise page
	 * 
	 * @return
	 */
	public String gettingAnswerText() {
		String textname = getAnswerText();
		String[] output = textname.split("=");
		String AnswerTest = output[1];
		return AnswerTest;
	}

	/**
	 * This method will fill deatils in flash excercise page
	 * 
	 * @param colorbtn
	 * @param AnswerText
	 * @param radiobtnName
	 * @param CheckboxName
	 */
	public void AddingFlashExcerciseDetails(String colorbtn, String AnswerText, String radiobtnName,
			String CheckboxName) {
		ResetButton.click();
		if (colorbtn.equalsIgnoreCase("Pink"))
			PinkButton.click();
		else if (colorbtn.equalsIgnoreCase("Purple")) {
			PurpleButton.click();
		}
		TextField.sendKeys(AnswerText);
		RadioButton(radiobtnName).click();
		for (int i = 0; i <= Checkbox(CheckboxName).size() - 1; i++) {
			Checkbox(CheckboxName).get(i).click();
		}
		CheckButton.click();

	}

}
