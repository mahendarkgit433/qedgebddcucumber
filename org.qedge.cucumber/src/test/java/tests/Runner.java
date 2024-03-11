package tests;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(features = {"src/test/resources/features"},
glue = {"gluecode"},
dryRun = false,
monochrome = true,
plugin = {"pretty","html:target/supplier_reports",
        "json:target/supplier_reports.json",
        "junit:target/supplier_reports.xml",
        "rerun:target/failedsuppliertestscenarios.txt"},
tags = "@supplierTest"
)
public class Runner extends AbstractTestNGCucumberTests
{
	//Rename Runner class as AppTest to run test through maven cmd
}
