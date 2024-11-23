document.addEventListener("DOMContentLoaded", function () {
   
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

    
    const partSelect = document.getElementById("partSelect");
    const quantityWrapper = document.getElementById("quantityWrapper");
    const partQuantityInput = document.getElementById("partQuantity");

    
    partSelect.addEventListener("change", function () {
        
        if (partSelect.value === "") {
           
            quantityWrapper.style.display = "none";
            partQuantityInput.value = ""; 
        } else {
            
            quantityWrapper.style.display = "block";
        }
    }); 
});


const tableSelect = document.getElementById("tableSelect2");
const dependentDropdownContainer = document.getElementById("dependentDropdownContainer2");
const dependentSelect = document.getElementById("recordSelect2");
const deleteButton = document.getElementById("deleteButton");

tableSelect.addEventListener("change", async function () {
    const selectedTable = tableSelect.value;

    if (selectedTable) {
        try {
            
            const response = await fetch(`/api/get-records?tableName=${selectedTable}`);
            const records = await response.json();

            
            dependentSelect.innerHTML = "";

           
            records.forEach(record => {
                const option = document.createElement("option");
                option.textContent = record.details; 
                option.value = record.id; 
                dependentSelect.appendChild(option);
            });

           
            dependentDropdownContainer.style.display = "block";
        } catch (error) {
            console.error("Error fetching records:", error);
        }
    } else {
        
        dependentDropdownContainer.style.display = "none";
    }
});


deleteButton.addEventListener("click", async function (event) {
    event.preventDefault();  

    const selectedTable = tableSelect.value;
    const selectedRecordId = dependentSelect.value;

    if (selectedTable && selectedRecordId) {
        try {
           
            const response = await fetch(`/api/delete-record?tableName=${selectedTable}&id=${selectedRecordId}`, {
                method: "DELETE"
            });

            if (response.ok) {
                alert("Record deleted successfully.");

               
                dependentSelect.remove(dependentSelect.selectedIndex);

                
                if (dependentSelect.options.length === 0) {
                    dependentDropdownContainer.style.display = "none";
                }
            } else {
                const errorMessage = await response.text();
                alert(`Failed to delete record: ${errorMessage}`);
            }
        } catch (error) {
            console.error("Error deleting record:", error);
            alert("An error occurred while deleting the record.");
            
        }
    } else {
        alert("Please select a valid table and record to delete.");
    }
});



const tableSelectUpdate = document.getElementById("tableSelectUpdate");
const dependentDropdownContainerUpdate = document.getElementById("dependentDropdownContainerUpdate");
const dependentSelectUpdate = document.getElementById("recordSelectUpdate");

tableSelectUpdate.addEventListener("change", async function () {
    const selectedTable = tableSelectUpdate.value;

    if (selectedTable) {
        try {
            const response = await fetch(`/api/get-records?tableName=${selectedTable}`);
            const records = await response.json();

            dependentSelectUpdate.innerHTML = "";

            records.forEach(record => {
                const option = document.createElement("option");
                option.textContent = record.details;
                option.value = record.id;
                dependentSelectUpdate.appendChild(option);
            });

            dependentDropdownContainerUpdate.style.display = "block";
        } catch (error) {
            console.error("Error fetching records:", error);
        }
    } else {
        dependentDropdownContainerUpdate.style.display = "none";
    }
});


flatpickr("#yearField", {
    dateFormat: "Y-m-d", // Allows users to select full date
    onChange: function(selectedDates) {
        if (selectedDates.length > 0) {
            const selectedYear = selectedDates[0].getFullYear(); // Extract the year
            document.getElementById("yearField").value = selectedYear; // Update the input value to the year
        }
    }
});

// Ensure the form always sends the year
document.querySelector("form").addEventListener("submit", function(event) {
    const yearField = document.getElementById("yearField");
    const selectedDate = new Date(yearField.value);

    // If the value is not already just a year, extract and set it
    if (!/^\d{4}$/.test(yearField.value) && !isNaN(selectedDate)) {
        yearField.value = selectedDate.getFullYear();
    }
});