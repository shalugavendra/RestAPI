package api.tests;

import java.io.FileNotFoundException;
import java.io.IOException;

import jakarta.mail.util.SharedFileInputStream;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import api.methods.ApiMethods;
import api.methods.EmailSender;
import io.restassured.response.Response;
import java.io.*;

public class TestCases {


    @Test(priority=1)
    public void testGetUser() throws IOException {
        Object logger;
        Response response = ApiMethods.getuser();
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(),200);

    }

    @AfterSuite
    public void sendEmailAfterExecution() {
        EmailSender sender = new EmailSender();
        String to = "thegavendrathakur@gmail.com";
        String from = "shalugavendra@gmail.com";
        String subject = "Sending Report after Execution";
        String text = "This is a example.";
        File file = new File("RestAPI\\Test-Report.html");

        boolean b = sender.sendEmail(to, from, subject, text, file);
        if (b) {
            System.out.println("Email sent");
        }
        else {
            System.out.println("There is some problem");
        }
    }
}

