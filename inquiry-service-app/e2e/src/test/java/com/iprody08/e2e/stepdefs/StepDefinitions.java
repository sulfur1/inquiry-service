package com.iprody08.e2e.stepdefs;

import io.cucumber.java.en.Given;

public class StepDefinitions {
    @Given("cucumber test")
    public void cucumber_test() {
        System.out.println("Cucumber works!");
    }
}
