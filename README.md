# Amazon Automation

UI test automation for selected Amazon India flows, built with Java, Selenium
WebDriver, and TestNG. The project uses a keyword-driven test flow backed by
Excel controller and test-data files.

## Project layout

```text
Amazon/
├── pom.xml
├── drivers/                         # ChromeDriver binaries
├── src/test/java/
│   ├── config/                      # Runtime properties and Excel data
│   ├── datatable/                  # Excel reader
│   ├── testcases/                  # Amazon business scenarios
│   └── testscripts/                # Driver, keywords, and shared library
└── test-output/                    # Generated TestNG reports
```

## Requirements

- Java 8 or later
- Apache Maven
- Google Chrome
- A ChromeDriver version compatible with the installed Chrome version

## Running the tests

1. Change into the Maven module:

   ```bash
   cd Amazon
   ```

2. Review `src/test/java/config/Amazon_config.properties` and update the
   target URL and browser settings for the local environment.

3. Run the TestNG tests:

   ```bash
   mvn test
   ```

The framework reads enabled test cases from
`src/test/java/config/amazon_controller.xls` and test data from
`src/test/java/config/amazon_testdata.xls`. Test reports are written to
`Amazon/test-output/`.

## Configuration notes

- The current driver setup contains Windows `.exe` binaries and several source
  paths use Windows separators. Update the driver path and path separators when
  running on another operating system.
- Do not commit Amazon account credentials. Use test credentials supplied
  through a secure local configuration or test environment.
- Amazon may require an interactive login, CAPTCHA, or additional verification;
  these are external prerequisites and can prevent automated runs.
