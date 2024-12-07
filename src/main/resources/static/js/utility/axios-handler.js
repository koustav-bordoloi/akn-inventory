// Centralized Axios instance with defaults
const axiosInstance = axios.create({
    baseURL: contextPath
});


// Show loader before the request is sent
axiosInstance.interceptors.request.use((config) => {
    showLoader();
    return config;
}, (error) => {
    return Promise.reject(error);
});

// Hide loader when the response is received 
axiosInstance.interceptors.response.use((response) => {
    hideLoader(); 
    return response;
    
}, (error) => {
    return Promise.reject(error);
});

// Function to convert FormData to JSON with trimming for non-file inputs
function formDataToJson(formData) {
    const formDataJson = {};
    formData.forEach((value, key) => {
        // Exclude trimming for file inputs
        formDataJson[key] = value instanceof File ? value : value.trim();
    });
    return formDataJson;
}