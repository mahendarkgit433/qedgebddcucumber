Feature: As an admin user validate supplier creation with multiple data

  @supplierTest
  Scenario Outline: As Admin user add supplier
    Given Launch Browser
    When Launch Url "http://webapp.qedgetech.com/"
    Then wait for username with "xpath" as "//input[@id='username']"
    When enter username with "xpath" as "//input[@id='username']" and testdata as "admin"
    When enter password with "id" as "password" and testdata as "master"
    Then click login button with "xpath" as "//button[text()='Login']"
    When wait for logout link with "xpath" as "//li[@id='mi_logout']/a"
    When wait for supplier link with "xpath" as "//li[@id='mi_a_suppliers']/a"
    Then click supplier link with "xpath" as "//li[@id='mi_a_suppliers']/a"
    When wait for Add + icon with "xpath" as "(//span[@data-caption='Add'])[1]/.."
    Then click on Add + icon with "xpath" as "(//span[@data-caption='Add'])[1]/.."
    When wait for capture supplier number with "id" as "x_Supplier_Number"
    Then capture supplier number with "id" as "x_Supplier_Number"
    When Enter in "<SupplierName>" with "id" as "x_Supplier_Name"
    When Enter in "<Address>" with "id" as "x_Address"
    When Enter in "<City>" with "id" as "x_City"
    When Enter in "<Country>" with "id" as "x_Country"
    When Enter in "<ContactPerson>" with "id" as "x_Contact_Person"
    When Enter in "<PhoneNumber>" with "id" as "x_Phone_Number"
    When Enter in "<Email>" with "id" as "x__Email"
    When Enter in "<MobileNumber>" with "id" as "x_Mobile_Number"
    When Enter in "<Note>" with "id" as "x_Notes"
    Then click on Add button with "xpath" as "//button[@id='btnAction']"
    When Wait for confirm ok button with "xpath" as "//button[.='OK!']"
    Then click on confirm ok button with "xpath" as "//button[.='OK!']"
    When wait for alert with "xpath" as "(//button[text()='OK'])[6]"
    Then click on alert with "xpath" as "(//button[text()='OK'])[6]"
    Then user validate the supplier table
    Then user closes the browser

    Examples: 
      | SupplierName | Address       | City        | Country | ContactPerson | PhoneNumber  | Email                | MobileNumber | Note     |
      | Mahendar     | Balapur       | Hyderabad   | India   | Radhika       |   8734567890 | Lp6K@example.com     |   3456544443 | Hi All   |
      | Ravindar     | Allire        | Mysore      | India   | Pavithra      |   9871940281 | Q123@example.com     |   2901739270 | Hi Any   |
      | John         | 123 Main St   | Anytown     | USA     | Sarah         | 555-123-4567 | john@example.com     | 555-987-6543 | Hello    |
      | Emily        | 456 Elm St    | Springfield | USA     | Michael       | 555-555-5555 | emily@example.com    | 555-123-4567 | Bye      |
      | David        | 789 Oak St    | Oakville    | Canada  | Jennifer      | 555-999-8888 | david@example.com    | 555-222-3333 | Hey      |
      | Samantha     | 321 Pine St   | Pineville   | USA     | Alex          | 555-111-2222 | samantha@example.com | 555-333-4444 | Nice     |
      | Robert       | 654 Maple St  | Mapleton    | Canada  | Michelle      | 555-777-8888 | robert@example.com   | 555-666-9999 | Buddy    |
      | Sarah        | 987 Cedar St  | Cedarville  | USA     | Kevin         | 555-222-3333 | sarah@example.com    | 555-444-5555 | Good     |
      | Michael      | 741 Birch St  | Birchwood   | Canada  | Amanda        | 555-444-5555 | michael@example.com  | 555-888-7777 | Done     |
      | Jennifer     | 852 Spruce St | Sprucetown  | USA     | Matthew       | 555-666-7777 | jennifer@example.com | 555-555-5555 | Hey Man  |
      | Alex         | 963 Oak St    | Oakdale     | Canada  | Stephanie     | 555-888-9999 | alex@example.com     | 555-999-8888 | Iam good |
      | Michelle     | 159 Elm St    | Elmvale     | USA     | Daniel        | 555-777-6666 | michelle@example.com | 555-222-1111 | HIIII    |
