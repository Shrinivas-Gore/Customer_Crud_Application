document.getElementById('register-form').addEventListener('submit', function(event) {
    event.preventDefault();

    const customer = {
        firstName: document.getElementById('firstName').value,
        lastName: document.getElementById('lastName').value,
        address: document.getElementById('address').value,
        city: document.getElementById('city').value,
        state: document.getElementById('state').value,
        email: document.getElementById('email').value,
        phone: document.getElementById('phone').value
    };

    fetch('http://localhost:9090/customer/add', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(customer)
    })
    .then(response => response.text())
    .then(data => {
        alert('Customer registered successfully');
        window.location.href = 'Customer.html';
    })
    .catch(error => {
        alert('Registration failed');
        console.error('There was a problem with the fetch operation:', error);
    });
});