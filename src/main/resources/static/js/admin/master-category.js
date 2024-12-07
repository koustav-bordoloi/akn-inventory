const apiAdmin = '/admin';
$(() => {
    $("#categoryTable").DataTable();
})

$("#saveCategoryBtn").on('click', () => {
    const formDataJson = formDataToJson(new FormData($("#categoryForm")[0]));

    axiosInstance.put(`${apiAdmin}/category/add`, formDataJson)
        .then(response => {
            // swalToast.fire({ icon: 'success', title: 'Operation Successful' });
            mySwal.fire({
                icon: "success",
                title: "Success",
                text: response.data,
                showConfirmButton: false,
                timer: 1500
            });
        }).catch(error => {
            console.error('Error', error);
        });

})
