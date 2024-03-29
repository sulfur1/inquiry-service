package com.iprody08.e2e;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@org.springframework.context.annotation.Configuration
public class CucumberReportRunner extends BlockJUnit4ClassRunner {
    @Value("S{spring.application.name}")
    private String projectName;
    @Value("${build.version}")
    private String buildNumber;
    @Value("${application.branch}")
    private String branchName;


    public CucumberReportRunner(Class<?> clazz) throws InitializationError {
        super(clazz);
    }

    @Override
    public void run(RunNotifier notifier) {
        super.run(notifier);
        generateReport();
    }

    public void generateReport() {
        File reportOutputDirectory = new File("target/report/cucumber/");

        List<String> jsonFiles = new ArrayList<>();
        jsonFiles.add("target/report/cucumber/cucumber-report.json");

        Configuration configuration = new Configuration(reportOutputDirectory, getProjectName());
        configuration.setBuildNumber(getBuildNumber());
        configuration.addClassifications("Build Number", configuration.getBuildNumber());
        configuration.addClassifications("Branch Name", getBranchName());

        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        reportBuilder.generateReports();
    }
    public String getBuildNumber() {
        return buildNumber;
    }

    private String getProjectName() {
        return projectName;
    }

    private String getBranchName() {
        return branchName;
    }
}
