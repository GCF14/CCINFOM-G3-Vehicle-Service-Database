document.addEventListener('DOMContentLoaded', function() {
    const serviceSelect = document.getElementById('serviceSelect');

    $('#exampleModal').on('show.bs.modal', function () {
        fetch('/api/services')
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok: ' + response.statusText);
                }
                return response.json();
            })
            .then(services => {
                console.log("Fetched services:", services); // Log the fetched services
                if (!Array.isArray(services)) {
                    throw new TypeError('Expected an array but got: ' + typeof services);
                }
                
                serviceSelect.innerHTML = '<option value="">Select a service...</option>'; // Reset dropdown
                services.forEach(service => {
                    const option = document.createElement('option');
                    option.value = service.serviceId;
                    option.textContent = service.serviceType;
                    serviceSelect.appendChild(option);
                });
            })
            .catch(error => console.error('Error fetching services:', error));
    });
});