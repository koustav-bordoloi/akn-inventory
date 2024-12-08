// Centralized Axios instance with defaults
const axiosInstance = axios.create({
    baseURL: contextPath
});

// Add a custom header to the Axios instance
axiosInstance.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

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

function handleError(error) {
    console.log("ERROR : ", error.response);
    if (error.response) {
        // The request was made and the server responded with a status code
        // that falls out of the range of 2xx
        // console.log(error.response.data);
        if (error.response.data.errorType === "VALIDATION") {
            return error.response.data.detail;
        }
        toastr.error(error.response.data === "" ? "Bad request!" : error.response.data.detail);
    } else if (error.request) {
        // The request was made but no response was received
        // `error.request` is an instance of XMLHttpRequest in the browser and an instance of
        // http.ClientRequest in node.js
        // console.log(error.request);
        toastr.error("The request was made but no response was received");
    } else {
        // Something happened in setting up the request that triggered an Error
        // console.log('Error', error.message);
        toastr.error('Error: ' + error.message);
    }
    // console.log(error.config);
}

function displayFormErrors(errorDetails, form) {
    const formElement = $(`#${form}`);
    let firstErrorElement = null;

    $.each(JSON.parse(errorDetails), (key, value) => {
        formElement.find(`#${key}`).addClass("is-invalid");
        formElement.find(`#${key}Error`).html(value);
        if (!firstErrorElement) {
            firstErrorElement = formElement.find(`#${key}`).focus();
        }
    });
}