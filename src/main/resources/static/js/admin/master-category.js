const apiCategory = '/admin/category';

$(() => {
    let categoryTbl;

    function initializeTable() { categoryTbl = $("#categoryTable").DataTable({ columnDefs: [{ targets: [0, 2, 3], className: "text-center" }] }); }

    function fetchAllCategories() {
        axiosInstance.get(`${apiCategory}/all`)
            .then(response => {
                const rows = response.data.data.map((category, index) => [
                    index + 1,
                    category.name,
                    `<span class="badge bg-${category.status === 'ACTIVE' ? 'success' : 'danger'} rounded-pill">${category.status}</span>`,
                    `<button class="btn btn-outline-dark btn-sm editBtn" data-id="${category.id}"><i class="fa-regular fa-pen-to-square"></i> Edit</button>`
                ]);
                categoryTbl.clear().rows.add(rows).draw();
            })
            .catch(() => mySwal.fire({
                icon: "error",
                title: "Error",
                text: "Failed to load categories. Please try again."
            }));
    }

    $("#saveCategoryBtn").on('click', () => {
        const formDataJson = formDataToJson(new FormData($("#categoryForm")[0]));

        const isEditMode = !!formDataJson.id;
        console.log(formDataJson);
        console.log(isEditMode);


        axiosInstance.put(`${apiCategory}/add`, formDataJson)
            .then(response => {
                mySwal.fire({
                    icon: "success",
                    title: "Success",
                    text: response.data.msg,
                    showConfirmButton: false,
                    timer: 1500
                });

                $('#categoryModal').modal('hide');

                const category = response.data.data;
                const rowData = [
                    isEditMode ? formDataJson.id : categoryTbl.rows().count() + 1,
                    category.name,
                    `<span class="badge bg-${category.status === 'ACTIVE' ? 'success' : 'danger'} rounded-pill">${category.status}</span>`,
                    `<button class="btn btn-outline-dark btn-sm editBtn" data-id="${category.id}"><i class="fa-regular fa-pen-to-square"></i> Edit</button>`
                ];

                const rowIndex = isEditMode
                    ? categoryTbl.rows().indexes().toArray().find(index => String(categoryTbl.row(index).data()[0]) === String(formDataJson.id))
                    : null;

                rowIndex !== undefined && rowIndex !== null
                    ? categoryTbl.row(rowIndex).data(rowData).draw(false)
                    : categoryTbl.row.add(rowData).draw(false);

            }).catch(error => {
                const errorDetails = handleError(error)
                if (errorDetails) {
                    displayFormErrors(errorDetails, "categoryForm");
                }
            });
    });

    $(document).on('click', '.editBtn', function () {
        const id = $(this).data('id');

        axiosInstance.get(`${apiCategory}/${id}`)
            .then(response => {
                const category = response.data.data;
                const categoryForm = $("#categoryForm")[0];

                Object.keys(category).forEach((key) => {
                    const element = categoryForm.elements[key];
                    if (element) {
                        element.value = category[key];
                    }
                });
                $("#categoryModal").modal("show");
            }).catch();
    })

    $("#categoryModal").on("hidden.bs.modal", function () {
        $("#categoryForm")[0].reset();
        $(".form-error").html("");
        $(".is-invalid").removeClass("is-invalid")
    })

    initializeTable();
    fetchAllCategories();
});
