// const BASE_URL = "http://localhost:7071"; // Change if needed
const BASE_URL = "http://172.20.0.3:7071"; // Change if needed

// Function to Add Admin
function addAdmin() {
    const adminData = {
        name: document.getElementById("adminName").value,
        phoneNo: document.getElementById("adminPhone").value,
        status: true
    };

    fetch(`${BASE_URL}/user/add/Admin`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(adminData)
    })
        .then(response => response.json())
        .then(data => alert(`Admin Added: ${data.name}`))
        .catch(error => console.error("Error:", error));
}

// Function to Add Customer
function addCustomer() {
    const customerData = {
        name: document.getElementById("customerName").value,
        phoneNo: document.getElementById("customerPhone").value,
        status: true
    };

    fetch(`${BASE_URL}/user/add/Customer`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(customerData)
    })
        .then(response => response.json())
        .then(data => alert(`Customer Added: ${data.name}`))
        .catch(error => console.error("Error:", error));
}

// Function to Add Waiter
function addWaiter() {
    const adminId = document.getElementById("adminIdForWaiter").value;
    const waiterData = {
        name: document.getElementById("waiterName").value,
        phoneNo: document.getElementById("waiterPhone").value,
        status: true
    };

    fetch(`${BASE_URL}/user/add/Waiter/{id}`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(waiterData)
    })
        .then(response => response.json())
        .then(data => alert(`Waiter Added: ${data.name}`))
        .catch(error => console.error("Error:", error));
}

// Function to Add Table
function addTable() {
    const tableData = {
        admin: { id: document.getElementById("adminIdForTable").value },
        price: parseFloat(document.getElementById("tablePrice").value)
    };

    fetch(`${BASE_URL}/tables/add`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(tableData)
    })
        .then(response => response.json())
        .then(data => alert(`Table Added: ${data.id}`))
        .catch(error => console.error("Error:", error));
}

// Function to Book Table
function bookTable() {
    const bookingData = {
        customer: { id: document.getElementById("customerIdForBooking").value },
        table_id: { id: document.getElementById("tableIdForBooking").value },
        booking_status: true
    };

    fetch(`${BASE_URL}/book/table`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(bookingData)
    })
        .then(response => response.json())
        .then(data => alert(`Table Booked: ${data.id}`))
        .catch(error => console.error("Error:", error));
}

// Function to Assign Waiter
function assignWaiter() {
    const assignData = {
        id: document.getElementById("bookingId").value,
        waiter: { id: document.getElementById("waiterId").value }
    };

    fetch(`${BASE_URL}/book/assignWaiter`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(assignData)
    })
        .then(response => response.json())
        .then(data => alert(`Waiter Assigned to Booking: ${data.id}`))
        .catch(error => console.error("Error:", error));
}
