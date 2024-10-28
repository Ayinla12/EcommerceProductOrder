# E-commerce Automation Test Cases

This document outlines the test cases for the E-commerce Automation Testing project on [Automation Exercise](http://automationexercise.com).

---

## Test Case List

| **Test Case ID** | **Test Description**                     | **Expected Result**                           |
|------------------|-----------------------------------------|-----------------------------------------------|
| TC_01            | Verify Home Page Load                   | Home page should load with correct title.     |
| TC_02            | Add Product to Cart                     | Product should be visible in the cart.        |
| TC_03            | Register and Create Account             | Account should be created successfully.       |
| TC_04            | Complete Order and Checkout             | Order confirmation message should appear.     |
| TC_05            | Delete Account After Order Placement    | Account deletion success message should show. |

---

## Detailed Test Cases

---

### Test Case ID: TC_01
**Title**: Verify Home Page Load

- **Description**: Verify that the home page loads correctly and displays the expected title.
- **Preconditions**: User is not logged in.
- **Steps**:
  1. Launch the browser.
  2. Navigate to [Automation Exercise](http://automationexercise.com).
  3. Observe the page title.
- **Expected Result**: The home page should be displayed with the title "Automation Exercise".

---

### Test Case ID: TC_02
**Title**: Add Product to Cart

- **Description**: Adds the first product on the homepage to the cart and verifies the product is added.
- **Preconditions**: Home page is loaded successfully.
- **Steps**:
  1. Add the first product to the cart by clicking the "Add to Cart" button.
  2. Click on the 'Cart' button.
  3. Observe the cart page to confirm the added item.
- **Expected Result**: The product should appear in the cart, and the cart page should be displayed.

---

### Test Case ID: TC_03
**Title**: Register and Create Account

- **Description**: Register a new user account and verify the successful creation.
- **Preconditions**: User is on the checkout page with items in the cart.
- **Steps**:
  1. Click on 'Register/Login'.
  2. Enter the registration details and submit.
  3. Confirm the "Account Created!" message.
- **Expected Result**: A confirmation message "Congratulations! Your new account has been successfully created!" should appear.

---

### Test Case ID: TC_04
**Title**: Complete Order and Checkout

- **Description**: Complete the checkout process with payment details and verify order confirmation.
- **Preconditions**: User is logged in and has items in the cart.
- **Steps**:
  1. Click on the 'Cart' button.
  2. Click 'Proceed To Checkout'.
  3. Enter order comments.
  4. Fill in payment details.
  5. Click 'Place Order'.
  6. Verify order confirmation message.
- **Expected Result**: The message "Your order has been placed successfully!" should be displayed.

---

### Test Case ID: TC_05
**Title**: Delete Account After Order Placement

- **Description**: Delete the user account after placing an order and verify the deletion message.
- **Preconditions**: Order is placed successfully, and the user is logged in.
- **Steps**:
  1. Navigate to account settings.
  2. Click on 'Delete Account'.
  3. Confirm deletion by clicking 'Continue'.
- **Expected Result**: A success message "ACCOUNT DELETED!" should be displayed.
