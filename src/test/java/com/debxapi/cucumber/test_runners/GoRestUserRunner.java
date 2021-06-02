package com.debxapi.cucumber.test_runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {
                "src/test/resources/features/GoRestUser.feature"
        },
        glue = {
                "com.debxapi.cucumber.steps.go_rest"
        },
        tags = {
                "@DeleteUser"
        },
        dryRun = false

)
public class GoRestUserRunner {
}
