package com.excercise.qa.testcases;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.excercise.qa.base.TestBase;
import com.excercise.qa.pages.FlashPage;

public class FlashPageTest extends TestBase {
	public static Logger logger = Logger.getLogger(FlashPageTest.class);
	public static String AnswerTest;
	public static FlashPage ex;

	FlashPageTest() {
		super();
	}

	@BeforeClass
	public void setUp() {
		initialization();
	}

	/*
	 * Validating the correctness of an answer by filling valid data
	 */
	@Test(dataProvider = "FlashexcerciseWithValidData")
	public void ValidateFlashexcerciseWithValidData(String colorButton, String AnswerText, String RadioName,
			String Checkbox) throws InterruptedException {
		logger.info("Executing validation of flash excersice page with valid data");
		ex = new FlashPage();
		AnswerTest = ex.gettingAnswerText();
		logger.info("Answer is" + AnswerTest);
		ex.AddingFlashExcerciseDetails(colorButton, AnswerTest, RadioName, Checkbox);
		String result = ex.getCorrectnessText();
		/* Assertion to check the correctness of the data filled */
		Assert.assertEquals(result, "Correct");
		logger.info("Validating the correctness of an answer by filling  valid data" + result);

	}

	/*
	 * Validating the correctness of an answer by filling different set of
	 * invalid data
	 */
	@Test(dataProvider = "FlashexcerciseWithInValidData", dependsOnMethods = "ValidateFlashexcerciseWithValidData")
	public void ValidateFlashexcerciseWithInValidData(String colorButton, String AnswerText, String RadioName,
			String Checkbox, String msgtext) throws InterruptedException {
		logger.info("Executing validation of flash excersice page with invalid data");
		ex.AddingFlashExcerciseDetails(colorButton, AnswerText, RadioName, Checkbox);
		String result = ex.getCorrectnessText();
		String messageText = ex.getMessageTextArea();
		/* Assertion to check the correctness of the data filled */
		Assert.assertEquals(result, "Incorrect");
		Assert.assertEquals(messageText, msgtext);
		logger.info("Validating the correctness of an answer by filling different set of invalid data" + result);
	}

	@AfterClass
	public void teardown() {
		driver.quit();
	}

	@DataProvider(name = "FlashexcerciseWithInValidData")
	private static Object[][] FlashexcerciseWithInValidData() {
		return new Object[][] { { "Purple", "Hello", "Correct", "Correct", "" },
				{ "Purple", "", "Wrong", "Correct", "required text empty" },
				{ "Pink", AnswerTest, "Wrong", "Correct", "" }, { "Pink", AnswerTest, "Correct", "Wrong", "" },
				{ "Purple", AnswerTest, "Correct", "Correct", "" }, { "Purple", AnswerTest, "Correct", "Wrong", "" },
				{ "Purple", AnswerTest, "Wrong", "Correct", "" }, { "", AnswerTest, "Correct", "Correct", "" },
				{ "", "", "", "", "required text empty" },

		};
	}

	@DataProvider(name = "FlashexcerciseWithValidData")
	private static Object[][] FlashexcerciseWithValidData() {
		return new Object[][] { { "Pink", AnswerTest, "Correct", "Correct" },

		};
	}

}
