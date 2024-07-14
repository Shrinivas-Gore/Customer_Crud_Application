
document.getElementById('create-customer-button').addEventListener('click', () => {
    window.location.href = 'newCustomer.html';
});

// Fetch data from API
let totalPages = 0; // Store the total number of pages

// Fetch data from API
fetch("http://localhost:9090/customer/findByPage?page=0")
    .then(response => response.json())
    .then(data => {
        totalPages = Math.ceil(data.length / 10 + 1); // Calculate the total number of pages
        displayCustomers(data, 1);
        updatePagination(1, totalPages);
    })
    .catch(error => {
        console.error("Error fetching data:", error);
    });

// Function to display customer data in the table
function displayCustomers(data, page) {
    if (!data || data.length === 0) {
        alert("No customers found");
        totalPages = totalPages - 1;
        return;
    }
    const customerList = document.getElementById('customer-list');
    customerList.innerHTML = '';
    const start = 0;
    const end = data.length;
    data.slice(start, end).forEach(customer => {
        const row = customerList.insertRow();
        const firstNameCell = row.insertCell();
        const lastNameCell = row.insertCell();
        const addressCell = row.insertCell();
        const cityCell = row.insertCell();
        const stateCell = row.insertCell();
        const emailCell = row.insertCell();
        const phoneCell = row.insertCell();
        const actionCell = row.insertCell();

        firstNameCell.textContent = customer.firstName;
        lastNameCell.textContent = customer.lastName;
        addressCell.textContent = customer.adress;
        cityCell.textContent = customer.city;
        stateCell.textContent = customer.state;
        emailCell.textContent = customer.email;
        phoneCell.textContent = customer.phone;

        const deleteButton = document.createElement('button');
        deleteButton.textContent = 'Delete';
        deleteButton.addEventListener('click', () => {
            const customerId = customer.customerId; // assume customer.id is the ID of the customer to be deleted
            console.log(customerId);
            const url = `http://localhost:9090/customer/delete/${customerId}`;
            fetch(url, {
                method: 'DELETE',
            })
                .then(response => response.text())
                .then(data => {
                    console.log(`${data} Customer deleted successfully: ${customerId}`);
                    // Remove the row from the table
                    row.remove();
                })
                .catch(error => {
                    console.error(`Error deleting customer: ${error}`);
                });
            console.log(`Deleting customer: ${customer.firstName} ${customer.lastName}`);
            // Remove row from table
            row.remove();
        });

        const editButton = document.createElement('button');
        editButton.textContent = 'Edit';
        editButton.addEventListener('click', () => {
            // Handle edit action here (e.g., open a modal for editing)
            console.log(`Editing customer: ${customer.firstName} ${customer.lastName}`);
            window.location.href = `update.html?customerId=${customer.customerId}`;
        });

        actionCell.appendChild(deleteButton);
        actionCell.appendChild(editButton);
    });
}

// Search functionality
const searchFieldSelect = document.getElementById('search-field');
const searchInput = document.getElementById('search-input');
const searchButton = document.getElementById('search-button');

searchButton.addEventListener('click', () => {
    const searchField = searchFieldSelect.value;
    const searchTerm = searchInput.value.toLowerCase();

    const url = `http://localhost:9090/customer/search?searchField=${searchField}&searchTerm=${searchTerm}`;

    fetch(url)
        .then(response => response.json())
        .then(data => {
            const page = 1;
            displayCustomers(data, page);
            updatePagination(page, Math.ceil(data.length / 10));
        })
        .catch(error => {
            console.error("Error searching customers:", error);
        });
});
// Create new customer functionality
const createCustomerButton = document.getElementById('create-customer-button');
createCustomerButton.addEventListener('click', () => {
    // Handle create new customer action here (e.g., open a form for creating a new customer)
    console.log('Creating new customer...');
    // You can navigate to another page for creating a new customer, or implement a modal
});

// Pagination functionality
const prevPageButton = document.getElementById('prev-page-button');
const nextPageButton = document.getElementById('next-page-button');
const pageNumberSpan = document.getElementById('page-number');

let currentPage = 1; // Store the current page number

function updatePagination(page, totalPages) {
    pageNumberSpan.textContent = `Page ${page} of ${totalPages}`;
    // prevPageButton.disabled = page === 1;
    // nextPageButton.disabled = page === totalPages;
}

prevPageButton.addEventListener('click', () => {
    if (currentPage > 1) {
        currentPage--;
        fetch(`http://localhost:9090/customer/findByPage?page=${currentPage - 1}`)
            .then(response => response.json())
            .then(data => {
                console.log(data);
                displayCustomers(data, currentPage);
                updatePagination(currentPage, totalPages--);
            })
            .catch(error => {
                console.error("Error fetching data:", error);
            });
    }
});

nextPageButton.addEventListener('click', () => {
    if (currentPage < totalPages) {
        currentPage++;
        fetch(`http://localhost:9090/customer/findByPage?page=${currentPage - 1}`)
            .then(response => response.json())
            .then(data => {
                displayCustomers(data, currentPage);
                updatePagination(currentPage, totalPages++);
            })
            .catch(error => {
                console.error("Error fetching data:", error);
            });
    }
});