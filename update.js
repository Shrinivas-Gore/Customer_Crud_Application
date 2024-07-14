const urlParams = new URLSearchParams(window.location.search);
const customerId = urlParams.get('customerId');

const firstNameInput = document.getElementById('firstName');
const lastNameInput = document.getElementById('lastName');
const addressInput = document.getElementById('address');
const cityInput = document.getElementById('city');
const stateInput = document.getElementById('state');
const emailInput = document.getElementById('email');
const phoneInput = document.getElementById('phone');

fetch(`http://localhost:9090/customer/byId/${customerId}`)
    .then(response => response.json())
    .then(customer => {
        firstNameInput.value = customer.firstName;
        lastNameInput.value = customer.lastName;
        addressInput.value = customer.adress;
        cityInput.value = customer.city;
        stateInput.value = customer.state;
        emailInput.value = customer.email;
        phoneInput.value = customer.phone;
    })
    .catch(error => {
        console.error(`Error fetching customer data: ${error}`);
    });

const updateForm = document.getElementById('update-form');
updateForm.addEventListener('submit', (e) => {
    e.preventDefault();

    const updatedCustomer = {
        firstName: firstNameInput.value,
        lastName: lastNameInput.value,
        adress: addressInput.value,
        city: cityInput.value,
        state: stateInput.value,
        email: emailInput.value,
        phone: phoneInput.value,
        customerId: customerId,
    };

    fetch(`http://localhost:9090/customer/update`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(updatedCustomer),
    })
        .then(response => response.text())
        .then(data => {
            console.log(data);
            alert(`Customer updated successfully: ${data}`);
            // Navigate back to the customer list page
            window.location.href = 'Customer.html';
            // window.location.replace('Customer.html');
        })
        .catch(error => {
            console.error(`Error updating customer: ${error}`);
        });
});