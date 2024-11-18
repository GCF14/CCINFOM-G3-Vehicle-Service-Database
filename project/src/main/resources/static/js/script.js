document.addEventListener("DOMContentLoaded", function () {
    // Populate Mechanic Dropdown
    fetch("/api/mechanics")
        .then((response) => {
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            return response.json();
        })
        .then((mechanics) => {
            const mechanicSelect = document.getElementById("mechanicSelect");
            mechanics.forEach((mechanic) => {
                const option = document.createElement("option");
                option.value = mechanic.mechanicId;
                option.textContent = `${mechanic.firstName} ${mechanic.lastName}`;
                mechanicSelect.appendChild(option);
            });
        })
        .catch((error) => {
            console.error("Error fetching mechanics:", error);
        });

    // Populate Service Dropdown
    fetch('/api/services')
        .then(response => response.json())
        .then(data => {
            const serviceSelect = document.getElementById('serviceSelect');
            data.forEach(service => {
                const option = document.createElement('option');
                option.value = service.serviceId;
                option.textContent = service.serviceType;
                serviceSelect.appendChild(option);
            });
        })
        .catch(error => {
            console.error('Error fetching services:', error);
        });

    // Populate Parts Dropdown
    fetch('/api/stocks')
        .then(response => response.json())
        .then(data => {
            const partSelect = document.getElementById('partSelect');
            data.forEach(stock => {
                const option = document.createElement('option');
                option.value = stock.stockId;
                option.textContent = `${stock.name} - ₱${stock.price}`;
                partSelect.appendChild(option);
            });
        })
        .catch(error => {
            console.error('Error fetching parts:', error);
        });

    // Show Quantity Field if Part is Selected
    const partSelect = document.getElementById("partSelect");
    const quantityWrapper = document.getElementById("quantityWrapper");
    const partQuantityInput = document.getElementById("partQuantity");

    // Add an event listener to detect changes in the dropdown
    partSelect.addEventListener("change", function () {
        // Check if the selected option is "None"
        if (partSelect.value === "") {
            // Hide the quantity input if "None" is selected
            quantityWrapper.style.display = "none";
            partQuantityInput.value = ""; // Clear the part quantity input field
        } else {
            // Show the quantity input if any other option is selected
            quantityWrapper.style.display = "block";
        }
    }); // <-- Missing closing brace here
});
